package com.holdem;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {
	final int SIZE = 52;
	final int NUMS = 13;
	final int SUITS = 4;
	
	int top = 0;
	
	Card[] cards = new Card[SIZE];
	
	Deck() {
		int index = 0;
		for(int i = 1; i <= SUITS; i++) {
			for(int j = 1; j <= NUMS; j++) {
				cards[index++] = new Card(j,i);
			}
		}
	}
	
	
	public void spread() {
		for(int i = 0; i < SIZE; i++) {
			cards[i].show();
			System.out.print(" ");
			if(i%13 == 12 && i > 0)
				System.out.println();
		}
	}
	
	public void shuffle() {
		List<Card> list = Arrays.asList(cards);
		Collections.shuffle(list);
		int i = 0;
		for(Card tmp : list) {
			cards[i++] = tmp;
		}
	}
	
	public Card draw() {
		return cards[top++];
	}
	
	public Card[] draws(int num) {
		Card[] output = new Card[num];
		
		for(int i = 0; i < num; i++)
			output[i] = cards[top++];
		
		return output;
	}
	
}
