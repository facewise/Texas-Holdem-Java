package com.holdem;

public class Player implements Comparable<Player> {
	public String name;
	public Hand hand;
	public int stack;
	public int pot;
	public boolean isActive;
	public double agg;

	Player(String name, int stack) {
		this.name = name;
		this.stack = stack;
		agg = Math.random() * 10;
		isActive = true;
	}

	@Override
	public String toString() {
		return this.name + "(" + hand.rank + ")";
	}

	public void show() {
		System.out.print(name + "'s hand : ");
		hand.show();
		System.out.println();
	}

	public void showBest() {
		System.out.print(name + "'s Best hand : ");
		for (int i = 0; i < hand.best.size(); i++) {
			hand.best.get(i).show();
			System.out.print(" ");
		}
		System.out.println();

		System.out.println(hand.text);
	}

	public int getRank() {
		return hand.getRank();
	}

	public void addStack(int n) {
		stack += n;
	}

	public void addPot(int n) {
		pot += n;
	}

	public void clearHand() {
		hand.clear();
	}

	public void clearPot() {
		pot = 0;
	}

	public void fold() {
		System.out.println(name + " folded.");
		isActive = false;
		clearHand();
	}

	public void setPot(int cur) {
		pot = cur;
	}

	@Override
	public int compareTo(Player p) {
		int mine = this.hand.getRank();
		int oppo = p.getRank();
		return mine - oppo;
	}

}