/* 
 * jfm/src/edu/asu/ser335/jfm/LoginLoggerObserver.java
 *
 * SER335 - Lab 6
 * Spring '26 
 * ndavispe
 *
 * Observer that writes failed login attempts to jfmlog.txt
 */

package edu.asu.ser335.jfm;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginLoggerObserver implements LoginAttemptObserver {
    private static final String LOG_FILE = "jfmlog.txt";

    @Override
    public void onFailedLogin(LoginAttemptEvent event) {
        // SER335 LAB6 TASK1 – write to log file
        try (PrintWriter out = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            out.printf("[%s] Failed login - User: %s, Password: %s, Role: %s%n",
                    event.getTimestamp(), event.getUsername(), event.getPassword(), event.getRole());
        } catch (IOException e) {
            System.err.println("Failed to write to log file: " + e.getMessage());
        }
    }
}