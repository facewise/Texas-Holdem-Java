package com.holdem;

class Card implements Comparable<Card> {

	int num;
	int suit;
	boolean isOpened = false;

	Card() {
		this.num = 0;
		this.suit = 0;
	}

	Card(int num, int suit) {
		if (num == 1)
			this.num = 14;
		else
			this.num = num;
		this.suit = suit;
	}

	void show() {
		if (num >= 2 && num <= 9)
			System.out.print(num);
		else if (num == 10)
			System.out.print("T");
		else if (num == 11)
			System.out.print("J");
		else if (num == 12)
			System.out.print("Q");
		else if (num == 13)
			System.out.print("K");
		else if (num == 14)
			System.out.print("A");
		if (suit == 1)
			System.out.print("♠︎");
		else if (suit == 2)
			System.out.print("♦︎");
		else if (suit == 3)
			System.out.print("♥︎︎︎");
		else if (suit == 4)
			System.out.print("︎♣︎");
	}

	@Override
	public String toString() {
		String output = null;
		if (num >= 2 && num <= 9)
			output = Integer.toString(num);
		else if (num == 10)
			output = "T";
		else if (num == 11)
			output = "J";
		else if (num == 12)
			output = "Q";
		else if (num == 13)
			output = "K";
		else if (num == 14)
			output = "A";
		return output;
	}

	@Override
	public int compareTo(Card o) {
		return o.num - this.num;
	}

}
