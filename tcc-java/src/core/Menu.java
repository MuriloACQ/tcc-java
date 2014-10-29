package core;

import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.System;
import java.util.logging.Logger;

import log.Log;

public class Menu extends PopupMenu{

	private static final long serialVersionUID = 1L;
	private static Logger LOGGER;

	public Menu(){
		super();
		LOGGER = Log.getLogger(this.getClass().toString());
		addExitOption();
	}
	
	private void addExitOption(){
		MenuItem exit = new MenuItem("Sair");
		
		exit.addActionListener( new ActionListener() {
		     public void actionPerformed(ActionEvent e) {
		    	 LOGGER.warning("Closing application...");
		    	 System.exit(0);
		     }
		});
		
		add(exit);
	}
}
