package view;

import javax.swing.JOptionPane;

public class Messager {

	public static void getMessagePanel(String title, String message) {
		JOptionPane.showMessageDialog(null, message, title,
				JOptionPane.INFORMATION_MESSAGE);
	}

	public static int getConfirmPanel(String title, String message) {
		return JOptionPane.showConfirmDialog(null, message, title,
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	}

}
