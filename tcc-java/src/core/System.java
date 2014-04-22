package core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import facade.DeviceInfo;
import facade.Measure;

public class System {

	private static Logger LOGGER;
	private DeviceInfo deviceInfo;

	public System(DeviceInfo deviceInfo) {
		LOGGER = Logger.getLogger(this.getClass().toString());
		this.deviceInfo = deviceInfo;
		if (isValidDevice()) {
			LOGGER.info("Valid device: starting processing");
			// TODO get authentication of user, if authentication is ok: run
			// application
			runApplication();
		} else {
			LOGGER.warning("Invalid device: invalid properties");
		}

	}

	// TODO improve this method
	private boolean isValidDevice() {
		return (deviceInfo.getSystem().equals("TDH2014") && deviceInfo.getId() != null);
	}

	// TODO implement this method
	private boolean isValidMeasure(Measure measure) {
		return true;
	}

	private void runApplication() {
		final File folder = new File(deviceInfo.getPath() + "measures");
		List<Measure> measures = new ArrayList<Measure>();
		for (final File fileEntry : folder.listFiles()) {
			try {
				BufferedReader bufferedReader = new BufferedReader(
						new FileReader(fileEntry));
				Measure measure = new Measure(bufferedReader);
				measure.setDevice(deviceInfo);
				measure.setFileName(fileEntry.getName());
				if (isValidMeasure(measure)) {
					measures.add(measure);
				} else {
					//TODO like exception??
				}
				// TODO make exception handler, maybe we should use generic
				// exception
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		for (Measure measure : measures) {
			LOGGER.info(measure.getFileName());
		}
	}
}
