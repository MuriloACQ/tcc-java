import java.util.logging.Logger;

import javax.swing.UIManager;

import log.Log;
import database.Connector;
import drive.FindDrive;

public class Main {

	public static void main(String[] args) {

		Logger LOGGER = Log.getLogger("Main");

		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			LOGGER.warning("Fail to apply Windows look and feel");
		}

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
