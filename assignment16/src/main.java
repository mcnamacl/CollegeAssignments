/* SELF ASSESSMENT

Harness Class: Member variables (8 marks) 8
All my data members are declared, private and the ones that don't change are marked as private. I also have a constant for the maximum number of uses of a harness.
Comment: yes all member variables are declared and marked private, I also have MAX_NUMBER_OF_TIMES set to 25

Harness Class: Harness constructor 1 & constructor 2 (6 marks) 6
I initialise all the variables using the parameters given and set the other members to reasonable default values.
Comment: yes all variables are initalised using this. or set to a resonable default value

Harness Class: checkHarness method (5 marks) 5
My method takes an instructor's name as a parameter, and if the harness is not on loan sets the instructor member variable to the given parameter value (assuming the instructor's name is not null/empty). It also resets the number of times the harness was used.
Comment: yes that is how my method works.

Harness Class: isHarnessOnLoan method (2 marks) 2
My method has no parameters and returns the value of the loan status variable.
Comment: it returns boolean 

Harness Class: canHarnessBeLoaned method (4 marks) 4
My method has no parameters and returns true if the harness is not on loan and if the number of times it was used is less than the maximum allowed number of times.
Comment: returns true only if !isHarnessOnLoan && noOfTimes < MAX_NUMBER_OF_TIMES

Harness Class: loanHarness method (6 marks) 6
My method has a member name as a parameter and it checks if harness can be loaned by using the canHarnessBeLoaned method. If true, it sets the club member value to the parameter value, sets the on loan status to true and increments the number of times used variable.
Comment: yes that is how my method works
 
Harness Class: returnHarness method (5 marks) 5
My method has no parameters, checks if the harness is on loan, and if so, changes its on-loan status to false, and resets the club member value.
Comment: yes my returnHarness method works identical to that.

Harness Class: toString method (3 marks) 3
My method returns a String representation of all the member variables.
Comment: yes, I use the toString generator

HarnessRecords Class: member variables (3 marks) 3
I declare the member variable as a private collection of Harnesses 
Comment:yes everyting is set as private

HarnessRecords Class: HarnessRecords constructor 1 & 2 (9 marks) 9
In the first constructor, I set the member variable to null [1 mark ]. In the second constructor, I use the Java I/O to read data from a text file I created containing sets of Harness characteristics. I use these set of characteristics to create Harness objects and add them to the collection. 
Comment: yes that is how my constructors work.

HarnessRecords Class: isEmpty method (1 marks) 1
I return true if the collection is null/empty and, false otherwise.
Comment: it returns a boolean

HarnessRecords Class: addHarness method (5 marks) 5
My method takes a Harness object as a parameter and adds the harness to the collection.
Comment: yes I use the harnessRecords.add() 

HarnessRecords Class: findHarness method (6 marks) 6
My method takes a make and model number as parameters. It checks if a harness with such properties exists and if it does it returns harness object, otherwise returns null.
Comment: that is how my method works

HarnessRecords Class: checkHarness method (6 marks) 6
must take instructor name, make and model number as parameters and return a Harness. If a harness with the make and model number exists by using the findHarness method and is not on loan, the Harness method checkHarness is called with the instructor name as a parameter and the updated Harness object is returned. If the harness is not available returns null.
Comment: that is how my checkHarness method works

HarnessRecords Class: loanHarness method (7 marks) 7
My method takes a club member name as a parameter and looks for an available harness by calling the method canHarnessBeLoaned be loaned. If an available harness is found it is loaned by using the Harness method loanHarness with the club member as a parameter, returning the harness. If there's no available harness null is returned.
Comment: my method works as described 

HarnessRecords Class: returnHarness method (7 marks) 7
My method takes a make and model number as parameters. It checks if a harness with those properties exists by using the findHarness method. If the found harness is not null, it returns the harness object by using Harness method returnHarness, otherwise returns null.
Comment: yes that is how my returnHarness method works (should also check if it actually loaned out or not first but have accounted for that in my main)

HarnessRecords Class: removeHarness method (8 marks) 8
My method takes a make and model number as parameters and check the collection for a harness with those properties and removes it. It returns the harness object if it is found, otherwise returns null.
Comment: yup that is how my method works

GUI (Java main line) (5 marks) 5
My test class has a menu which implements at least the five points specified using the HarnessRecords class methods.
Comment: my main line is functional as an implementation of the two classes
*/

import java.util.Scanner;

public class main {

	public static final int MAKE = 0;
	public static final int MODEL_NUMBER = 1;
	public static final int INSTRUCTOR = 2;

	public static void main(String[] args) {
		System.out.println(
				"What operation would you like to preform? Would you like to add a new harness, remove a harness, "
						+ "record a checked harness, loan a harness, return a harness or see all harnesses in the system?");
		Scanner input = new Scanner(System.in);
		String userInput = input.nextLine().toLowerCase();
		boolean finished = false, valid = false;
		HarnessRecords harnessRecords = new HarnessRecords();
		while (!finished) {
			while (!valid) {
				if (userInput.equals("add a new harness") || userInput.equals("remove a harness")
						|| userInput.equals("record a checked harness") || userInput.equals("loan a harness")
						|| userInput.equals("return a harness")) {
					valid = true;

				} else if (userInput.equals("exit")) {
					valid = true;
				} else {
					System.out.println("Sorry invalid input, please try again or else type 'exit'");
					input = new Scanner(System.in);
					userInput = input.nextLine();
				}
			}
			if (userInput.equals("add a new harness")) {
				addHarness(harnessRecords);
			} else if (userInput.equals("remove a harness")) {
				removeHarness(harnessRecords);
			} else if (userInput.equals("record a checked harness")) {
				recordHarness(harnessRecords);
				System.out.println("The current harnesses are: \n");
			} else if (userInput.equals("loan a harness")) {
				loanHarness(harnessRecords);
			} else if (userInput.equals("return a harness")) {
				returnHarness(harnessRecords);
			} else if (userInput.equals("see all harnesses")) {
				System.out.println("The current harnesses in the system are: ");
				for (Harness harness : harnessRecords.getHarnesses()) {
					System.out.println(harness.toString());
				}
			} else if (userInput.equals("exit")) {
				finished = true;
				break;
			} else {
				System.out.println("Sorry very invalid input no can do buddy.");
			}
			valid = false;
			System.out.println("Would you like to change task?");
			Scanner userInputTwo = new Scanner(System.in);
			String taskChange = userInputTwo.nextLine().toLowerCase();
			if (taskChange.equals("yes")) {
				valid = true;
				System.out.println("what task would you like to do next? or else type exit.");
				input = new Scanner(System.in);
				userInput = input.nextLine();
				if (userInput.equals("exit")) {
					valid = true;
					finished = true;
				}
			}
		}
	}

	public static boolean isValidModelNumber(String input) {
		return input.matches("[0-9]+");
	}

	public static void addHarness(HarnessRecords harnessRecords) {
		String[] harnessData = null;
		long modelNumber = 0;
		System.out.println(
				"Please enter the make, model number, and name of the instructor who checked the harness separated by commas.");
		boolean valid = false;
		while (!valid) {
			Scanner input = new Scanner(System.in);
			String userInput = input.nextLine();
			harnessData = userInput.split(",");
			if (harnessData.length != 3) {
				System.out.println(
						"Sorry, invalid amount of inputs, please try again with three inputs separated by commas.");
			} else {
				valid = true;
			}
		}
		String make = harnessData[MAKE];
		String check = harnessData[MODEL_NUMBER];
		if (isValidModelNumber(check)) {
			modelNumber = Long.parseLong(harnessData[MODEL_NUMBER]);
			String instructor = harnessData[INSTRUCTOR];
			Harness harness = new Harness(make, modelNumber, instructor);
			harnessRecords.addHarness(harness);
		} else {
			System.out.println("Sorry that is not a valid model number. Harness not added to system :( oooopss");
		}
	}

	public static void removeHarness(HarnessRecords harnessRecords) {
		String[] harnessData = null;
		long modelNumber = 0;
		System.out.println("Please enter the make and model number separated by commas.");
		boolean valid = false;
		while (!valid) {
			Scanner input = new Scanner(System.in);
			String userInput = input.nextLine();
			harnessData = userInput.split(",");
			if (harnessData.length != 2) {
				System.out.println(
						"Sorry, invalid amount of inputs, please try again with two inputs separated by commas.");
			} else {
				valid = true;
			}
		}
		String make = harnessData[MAKE];
		String check = harnessData[MODEL_NUMBER];
		if (isValidModelNumber(check)) {
			modelNumber = Long.parseLong(harnessData[MODEL_NUMBER]);
		}
		harnessRecords.removeHarness(make, modelNumber);
	}

	public static void recordHarness(HarnessRecords harnessRecords) {
		String[] harnessData = null;
		boolean finished = false;
		long modelNumber = 0;
		while (!finished) {
			System.out.println(
					"Please enter the make, model number, and name of the instructor who checked the harness separated by commas.");
			boolean valid = false;
			while (!valid) {
				Scanner input = new Scanner(System.in);
				String userInput = input.nextLine();
				harnessData = userInput.split(",");
				if (harnessData.length != 3) {
					System.out.println(
							"Sorry, invalid amount of inputs, please try again with three inputs separated by commas.");
				} else {
					valid = true;
				}
			}
			String make = harnessData[MAKE];
			String check = harnessData[MODEL_NUMBER];
			if (isValidModelNumber(check)) {
				modelNumber = Long.parseLong(harnessData[MODEL_NUMBER]);
			}
			String instructor = harnessData[INSTRUCTOR];
			Harness harness = harnessRecords.checkHarness(instructor, make, modelNumber);
			if (harness == null) {
				System.out.println(
						"Sorry that harness is not in the system. Please retry. Here is a list of all harnesses in the System:");
				for (Harness harnessDisplay : harnessRecords.getHarnesses()) {
					System.out.println(harnessDisplay.toString());
				}
			} else {
				finished = true;
			}
		}
	}

	public static void loanHarness(HarnessRecords harnessRecords) {
		System.out.println("Please enter the name of the borrower.");
		Scanner input = new Scanner(System.in);
		String borrowerName = input.nextLine();
		harnessRecords.loanHarness(borrowerName);
	}

	public static void returnHarness(HarnessRecords harnessRecords) {
		String[] harnessData = null;
		long modelNumber = 0;
		Harness harness = null;
		System.out.println("Please enter the make and model number separated by commas.");
		boolean valid = false;
		while (!valid) {
			Scanner input = new Scanner(System.in);
			String userInput = input.nextLine();
			harnessData = userInput.split(",");
			if (harnessData.length != 2) {
				System.out.println(
						"Sorry, invalid amount of inputs, please try again with two inputs separated by commas.");
			} else {
				valid = true;
				String make = harnessData[MAKE];
				String check = harnessData[MODEL_NUMBER];
				valid = false;
				if (isValidModelNumber(check)) {
					modelNumber = Long.parseLong(harnessData[MODEL_NUMBER]);
					valid = true;
					harness = harnessRecords.findHarness(make, modelNumber);
					if (harness != null && !harness.canHarnessBeLoaned()) {
						harnessRecords.returnHarness(make, modelNumber);
					} else {
						System.out.println("Um, this harness is not on loan...");
					}
				} else {
					System.out.println("Sorry that is not a valid model number, please try again.");
				}
			}
		}
		if (harness == null) {
			System.out.println("Oops, looks like that harness is not in the system :(");
		}
	}
}
