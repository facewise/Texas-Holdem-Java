package com.holdem;

import java.util.ArrayList;
import java.util.HashSet;

public class MainClass {
	
	public static Deck d = new Deck();
	
	public static Board b = new Board();
	
	public static ArrayList<Player> players = new ArrayList<Player>();
	
	public static void apply() {
		for(int i = 0; i < players.size(); i++) {
			for(Card c : b.board) {
				if(!players.get(i).hand.hands.contains(c)) {
					players.get(i).hand.hands.add(c);
				}
			}
		}
	}
	
	public static void flop() {
		b.board.add(d.draw());
		b.board.add(d.draw());
		b.board.add(d.draw());
		apply();
		b.show();
	}
	
	public static void turn() {
		b.board.add(d.draw());
		apply();
		b.show();
	}
	
	public static void river() {
		b.board.add(d.draw());
		apply();
		b.show();
	}

	public static void main(String[] args) {
		
		long start = System.currentTimeMillis();
		d.shuffle();
		
		Player p1 = new Player("p1", 100);
		Player p2 = new Player("p2", 100);
		players.add(p1);
		players.add(p2);
		
		p1.hand = new Hand(d.draws(2));
		p1.show();
		p2.hand = new Hand(d.draws(2));
		p2.show();
		
		flop();
		turn();
		river();
		p1.getRank();
		System.out.println();
		p2.getRank();
		
		long end = System.currentTimeMillis();
		
		System.out.println(end - start + "ms");
	}

}
