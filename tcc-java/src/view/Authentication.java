package view;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import core.System;
import vo.Auth;

public class Authentication {

	public static Auth getAuthPanel() {
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
		}

		JTextField username = new JTextField();
		JTextField password = new JPasswordField();
		Object[] message = { "Usuário:", username, "Senha:", password };

		int option = JOptionPane.showConfirmDialog(null, message,
				"Autenticador", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			return new Auth(username.getText(), password.getText());
		}
		int response = Messager.getConfirmPanel("Atenção",
				"Você dejesa abortar o processo?");
		if (response == JOptionPane.YES_OPTION) {
			System.ABORT = true;
		}
		return null;
	}

}
