package com.holdem;

import java.util.ArrayList;

class Board {
	ArrayList<Card> board;

	Board() {
		board = new ArrayList<>();
	}

	void add(Card c) {
		board.add(c);
	}

	void show() {
		System.out.print("Community Cards : ");
		for (Card c : board) {
			c.show();
			System.out.print(" ");
		}
		System.out.println();
	}
}