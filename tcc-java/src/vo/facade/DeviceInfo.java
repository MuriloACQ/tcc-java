/**
 * All properties of this class should be String objects, if you want use other types, they have to be converted by getters
 * The name of the properties should be the same of the properties of root.ini file
 * @author Murilo Quadros
 */

package vo.facade;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Field;

public class DeviceInfo {
	
	//TODO Complete this class
	private String system;
	private String id;
	
	private String path;

	public DeviceInfo(BufferedReader bufferedReader) throws IOException,
			NoSuchFieldException, SecurityException, IllegalArgumentException,
			IllegalAccessException {
		while (bufferedReader.ready()) {
			String line = bufferedReader.readLine();
			String[] lineArray = line.split("=");
			Field field = this.getClass().getDeclaredField(lineArray[0]);
			field.set(this, lineArray[1]);
		}
		bufferedReader.close();
	}
	
	public String getSystem(){
		return system;
	}
	
	public String getId(){
		return id;
	}
	
	public void setPath(String path){
		this.path = path;
	}
	
	public String getPath(){
		return path;
	}
}
