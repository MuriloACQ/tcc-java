/**
 * Waits for USB devices to be plugged in/unplugged and outputs a message
 * 
 * @author Keang
 * @version 1.0, 26/09/2008
 */

package drive;

import java.io.*;
import java.util.logging.Logger;

public class FindDrive extends Thread {

	private static Logger LOGGER;

	public void run() {
		LOGGER = Logger.getLogger(this.getClass().toString());
		String[] letters = new String[] { "A", "B", "C", "D", "E", "F", "G",
				"H", "I" };
		File[] drives = new File[letters.length];
		boolean[] isDrive = new boolean[letters.length];

		// init the file objects and the initial drive state
		for (int i = 0; i < letters.length; ++i) {
			drives[i] = new File(letters[i] + ":/");

			isDrive[i] = drives[i].canRead();
		}

		LOGGER.info("waiting for devices...");

		// loop indefinitely
		while (true) {
			// check each drive
			for (int i = 0; i < letters.length; ++i) {
				boolean pluggedIn = drives[i].canRead();

				// if the state has changed output a message
				if (pluggedIn != isDrive[i]) {
					if (pluggedIn) {
						LOGGER.info("Drive " + letters[i]
								+ " has been plugged in");
						new Handler(letters[i]).run();
					} else {
						LOGGER.info("Drive " + letters[i]
								+ " has been unplugged");
					}

					isDrive[i] = pluggedIn;
				}
			}

			// wait before looping
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) { /* do nothing */
			}

		}
	}
}