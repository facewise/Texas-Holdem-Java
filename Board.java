package com.holdem;

import java.util.ArrayList;

public class Board {
	public ArrayList<Card> board;

	Board() {
		board = new ArrayList<Card>();
	}

	public void add(Card c) {
		board.add(c);
	}

	public void show() {
		System.out.print("Commnunity Cards : ");
		for (Card c : board) {
			c.show();
			System.out.print(" ");
		}
		System.out.println();
	}
}