package vo.facade;

import java.sql.SQLException;

import com.mysql.jdbc.ResultSet;

public class User {

	private Integer id;
	private String name;

	public User(ResultSet resultSet) throws SQLException {
		id = resultSet.getInt("id");
		name = resultSet.getString("name");
	}

	public String getName() {
		return name;
	}

	public Integer getId() {
		return id;
	}

}
