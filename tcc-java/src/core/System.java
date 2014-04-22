package core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import model.UserModel;
import view.Authentication;
import view.Messager;
import vo.Auth;
import vo.User;
import facade.DeviceInfo;
import facade.Measure;

public class System {

	public static boolean ABORT;

	private static Logger LOGGER;
	private DeviceInfo deviceInfo;
	private User user = null;

	public System(DeviceInfo deviceInfo) {
		ABORT = false;
		LOGGER = Logger.getLogger(this.getClass().toString());
		this.deviceInfo = deviceInfo;
		if (isValidDevice()) {
			LOGGER.info("Valid device: starting processing");
			do {
				user = authentication();
				if (ABORT) {
					break;
				}
				if (user == null)
					Messager.getMessagePanel("Atenção",
							"É necessário autenticação para prosseguir");
			} while (user == null);
			if (!ABORT) {
				runApplication();
			}
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

	private User authentication() {
		Auth auth = Authentication.getAuthPanel();
		User user = null;
		if (auth != null) {
			UserModel userModel = new UserModel();
			try {
				user = userModel.getUser(auth);
			} catch (SQLException e) {
				user = null;
				e.printStackTrace();
			}
		}
		return user;
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
					// TODO like exception??
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
