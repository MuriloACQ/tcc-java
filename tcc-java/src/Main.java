import java.util.logging.Logger;

import log.Log;
import core.Boot;
import exception.BootAlreadyDoneException;

public class Main{

	public static void main(String[] args) {

		Logger LOGGER = Log.getLogger(Main.class.getName());
		
		try {
			new Boot();
		} catch (BootAlreadyDoneException e) {
			LOGGER.warning(e.getMessage());
		}
		
	}

}
