/**
 * Simple database helper
 * @author Murilo Quadros
 */

package database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

public class Database {

	private Connection connection;

	private String queryWhere = "WHERE ";
	private String querySelect = "SELECT ";
	private String querySet = "SET ";
	private String queryOrderBy = "ORDER BY ";
	private String queryLimit = "LIMIT ";

	private boolean where = false;
	private boolean select = false;
	private boolean set = false;
	private boolean orderBy = false;
	private boolean limit = false;

	public Database(Connection conn) {
		connection = conn;
	}

	public Integer insert(Map<String, String> data, String table) {
		String query = "INSERT INTO " + table + " ";
		set(data);
		query += getQuery();
		Statement stm = null;
		Integer id = null;
		ResultSet resultSet = null;
		try {
			stm = (Statement) connection.createStatement();
			id = stm.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			resultSet = (ResultSet) stm.getGeneratedKeys();
			while(resultSet.next()){
				id = resultSet.getInt(1);
			}
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		clear();
		return id;
	}

	public void update(Map<String, String> data, String table) {
		String query = "UPDATE " + table + " ";
		set(data);
		query += getQuery();
		Statement stm = null;
		try {
			stm = (Statement) connection.createStatement();
			stm.execute(query);
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		clear();
	}

	public ResultSet get(String table) {
		if (!select) {
			querySelect += "* ";
			select = true;
		}
		querySelect += " FROM " + table;
		Statement stm = null;
		ResultSet resultSet = null;
		try {
			stm = (Statement) connection.createStatement();
			resultSet = (ResultSet) stm.executeQuery(getQuery());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		clear();
		return resultSet;
	}

	public void delete(String table) {
		String query = "DELETE FROM " + table + " ";
		query += getQuery();
		Statement stm = null;
		try {
			stm = (Statement) connection.createStatement();
			stm.execute(query);
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		clear();
	}

	public ResultSet query(String query) {
		Statement stm = null;
		ResultSet resultSet = null;
		try {
			stm = (Statement) connection.createStatement();
			resultSet = (ResultSet) stm.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}
	
	public void select(String data) {
		List<String> list = new ArrayList<String>();
		list.add(data);
		select(list);
	}

	public void select(List<String> data) {
		for (String dt : data) {
			if (!select) {
				querySelect += dt;
				select = true;
			} else {
				querySelect += ", " + dt;
			}
		}
	}

	public void where(Map<String, String> data) {
		where(data, "AND", "=");
	}

	public void where_OR(Map<String, String> data) {
		where(data, "OR", "=");
	}

	public void like(Map<String, String> data) {
		where(data, "AND", "LIKE");
	}

	public void like_OR(Map<String, String> data) {
		where(data, "OR", "LIKE");
	}

	public void where(String key, String value) {
		Map<String, String> data = new HashMap<String, String>();
		data.put(key, value);
		where(data, "AND", "=");
	}

	public void where_OR(String key, String value) {
		Map<String, String> data = new HashMap<String, String>();
		data.put(key, value);
		where(data, "OR", "=");
	}

	public void like(String key, String value) {
		Map<String, String> data = new HashMap<String, String>();
		data.put(key, value);
		where(data, "AND", "LIKE");
	}

	public void like_OR(String key, String value) {
		Map<String, String> data = new HashMap<String, String>();
		data.put(key, value);
		where(data, "OR", "LIKE");
	}

	public void whereInitiateGroup() {
		queryWhere += " ( ";
	}

	public void whereFinishGroup() {
		queryWhere += " ) ";
	}

	public void orderBy(List<String> data) {
		orderBy(data, "ASC");
	}

	public void orderBy(String data) {
		List<String> list = new ArrayList<String>();
		list.add(data);
		orderBy(list, "ASC");
	}

	public void orderByDesc(List<String> data) {
		orderBy(data, "DESC");
	}

	public void orderByDesc(String data) {
		List<String> list = new ArrayList<String>();
		list.add(data);
		orderBy(list, "DESC");
	}

	public void limit(int offset, int quantity) {
		queryLimit = "LIMIT " + offset + ", " + quantity;
		limit = true;
	}

	public void limit(int quantity) {
		limit(0, quantity);
	}

	public String getQuery() {
		String query = "";
		if (set) {
			query += querySet + " ";
		}
		if (select) {
			query += querySelect + " ";
		}
		if (where) {
			query += queryWhere + " ";
		}
		if (orderBy) {
			query += queryOrderBy + " ";
		}
		if (limit) {
			query += queryLimit + " ";
		}
		return query.trim();
	}

	private void where(Map<String, String> data, String type, String operator) {
		Iterator<Map.Entry<String, String>> iterator = data.entrySet()
				.iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, String> mapEntry = iterator.next();
			if (!where) {
				queryWhere += mapEntry.getKey() + " " + operator + " '"
						+ mapEntry.getValue() + "'";
				where = true;
			} else {
				queryWhere += " " + type + " " + mapEntry.getKey() + " "
						+ operator + " '" + mapEntry.getValue() + "'";
			}

		}
	}

	private void set(Map<String, String> data) {
		Iterator<Map.Entry<String, String>> iterator = data.entrySet()
				.iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, String> mapEntry = iterator.next();
			if (!set) {
				querySet += mapEntry.getKey() + " = '" + mapEntry.getValue()
						+ "'";
				set = true;
			} else {
				querySet += ", " + mapEntry.getKey() + " = '"
						+ mapEntry.getValue() + "'";
			}

		}
	}

	private void orderBy(List<String> data, String type) {
		queryOrderBy = "ORDER BY ";
		for (String dt : data) {
			if (!orderBy) {
				queryOrderBy += dt;
				orderBy = true;
			} else {
				queryOrderBy += ", " + dt;
			}
		}
		queryOrderBy += " " + type;
	}

	private void clear() {
		queryWhere = "WHERE ";
		querySelect = "SELECT ";
		querySet = "SET ";
		queryOrderBy = "ORDER BY ";
		queryLimit = "LIMIT ";

		where = false;
		select = false;
		set = false;
		orderBy = false;
		limit = false;
	}

}
