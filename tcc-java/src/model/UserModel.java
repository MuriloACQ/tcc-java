package model;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.mysql.jdbc.ResultSet;

import vo.Auth;
import vo.User;
import database.Connector;
import database.Database;

public class UserModel {

	private final String USER_TABLE = "user";

	private Database db;

	public UserModel() {
		db = new Database(Connector.getConnection());
	}

	public User getUser(Auth auth) throws SQLException {
		Map<String, String> data = new HashMap<String, String>();
		data.put("username", auth.getUsername());
		data.put("password", auth.getPassword());
		db.where(data);
		ResultSet resultSet = db.get(USER_TABLE);
		if (resultSet != null && resultSet.next()) {
			return new User(resultSet);
		}
		return null;
	}

}
