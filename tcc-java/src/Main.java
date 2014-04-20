import com.mysql.jdbc.Connection;

import database.Connector;
import database.Database;
import drive.FindDrive;

public class Main {

	public static void main(String[] args) {
		Connector.setSchema("tcc");
		Connection connection = Connector.getConnection();
		System.out.println("Database status: "+Connector.getConnetionStatus());

		Database database = new Database(connection);
		FindDrive findDrive = new FindDrive();
		findDrive.run();
	}

}
