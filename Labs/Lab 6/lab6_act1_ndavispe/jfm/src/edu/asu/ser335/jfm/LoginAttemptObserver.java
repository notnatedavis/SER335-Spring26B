/* 
 * jfm/src/edu/asu/ser335/jfm/LoginAttemptObserver.java
 *
 * SER335 - Lab 6
 * Spring '26 
 * ndavispe
 *
 * Observer interface for login attempts
 */

package edu.asu.ser335.jfm;

public interface LoginAttemptObserver {
    // SER335 LAB6 TASK1
    void onFailedLogin(LoginAttemptEvent event);
}