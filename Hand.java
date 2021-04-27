package com.holdem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class Hand {

	ArrayList<Card> hands = new ArrayList<>();
	ArrayList<Card> best = new ArrayList<>();
	ArrayList<Card> kickers = new ArrayList<>();
	int rank;
	String text;

	Hand(Card[] cards) {
		this.hands.addAll(Arrays.asList(cards));
		rank = -101;
	}

	void clear() {
		hands.clear();
		best.clear();
		kickers.clear();
	}

	void show() {
		hands.get(0).show();
		System.out.print(" ");
		hands.get(1).show();
		System.out.print(" ");
	}

	private boolean isSuited(ArrayList<Integer> a) {
		int size = a.size();
		boolean out = true;
		for (int i = 0; i < size - 1; i++) {
			out &= a.get(i).equals(a.get(i + 1));
		}
		return out;

	}

	@SuppressWarnings("unchecked")
	int getRank() {
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
		ArrayList<Card> nums;

		nums = (ArrayList<Card>) hands.clone();

		Collections.sort(nums);

		kickers.clear();

		int pt = 0;
		/// This block below checks if there's a kind.
		do {
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
				} else {
					pt++;
				}
			} else if (kind > 1 && subKind == 1) {
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
			} else if (kind > 1 && subKind > 1) {
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
					subKind = Math.max(tmpKind, subKind);
					pairNum2 = Math.max(tmpNum, pairNum2);
					pt += subKind;
				} else {
					pt++;
				}
			}

		} while (pt < SIZE);

		//////// HIGHCARD
		if (kind == 1) {
			best.clear();
			int tmp;
			best.addAll(nums);
			tmp = HIGH + best.get(0).num;
			if (tmp > rank) {
				rank = tmp;

				if (best.size() > 5) {
					best.remove(5);
					if (best.size() > 5) {
						best.remove(5);
						kickers.clear();
						kickers.add(best.get(1));
						kickers.add(best.get(2));
						kickers.add(best.get(3));
						kickers.add(best.get(4));
					}

				}
				if (best.get(0).num < 11)
					text = best.get(0).num + " 하이카드";
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
				for (Card c : nums) {
					if (c.num == pairNum1) {
						best.add(c);
					}
				}
				temp.removeAll(best);
				kickers.clear();
				if (temp.size() >= 3) {
					kickers.add(temp.get(0));
					kickers.add(temp.get(1));
					kickers.add(temp.get(2));
				}
				best.addAll(kickers);
				if (pairNum1 < 11)
					text = pairNum1 + " 원페어";
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
					for (Card c : nums) {
						if (c.num == pairNum1 || c.num == pairNum2) {
							best.add(c);
						}
					}
					temp.removeAll(best);
					kickers.clear();
					if (temp.size() >= 1) {
						kickers.add(temp.get(0));
					}
					best.addAll(kickers);
					if (pairNum1 < 11)
						text = pairNum1 + " 투페어";
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
					for (Card c : nums) {
						if (c.num == pairNum1 || c.num == pairNum2) {
							best.add(c);
						}
					}
					temp.removeAll(best);
					kickers.clear();
					if (temp.size() >= 1) {
						kickers.add(temp.get(0));
					}
					best.addAll(kickers);
					if (pairNum2 < 11)
						text = pairNum1 + " 투페어";
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
				for (Card c : nums) {
					if (c.num == pairNum1) {
						best.add(c);
					}
				}
				temp.removeAll(best);
				kickers.clear();
				if (temp.size() >= 2) {
					kickers.add(temp.get(0));
					kickers.add(temp.get(1));
				}
				best.addAll(kickers);
				if (pairNum1 < 11)
					text = pairNum1 + " 트리플";
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
				kickers.clear();
				for (Card c : nums) {
					if (c.num == pairNum1) {
						best.add(c);
					}
				}
				for (Card c : nums) {
					if (c.num == pairNum2 && best.size() < 5) {
						best.add(c);
					}
				}
				if (pairNum1 < 11)
					text = pairNum1 + " 풀하우스";
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
				kickers.clear();
				for (Card c : nums) {
					if (c.num == pairNum2) {
						best.add(c);
					}
				}
				for (Card c : nums) {
					if (c.num == pairNum1 && best.size() < 5) {
						best.add(c);
					}
				}
				if (pairNum2 < 11)
					text = pairNum2 + " 풀하우스";
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
				for (Card c : nums) {
					if (c.num == pairNum1) {
						best.add(c);
					}
				}
				temp.removeAll(best);
				kickers.clear();
				if (temp.size() >= 1) {
					kickers.add(temp.get(0));
				}
				best.addAll(kickers);
				if (pairNum1 < 11)
					text = pairNum1 + " 포카드";
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
		int max;

		/// This block below checks if the numerators are in a row.

		ArrayList<Card> temp = new ArrayList<>();

		while (pt < SIZE - 1) {
			if (rows == 1 && nums.get(pt).compareTo(nums.get(pt + 1)) == -1) {
				max = nums.get(pt).num;
				rows++;
				temp.add(nums.get(pt));
				suits.add(nums.get(pt).suit);
				pt++;
				temp.add(nums.get(pt));
				suits.add(nums.get(pt).suit);
				while (pt < SIZE - 1 && rows > 1) {
					if (nums.get(pt).compareTo(nums.get(pt + 1)) == -1) {
						rows++;
						temp.add(nums.get(pt + 1));
						suits.add(nums.get(pt + 1).suit);
						pt++;
					}

					else if (nums.get(pt).num == nums.get(pt + 1).num) {
						pt++;
					}

					else {
						temp.clear();
						suits.clear();
						rows = 1;
						pt++;
					}
				}

				if (rows >= 5) {
					if (!isSuited(suits)) {
						int tmp = STRAIGHT + max;
						if (tmp > rank) {
							rank = tmp;
							best.clear();
							kickers.clear();
							best.addAll(temp);
							if (best.size() > 5) {
								best.remove(5);
								if (best.size() > 5)
									best.remove(5);
							}
							temp.clear();

							if (max < 11)
								text = max + " 스트레이트";
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
							best.addAll(temp);
							temp.clear();
							if (max < 11)
								text = max + " 스트레이트 플러쉬";
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
					rows = 1;
					suits.clear();
				}
			}

			else {
				pt++;
			}

		}

		/////// This below checks if it's flush.
		if (rank < FLUSH)

		{
			if (SIZE >= 5) {
				pt = 0;
				suits.clear();
				ArrayList<Card> temp1 = new ArrayList<>();

				for (Card c : nums) {
					suits.add(c.suit);
				}

				for (int suitNum = 1; suitNum <= 4 && suitCount < 5; suitNum++) {
					if (suits.contains(suitNum)) {
						for (Integer i : suits) {
							if (i == suitNum) {
								suitCount++;
								temp1.add(nums.get(pt));
							}
							pt++;
						}
						if (suitCount >= 5) {
							Collections.sort(temp1);
							int fmax1 = temp1.get(0).num;
							if (temp1.size() > 5) {
								temp1.remove(5);
								if (temp1.size() > 5)
									temp1.remove(5);
							}
							int tmp = FLUSH + fmax1;
							if (tmp > rank) {
								best.clear();
								best.addAll(temp1);
								kickers.clear();
								kickers.add(best.get(1));
								kickers.add(best.get(2));
								kickers.add(best.get(3));
								kickers.add(best.get(4));
								rank = tmp;
								if (fmax1 < 11) {
									text = fmax1 + " 플러쉬";
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
							temp1.clear();
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