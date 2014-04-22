package vo;

import java.sql.SQLException;

import com.mysql.jdbc.ResultSet;

public class User {

	private String name;

	public User(ResultSet resultSet) throws SQLException {
		name = resultSet.getString("name");
	}

	public String getName() {
		return name;
	}
}
