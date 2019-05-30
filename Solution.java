import java.util.Scanner;

public class Solution {
	private static int minFrequency = Integer.MAX_VALUE;
	private static int index_minFrequency;
	private static int index_firstMinValueChar;
	private static int index_secondMinValueChar;
	/**
	 * Decimal value of character "z" as per ASCII Table.
	 */
	private static int first_minValueChar = 122;
	private static int second_minValueChar = 122;

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
	 * The method finds the values/indexes of the lexicographically smallest and
	 * second smallest characters as well as the frequency/index of the character
	 * with the smallest number of occurrence.
	 */
	private static void findKeyValuesAndIndexes(int[] inputArray) {

		for (int i = 0; i < inputArray.length; i++) {

			if (inputArray[i] != 0) {

				if (inputArray[i] < minFrequency) {
					minFrequency = inputArray[i];
					index_minFrequency = i;
				}
				if (97 + i <= first_minValueChar) {
					first_minValueChar = 97 + i;
					index_firstMinValueChar = i;
				}
				if (97 + i > first_minValueChar && 97 + i <= second_minValueChar) {
					second_minValueChar = 97 + i;
					index_secondMinValueChar = i;
				}
			}
		}
	}

	/**
	 * The method finds the string configuration that would have a minimum sum of
	 * the values contained in Longest Prefix Suffix Array of Knuth–Morris–Pratt
	 * Algorithm.
	 * 
	 * If more than one string configuration has this minimum sum, then the string
	 * with the smallest lexicographical value is returned.
	 * 
	 * @return A string corresponding to the above-mentioned conditions.
	 */
	private static String findString_MinimumKMPandLexicographicallySmallest(int[] inputArray) {
		findKeyValuesAndIndexes(inputArray);
		StringBuilder minimum = new StringBuilder();

		if (minFrequency >= 2 && index_minFrequency == index_firstMinValueChar) {
			minimum.append((char) (97 + index_minFrequency));
			minimum.append((char) (97 + index_minFrequency));
			inputArray[index_minFrequency] -= 2;

			while (inputArray[index_secondMinValueChar] > 0 && inputArray[index_minFrequency] > 0) {

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
