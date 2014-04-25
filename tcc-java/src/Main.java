import java.util.logging.Logger;

import database.Connector;
import drive.FindDrive;

public class Main {

	public static void main(String[] args) {

		Logger LOGGER = Logger.getLogger("Main");
		Connector.setServer("mysql.tiagoaveiro.com.br");
		Connector.setSchema("tiagoaveiro02");
		Connector.setAuthentication("tiagoaveiro02", "tcctcc");
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
