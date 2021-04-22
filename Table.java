package com.holdem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

import static java.lang.System.out;

public class Table {
	private Deck d;

	private Board b;

	private int tablePot;

	private ArrayList<Player> players;
	
	ArrayList<Player> kicking;
	
	HashMap<String, Integer> hashMap1;
	
	HashMap<String, Integer> hashMap2;
	
	public int tableNum;

	private int DBU;

	private int BB;

	Scanner sc = new Scanner(System.in);

	Table() {
		d = new Deck();
		b = new Board();
		tablePot = 0;
		hashMap1 = new HashMap<>();
		hashMap2 = new HashMap<>();
		players = new ArrayList<>();
		tableNum = this.hashCode();
		DBU = 0;
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
	//	b.show();
	}

	private void turn() {
		b.board.add(d.draw());
		apply();
	//	b.show();
	}

	private void river() {
		b.board.add(d.draw());
		apply();
	//	b.show();
	}

	private void showdown() {

		kicking = new ArrayList<Player>();
		ArrayList<Player> temp = new ArrayList<>();
		Player winner;

		for (Player p : players) {
			if (p.isActive)
				temp.add(p);
		}
		Collections.sort(temp, Collections.reverseOrder());
		int maxRank = temp.get(0).hand.rank;

		for (Player p : players) {
		//	p.showBest();
			if (p.hand.rank == maxRank) {
				kicking.add(p);
			}
		}

		//System.out.println();

		if (kicking.size() == 1) {
		//	winner = kicking.get(0);
		//	winner.addStack(tablePot);
		//	System.out.print("Winner : ");
		//	System.out.println(kicking.toString());
		//	init();
		}

		else {
			if (kicking.get(0).hand.kickers.size() == 0) {
			//	split(kicking);
			//	System.out.println("Splitted Pot");
			//	System.out.println(kicking.toString());
			}

			else {
				kicking = getWinners(kicking);
				if (kicking.size() == 1) {
				//	winner = kicking.get(0);
				//	winner.addStack(tablePot);
				//	System.out.print("Winner : ");
				//	System.out.println(kicking.toString());
				}

				else {
					split(kicking);
				//	System.out.println("Splitted Pot");
				//	System.out.println(kicking.toString());
				}
			}
		}
		//System.out.println();
		//System.out.println(temp.toString());
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
	
	private int ask() {
		int tmp;
		out.print("BET (0 : Fold) :  ");
		tmp = sc.nextInt();
		if (tmp == 0)
			return 0;
		else if (tmp > 0) {
			return tmp;
		} else {
			return ask();
		}

	}

	public void debug() {
		init();
		Player user = new Player("USER", 100);
		players.add(user);

		players.add(new Player("CPU1", 100));
		players.add(new Player("CPU2", 100));
		players.add(new Player("CPU3", 100));
		players.add(new Player("CPU4", 100));
		players.add(new Player("CPU5", 100));
		players.add(new Player("CPU6", 100));
		players.add(new Player("CPU7", 100));
		players.add(new Player("CPU8", 100));

		for (Player p : players) {
			Hand tmp = new Hand(d.draws(2));
			p.hand = tmp;
			Collections.sort(tmp.hands);
			String tempString = tmp.hands.get(0).toString()
					+ tmp.hands.get(1).toString();
			if(tmp.hands.get(0).suit==tmp.hands.get(1).suit)
				tempString = tempString + "s";
			if(!hashMap1.containsKey(tempString)) {
				hashMap1.put(tempString, 0);
			}
			else {
				hashMap1.put(tempString, hashMap1.get(tempString)+1);
			}
			//p.show();
		}
		
		flop();
		turn();
		river();
		showdown();
		for(Player p : kicking) {
			ArrayList<Card> temp = new ArrayList<>();
			temp.add(p.hand.hands.get(0));
			temp.add(p.hand.hands.get(1));
			Collections.sort(temp);
			
			String tempString = temp.get(0).toString()
					+ temp.get(1).toString();
			
			if(temp.get(0).suit==temp.get(1).suit)
				tempString = tempString + "s";
			
			if(!hashMap2.containsKey(tempString)) {
				hashMap2.put(tempString, 0);
			}
			else {
				hashMap2.put(tempString, hashMap2.get(tempString)+1);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void run() {
		init();
		Player user = new Player("USER", 100);
		players.add(user);
		players.add(new Player("CPU1", 100));
		players.add(new Player("CPU2", 100));
		players.add(new Player("CPU3", 100));
		players.add(new Player("CPU4", 100));
		players.add(new Player("CPU5", 100));

		for (Player p : players) {
			p.hand = new Hand(d.draws(2));
		}
		user.show();
		if (ask() > 0) {
			flop();
			int tmp = ask();
			if (tmp > 0) {
				tablePot = tmp;
				for (int i = 0; i < players.size(); i++) {
					if (players.get(i) != user)
						players.get(i).setPot(tablePot);
				}
				ArrayList<Player> temp = (ArrayList<Player>) players.clone();
				for (Player p : temp) {
					if (!p.isActive) {
						players.remove(p);
					}
				}

				System.out.println();
				turn();
				tmp = ask();
				if (tmp > 0) {
					tablePot = tmp;
					for (int i = 0; i < players.size(); i++) {
						if (players.get(i) != user)
							players.get(i).setPot(tablePot);
					}
					temp = (ArrayList<Player>) players.clone();
					for (Player p : temp) {
						if (!p.isActive) {
							players.remove(p);
						}
					}

					System.out.println();
					river();
					if (ask() > 0)
						showdown();
				} else {
					user.fold();
					showdown();
				}

			} else {
				user.fold();
				showdown();
			}

		} else {
			user.fold();
			showdown();
		}

		System.out.print("Restart -- ");
		if (ask() > 0) {
			run();
		}

	}
}