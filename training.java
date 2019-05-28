package dummy;

import java.util.Calendar;
import java.util.Scanner;

public class training {

	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		System.out.println("Enter your expenses to be stored today");
		System.out.print("Please enter your expense type ");
		int x=input.nextInt();
		Calendar cal = Calendar.getInstance();
		System.out.println(cal.get(Calendar.DAY_OF_MONTH));
		

	}

}
