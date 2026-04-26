/* 
 * jfm/src/edu/asu/ser335/jfm/LoginAttemptEvent.java
 *
 * SER335 - Lab 6
 * Spring '26 
 * ndavispe
 *
 * Event object carrying details of a login attempt
 */

package edu.asu.ser335.jfm;

import java.time.LocalDateTime;

public class LoginAttemptEvent {
    private final String username;
    private final String password;
    private final String role;
    private final LocalDateTime timestamp;

    // SER335 LAB6 TASK1
    public LoginAttemptEvent(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.timestamp = LocalDateTime.now();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}