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
		return this.name + "(" + Integer.toString(hand.rank) + ")";
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
		int rank = this.getRank();
		double ratio = (double) (cur / stack) * 100;
		double agg = this.agg;
		double rand = Math.random() * 100;
		double pb = rand + agg;
		if (rank < 0) {
			if (ratio > 20) {
				if (pb < 90) {
					fold();
				} else {
					pot = cur;
				}
			} else {
				if (pb < 60) {
					fold();
				} else {
					pot = cur;
				}
			}
		}

		else if (rank < 100) {
			if (ratio > 20) {
				if (pb < 50) {
					fold();
				} else {
					pot = cur;
				}
			}
			else {
				if (pb < 25) {
					fold();
				}
				else {
					pot = cur;
				}
			}
		}

		else if (rank < 200) {
			if (ratio > 20) {
				if (pb < 20) {
					fold();
				} else {
					pot = cur;
				}
			}
			else {
				if (pb < 5) {
					fold();
				}
				else {
					pot = cur;
				}
			}
		}

		else {
			if (ratio > 50) {
				if (pb < 20) {
					fold();
				} else {
					pot = cur;
				}
			}
			else {
				if (pb < 3) {
					fold();
				}
				else {
					pot = cur;
				}
			}
		}
	}

	@Override
	public int compareTo(Player p) {
		int mine = this.hand.getRank();
		int oppo = p.getRank();
		return mine - oppo;
	}

}