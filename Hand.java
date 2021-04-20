package com.holdem;

import java.util.ArrayList;
import java.util.Collections;

public class Hand {

	public ArrayList<Card> hands = new ArrayList<Card>();
	ArrayList<Card> best = new ArrayList<Card>();
	Card kicker1;
	Card kicker2;
	Card kicker3;
	Card kicker4;
	int rank;
	String text;

	Hand(Card[] cards) {
		for (Card tmp : cards)
			this.hands.add(tmp);
		rank = -101;
	}

	public void clear() {
		hands.clear();
		best.clear();
		kicker1 = new Card();
		kicker2 = new Card();
		kicker3 = new Card();
		kicker4 = new Card();
	}
	
	public void clearKickers() {
		kicker1 = new Card();
		kicker2 = new Card();
		kicker3 = new Card();
		kicker4 = new Card();
	}

	public void show() {
		hands.get(0).show();
		System.out.print(" ");
		hands.get(1).show();
		System.out.print(" ");
	}

	private boolean isSuited(ArrayList<Integer> a) {
		int size = a.size();
		boolean out = true;
		for (int i = 0; i < size - 1; i++) {
			out &= a.get(i) == a.get(i + 1);
		}
		return out;

	}

	@SuppressWarnings("unchecked")
	public int getRank() {
		final int SIZE = hands.size();

		final int HIGH = -100;
		final int PAIR = 0;
		final int TWOPAIR = 100;
		final int TRIPLE = 200;
		final int STRAIGHT = 300;
		final int FLUSH = 400;
		final int FULLHOUSE = 500;
		final int FOURCARD = 700;
		final int SF = 800;
		final int RF = 5000;

		rank = -101;
		int pairNum1 = 0, pairNum2 = 0;
		int rows = 1;
		int kind = 1, subKind = 1, suitCount = 0;

		ArrayList<Integer> suits = new ArrayList<>();
		ArrayList<Card> nums = new ArrayList<Card>();

		nums = (ArrayList<Card>) hands.clone();

		Collections.sort(nums);
		
		clearKickers();

		int pt = 0;
		/// This block below checks if there's a kind.
		while (true) {
			int i = 1;
			if (kind == 1) {
				if (pt < SIZE - 1 && nums.get(pt).compareTo(nums.get(pt + 1)) == 0) {
					while (nums.get(pt).compareTo(nums.get(pt + i)) == 0) {
						kind++;
						pairNum1 = nums.get(pt).num;
						i++;
						if (pt + i == SIZE)
							break;
					}
					pt += kind;
				}

				else {
					pt++;
				}
			}

			else if (kind > 1 && subKind == 1) {
				if (pt < SIZE - 1 && nums.get(pt).compareTo(nums.get(pt + 1)) == 0) {
					while (nums.get(pt).compareTo(nums.get(pt + i)) == 0) {
						subKind++;
						pairNum2 = nums.get(pt).num;
						i++;
						if (pt + i == SIZE)
							break;
					}
					pt += subKind;
				} else {
					pt++;
				}
			}

			else if (kind > 1 && subKind > 1) {
				int tmpKind = 1;
				int tmpNum = 0;
				if (pt < SIZE - 1 && nums.get(pt).compareTo(nums.get(pt + 1)) == 0) {
					while (nums.get(pt).compareTo(nums.get(pt + i)) == 0) {
						tmpKind++;
						tmpNum = nums.get(pt).num;
						i++;
						if (pt + i == SIZE)
							break;
					}
					subKind = tmpKind > subKind ? tmpKind : subKind;
					pairNum2 = tmpNum > pairNum2 ? tmpNum : pairNum2;
					pt += subKind;
				}

				else {
					pt++;
				}
			}

			if (pt >= SIZE) {
				break;
			}

		}

		//////// HIGHCARD
		if (kind == 1 && subKind == 1) {
			best.clear();
			int tmp = 0;
			for (Card c : nums) {
				best.add(c);
			}
			tmp = HIGH + best.get(0).num;
			if (tmp > rank) {
				rank = tmp;
				
				if (best.size() > 5) {
					best.remove(5);
					if (best.size() > 5) {
						best.remove(5);
						kicker1 = best.get(1);
						kicker2 = best.get(2);
						kicker3 = best.get(3);
						kicker4 = best.get(4);
					}
				}
				if (best.get(0).num < 11)
					text = Integer.toString(best.get(0).num) + " 하이카드";
				else if (best.get(0).num == 11)
					text = "J 하이카드";
				else if (best.get(0).num == 12)
					text = "Q 하이카드";
				else if (best.get(0).num == 13)
					text = "K 하이카드";
				else if (best.get(0).num == 14)
					text = "Ace 하이카드";
			}

		}

		//////// ONE PAIR
		if (kind >= 2) {
			int tmp = PAIR + pairNum1;
			if (tmp > rank) {
				ArrayList<Card> temp = (ArrayList<Card>) nums.clone();
				rank = tmp;
				best.clear();
				for (int i = 0; i < nums.size(); i++) {
					if (nums.get(i).num == pairNum1) {
						best.add(nums.get(i));
					}
				}
				temp.removeAll(best);
				kicker1 = temp.get(0);
				kicker2 = temp.get(1);
				kicker3 = temp.get(2);
				best.add(kicker1);
				best.add(kicker2);
				best.add(kicker3);
				if (pairNum1 < 11)
					text = Integer.toString(pairNum1) + " 원페어";
				else if (pairNum1 == 11)
					text = "J 원페어";
				else if (pairNum1 == 12)
					text = "Q 원페어";
				else if (pairNum1 == 13)
					text = "K 원페어";
				else if (pairNum1 == 14)
					text = "Ace 원페어";
			}
		}

		//////// TWO PAIRS
		if (kind >= 2 && subKind >= 2) {
			if (pairNum1 > pairNum2) {
				int tmp = TWOPAIR + (pairNum1 - 2) * (pairNum1 - 3) / 2 + pairNum2 - 2;
				if (tmp > rank) {
					ArrayList<Card> temp = (ArrayList<Card>) nums.clone();
					rank = tmp;
					best.clear();
					for (int i = 0; i < nums.size(); i++) {
						if (nums.get(i).num == pairNum1 || nums.get(i).num == pairNum2) {
							best.add(nums.get(i));
						}
					}
					temp.removeAll(best);
					kicker1 = temp.get(0);
					best.add(kicker1);
					if (pairNum1 < 11)
						text = Integer.toString(pairNum1) + " 투페어";
					else if (pairNum1 == 11)
						text = "J 투페어";
					else if (pairNum1 == 12)
						text = "Q 투페어";
					else if (pairNum1 == 13)
						text = "K 투페어";
					else if (pairNum1 == 14)
						text = "Ace 투페어";
				}
			}

			else {
				int tmp = TWOPAIR + (pairNum2 - 2) * (pairNum2 - 3) / 2 + pairNum1 - 2;
				if (tmp > rank) {
					ArrayList<Card> temp = (ArrayList<Card>) nums.clone();
					rank = tmp;
					best.clear();
					for (int i = 0; i < nums.size(); i++) {
						if (nums.get(i).num == pairNum1 || nums.get(i).num == pairNum2) {
							best.add(nums.get(i));
						}
					}
					temp.removeAll(best);
					kicker1 = temp.get(0);
					best.add(kicker1);
					if (pairNum2 < 11)
						text = Integer.toString(pairNum1) + " 투페어";
					else if (pairNum2 == 11)
						text = "J 투페어";
					else if (pairNum2 == 12)
						text = "Q 투페어";
					else if (pairNum2 == 13)
						text = "K 투페어";
					else if (pairNum2 == 14)
						text = "Ace 투페어";
				}
			}
		}

		//////// TRIPLES
		if (kind >= 3) {
			int tmp = TRIPLE + pairNum1;
			if (tmp > rank) {
				ArrayList<Card> temp = (ArrayList<Card>) nums.clone();
				rank = tmp;
				best.clear();
				for (int i = 0; i < nums.size(); i++) {
					if (nums.get(i).num == pairNum1) {
						best.add(nums.get(i));
					}
				}
				temp.removeAll(best);
				kicker1 = temp.get(0);
				kicker2 = temp.get(1);
				best.add(kicker1);
				best.add(kicker2);
				if (pairNum1 < 11)
					text = Integer.toString(pairNum1) + " 트리플";
				else if (pairNum1 == 11)
					text = "J 트리플";
				else if (pairNum1 == 12)
					text = "Q 트리플";
				else if (pairNum1 == 13)
					text = "K 트리플";
				else if (pairNum1 == 14)
					text = "Ace 트리플";
			}
		}

		//////// FULLHOUSE
		if ((kind >= 3 && subKind >= 2)) {
			int tmp = FULLHOUSE + (pairNum1 - 2) * 13 + pairNum2 - 2;
			if (tmp > rank) {
				rank = tmp;
				best.clear();
				for (int i = 0; i < nums.size(); i++) {
					if (nums.get(i).num == pairNum1) {
						best.add(nums.get(i));
					}
				}
				for (int i = 0; i < nums.size(); i++) {
					if (nums.get(i).num == pairNum2 && best.size() < 5) {
						best.add(nums.get(i));
					}
				}
				if (pairNum1 < 11)
					text = Integer.toString(pairNum1) + " 풀하우스";
				else if (pairNum1 == 11)
					text = "J 풀하우스";
				else if (pairNum1 == 12)
					text = "Q 풀하우스";
				else if (pairNum1 == 13)
					text = "K 풀하우스";
				else if (pairNum1 == 14)
					text = "Ace 풀하우스";
			}
		}

		else if ((kind >= 2 && subKind >= 3)) {
			int tmp = FULLHOUSE + (pairNum2 - 2) * 13 + pairNum1 - 2;
			if (tmp > rank) {
				rank = tmp;
				best.clear();
				for (int i = 0; i < nums.size(); i++) {
					if (nums.get(i).num == pairNum2) {
						best.add(nums.get(i));
					}
				}
				for (int i = 0; i < nums.size(); i++) {
					if (nums.get(i).num == pairNum1 && best.size() < 5) {
						best.add(nums.get(i));
					}
				}
				if (pairNum2 < 11)
					text = Integer.toString(pairNum2) + " 풀하우스";
				else if (pairNum2 == 11)
					text = "J 풀하우스";
				else if (pairNum2 == 12)
					text = "Q 풀하우스";
				else if (pairNum2 == 13)
					text = "K 풀하우스";
				else if (pairNum2 == 14)
					text = "Ace 풀하우스";
			}
		}

		//////// FOURCARD
		if (kind >= 4) {
			int tmp = FOURCARD + pairNum1;
			if (tmp > rank) {
				ArrayList<Card> temp = (ArrayList<Card>) nums.clone();
				rank = tmp;
				best.clear();
				for (int i = 0; i < nums.size(); i++) {
					if (nums.get(i).num == pairNum1) {
						best.add(nums.get(i));
					}
				}
				temp.removeAll(best);
				kicker1 = temp.get(0);
				best.add(kicker1);
				if (pairNum1 < 11)
					text = Integer.toString(pairNum1) + " 포카드";
				else if (pairNum1 == 11)
					text = "J 포카드";
				else if (pairNum1 == 12)
					text = "Q 포카드";
				else if (pairNum1 == 13)
					text = "K 포카드";
				else if (pairNum1 == 14)
					text = "Ace 포카드";
			}
		}

		pt = 0;
		int max = 0;

		/// This block below checks if the numerators are in a row.
		while (true) {
			if (pt >= SIZE - 1) {
				break;
			}

			else {
				if (pt < 3 && nums.get(pt).compareTo(nums.get(pt + 1)) == -1) {
					max = nums.get(pt).num;
					while (true) {
						if(pt >= SIZE)
							break;
						if (nums.get(pt).compareTo(nums.get(pt + 1)) == -1) {
							rows++;
							suits.add(nums.get(pt).suit);
							pt++;
							if (pt >= SIZE - 1) {
								break;
							}
						}

						else if (nums.get(pt).num == nums.get(pt + 1).num) {
							pt += 2;
							if (pt >= SIZE - 1) {
								break;
							}
						}
						else
							break;
					}
					if (rows >= 5) {
						if (!isSuited(suits)) {
							int tmp = STRAIGHT + max;
							if (tmp > rank) {
								rank = tmp;
								best.clear();
								int i = 0;
								while (i < nums.size()) {
									if (nums.get(i).num == max) {
										while (best.size() < 5) {
											best.add(nums.get(i));
											i++;
										}
									} else {
										i++;
									}
								}
								if (max < 11)
									text = Integer.toString(max) + " 스트레이트";
								else if (max == 11)
									text = "J 스트레이트";
								else if (max == 12)
									text = "Q 스트레이트";
								else if (max == 13)
									text = "K 스트레이트";
								else if (max == 14)
									text = "마운틴";
							}
						}

						else if (isSuited(suits)) {
							int tmp = SF + max;
							if (tmp > rank) {
								best.clear();
								int i = 0;
								while (i < nums.size()) {
									if (nums.get(i).num == max) {
										while (best.size() < 5) {
											if (nums.get(i).suit == suits.get(0))
												best.add(nums.get(i));
											i++;
										}
									} else {
										i++;
									}
								}
								if (max < 11)
									text = Integer.toString(max) + " 스트레이트 플러쉬";
								else if (max == 11)
									text = "J 스트레이트 플러쉬";
								else if (max == 12)
									text = "Q 스트레이트 플러쉬";
								else if (max == 13)
									text = "K 스트레이트 플러쉬";
								else if (max == 14) {
									tmp = RF;
									text = "로얄 스트레이트 플러쉬";
								}
								rank = tmp;
							}
						}
					}

					else {
						rows = 0;
						suits.clear();
					}
				}

				else if (pt > 2) {
					break;
				}

				else {
					pt++;
				}
			}
		}

		/////// This below checks if it's flush.
		if (rank < FLUSH) {
			if (SIZE >= 5) {
				pt = 0;
				suits.clear();
				ArrayList<Card> temp = new ArrayList<Card>();

				for (Card c : nums) {
					suits.add(c.suit);
				}

				for (int suitNum = 1; suitNum <= 4 && suitCount < 5; suitNum++) {
					if (suits.contains(suitNum)) {
						for (Integer i : suits) {
							if (i == suitNum) {
								suitCount++;
								temp.add(nums.get(pt));
							}
							pt++;
						}
						if (suitCount >= 5) {
							Collections.sort(temp);
							int fmax1 = temp.get(0).num;
							if (temp.size() > 5) {
								temp.remove(5);
								if (temp.size() > 5)
									temp.remove(5);
							}
							int tmp = FLUSH + fmax1;
							if (tmp > rank) {
								best.clear();
								for (Card c : temp) {
									best.add(c);
								}
								kicker1 = best.get(1);
								kicker2 = best.get(2);
								kicker3 = best.get(3);
								kicker4 = best.get(4);
								rank = tmp;
								if (fmax1 < 11) {
									text = Integer.toString(fmax1) + " 플러쉬";
								} else if (fmax1 == 11) {
									text = "J 플러쉬";
								} else if (fmax1 == 12) {
									text = "Q 플러쉬";
								} else if (fmax1 == 13) {
									text = "K 플러쉬";
								} else if (fmax1 == 14) {
									text = "Ace 플러쉬";
								}
							}
						} else {
							temp.clear();
							suitCount = 0;
							pt = 0;
						}
					}
				}
			}
		}
		return rank;
	}

}