package com.holdem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import static java.lang.System.out;

public class Table {
	private Deck d;

	private Board b;

	private int tablePot;

	private ArrayList<Player> players;

	private ArrayList<Integer> playersPot;

	private int tableNum;

	private int dButton;

	private int BB;

	Scanner sc = new Scanner(System.in);

	Table() {
		d = new Deck();
		b = new Board();
		tablePot = 0;
		players = new ArrayList<>();
		playersPot = new ArrayList<>();
		tableNum = this.hashCode();
		dButton = 0;
		BB = 0;
	}

	private void apply() {
		for (int i = 0; i < players.size(); i++) {
			for (Card c : b.board) {
				if (!players.get(i).hand.hands.contains(c)) {
					players.get(i).hand.hands.add(c);
				}
			}
		}
	}

	private void flop() {
		b.board.add(d.draw());
		b.board.add(d.draw());
		b.board.add(d.draw());
		apply();
		b.show();
	}

	private void turn() {
		b.board.add(d.draw());
		apply();
		b.show();
	}

	private void river() {
		b.board.add(d.draw());
		apply();
		b.show();
	}

	@SuppressWarnings("unchecked")
	private void showdown() {

		ArrayList<Player> kicking = new ArrayList<Player>();
		ArrayList<Player> temp = (ArrayList<Player>) players.clone();
		Player winner;

		Collections.sort(temp, Collections.reverseOrder());
		int maxRank = temp.get(0).hand.rank;

		for (Player p : players) {
			p.showBest();
			if (p.hand.rank == maxRank) {
				kicking.add(p);
			}
		}

		System.out.println();

		if (kicking.size() == 1) {
			winner = kicking.get(0);
			winner.addStack(tablePot);
			System.out.print("Winner : ");
			System.out.println(kicking.toString());
			init();
		}

		else {
			if (kicking.get(0).hand.kickers.size() == 0) {
				split(kicking);
				System.out.println("Splitted Pot");
				System.out.println(kicking.toString());
			}

			else {
				kicking = getWinners(kicking);
				if (kicking.size() == 1) {
					winner = kicking.get(0);
					winner.addStack(tablePot);
					System.out.print("Winner : ");
					System.out.println(kicking.toString());
				}

				else {
					split(kicking);
					System.out.println("Splitted Pot");
					System.out.println(kicking.toString());
				}
			}
		}
		System.out.println();
		System.out.println(temp.toString());
	}

	@SuppressWarnings("unchecked")
	private ArrayList<Player> getWinners(ArrayList<Player> player) {
		ArrayList<Player> winners = (ArrayList<Player>) player.clone();
		int max;
		int size = player.get(0).hand.kickers.size();

		for (int i = 0; i < size && winners.size() > 1; i++) {
			max = 0;
			ArrayList<Player> temp = (ArrayList<Player>) winners.clone();
			for (Player p : temp) {
				if (p.hand.kickers.get(i).num > max) {
					max = p.hand.kickers.get(i).num;
					winners.clear();
					winners.add(p);
				} else if (p.hand.kickers.get(i).num == max) {
					winners.add(p);
				}
			}
		}

		return winners;
	}

	private void split(ArrayList<Player> player) {
		int splitPot = tablePot;
		splitPot /= player.size();
		for (Player p : player) {
			p.addStack(splitPot);
		}
		tablePot = 0;
	}

	private void init() {
		tablePot = 0;
		players.clear();
		d = new Deck();
		d.shuffle();
		b.board.clear();
	}

	private void setBB() {
		out.print("BB금액을 설정하세요. : ");
		int tmp = sc.nextInt();
		if (tmp > 0) {
			BB = tmp;
			System.out.println("설정이 완료되었습니다.");
		} else {
			System.out.println("Out of Range");
			setBB();
		}
	}

	private boolean ask() {
		int tmp;
		out.print("GO / STOP >> ");
		tmp = sc.nextInt();
		if (tmp == 1)
			return true;
		else if (tmp == 2)
			return false;
		else
			return ask();

	}

	public void run() {
		init();
		Player user = new Player("USER", 10000);
		players.add(user);
		players.add(new Player("CPU1", 10000));
		players.add(new Player("CPU2", 10000));
		players.add(new Player("CPU3", 10000));
		players.add(new Player("CPU4", 10000));
		players.add(new Player("CPU5", 10000));

		for (Player p : players) {
			p.hand = new Hand(d.draws(2));
		}
		user.show();
		if (ask()) {
			flop();
			if (ask()) {
				turn();
				if (ask()) {
					river();
					showdown();
				}
				else
					showdown();
			}
			else
				showdown();
		}
		else
			showdown();
		
		System.out.print("Restart -- ");
		if(ask())
			run();

	}
}