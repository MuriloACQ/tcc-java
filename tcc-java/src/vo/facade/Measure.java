/**
 * All no settable properties of this class should be String objects, if you want use other types, they have to be converted by getters
 * The name of the no settable properties should be the same of the properties of root.ini file
 * @author Murilo Quadros
 */

package vo.facade;

import java.io.BufferedReader;
import java.lang.reflect.Field;

import encryption.AES;

public class Measure {

	private Integer id;
	private DeviceInfo device;
	private String fileName;
	
	//no settable properties
	private String customer;
	private String value;
	private String date;
	private String address;

	public Measure(BufferedReader bufferedReader) throws Exception {
		while (bufferedReader.ready()) {
			String line = bufferedReader.readLine();
			String[] lineArray = line.split("=");
			Field field = this.getClass().getDeclaredField(lineArray[0]);
			String decryptedValue = AES.decryptAndVerify(AES.stringToByteArray(lineArray[1]), "TDH");
			field.set(this, decryptedValue);
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
