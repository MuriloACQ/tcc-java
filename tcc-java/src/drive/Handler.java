package drive;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

import core.System;
import facade.DeviceInfo;

public class Handler extends Thread {
	
	private String path;
	private static Logger LOGGER;
	
	public Handler(String path){
		this.path = path+":/";
		LOGGER = Logger.getLogger(this.getClass().toString());
	}
	
	public void run() {
		LOGGER.info("Handling "+ path);
		try {
			BufferedReader bufferReader = new BufferedReader(new FileReader(path+"device.info"));
			LOGGER.info("Device connected");
			DeviceInfo deviceInfo = new DeviceInfo(bufferReader);
			deviceInfo.setPath(path);
			new System(deviceInfo);
		} catch (FileNotFoundException e) {
			LOGGER.warning("Invalid device: root.ini file does not exist");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
