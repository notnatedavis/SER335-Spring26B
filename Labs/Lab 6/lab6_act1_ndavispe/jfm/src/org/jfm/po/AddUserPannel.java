/**
 * 
 */
package org.jfm.po;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import edu.asu.ser335.jfm.RolesSingleton;
import edu.asu.ser335.jfm.UsersSingleton;

/**
 * @author Nikhil Hiremath
 *
 */
public class AddUserPannel extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JLabel labelUsername = new JLabel("Enter username: ");
	private JLabel labelPassword = new JLabel("Enter password: ");
	private JLabel labelRole = new JLabel("Enter Role: ");
	private JLabel message;
	private JTextField textUsername = new JTextField(20);
	private JPasswordField fieldPassword = new JPasswordField(20);
	private JButton buttonCreateUser = new JButton("Submit");
	private JPanel newPanel;
	private JComboBox<String> roleList;

	public AddUserPannel() {
		// create a new panel with GridBagLayout manager
		newPanel = new JPanel(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);

		// add components to the panel
		// UserName
		constraints.gridx = 0;
		constraints.gridy = 0;
		newPanel.add(labelUsername, constraints);

		constraints.gridx = 1;
		newPanel.add(textUsername, constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;

		// Password
		newPanel.add(labelPassword, constraints);

		constraints.gridx = 1;
		newPanel.add(fieldPassword, constraints);

		constraints.gridx = 0;
		constraints.gridy = 2;

		// Role
		newPanel.add(labelRole, constraints);
		constraints.gridx = 1;

		// newPanel.add(textRole, constraints);
		// drop down
	
		roleList = new JComboBox<String>(RolesSingleton.getRoleMapping().getDisplayRoles());

		// add to the parent container (e.g. a JFrame):
		newPanel.add(roleList, constraints);

		// System.out.println("Selected role: " + role);

		constraints.gridx = 0;
		constraints.gridy = 3;

		message = new JLabel();
		newPanel.add(message, constraints);
		constraints.gridx = 1;

		constraints.gridwidth = 3;
		constraints.anchor = GridBagConstraints.CENTER;
		newPanel.add(buttonCreateUser, constraints);

		// Adding the listeners to components..

		buttonCreateUser.addActionListener(this);

		// set border for the panel
		newPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Admin Panel"));

		// add the panel to this frame
		add(newPanel);

		pack();
		setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String userName = textUsername.getText();
		String password = String.valueOf(fieldPassword.getPassword());
		String role = (String) roleList.getSelectedItem();

		// SER335 LAB5 : defensive input validation
		if (userName.isEmpty() || password.isEmpty()) {
			JOptionPane.showMessageDialog(this,
				"Username and password cannot be empty.",
				"Input Error",
				JOptionPane.ERROR_MESSAGE);
			return;
		}

		try {
			if (!UsersSingleton.createPasswordMapping(userName, password, role)) {
				JOptionPane.showMessageDialog(this,
					"User already exists or fields incomplete!",
					"Creation Failed",
					JOptionPane.WARNING_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this,
					"User created successfully!",
					"Success",
					JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}
		} catch (Exception ex) {
			// SER335 LAB5 : specific error messages, no more stack trace
			String errorMsg = ex.getMessage();
			if (errorMsg.contains("salt")) {
				JOptionPane.showMessageDialog(this,
					"Cannot write salts.json. Check file permissions.",
					"File System Error",
					JOptionPane.ERROR_MESSAGE);
			} else if (errorMsg.contains("authentication")) {
				JOptionPane.showMessageDialog(this,
					"Cannot write authentication.json. Check file permissions.",
					"File System Error",
					JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this,
					"Error creating user: " + errorMsg,
					"Unexpected Error",
					JOptionPane.ERROR_MESSAGE);
			}
		}
	}
