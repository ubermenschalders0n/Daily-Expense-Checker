package se315;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


public class userExpense {
	public final static String DB_URL = "jdbc:mysql://localhost:3306/baseBank?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	public final static String USER = "root";
	public final static String PASSWORD = "1625";

	public static void insertUser(String userName, int value, String expenseType)
			throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		Connection conn = null;
		PreparedStatement stmt = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
		String sql = "INSERT INTO userExpense " + "(userName,value,expenseType)VALUES " + "(?,?,?)";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, userName);
		stmt.setInt(2, value);
		stmt.setString(3, expenseType);
		stmt.executeUpdate();
		conn.close();
		stmt.close();
	}

	public static void updateValue(String expenseType, int value) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
		String sql = "Select value from userExpense where expenseType=?";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, expenseType);
		ResultSet rs = stmt.executeQuery();
		rs.first();
		int oldValue = rs.getInt(1);
		int newValue = oldValue + value;
		String sql2 = "Update userExpense SET value=? where expenseType=?";
		stmt = conn.prepareStatement(sql2);
		stmt.setInt(1, newValue);
		stmt.setString(2, expenseType);
		stmt.executeUpdate();
		conn.close();
		stmt.close();
	}

	public static int getValue(String expenseType) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
		String sql = "Select value from userExpense where expenseType=?";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, expenseType);
		return stmt.executeQuery(sql).getInt(1);

	}

	public static void getSum(String userName) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
		String sql = "select SUM(value),expenseType from userExpense where userName=? group by expenseType";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, userName);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			int value=rs.getInt("sum(value)");
			String expenseType=rs.getString("expenseType");
			System.out.println("You spent "+value+" liras for "+expenseType);
		}
	}
	public static void expenseStatistics(String userName) throws ClassNotFoundException, SQLException {
		int sum = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
		String sql = "select SUM(value),expenseType from userExpense where userName=? group by expenseType";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, userName);
		ResultSet rs = stmt.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnsNumber = rsmd.getColumnCount()+1;
		int[] values=new int[columnsNumber];
		String[] expenses=new String[columnsNumber];
		for(int i=0;i<columnsNumber;i++) {
			if(rs.next()) {
				String expenseType=rs.getString("expenseType");
				int value=rs.getInt("sum(value)");
				sum += value;
				values[i]=value;
				expenses[i]=expenseType;
			}
		}
		System.out.println("You spent " + sum + " liras");
		for(int i=0;i<columnsNumber;i++) {
			if(expenses[i]!=null)
			System.out.println("You spent %" + (values[i] * 100) / sum + " on " + expenses[i]);
		}
	}
	public static void incomeSum(String userName) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
		String sql = "select SUM(value),expenseType from userExpense where userName=? group by expenseType";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, userName);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			int value=rs.getInt("sum(value)");
			String expenseType=rs.getString("expenseType");
			System.out.println("You spent "+value+" liras for "+expenseType);
		}
	}

}

/*
 * public static String getType(String expenseType) throws
 * ClassNotFoundException, SQLException { Connection conn=null; Statement
 * stmt=null; Class.forName("com.mysql.jdbc.Driver"); conn =
 * DriverManager.getConnection(DB_URL, USER, PASSWORD); String
 * sql="Select expenseType from userExpense"; stmt=conn.createStatement();
 * return stmt.executeQuery(sql).getString(1);
 * 
 * }
 */
