package com.holdem;

import java.util.ArrayList;
import java.util.Collections;

public class MainClass {

	private static Deck d = new Deck();

	private static Board b = new Board();

	private static int tablePot = 0;

	private static ArrayList<Player> players = new ArrayList<Player>();

	private static void apply() {
		for (int i = 0; i < players.size(); i++) {
			for (Card c : b.board) {
				if (!players.get(i).hand.hands.contains(c)) {
					players.get(i).hand.hands.add(c);
				}
			}
		}
	}

	private static void flop() {
		b.board.add(d.draw());
		b.board.add(d.draw());
		b.board.add(d.draw());
		apply();
		b.show();
	}

	private static void turn() {
		b.board.add(d.draw());
		apply();
		b.show();
	}

	private static void river() {
		b.board.add(d.draw());
		apply();
		b.show();
	}

	@SuppressWarnings("unchecked")
	private static void showdown() {

		ArrayList<Player> kicking = new ArrayList<Player>();
		ArrayList<Player> temp = (ArrayList<Player>) players.clone();

		Collections.sort(temp, Collections.reverseOrder());
		int maxRank = temp.get(0).hand.rank;

		for (Player p : players) {
			p.showBest();
			if (p.hand.rank == maxRank) {
				kicking.add(p);
			}
		}
		if (kicking.size() == 1) {
			kicking.get(0).addPot(tablePot);
			System.out.println("Winner : " + kicking.get(0).name);
			restart();
		}

		else {
			if (kicking.get(0).hand.kicker1.num == 0) {
				split();
				System.out.println("Splitted Pot");
			} 
			else {
				if (kicking.size() == 1) {
					kicking.get(0).addPot(tablePot);
					System.out.println("Winner : " + kicking.get(0).name);
				} 
				else {
					kicking = getWinners(kicking);
					if(kicking.size() == 1) {
						kicking.get(0).addPot(tablePot);
						System.out.println("Winner : " + kicking.get(0).name);
					}
					else {
						split();
						System.out.println("Splitted Pot");
					}
				}
			}
		}
		System.out.println();
		System.out.println(temp.toString());
	}

	private static ArrayList<Player> getWinners(ArrayList<Player> player) {
		ArrayList<Player> winners = new ArrayList<Player>();
		int max = 0;
		for (Player p : player) {
			if (p.hand.kicker1.num >= max) {
				max = p.hand.kicker1.num;
				winners.add(p);
			}
		}
		if (winners.size() > 1) {
			player.removeAll(winners);
			winners.clear();
			for (Player p : player) {
				if (p.hand.kicker2.num >= max) {
					max = p.hand.kicker2.num;
					winners.add(p);
				}
			}
		}
		if (winners.size() > 1) {
			player.removeAll(winners);
			winners.clear();
			for (Player p : player) {
				if (p.hand.kicker3.num >= max) {
					max = p.hand.kicker3.num;
					winners.add(p);
				}
			}
		}
		if (winners.size() > 1) {
			player.removeAll(winners);
			winners.clear();
			for (Player p : player) {
				if (p.hand.kicker4.num >= max) {
					max = p.hand.kicker4.num;
					winners.add(p);
				}
			}
		}
		return winners;
	}

	private static void split() {
		// TODO Auto-generated method stub

	}

	private static void restart() {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {

		long start = System.currentTimeMillis();
		d.shuffle();

		Player p1 = new Player("p1", 100);
		Player p2 = new Player("p2", 100);
		Player p3 = new Player("p3", 100);
		Player p4 = new Player("p4", 100);
		Player p5 = new Player("p5", 100);
		Player p6 = new Player("p6", 100);
		players.add(p1);
		players.add(p2);
		players.add(p3);
		players.add(p4);
		players.add(p5);
		players.add(p6);

		p1.hand = new Hand(d.draws(2));
		p1.show();
		p2.hand = new Hand(d.draws(2));
		p2.show();
		p3.hand = new Hand(d.draws(2));
		p3.show();
		p4.hand = new Hand(d.draws(2));
		p4.show();
		p5.hand = new Hand(d.draws(2));
		p5.show();
		p6.hand = new Hand(d.draws(2));
		p6.show();

		flop();
		turn();
		river();
		System.out.println();
		showdown();

		long end = System.currentTimeMillis();

		System.out.println(end - start + "ms");
	}

}
