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
			LOGGER.info("Valid device: starting processing");
			//TODO get authentication of user, if authentication is ok: run application
			runApplication();
		} else {
			LOGGER.warning("Invalid device: invalid properties");
		}
		
	}
	
	private boolean isValidDevice(){
		return (deviceInfo.getSystem().equals("TDH2014")
				&& deviceInfo.getId() != null);
	}
	
	private void runApplication(){
		//TODO
	}
}
