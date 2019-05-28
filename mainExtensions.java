package se315;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class mainExtensions {
	/*public static void expenseDetails(String userName) throws ClassNotFoundException, SQLException {
		ResultSet rs = userExpense.getSum(userName);
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		while (rs.next()) {
			int value=rs.getInt("value");
			String expenseType=rs.getString("expenseType");
			for (int i = 1; i < columnsNumber; i++) {
				System.out.println("Spent " + value + " liras for " + expenseType);
			}
		}
	}

	public static void expenseStatistics(String userName) throws ClassNotFoundException, SQLException {
		int sum = 0;
		ResultSet rs = userExpense.getSum(userName);
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		while (rs.next()) {
			for (int i = 1; i < columnsNumber; i++) {
				sum += rs.getInt(i);
			}
			System.out.println("You spent " + sum + " liras");
			for (int i = 1; i < columnsNumber; i++) {
				System.out.println("You spent %" + (rs.getInt(i) * 100) / sum + " on " + rs.getString(i));
			}
		}
	}*/
	//public static void nearWage

	public static void menu() {
		System.out.println("Press 1 to enter your daily expenses");
		System.out.println("To find the best deal for an expense type press 2");
		System.out.println("To update your upper limit please press 3");
		System.out.println("To get your expense details press 4");
		System.out.println("To get your expense statistics press 5");
		System.out.println("To update your monthly income press 6");
		System.out.println("To get statistics near your monthly income press 7");
		System.out.println("To log out press 8");
	}
	public static void expenseType() {
		System.out.println("Expense types are Clothing,Coffee,Dairy,Food,Games,Technology or enter a new type");
	}
}
