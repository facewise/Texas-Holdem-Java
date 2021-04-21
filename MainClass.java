package com.holdem;

public class MainClass {

	public static void main(String[] args) {

		Table t = new Table();
		System.out.println("T#" + t.tableNum%10000);
		//t.run();
		t.debug();
	}

}
