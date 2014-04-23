package model;

import java.util.HashMap;
import java.util.Map;

import vo.User;
import database.Connector;
import database.Database;
import facade.Measure;

public class MeasureModel {

	private final String MEASURE_TABLE = "measure";

	private Database db;

	public MeasureModel() {
		db = new Database(Connector.getConnection());
	}

	public void insert(Measure measure, User user) {
		Map<String, String> data = new HashMap<String, String>();
		data.put("device", measure.getDevice().getId());
		data.put("user", user.getId().toString());
		data.put("customer", measure.getCustomer());
		data.put("date", measure.getDate());
		data.put("value", measure.getValue());
		data.put("address", measure.getAddress());
		Integer id = db.insert(data, MEASURE_TABLE);
		measure.setId(id);
	}
}
