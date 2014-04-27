package log;

import java.io.File;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class Log {

	private static Handler HANDLER;
	private static final String FOLDER = "Log/";
	private static final String FILE = "System.log";

	public static Logger getLogger(String name) {
		Logger LOGGER = Logger.getLogger(name);
		if (HANDLER == null) {

			try {
				HANDLER = new FileHandler(FOLDER + FILE);
				HANDLER.setFormatter(new HtmlFormatter());
			} catch (Exception e) {
				File dir = new File(FOLDER);
				dir.mkdir();
				try {
					HANDLER = new FileHandler(FOLDER + FILE);
					HANDLER.setFormatter(new HtmlFormatter());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}

		}
		LOGGER.addHandler(HANDLER);
		return LOGGER;
	}

}
