package com.holdem;


public class Card implements Comparable<Card>{
	
	public int num;
	public int suit;
	boolean isOpened = false;
	
	Card() {
		this.num = 0;
		this.suit = 0;
	}
	
	Card(int num, int suit) {
		if(num == 1)
			this.num = 14;
		else
			this.num = num;
		this.suit = suit;};
		
	public void show() {
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
	
	public String toString() {
		String output = "";
		if (num >= 2 && num <= 9)
			output.concat(Integer.toString(num));
		else if (num == 10)
			output.concat("T");
		else if (num == 11)
			output.concat("J");
		else if (num == 12)
			output.concat("Q");
		else if (num == 13)
			output.concat("K");
		else if (num == 14)
			output.concat("A");
		if (suit == 1)
			output.concat("♠︎");
		else if (suit == 2)
			output.concat("♦︎");
		else if (suit == 3)
			output.concat("♥︎︎︎");
		else if (suit == 4)
			output.concat("︎♣︎");
		return output;
	}

	@Override
	public int compareTo(Card o) {
		return o.num - this.num;
	}

}
