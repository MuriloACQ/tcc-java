package core;

import java.awt.Toolkit;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.UIManager;

import log.Log;
import database.Connector;
import drive.FindDrive;
import exception.BootAlreadyDoneException;

import java.lang.System;

public class Boot extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private static Logger LOGGER;
	private static boolean booted = false;

	public Boot() throws BootAlreadyDoneException{
		if(booted){
			throw new BootAlreadyDoneException("You cannot boot application twice");
		}
		LOGGER = Log.getLogger(this.getClass().toString());
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			LOGGER.warning("Fail to apply Windows look and feel, "
					+ e.getMessage());
		}
		
		setIconImage(Toolkit.getDefaultToolkit().createImage("icon.png"));
		
		try {
			Tray tray = new Tray();
			tray.setMenu(new Menu());
			tray.setImage("tray-icon.png");
			tray.setDescription("TDH2014");
			tray.createTrayIcon();
		} catch (Exception e) {
			LOGGER.warning(e.getMessage());
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
		
		booted = true;
	}
	
}
