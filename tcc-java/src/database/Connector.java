/**
 * @author Murilo Quadros
 */

package database;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class Connector {
	static String status = "not connected";
	static String server = "localhost";
	static String schema = "public";
	static String user = "root";
	static String password = "";

	public static void setAuthentication(String username, String pass) {
		user = username;
		password = pass;
	}

	public static void setSchema(String schemaname) {
		schema = schemaname;
	}

	public static void setServer(String servername) {
		server = servername;
	}

	public static String getConnetionStatus() {
		return status;
	}

	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://" + server + "/" + schema + "?user="
					+ user + "&password=" + password;
			con = (Connection) DriverManager.getConnection(url);
			status = "connected";
		} catch (ClassNotFoundException e) {
			status = e.getMessage();
		} catch (SQLException e) {
			status = e.getMessage();
		} catch (Exception e) {
			status = e.getMessage();
		}
		return con;
	}
}
