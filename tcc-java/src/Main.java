import java.util.logging.Logger;

import com.mysql.jdbc.Connection;

import database.Connector;
import database.Database;
import drive.FindDrive;

public class Main {

	public static void main(String[] args) {
		Logger LOGGER = Logger.getLogger("Main");
		Connector.setSchema("tcc");
		Connection connection = Connector.getConnection();
		LOGGER.info("Database status: " + Connector.getConnetionStatus());
		if (Connector.isConnected()) {
			FindDrive findDrive = new FindDrive();
			findDrive.run();
		}
	}

}
