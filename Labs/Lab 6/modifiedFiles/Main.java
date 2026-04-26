/* 
 * jfm/src/org/jfm/main/Main.java
 *
 * SER335 - Lab 6
 * Spring '26 
 * ndavispe
 *
 * Attaches the login logger observer at startup
 */

package org.jfm.main;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.UIManager;

import org.jfm.filesystems.JFMFileSystem;
import org.jfm.views.JFMView;

import edu.asu.ser335.jfm.LoginAttemptSubject;
import edu.asu.ser335.jfm.LoginLoggerObserver;
import edu.asu.ser335.jfm.RolesSingleton;
import edu.asu.ser335.jfm.SaltsSingleton;
import edu.asu.ser335.jfm.UsersSingleton;

/**
 * Title: Java File Manager Description: Copyright: Copyright (c) 2001 Company:
 * Home
 * 
 * @author Giurgiu Sergiu
 * @version 1.0
 */
public class Main {
	private boolean packFrame = false;

	/** Construct the application */
	public Main() {
		LoginPannel pannel = new LoginPannel();
		if (packFrame) {
			pannel.pack();
		} else {
			pannel.validate();
		}
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = pannel.getSize();
		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}
		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}
		pannel.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
		pannel.setVisible(true);
	}

	/** Main method */
	public static void main(String[] args) {
		try {
			UsersSingleton.getUsers();
			RolesSingleton.getRoleMapping();
			SaltsSingleton.getUserSalts();

			// SER335 LAB6 TASK1 – attach logger observer
			LoginAttemptSubject.getInstance().attach(new LoginLoggerObserver());

			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			JFMView.registerViews();
			JFMFileSystem.registerFilesystems();
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unexpected error, exiting");
			System.exit(-1);
		}
		new Main();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
			}
		});
	}
}