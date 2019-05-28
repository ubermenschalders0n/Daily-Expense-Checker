package dummy;

import java.util.Random;
import java.util.Scanner;

import se315.bankUser;
import se315.userCost;
import se315.userShops;

public class fillDataBases {

	public static void main(String[] args) throws Exception {
		Random generator = new Random();
		String[] names = { "Burak", "Ahmet", "Ayse", "Elif", "Baris", "Melike", "Dogukan", "Simin", "Senem" };
		int[] income = { 1000, 2000, 3000, 4000, 1500, 2200, 1700, 2400 };
		String[] password= {"1625","1234","123456","1999bb","gs1999bb","flipper1234","fsociety1111","100110101","8762"};
		String[] expenseType = { "Dairy", "Clothing", "Food", "Technology", "Coffee", "Games" };
		String[] cheapestShops = { "Migros", "Mavi", "Kipa", "Vatan", "Pablo", "Steam" };
		int[] shopValue = { 20, 60, 32, 1199, 9, 45 };
		userCost.insertUser1("Thael",50000,2000,0);
		/*for(int i=0;i<expenseType.length;i++) {
		userShops.insertShops(expenseType[i], cheapestShops[i], shopValue[i]);
		 }
	    for (int i = 0; i < names.length; i++) {
			int randomIndex = generator.nextInt(names.length-1);
				userCost.insertUser(names[i], income[randomIndex]);
		}
		
		
		Scanner input=new Scanner(System.in);
		System.out.println("Enter your expense type to get the best shop available ");
		String bestExpense = input.nextLine();
		userShops.getShop(bestExpense);
		for(int i=0;i<names.length;i++) {
			int randomIndex=generator.nextInt(names.length-1);
			bankUser.insertUser(names[i],password[randomIndex] );
		}
		/*bankUser.insertUser("Thael", "1234");
		System.out.println(bankUser.checkData("qwe","1111"));*/
	}
}
