package se315;

import java.util.Calendar;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {
		String trueUser="not found", truePassword = "not found";
		boolean loginTrue = false;
		Calendar cal = Calendar.getInstance();
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to PRO-315 please enter your credentials or create new account ");
		while(loginTrue==false) {
			System.out.println("Type login to log on to system or register to create new account");
			String ifStatement = input.nextLine();
			ifStatement.toLowerCase();
				if (ifStatement.equals("login")) {
					System.out.print("Username: ");
					String userName = input.nextLine();
					trueUser = userName;
					System.out.print("Password: ");
					String password = input.nextLine();
					truePassword = password;
				} else if (ifStatement.equals("register")) {
					System.out.println("Enter your userName");
					System.out.print("Username: ");
					String userName = input.nextLine();
					trueUser = userName;
					System.out.println("Enter your password");
					System.out.print("Password: ");
					String password = input.nextLine();
					truePassword = password;
					System.out.println("Please enter your monthly income");
					int income=input.nextInt();
					System.out.println("Please enter your upper limit");
					input.nextLine();
					int upperLimit=input.nextInt();
					bankUser.insertUser(userName, password);
					userCost.insertUser1(trueUser, income, upperLimit,0);
				} 
				else {
					System.out.println("Wrong input try again");
					loginTrue=false;
				}
				if (bankUser.checkData(trueUser, truePassword)) {
					loginTrue = true;
					System.out.println("Welcome " + trueUser);
					int timeNow = cal.get(Calendar.DAY_OF_MONTH);
					int timeDifference = timeNow - bankUser.getTime(trueUser);
					System.out.println("You have been offline for " + timeDifference + " days");
					break;
				} else {
					System.out.println("Your credentials are wrong please check or create new account");
					loginTrue=false;
					return;
				}
		}
		while (loginTrue) {
			mainExtensions.menu();
			int select = input.nextInt();
			switch (select) {
			case 1:
				System.out.println("Enter your expenses to be stored today");
				System.out.print("Please enter your expense value ");
				int cost=input.nextInt();
				input.nextLine();
				System.out.print("Please enter your expense type ");
				mainExtensions.expenseType();
				String expenseType = input.nextLine();
				if((userCost.getCost(trueUser)+cost)>userCost.getUpperLimit(trueUser)) {
					System.out.println("You have violated your upper limit you can't spend more aborting transaction");
					System.out.println("If you wish to update your upper limit press 3");
					break;
				}
				userExpense.insertUser(trueUser, cost, expenseType);
				userCost.updateCost(trueUser, cost);
				break;
			case 2:
				System.out.println("Enter your expense type to get the best shop available ");
				mainExtensions.expenseType();
				input.nextLine();
				String bestExpense = input.nextLine();
				userShops.getShop(bestExpense);
				break;
			case 3:
				System.out.println("Please enter your new upper limit for expenses");
				int upperLimit = input.nextInt();
				userCost.updateUpperLimit(trueUser, upperLimit);
				System.out.println("Upper limit updated to " + upperLimit);
				break;
			case 4:
				userCost.getCost(trueUser);
				userExpense.getSum(trueUser);
				break;
			case 5:
				userExpense.expenseStatistics(trueUser);
				break;
			case 6:
				System.out.println("Enter your monthly income to update your income");
				input.nextLine();
				int income=input.nextInt();
				userCost.updateIncome(trueUser, income);
				System.out.println("Your new monthly income is ");
				break;
			case 7:
				userCost.incomeStatistics(trueUser);
				break;
			case 8:
				loginTrue = false;
				System.out.println("See you around");
				input.close();
				break;
			}
		}
		
	}

}
