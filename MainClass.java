package com.holdem;

import java.util.Set;

public class MainClass {

	public static void main(String[] args) {

		int iter = 100000;
		Table t = new Table();
		// System.out.println("T#" + t.tableNum % 10000);
		// t.run();
		for (int i = 0; i < iter; i++)
			t.debug();
		Set<String> set = t.hashMap1.keySet();
		for (String hs : set) {
			if (hs.startsWith("A")) {
				if (t.hashMap2.get(hs) > 0) {
					System.out.printf("%s : %.1f\n", hs, (double) t.hashMap2.get(hs) / t.hashMap1.get(hs) * 100);
				}
			}
		}
		System.out.println();
		System.out.println();
		for (String hs : set) {
			if (hs.startsWith("K")) {
				if (t.hashMap2.get(hs) > 0) {
					System.out.printf("%s : %.1f\n", hs, (double) t.hashMap2.get(hs) / t.hashMap1.get(hs) * 100);
				}
			}
		}
		System.out.println();
		System.out.println();
		for (String hs : set) {
			if (hs.startsWith("Q")) {
				if (t.hashMap2.get(hs) > 0) {
					System.out.printf("%s : %.1f\n", hs, (double) t.hashMap2.get(hs) / t.hashMap1.get(hs) * 100);
				}
			}
		}
		System.out.println();
		System.out.println();
		for (String hs : set) {
			if (hs.startsWith("J")) {
				if (t.hashMap2.get(hs) > 0) {
					System.out.printf("%s : %.1f\n", hs, (double) t.hashMap2.get(hs) / t.hashMap1.get(hs) * 100);
				}
			}
		}
		System.out.println();
		System.out.println();
		for (String hs : set) {
			if (hs.startsWith("T")) {
				if (t.hashMap2.get(hs) > 0) {
					System.out.printf("%s : %.1f\n", hs, (double) t.hashMap2.get(hs) / t.hashMap1.get(hs) * 100);
				}
			}
		}
		System.out.println();
		System.out.println();
		for (String hs : set) {
			if (hs.startsWith("9")) {
				if (t.hashMap2.get(hs) > 0) {
					System.out.printf("%s : %.1f\n", hs, (double) t.hashMap2.get(hs) / t.hashMap1.get(hs) * 100);
				}
			}
		}
		System.out.println();
		System.out.println();
		for (String hs : set) {
			if (hs.startsWith("8")) {
				if (t.hashMap2.get(hs) > 0) {
					System.out.printf("%s : %.1f\n", hs, (double) t.hashMap2.get(hs) / t.hashMap1.get(hs) * 100);
				}
			}
		}
		System.out.println();
		System.out.println();
		for (String hs : set) {
			if (hs.startsWith("7")) {
				if (t.hashMap2.get(hs) > 0) {
					System.out.printf("%s : %.1f\n", hs, (double) t.hashMap2.get(hs) / t.hashMap1.get(hs) * 100);
				}
			}
		}
		System.out.println();
		System.out.println();
		for (String hs : set) {
			if (hs.startsWith("6")) {
				if (t.hashMap2.get(hs) > 0) {
					System.out.printf("%s : %.1f\n", hs, (double) t.hashMap2.get(hs) / t.hashMap1.get(hs) * 100);
				}
			}
		}
		System.out.println();
		System.out.println();
		for (String hs : set) {
			if (hs.startsWith("5")) {
				if (t.hashMap2.get(hs) > 0) {
					System.out.printf("%s : %.1f\n", hs, (double) t.hashMap2.get(hs) / t.hashMap1.get(hs) * 100);
				}
			}
		}
		System.out.println();
		System.out.println();
		for (String hs : set) {
			if (hs.startsWith("4")) {
				if (t.hashMap2.get(hs) > 0) {
					System.out.printf("%s : %.1f\n", hs, (double) t.hashMap2.get(hs) / t.hashMap1.get(hs) * 100);
				}
			}
		}
		System.out.println();
		System.out.println();
		for (String hs : set) {
			if (hs.startsWith("3")) {
				if (t.hashMap2.get(hs) > 0) {
					System.out.printf("%s : %.1f\n", hs, (double) t.hashMap2.get(hs) / t.hashMap1.get(hs) * 100);
				}
			}
		}
		System.out.println();
		System.out.println();
		for (String hs : set) {
			if (hs.startsWith("2")) {
				if (t.hashMap2.get(hs) > 0) {
					System.out.printf("%s : %.1f\n", hs, (double) t.hashMap2.get(hs) / t.hashMap1.get(hs) * 100);
				}
			}
		}
		
	}

}
