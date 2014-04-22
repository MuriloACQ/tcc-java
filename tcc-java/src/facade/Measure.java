package facade;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Field;

public class Measure {

	private DeviceInfo device;
	private String fileName;
	private String customer;
	private String amount;
	private String date;

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

	public String getAmount() {
		return amount;
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
}
