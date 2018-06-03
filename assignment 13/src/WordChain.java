
/* SELF ASSESSMENT 

1. readDictionary
- I have the correct method definition [Mark out of 5:5]
- Comment: yes, the method is an arraylist;
- My method reads the words from the "words.txt" file. [Mark out of 5:5]
- Comment: yes, it uses String check = bufferedReader.readline() to denote a new word or if check=null, then it ends.
- It returns the contents from "words.txt" in a String array or an ArrayList. [Mark out of 5:5]
- Comment: the contents are returned as an arraylist;

2. readWordList
- I have the correct method definition [Mark out of 5:5]
- Comment: yes, it is an arraylist method;
- My method reads the words provided (which are separated by commas, saves them to an array or ArrayList of String references and returns it. [Mark out of 5:]
- Comment: yes, the delimiter is set as ", " and it saves each word to an arraylist;

3. isUniqueList
- I have the correct method definition [Mark out of 5:5]
- Comment: yes, it is a boolean method;
- My method compares each word in the array with the rest of the words in the list. [Mark out of 5:5]
- Comment: yes the method uses a loop to compare each word with the rest of the words in the list;
- Exits the loop when a non-unique word is found. [Mark out of 5:5]
- Comment: the loop described above will only continue as long as isUnique=true;
- Returns true is all the words are unique and false otherwise. [Mark out of 5:5]
- Comment: as long as all the words are unique true is returned, else false is;

4. isEnglishWord
- I have the correct method definition [Mark out of 5:5]
- Comment: yes, it is a boolean method
- My method uses the binarySearch method in Arrays library class. [Mark out of 3:3]
- Comment: the method uses binarySearch to check the words
- Returns true if the binarySearch method return a value >= 0, otherwise false is returned. [Mark out of 2:2]
- Comment: yes, that is how my method works;

5. isDifferentByOne
- I have the correct method definition [Mark out of 5:5]
- Comment: yes it a boolean method;
- My method loops through the length of a words comparing characters at the same position in both words searching for one difference. [Mark out of 10:10]
- Comment: the method first checks that the words are the same length and only if they are, will it make sure that they are only
		   different by one letter;

6. isWordChain
- I have the correct method definition [Mark out of 5:5]
- Comment: yes, it is a boolean method.
- My method calls isUniqueList, isEnglishWord and isDifferentByOne methods and prints the appropriate message [Mark out of 10:]
- Comment: the method calls each of the the functions.

7. main
- Reads all the words from file words.txt into an array or an ArrayList using the any of the Java.IO classes covered in lectures [Mark out of 10:10]
- Comment: yes, I use the buffered reader method.
- Asks the user for input and calls isWordChain [Mark out of 5:5]
- Comment: the method will continue to ask for an input until a blank string is inputed.

 Total Mark out of 100 (Add all the previous marks):100;
*/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class WordChain {

	public static void main(String[] args) {
		boolean finished = false;
		ArrayList<String> words = new ArrayList<String>();
		Scanner input = new Scanner(System.in);
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader("wordChecker.txt"));
			words = readDictionary(bufferedReader, words);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (!finished) {
			System.out.println(
					"What are the words you would like to input? (separated by commas or an empty list to quit).");
			input = new Scanner(System.in);
			String wordList = input.nextLine();
			if (wordList.length() != 0 && isValid(wordList)) {
				ArrayList<String> wordChain = readWordList(wordList);
				if (isWordChain(wordChain, words)) {
					System.out.println("This is a valid wordchain.");
				} else {
					System.out.println("Uh oh, this is not a valid wordchain.");

				}
			} else if (wordList.length() > 0) {
				System.out.println("Sorry, only letters and commas are valid for this program.");
			} else {
				System.out.println("Goodbye, hope you enjoyed!");
				finished = true;
				input.close();
			}
		}
	}

	public static boolean isValid(String input) {
		return input.matches("[a-z\\s',A-Z]+");
	}

	public static ArrayList<String> readDictionary(BufferedReader bufferedReader, ArrayList<String> words) {
		try {
			String word = bufferedReader.readLine();
			while (word != null) {
				words.add(word);
				word = bufferedReader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return words;
	}

	public static ArrayList<String> readWordList(String unsortedWords) {
		String[] sortedWords = unsortedWords.split(", ");
		List<String> sortedList = Arrays.asList(sortedWords);
		ArrayList<String> wordChain = new ArrayList<String>(sortedList);
		return wordChain;
	}

	public static boolean isUniqueList(List<String> wordChain) {
		boolean isUnique = true;
		int indexOfWord = 0;
		int nextWord = 0;
		while (indexOfWord < wordChain.size() && isUnique == true) {
			String wordToCheck = wordChain.get(indexOfWord);
			nextWord = indexOfWord + 1;
			while (nextWord < wordChain.size()) {
				String wordToCheckAgainst = wordChain.get(nextWord);
				if (wordToCheck.equals(wordToCheckAgainst)) {
					isUnique = false;
				}
				nextWord++;
			}
			indexOfWord++;
		}
		return isUnique;
	}

	public static boolean isEnglishWord(ArrayList<String> wordChain, ArrayList<String> words) {
		boolean result = true;
		int indexInDictionary = 0;
		int indexOfWord = 0;
		Collections.sort(wordChain);
		Collections.sort(words);
		while (indexOfWord < wordChain.size()) {
			indexInDictionary = Collections.binarySearch(words, wordChain.get(indexOfWord));
			if (indexInDictionary < 0) {
				result = false;
			}
			indexOfWord++;
		}
		return result;
	}

	public static boolean isDifferentByOne(ArrayList<String> wordChain) {
		boolean isDifferentByOne = true;
		int indexOfWord = 0;
		while (indexOfWord + 1 < wordChain.size() && isDifferentByOne == true) {
			int count = 0;
			int indexOfLetter = 0;
			String wordToCheck = wordChain.get(indexOfWord);
			wordToCheck = wordToCheck.replaceAll("[^a-z\\sA-Z]", "");
			String wordToCheckAgainst = wordChain.get(indexOfWord + 1);
			wordToCheckAgainst = wordToCheckAgainst.replaceAll("[^a-z\\sA-Z]", "");
			if (wordToCheck.length() == wordToCheckAgainst.length()) {
				while (indexOfLetter < wordToCheck.length() && count < 2) {
					char letterToCheck = wordToCheck.charAt(indexOfLetter);
					char letterToCheckAgainst = wordToCheckAgainst.charAt(indexOfLetter);
					if (letterToCheck != letterToCheckAgainst) {
						count++;
					}
					indexOfLetter++;
					if (count > 1) {
						isDifferentByOne = false;
					}
				}
				indexOfWord++;
			} else {
				isDifferentByOne = false;
			}
		}
		return isDifferentByOne;
	}

	public static boolean isWordChain(ArrayList<String> wordChain, ArrayList<String> words) {
		boolean result = false;
		if (wordChain.size() > 1 && isUniqueList(wordChain) && isDifferentByOne(wordChain)
				&& isEnglishWord(wordChain, words)) {
			result = true;
		}
		return result;
	}
}
