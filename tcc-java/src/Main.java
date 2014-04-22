import java.util.logging.Logger;

import database.Connector;
import drive.FindDrive;

public class Main {

	public static void main(String[] args) {
		Logger LOGGER = Logger.getLogger("Main");
		Connector.setSchema("tcc");
		Connector.getConnection();
		LOGGER.info("Database status: " + Connector.getConnetionStatus());
		if (Connector.isConnected()) {
			FindDrive findDrive = new FindDrive();
			findDrive.start();
		} else {
			LOGGER.warning("Closing application");
			System.exit(0);
		}
	}

}
