package se315;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;

public class userCost {
	public final static String DB_URL = "jdbc:mysql://localhost:3306/baseBank?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	public final static String USER = "root";
	public final static String PASSWORD = "1625";

	public static void insertUser1(String userName, int monthlyIncome, int upperLimit, int costs)
			throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		Connection conn = null;
		PreparedStatement stmt = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
		String sql = "INSERT INTO userCost " + "(userName,monthlyIncome,upperLimit,costs)VALUES " + "(?,?,?,?)";
		Calendar cal = Calendar.getInstance();
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, userName);
		stmt.setInt(2, monthlyIncome);
		stmt.setInt(3, upperLimit);
		stmt.setInt(4, costs);
		stmt.executeUpdate();
		conn.close();
		stmt.close();
	}

	public static void insertUser(String userName, int monthlyIncome)
			throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		Connection conn = null;
		PreparedStatement stmt = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
		String sql = "INSERT INTO userCost " + "(userName,monthlyIncome)VALUES " + "(?,?)";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, userName);
		stmt.setInt(2, monthlyIncome);
		stmt.executeUpdate();
		conn.close();
		stmt.close();
	}

	public static void printRs() throws SQLException, ClassNotFoundException {
		Connection conn = null;
		Statement stmt = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
		String sql = "Select userName,monthlyIncome,upperLimit,costs,ts from userCost";
		stmt = conn.createStatement();
		ResultSet resultSet = stmt.executeQuery(sql);
		ResultSetMetaData rsmd = resultSet.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		while (resultSet.next()) {
			for (int i = 1; i <= columnsNumber; i++) {
				if (i > 1)
					System.out.print(",  ");
				String columnValue = resultSet.getString(i);
				System.out.print(columnValue + " " + rsmd.getColumnName(i));
			}
			System.out.println("");
		}
		conn.close();
		stmt.close();
		resultSet.close();

	}

	public static void updateUpperLimit(String userName, int upperLimit) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
		String sql = "Update userCost SET upperLimit=? where userName=?";
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, upperLimit);
		stmt.setString(2, userName);
		stmt.executeUpdate();
		conn.close();
		stmt.close();
	}

	public static void updateCost(String userName, int cost) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
		String sql = "Select costs from userCost where userName=?";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, userName);
		ResultSet rs = stmt.executeQuery();
		rs.first();
		int oldCost = 0;
		if (rs.next()) {
			oldCost = rs.getInt("userCost");
		}
		int newCost = oldCost + cost;
		String sql2 = "Update userCost SET costs=? where userName=?";
		stmt = conn.prepareStatement(sql2);
		stmt.setInt(1, newCost);
		stmt.setString(2, userName);
		stmt.executeUpdate();
		conn.close();
		stmt.close();
	}

	public static void updateIncome(String userName, int income) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
		String sql = "Update userCost SET monthlyIncome=? where userName=?";
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, income);
		stmt.setString(2, userName);
		stmt.executeUpdate();
		conn.close();
		stmt.close();
	}

	public static int getIncome(String userName) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs;
		int income = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			String sql = "Select monthlyIncome from userCost where userName=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, userName);
			rs = stmt.executeQuery();
			rs.first();
			income = rs.getInt("monthlyIncome");
			conn.close();
			stmt.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return income;
	}

	public static int getCost(String userName) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int costs = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			String sql = "Select costs from userCost where userName=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, userName);
			ResultSet rs = stmt.executeQuery();
			rs.first();
			costs = rs.getInt("costs");
			conn.close();
			stmt.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return costs;
	}

	public static int getUpperLimit(String userName) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs;
		int upperLimit = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			String sql = "Select upperLimit from userCost where userName=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, userName);
			rs = stmt.executeQuery();
			rs.first();
			upperLimit = rs.getInt("upperLimit");
			conn.close();
			stmt.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return upperLimit;
	}

	public static void incomeStatistics(String userName) {
		try {
			Connection conn = null;
			PreparedStatement stmt = null;
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			int income = getIncome(userName);
			int costs = getCost(userName);
			int incomeLower = income - 1000;
			int incomeGreater = income + 1000;
			String sql = "Select userName,costs from userCost where monthlyIncome>" + incomeLower
					+ " and monthlyIncome<" + incomeGreater;
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			int i = 0;
			while (rs.next()) {
				i++;
				int lessorNot = rs.getInt("costs") - costs;
				if (lessorNot > 0) {
					System.out.println("You spent " + lessorNot + " less than " + i + ". person");
				} else if (lessorNot == 0) {
					System.out.println("You spent the same amount as " + i + ". person");
				} else if (lessorNot < 0) {
					lessorNot *= -1;
					System.out.println("You spent " + lessorNot + " more than " + i + ". person");
				}
			}
			rs.first();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnNumber = rsmd.getColumnCount()+1;
			String[] names = new String[columnNumber];
			i=0;
			while (rs.next()) {
				names[i] = rs.getString("userName");
			}
			ResultSet rs2 = stmt.executeQuery();
			rs2.beforeFirst();
			for (int j = 0; j < names.length; j++) {
				String sql2 = "select sum(value),expenseType from userExpense where userName='" + names[i]
						+ "' group by expenseType,userName";
				stmt = conn.prepareStatement(sql2);
				rs2 = stmt.executeQuery();
			}
			String sql3 = "select sum(value),expenseType from userExpense where userName='" + userName
					+ "' group by expenseType,userName";
			stmt = conn.prepareStatement(sql3);
			ResultSet rs3 = stmt.executeQuery();
			rs3.first();
			ResultSetMetaData rsmd2=rs2.getMetaData();
			ResultSetMetaData rsmd3=rs3.getMetaData();
			/*while (rs2.next()) {
				f++;
				String myValue = rs3.getString("expenseType");
				String theirValue = rs2.getString("expenseType");
				int myCost = rs3.getInt("sum(value)");
				int theirCost = rs2.getInt("sum(value)");
				int difference = theirCost - myCost;
				if (theirValue.equalsIgnoreCase(myValue) && difference > 0) {
					System.out.println("You spent " + difference + " less on " + myValue + " than " + f + ". person");
				} else if (theirValue.equalsIgnoreCase(myValue) && difference < 0) {
					difference *= -1;
					System.out.println("You spent " + difference + " more on " + myValue + " than " + f + ". person");
				}
			}*/
			for(int k=0;k<rsmd3.getColumnCount();k++) {
				int f=0;
				String myValue =rs3.getString("expenseType");
				int myCost = rs3.getInt("sum(value)");
				for(int j=0;j<rsmd2.getColumnCount();j++) {
					f++;
					rs2.first();
					String theirValue = rs2.getString("expenseType");
					int theirCost = rs2.getInt("sum(value)");
					int difference = theirCost - myCost;
					if (theirValue.equalsIgnoreCase(myValue) && difference > 0) {
						System.out.println("You spent " + difference + " less on " + myValue + " than " + f + ". person");
					} else if (theirValue.equalsIgnoreCase(myValue) && difference < 0) {
						difference *= -1;
						System.out.println("You spent " + difference + " more on " + myValue + " than " + f + ". person");
					}
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// public static void selectName(String userID)
}
