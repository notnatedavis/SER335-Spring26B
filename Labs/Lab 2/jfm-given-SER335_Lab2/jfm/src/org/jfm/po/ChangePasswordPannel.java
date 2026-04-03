/* 
 * jfm/src/org/jfm/main/ChangePasswordPannel.java
 * Task H3 : 4.
 * SER335 Lab 2
 * Spring '26B
 * Nathaniel Davis-Perez
 */

package org.jfm.po;

// ----- Imports -----

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

// ----- Main -----

public class ChangePasswordPannel extends JFrame implements ActionListener {

    // constants
    private static final long serialVersionUID = 1L;
    private JLabel labelUsername = new JLabel("Username: ");
    private JLabel labelPassword = new JLabel("New Password: ");
    private JLabel labelRole = new JLabel("Role: ");
    private JTextField textUsername = new JTextField(20);
    private JPasswordField fieldPassword = new JPasswordField(20);
    private JButton buttonSubmit = new JButton("Submit");
    private JPanel newPanel;
    private JComboBox<String> roleList;

    public ChangePasswordPannel() {
        newPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        // username
        constraints.gridx = 0;
        constraints.gridy = 0;
        newPanel.add(labelUsername, constraints);

        constraints.gridx = 1;
        newPanel.add(textUsername, constraints);

        // password
        constraints.gridx = 0;
        constraints.gridy = 1;
        newPanel.add(labelPassword, constraints);

        constraints.gridx = 1;
        newPanel.add(fieldPassword, constraints);

        // role (filled from user)
        constraints.gridx = 0;
        constraints.gridy = 2;
        newPanel.add(labelRole, constraints);

        constraints.gridx = 1;
        roleList = new JComboBox<String>(RolesSingleton.getRoleMapping().getDisplayRoles());
        roleList.setEnabled(false); // readonly – set on username entry

        newPanel.add(roleList, constraints);

        // submit button
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        newPanel.add(buttonSubmit, constraints);

        buttonSubmit.addActionListener(this);

        // auto-fill role if username field lost
        textUsername.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateRoleFromUsername();
            }
        });

        newPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Change Password"));
        add(newPanel);

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * Updates the role combo box based on the entered username.
     * If user exists, selects the current role; otherwise disables the combo box.
     */
    private void updateRoleFromUsername() {
        String userName = textUsername.getText().trim();
        if (userName.isEmpty()) {
            roleList.setEnabled(false);
            roleList.setSelectedIndex(-1);
            return;
        }
        try {
            String role = UsersSingleton.getUserRoleMapping().get(userName);
            if (role != null) {
                roleList.setSelectedItem(role);
                roleList.setEnabled(false); // Read-only – role cannot be changed here
            } else {
                roleList.setEnabled(false);
                roleList.setSelectedIndex(-1);
            }
        } catch (Exception e) {
            roleList.setEnabled(false);
            roleList.setSelectedIndex(-1);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String userName = textUsername.getText().trim();
        String newPassword = new String(fieldPassword.getPassword());
        String selectedRole = (String) roleList.getSelectedItem();

        // 1. validate fields
        if (userName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "username cannot be empty",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (newPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "password cannot be empty",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (selectedRole == null) {
            JOptionPane.showMessageDialog(this, "please select a role",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // 2. check user exists & role matches
            String currentRole = UsersSingleton.getUserRoleMapping().get(userName);

            if (currentRole == null) {
                JOptionPane.showMessageDialog(this, "user does not exist: " + userName,
                        "validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!currentRole.equals(selectedRole)) {
                JOptionPane.showMessageDialog(this,
                        "role does not match the user's current role\n" +
                        "current role for " + userName + " is: " + currentRole,
                        "validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 3. update password
            UsersSingleton.updatePassword(userName, newPassword);
            JOptionPane.showMessageDialog(this,
                    "password successfully changed for user : " + userName,
                    "success", JOptionPane.INFORMATION_MESSAGE);
            dispose(); // close the dialog

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "failed to change password : " + ex.getMessage(),
                    "error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}