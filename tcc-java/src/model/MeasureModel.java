package model;

import java.util.HashMap;
import java.util.Map;

import vo.facade.Measure;
import vo.facade.User;
import database.Connector;
import database.Database;

public class MeasureModel {

	private final String MEASURE_TABLE = "measure";

	private Database db;

	public MeasureModel() {
		db = new Database(Connector.getConnection());
	}

	// Caution: if String contains padding you need to use trim method to remove the trash
	public void insert(Measure measure, User user) {
		Map<String, String> data = new HashMap<String, String>();
		data.put("device", measure.getDevice().getId());
		data.put("user", user.getId().toString());
		data.put("customer", measure.getCustomer());
		data.put("date", measure.getDate().trim());
		data.put("value", measure.getValue());
		data.put("address", measure.getAddress());
		Integer id = db.insert(data, MEASURE_TABLE);
		measure.setId(id);
	}
}
