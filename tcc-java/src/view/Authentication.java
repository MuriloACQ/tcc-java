package view;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import vo.Auth;

public class Authentication {
	
	private static JTextField username = new JTextField();
	private static JTextField password = new JPasswordField();

	public static Auth getAuthPanel() {

		int option = getIntegerOptionAuthPanel();
		if (option == JOptionPane.OK_OPTION) {
			return new Auth(username.getText(), password.getText());
		}
		return null;
	}
	
	public  static int getIntegerOptionAuthPanel(){
		
		Object[] message = { "Usuário:", username, "Senha:", password };
		return JOptionPane.showConfirmDialog(null, message,
				"Autenticador", JOptionPane.OK_CANCEL_OPTION);
	}

}
