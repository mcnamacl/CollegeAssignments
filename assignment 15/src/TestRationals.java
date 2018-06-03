import java.util.Scanner;

/* SELF ASSESSMENT 

Class Rational 
I declared two member variables: numerator and denominator (marks out of 4: 4).
Comment: yes there are  two private variables declared at the start of the class;

Constructor 1 
My program takes take two integers as parameters (for numerator and denominator) and initialises the member variables with the corresponding values . If the denominator is equal to 0 I throw an exception (marks out of 5: 5).
Comment: yes there are two parametres, numerator and denominator and they get initialised in using this. 

Constructor 2 
My program takes only one integer as parameter (numerator), and set the numerator to this value . I set the denominator to 1 in this case, as the resulting rational number in this case is an integer (marks out of 3: ).
Comment: If there is only a numerator then the denominator is set as 1 and the numerator is initalised using this

Add Method 
My program takes only a rational number as a parameter and returns a new rational number which has a numerator and denominator which the addition of the two objects - this and the parameter. My program does not overwrite any of the other two rational numbers (marks out of 8: 8).
Comment: yes, I use first.add(second) to preform this and get the total which is returned as a rational. There is a new Rational object created to hold the total so neither of the inputs are overwritten.

Subtract Method 
I have implemented this the same as add method, except it implements subtraction (marks out of 8: 8).
Comment: my subtract method works the same as my add method except the two fractions get subtracted from one another

Multiply Method 
I have implemented this the same as add method, except it implements multiplication (marks out of 8: 8).
Comment: my multiply method works the same as my add method except the two fractions get multiplied from one another.

Divide Method 
I have implemented this the same as add method, except it implements divide (marks out of 8: ).
Comment: my multiply method works the same as my add method except the two fractions get divide from one another.

Equals Method 
My program takes a rational number as a parameter and compares it to the reference object. I only use multiplication between numerators/denominators for the purpose of comparison, as integer division will lead to incorrect results. I return a boolean value ((marks out of 8: 8).
Comment: yes, the function only uses multiplication to determine if they are equal by multiplying numerator by the others denominator and vice versa

isLessThan 
My program takes a rational number as a parameter and compares it to the reference object. I only use multiplication as integer division will lead to incorrect results. I return a boolean value (marks out of 8: ).
Comment: my isLessThan function works the same as my equals method except instead of == I use <

Simplify Method 
My program returns a rational number but not a new rational number, instead it returns the current reference which is this. It doesn't take any parameters as it works only with the reference object. I first find the greatest common divisor (GCD) between the numerator and denominator, and then obtain the new numerator and denominator by dividing to the GCD (marks out of 8: 8).
Comment: my rational number is not a new rational but it is the simplified version of the original fraction

gcd function 
My program returns the greatest common divider of two integers: the numerator and the denominator (marks out of 6: 6).
Comment: yes, I use the BigInteger function, gcd, in order to work out the common divider

toString Method 
My program returns a string showing the fraction representation of the number, eg. "1/2". It takes no parameters (marks out of 4: 4).
Comment: yes, that is how my string method works

Test Client Class 
My program asks the user for two rational numbers, creates two rational objects using the constructor and passing in the provided values, calls addition, subtraction, multiplication, division, comparison and simplification and prints out the results (marks out of 22: 22).
Comment: yes, my main makes two different rational numbers from the input given and outputs all the results.
*/

public class TestRationals {

	public static void main(String[] args) {
		boolean finished = false, valid = false;
		String check, otherCheck;
		Rational first, second;
		Scanner input = new Scanner("");
		while (!finished) {
			while (!valid) {
				System.out.println("What are the two rational numbers that you would you like to input?");
				input = new Scanner(System.in);
				if (input.hasNext("Exit") || input.hasNext("exit")) {
					System.out.println("Goodbye.");
					finished = true;
					valid = true;
				} else {
					input.useDelimiter("\\s");
					check = input.next();
					otherCheck = input.next();
					if (isValid(check) && isValid(otherCheck)) {
						valid = true;
						first = inputs(check);
						check = input.nextLine();
						valid = false;
						second = inputs(otherCheck);
						Rational total;
						total = (second.add(first));
						total = total.simplify();
						System.out.println(first.toString() + " + " + second.toString() + " = " + total.toString());

						total = second.multiply(first);
						total = total.simplify();
						System.out.println(first.toString() + " * " + second.toString() + " = " + total.toString());

						total = second.divide(first);
						total = total.simplify();
						System.out.println(first.toString() + " / " + second.toString() + " = " + total.toString());

						if (second.equals(first)) {
							System.out.println(first.toString() + " - " + second.toString() + " = 0");
							System.out.println("They are equal.");
						} else {
							total = second.subtract(first);
							total = total.simplify();
							System.out.println(first.toString() + " - " + second.toString() + " = " + total.toString());

							if (second.isLessThan(first)) {
								first = first.simplify();
								System.out.println("The lower fraction is : " + first.toString());
							} else {
								second = second.simplify();
								System.out.println("The lower fraction is : " + second.toString());
							}
						}
					} else {
						System.out.println("Sorry invalid input please try again.");
					}
				}
			}
			input.close();
		}
	}

	public static boolean isValid(String input) {
		return input.matches("[\\s\\d/-]+");
	}

	public static Rational inputs(String check) {
		Rational rational;
		if (check.indexOf('/') > -1) {
			String parts[] = check.split("/");
			String partOne = parts[0];
			int numerator = Integer.parseInt(partOne);
			String partTwo = parts[1];
			int denominator = Integer.parseInt(partTwo);
			if (denominator < 0) {
				numerator = -numerator;
				denominator = -denominator;
			}
			return rational = new Rational(numerator, denominator);
		} else {
			int firstNumerator = Integer.parseInt(check);
			return rational = new Rational(firstNumerator);
		}
	}

}
