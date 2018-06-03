import java.util.*;

public class Cipher {

	public static final String VALID_CHARACTERS = "abcdefghijklmnopqrstuvwxyz" + " ";

	public static void main(String[] args) {
		boolean finished = false;
		char[] cipher = VALID_CHARACTERS.toCharArray();
		char[] tmp = new char[27];
		System.arraycopy(cipher, 0, tmp, 0, cipher.length);
		while (!finished) {
			System.out.println("What sentence would you like to encrypt? or else type 'Exit'.");
			Scanner input = new Scanner(System.in);
			if (input.hasNext("Exit")) {
				System.out.println("Thank you and goodbye.");
				finished = true;
			} else {
				String userInput = input.nextLine();
				String sentence = userInput.toLowerCase();
				try {
					if (isValidInput(sentence)) {
						char[] characterArray = sentence.toCharArray();
						createCipher(cipher);
						encrypt(cipher, characterArray, tmp);

						String encryptedString = new String(characterArray);
						System.out.println("The encrypted version is: " +encryptedString + ".");

						char[] encrypted = encryptedString.toCharArray();
						decrypt(cipher, encrypted, tmp);
						String decryptedString = new String(encrypted);
						System.out.println("The decrypted version is: " + decryptedString + ".");

					}
				} catch (Exception ArrayIndexOutOfBoundsException) {
					System.out.println("Sorry invalid input. Please try again by inputting either letters or spaces"
							+ " or else typing 'Exit'.");
				}

			}

		}
	}

	public static boolean isValidInput(String input) {
		char[] inputCheck = input.toCharArray();
		char[] validCharacters = VALID_CHARACTERS.toCharArray();
		for (int characterToCheck = 0; characterToCheck < inputCheck.length; characterToCheck++) {
			int lettersInAlphabet = -1;
			do {
				lettersInAlphabet++;
			} while (validCharacters[lettersInAlphabet] != inputCheck[characterToCheck]);
			if (lettersInAlphabet > 27) {
				return false;
			}
		}
		return true;
	}

	public static void createCipher(char[] cipher) {
		Random gettingRandomLetter = new Random();
		for (int amountOfCharacters = cipher.length - 1; amountOfCharacters > 0; amountOfCharacters--) {
			int random = gettingRandomLetter.nextInt(amountOfCharacters);
			int tmpValue = cipher[amountOfCharacters];
			cipher[amountOfCharacters] = cipher[random];
			cipher[random] = (char) tmpValue;
		}
	}

	public static void encrypt(char[] cipher, char[] characterArray, char[] tmp) {
		for (int letter = 0; letter < characterArray.length; letter++) {
			int replacementLetter = 0;
			while (characterArray[letter] != tmp[replacementLetter]) {
				replacementLetter++;
			}
			characterArray[letter] = cipher[replacementLetter];
		}
	}

	public static void decrypt(char[] cipher, char[] encrypted, char[] tmp) {
		for (int letter = 0; letter < encrypted.length; letter++) {
			int replacementLetter = 0;
			while (encrypted[letter] != cipher[replacementLetter]) {
				replacementLetter++;
			}
			encrypted[letter] = tmp[replacementLetter];
		}
	}

}
