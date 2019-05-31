package yetAnother_KMP_Problem.Streamlined;

import java.util.Scanner;

public class Solution {
	private static int minFrequency = Integer.MAX_VALUE;
	private static int index_minFrequency;
	private static int index_firstMinValueChar;
	private static int index_secondMinValueChar;

	public static void main(String[] arg) {
		Scanner scanner = new Scanner(System.in);
		int[] inputArray = new int[26];
		for (int i = 0; i < 26; i++) {
			inputArray[i] = scanner.nextInt();
		}
		scanner.close();
		String result = findString_MinimumKMPandLexicographicallySmallest(inputArray);
		System.out.println(result);
	}

	/**
	 * The method finds the indexes of the lexicographically smallest and second
	 * smallest characters as well as the frequency and index of the character with
	 * the smallest number of occurrence. The corresponding instance variables are
	 * modified accordingly.
	 * 
	 * Since the 26 values in the input array represent the frequency of the
	 * alphabet characters, aligned in lexicographically ascending order, the first
	 * and second non-zero values represent the smallest and second smallest
	 * lexicographical characters.
	 */
	private static void findKeyValuesAndIndexes(int[] inputArray) {

		boolean firstNonZeroValueReached = false;
		boolean secondNonZeroValueReached = false;

		for (int i = 0; i < inputArray.length; i++) {

			if (inputArray[i] != 0) {

				if (inputArray[i] < minFrequency) {
					minFrequency = inputArray[i];
					index_minFrequency = i;
				}

				if (firstNonZeroValueReached == false) {
					index_firstMinValueChar = i;
					firstNonZeroValueReached = true;
				} else if (firstNonZeroValueReached == true && secondNonZeroValueReached == false) {
					index_secondMinValueChar = i;
					secondNonZeroValueReached = true;
				}
			}
		}
	}

	/**
	 * The method finds the string configuration that would have a minimum sum of
	 * the values contained in Longest Prefix Suffix Array of Knuth–Morris–Pratt
	 * Algorithm and has lexicographically smallest value for this minimum sum.
	 * 
	 * The method has the following steps:
	 * 
	 * 1. If minimum frequency is greater than 2 and the character with minimum
	 * frequency has also the smallest lexicographical value, and there are at least
	 * two lexicographically different character sets, then:
	 * 
	 * 1.1 Put two "minimum frequency characters" at the front of the string.
	 * 
	 * 1.2 While there are still remaining "minimum frequency characters",
	 * alternately append one "second smallest character" and one "minimum frequency
	 * character".
	 * 
	 * 1.3 Append the rest of the characters as they occur in the input array.
	 * 
	 * 2. If the conditions in step 1 are not met, then:
	 * 
	 * 2.1 Put one "minimum frequency character" at the front of the string.
	 * 
	 * 2.2 Append the rest of the characters as they occur in the input array.
	 * 
	 * @return A string with minimum sum of the values in Longest Prefix Suffix
	 *         Array and with the smallest lexicographical value for this minimum
	 *         sum.
	 */
	private static String findString_MinimumKMPandLexicographicallySmallest(int[] inputArray) {
		findKeyValuesAndIndexes(inputArray);
		StringBuilder minimum = new StringBuilder();

		if (minFrequency >= 2 && index_minFrequency == index_firstMinValueChar && index_secondMinValueChar != 0) {

			minimum.append((char) (97 + index_minFrequency));
			minimum.append((char) (97 + index_minFrequency));
			inputArray[index_minFrequency] -= 2;

			while (inputArray[index_minFrequency] > 0) {

				minimum.append((char) (97 + index_secondMinValueChar));
				inputArray[index_secondMinValueChar]--;

				minimum.append((char) (97 + index_minFrequency));
				inputArray[index_minFrequency]--;
			}

			for (int i = index_minFrequency; i < inputArray.length; i++) {
				while (inputArray[i] > 0) {
					minimum.append((char) (97 + i));
					inputArray[i]--;
				}
			}
		} else {
			minimum.append((char) (97 + index_minFrequency));
			inputArray[index_minFrequency]--;

			for (int i = 0; i < inputArray.length; i++) {
				while (inputArray[i] != 0) {
					minimum.append((char) (97 + i));
					inputArray[i]--;
				}
			}
		}
		return minimum.toString();
	}
}
