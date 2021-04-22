package com.holdem;

import java.util.Set;

public class MainClass {

	public static void main(String[] args) {

		int iter = 1000000;
		Table t = new Table();
		//System.out.println("T#" + t.tableNum % 10000);
		// t.run();
		for (int i = 0; i < iter; i++)
			t.debug();
		Set<String> set = t.hashMap.keySet();
		for (String hs : set) {
			if (t.hashMap.get(hs) > 0) {
				System.out.printf("%s : %.2f%n",hs,(double)t.hashMap.get(hs)/iter * 100);
			}
		}
	}

}
