package core;

import java.util.logging.Logger;

import facade.DeviceInfo;

public class System {
	
	private static Logger LOGGER;
	private DeviceInfo deviceInfo;
	
	public System(DeviceInfo deviceInfo){
		LOGGER = Logger.getLogger(this.getClass().toString());
		this.deviceInfo = deviceInfo;
		if(isValidDevice()){
			//TODO get authentication of user, if authentication is ok: run application
			runApplication();
		} else {
			LOGGER.warning("Invalid device: invalid properties");
		}
		
	}
	
	private boolean isValidDevice(){
		//TODO verify if properties of deviceInfo are valid
		return true;
	}
	
	private void runApplication(){
		//TODO
	}
}
