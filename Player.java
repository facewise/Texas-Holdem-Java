package com.holdem;

public class Player {
	public String name;
	public Hand hand;
	public int stack;
	public boolean isActive;
	public int pot;
	
	Player(String name, int stack) {
		this.name = name;
		this.stack = stack;
	}
	
	public void show() {
		System.out.print(name + "'s hand : ");
		hand.show();
		System.out.println();
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
	
	public void clearPot() {
		pot = 0;
	}
	
}