package se315;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class userShops {
	public final static String DB_URL = "jdbc:mysql://localhost:3306/baseBank?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	public final static String USER = "root";
	public final static String PASSWORD = "1625";

	public static void insertShops(String expenseType, String cheapestShop, int value)
			throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		Connection conn = null;
		PreparedStatement stmt = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
		String sql = "INSERT INTO userShops " + "(expenseType,cheapestShop,value)VALUES " + "(?,?,?)";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, expenseType);
		stmt.setString(2, cheapestShop);
		stmt.setInt(3, value);
		stmt.executeUpdate();
		conn.close();
		stmt.close();
	}

	public static void getShop(String expenseType) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		Statement stmt = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
		String sql = "Select cheapestShop,value from userShops where expenseType='" + expenseType + "'";
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		//rs.first();
		//if (rs.next()) {
			while (rs.next()) {
				String cheapestShop = rs.getString("cheapestShop");
				int values = rs.getInt("value");
				System.out.println("You can shop cheapest at " + cheapestShop + " for " + values);
			//}
		}// else {
			//System.out.println(expenseType + " not found");
		//}
	}
}
