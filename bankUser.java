package se315;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

public class bankUser {
	public final static String DB_URL = "jdbc:mysql://localhost:3306/baseBank?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	public final static String USER = "root";
	public final static String PASSWORD = "1625";

	public static void insertUser(String userName, String password)
			throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		Connection conn = null;
		PreparedStatement stmt = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
		String sql = "INSERT INTO bankUser " + "(userID,userName,password)VALUES " + "(?,?,?)";
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, createID());
		stmt.setString(2, userName);
		stmt.setBytes(3, encryption.encryptKey(password));
		stmt.executeUpdate();
		conn.close();
		stmt.close();
	}

	public static boolean checkData(String name, String password) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		boolean found = false;
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
		String sql = "SELECT EXISTS(SELECT userName,password from bankUser WHERE userName=? and password=?)";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, name);
		stmt.setBytes(2, encryption.encryptKey(password));
		ResultSet rs = stmt.executeQuery();
		rs.first();
		if (rs.getBoolean(1)) {
			found = true;
		}
		return found;
	}

	public static void updateDate(String userID) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
		String sql = "Update bankUser SET ts=? where userID=?";
		Calendar cal = Calendar.getInstance();
		Timestamp timeStamp = new Timestamp(cal.getTimeInMillis());
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, userID);
		stmt.setTimestamp(2, timeStamp);
		stmt.executeUpdate();
		conn.close();
		stmt.close();
	}

	public static int createID() throws ClassNotFoundException, SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
		String sql = "Select max(userID) from bankUser";
		stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("max(userID)") + 1;
		} else {
			return 1;
		}

	}

	public static int getTime(String userName) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;;
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
		String sql = "select extract(day from ts) from bankUser where userName=?";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, userName);
		ResultSet rs = stmt.executeQuery();
		rs.first();
		return rs.getInt("extract(day from ts)");
	}
	/*
	 * public static String getUserName() throws ClassNotFoundException,
	 * SQLException { Connection conn = null; PreparedStatement stmt = null;
	 * Class.forName("com.mysql.jdbc.Driver"); conn =
	 * DriverManager.getConnection(DB_URL, USER, PASSWORD); String sql =
	 * "Select value from userExpense where expenseType=?"; stmt =
	 * conn.prepareStatement(sql); stmt.setString(1, expenseType); return
	 * stmt.executeQuery(sql).getInt(1);
	 * 
	 * }
	 */
}
