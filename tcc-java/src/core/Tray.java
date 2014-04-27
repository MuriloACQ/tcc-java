package core;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;

import exception.UnsupportedSystemTrayException;

public class Tray {
	
	private SystemTray systemTray;
	private PopupMenu menu;
	private String description;
	private Image image;

	public Tray() throws UnsupportedSystemTrayException {
		if (SystemTray.isSupported()) {
			systemTray = SystemTray.getSystemTray();
		} else {
			throw new UnsupportedSystemTrayException("SystemTray is not supported for your operacional system");
		}
	}
	
	public void setMenu(PopupMenu menu){
		this.menu = menu;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public void setImage(String imagePath){
		image = Toolkit.getDefaultToolkit().createImage(imagePath);
	}
	
	public void createTrayIcon() throws AWTException{
		TrayIcon trayIcon = new TrayIcon(image,description,menu);
		systemTray.add(trayIcon);
	}
	
}
