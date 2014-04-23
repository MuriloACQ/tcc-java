package facade;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Field;

public class Measure {

	private Integer id;
	private DeviceInfo device;
	private String fileName;
	
	//no settable properties
	private String customer;
	private String value;
	private String date;
	private String address;

	public Measure(BufferedReader bufferedReader) throws IOException,
			NoSuchFieldException, SecurityException, IllegalArgumentException,
			IllegalAccessException {
		while (bufferedReader.ready()) {
			String line = bufferedReader.readLine();
			String[] lineArray = line.split("=");
			Field field = this.getClass().getDeclaredField(lineArray[0]);
			// TODO decrypt lineArray[1] to get the original value
			field.set(this, lineArray[1]);
		}
		bufferedReader.close();
	}

	public void setDevice(DeviceInfo device) {
		this.device = device;
	}

	public DeviceInfo getDevice() {
		return device;
	}

	public String getCustomer() {
		return customer;
	}

	public String getValue() {
		return value;
	}

	public String getDate() {
		return date;
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String name) {
		this.fileName = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

}
