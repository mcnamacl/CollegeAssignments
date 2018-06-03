/* SELF ASSESSMENT 

1. ResolveBet

I have correctly defined ResolveBet which takes the bet type (String) and the Wallet object, and a void return type [Mark out of 7: 7].
Comment: yes it is a void return type, it takes the String type and the wallet object.
My program presents the amount of cash in the wallet and asks the user how much he/she would like to bet [Mark out of 8: 8].
Comment: yes that is how my program works, it gives you the amount of cash in the wallet and then asks them how much they would like to bet
My program ensures the bet amount is not greater than the cash in the wallet [Mark out of 5: 5].
Comment: yes before doing so my program will check to make such that there is enough money in the wallet to make the bet that they request
My program creates three Dice objects, rolls them and creates a total variable with a summation of the roll values returned [Mark out of 15: 15]..
Comment: yes there are three dice, there is a total int that keeps track of the total to the three dice
My program determines the winnings by comparing the bet type with the total and comparing the bet type with the dice faces for the triple bet [Mark out of 20: 20].
Comment: yes the program takes the type of the bet and checks to see if the rolls match the criteria of the bet type and if so give you back that amount or else tell you that you 
have lost the amount that you bet
My program outputs the results (win or loss) and adds the winnings to the wallet if user wins or removes the bet amount from the wallet if the user loses [Mark out of 10: 10].
Comment:yes, my program will add the winnings if the outcome matches the criteria of the bet type and add the amount else the amount in the wallet is originalAmount-amountBetted


2. Main

I ask the user for the amount of cash he/she has, create a Wallet object and put this cash into it [Mark out of 15: 15]
Comment:yes, I ask the user how much cash they would like to enter and there is a wallet created to hold that amount of cash
My program loops continuously until the user either enters quit or the cash in the wallet is 0 [Mark out of 5: 5]
Comment: yes, the only way that the program stops is if the user types exit or the wallet is empty
I ask the user to enter any of the four bet types or quit [Mark out of 5: 5].
Comment: yes I do ask to see what type of bet they want to make or if they want to exit
My program calls resolveBet for each bet type entered [Mark out of 5: 5].
Comment: yes that is exactly what my program does until finished = true;
At the end of the game my program presents a summary message regarding winnings and losses [Mark out of 5: 5]
Comment: yes, the program tells you what you initially had in the beginning and what you now have.

 Total Mark out of 100 (Add all the previous marks):
*/

import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		Wallet wallet = new Wallet();
		boolean valid = false;
		Scanner inputType;
		while (!valid) {
			System.out.println("How much money do you want to start with?");
			Scanner input = new Scanner(System.in);
			if (input.hasNextDouble()) {
				valid = true;
				double money = input.nextDouble();
				wallet.put(money);
				boolean finished = false;
				while (!finished && wallet.check() != 0) {
					System.out.println("\nWhat bet would you like to place? The types are:  	 	 \r\n" + 
							"Triple	All 3 dice show same number(but not 1s or 6s). 30:1\r\n" + 
							"Field	Total of 3 dice < 8 or total is > 12.	        1:1\r\n" + 
							"High	Total of 3 dice > 10 (but not a Triple).        1:1\r\n" + 
							"Low	Total of 3 dice < 11 (but not a Triple).	1:1");
					inputType = new Scanner(System.in);
					String type = inputType.nextLine().toLowerCase();
					if (type.equals("exit")) {
						finished = true;
					} else {
						if (type.equals("triple") || type.equals("field") || type.equals("high")
								|| type.equals("low")) {
							placeBet(wallet, type);
						} else {
							System.out.println("Sorry invalid input, please try again by inputting a valid bet type.");
						}
					}
				}
				double difference = wallet.check()-money;
				System.out.println("You initally had " + money + ".\nYou now have: " +  wallet.check() + "\nThe difference is: " + difference);
				input.close();
			} else {
				System.out.println("Sorry invalid input please type in a number.");
			}
		}
	}

	public static boolean hasEnoughMoney(Wallet wallet, double money) {
		return wallet.get(money);
	}

	public static double cashInWallet(Wallet wallet) {
		return wallet.check();
	}

	public static void placeBet(Wallet wallet, String type) {
		double returnedAmount = 0;
		boolean valid = false;
		while (!valid) {
			System.out.println("How much money would you like to bet?" + "\nThis is the amount that you have: "
					+ wallet.toString());
			Scanner inputType = new Scanner(System.in);
			if (inputType.hasNextDouble()) {
				valid = true;
				double amountToBet = inputType.nextDouble();
				if (hasEnoughMoney(wallet, amountToBet)) {
					Dice dieOne = new Dice();
					Dice dieTwo = new Dice();
					Dice dieThree = new Dice();
					int firstRoll = checkDice(dieOne);
					int secondRoll = checkDice(dieTwo);
					int thirdRoll = checkDice(dieThree);
					int total = firstRoll + secondRoll + thirdRoll;
					if (firstRoll == secondRoll && secondRoll == thirdRoll && type.equals("triple") && firstRoll != 6
							&& firstRoll != 1) {
						System.out.println("You won: ");
						System.out.printf("%.2f", amountToBet * 30);
						returnedAmount = amountToBet * 30;
					} else if ((total < 8 || total > 12) && type.equals("field")) {
						System.out.println("You won: ");
						System.out.printf("%.2f", amountToBet);
						returnedAmount = amountToBet * 2;
					} else if ((total > 10) && type.equals("high")) {
						System.out.println("You won: ");
						System.out.printf("%.2f", amountToBet);
						returnedAmount = amountToBet * 2;
					} else if ((total < 11) && type.equals("low")) {
						System.out.println("You won: ");
						System.out.printf("%.2f", amountToBet);
						returnedAmount = amountToBet * 2;
					} else {
						System.out.println("You lost: ");
						System.out.printf("%.2f", amountToBet);
						returnedAmount = 0;
					}
				} else {
					System.out.println("This is beyond the amount that you have, nice try son.");
				}
				wallet.put(returnedAmount);
			} else {
				System.out.println("Sorry invalid input please type in a number.");
			}
		}
	}

	public static int checkDice(Dice dice) {
		dice.roll();
		System.out.println(dice.toString());
		return dice.topFace();
	}
}
