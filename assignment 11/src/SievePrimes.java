import java.util.Scanner;

/*SELF ASSESSMENT 
   1. createSequence:
Did I use the correct method definition?
Mark out of 5:5
Comment: yes I did.
Did I create an array of size n (passed as the parameter) and initialise it?
Mark out of 5: 5
Comment: yes that is what happens
Did I return the correct item?
Mark out of 5: 5
Comment: the array is updated.
   2. crossOutMultiples
Did I use the correct method definition?
Mark out of 5: 5
Comment: yes I did
Did I ensure the parameters are not null and one of them is a valid index into the array
Mark out of 2: 2
Comment: none of the parametres are null
Did I loop through the array using the correct multiple?
Mark out of 5: 5
Comment: yes that is what happens
Did I cross out correct items in the array that were not already crossed out?
Mark out of 3: 3
Comment: yes that is what the array does
   3. sieve   
Did I have the correct function definition?
Mark out of 5: 5
Comment: yes I believe so
Did I make calls to other methods?
Mark out of 5: 5
Comment: yes the array makes calls to other methods    
Did I return an array with all non-prime numbers are crossed out?
Mark out of 2: 2
Comment: at the end yes
   4. sequenceTostring  
Did I have the correct function definition?
Mark out of 5: 5
Comment: yes I believe so 
Did I ensure the parameter to be used is not null?
Mark out of 3: 3
Comment: no parametre is null
Did I Loop through the array updating the String variable with the non-crossed out numbers and the crossed numbers in brackets? 
Mark out of 10: 10
Comment: yes that is what the function does
   5. nonCrossedOutSubseqToString  
Did I have the correct function definition
Mark out of 5: 5
Comment:        I believe so 
Did I ensure the parameter to be used is not null?  
Mark out of 3: 3
Comment: yes no parametre is null
Did I loop through the array updating the String variable with just the non-crossed out numbers? 
Mark out of 5: 5
Comment: yes that is all that it prints
   6. main  
Did I ask  the user for input n and handles input errors?  
Mark out of 5: 5
Comments: yes it does
Did I make calls to other methods (at least one)?
Mark out of 5: 5
Comment:  yes it does
Did I print the output as shown in the question?  
Mark out of 5: 5
Comment:  yes that the output
   7. Overall
Is my code indented correctly?
Mark out of 4: 4
Comments: yes it is 
Do my variable names make sense?
Mark out of 4: 4
Comments: yes they do
Do my variable names, method names and class name follow the Java coding standard
Mark out of 4: 4
Comments: yes they do 
      Total Mark out of 100 (Add all the previous marks): 100 */

public class SievePrimes {

	public static void main(String[] args) {
		System.out.println("What is the number you wish to enter?");
		Scanner input = new Scanner(System.in);
		if (input.hasNextInt()) {
			int userInput = input.nextInt();
			if (userInput > 2) {
				boolean[] trueFalseArray = new boolean[userInput - 1];
				int[] numbers = new int[userInput - 1];
				int[] divisorCount = { 2 };
				createSequence(trueFalseArray, userInput, numbers);
				sieve(divisorCount, trueFalseArray, numbers, userInput);
				nonCrossedOutSubseqToString(numbers, trueFalseArray);
			}
			else {
				System.out.println("Sorry the number you have entered is less than 2,"
						+ " please try again by inputting a number greater than 2.");
			}
		}
		else {
			System.out.println("Sorry invalid input please try again by inputting a number greater than 2.");
		}
		input.close();
	}

	//fills boolean array with true and fills int array with numbers from 2 - userInput
	public static void createSequence(boolean[] trueFalseArray, int userInput, int[] numbers) {
		for (int filler = 0; filler < trueFalseArray.length; filler++) {
			trueFalseArray[filler] = true;
			numbers[filler] = filler + 2;
			if (filler>0) {
				System.out.print(", ");
			}
			System.out.print(numbers[filler]);
		}
	}

	//sets boolean array as false whenever the corresponding number in the int array != prime
	public static void crossOutHigherMultiples(boolean[] trueFalseArray, int[] numbers, int[] divisorCount) {
		int divisor = divisorCount[0];
		int arrayCounter = divisor - 2;
		for (int counter = divisor; counter <= numbers[numbers.length-1]; counter++) {
			if (numbers[arrayCounter] % divisor == 0 && numbers[arrayCounter] != divisor) {
				trueFalseArray[arrayCounter] = false;
			}
			arrayCounter++;
		}
	}

	//runs previous function until it has sieved out all not prime numbers from int array
	public static void sieve(int[] divisorCount, boolean[] trueFalseArray, int[] numbers, int userInput) {
		int checkerForSquareRoot = 0;
		while (divisorCount[0] < userInput && checkerForSquareRoot < Math.sqrt(numbers[numbers.length - 1])) {
			int check = 0;
			while (trueFalseArray[divisorCount[0] - 2] != true) {
				divisorCount[0]++;
				check++;
			}
			if (trueFalseArray[divisorCount[0] - 2] == true) {
				checkerForSquareRoot = numbers[divisorCount[0] - 2] + 1;
			}
			crossOutHigherMultiples(trueFalseArray, numbers, divisorCount);
			sequenceToString(trueFalseArray, numbers);
			if (check == 0) {
				divisorCount[0]++;
			}
		}
	}

	//takes int array and prints it 
	public static void sequenceToString(boolean[] trueFalseArray, int[] numbers) {
		for (int counter = 0; counter < trueFalseArray.length; counter++) {
			if (counter > 0&&counter!=trueFalseArray.length) {
				System.out.print(", ");
				if (trueFalseArray[counter] != true) {
					System.out.print("[" + numbers[counter] + "]");
				} else {
					System.out.print(numbers[counter]);
				}
			} else {
				System.out.print("\n" + numbers[counter]);
			}
		}
	}

	//prints prime numbers
	public static void nonCrossedOutSubseqToString(int[] numbers, boolean[] trueFalseArray) {
		System.out.print("\nThe Primes are: ");
		for (int counter = 0; counter < numbers.length; counter++) {
			if (trueFalseArray[counter] == true) {
				if (counter > 0) {
					System.out.print(", ");
				}
				System.out.print(numbers[counter]);
			}
		}
		System.out.print(".");
	}
} 
