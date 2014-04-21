/**
 * This is a singleton class, you can only have one connection
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

	static Connection connection = null;

	static boolean connected = false;

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

	public static boolean isConnected() {
		return connected;
	}

	public static Connection getConnection() {
		if (!connected) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				String url = "jdbc:mysql://" + server + "/" + schema + "?user="
						+ user + "&password=" + password;
				connection = (Connection) DriverManager.getConnection(url);
				status = "connected";
				connected = true;
			} catch (ClassNotFoundException e) {
				status = e.getMessage();
			} catch (SQLException e) {
				status = e.getMessage();
			} catch (Exception e) {
				status = e.getMessage();
			}
		}
		return connection;
	}
}
