/**
 * All properties of this class should be String objects, if you want use other types, they have to be converted by getters
 * @author Murilo Quadros
 */
package facade;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Field;

public class DeviceInfo {
	
	//TODO Complete this class
	private String id;

	public DeviceInfo(BufferedReader bufferReader) throws IOException,
			NoSuchFieldException, SecurityException, IllegalArgumentException,
			IllegalAccessException {
		while (bufferReader.ready()) {
			String line = bufferReader.readLine();
			String[] lineArray = line.split("=");
			Field field = this.getClass().getDeclaredField(lineArray[0]);
			field.set(this, lineArray[1]);
		}
		bufferReader.close();
	}
	
	public String getId(){
		return id;
	}
}
