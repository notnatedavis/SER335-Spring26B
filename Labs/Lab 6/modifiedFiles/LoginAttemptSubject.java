/* 
 * jfm/src/edu/asu/ser335/jfm/LoginAttemptSubject.java
 *
 * SER335 - Lab 6
 * Spring '26 
 * ndavispe
 *
 * Subject (observable) that manages observers and notifies them of failed login attempts
 */

package edu.asu.ser335.jfm;

import java.util.ArrayList;
import java.util.List;

public class LoginAttemptSubject {
    private static LoginAttemptSubject instance = null;
    private List<LoginAttemptObserver> observers = new ArrayList<>();

    // SER335 LAB6 TASK1
    private LoginAttemptSubject() {}

    public static LoginAttemptSubject getInstance() {
        if (instance == null) {
            instance = new LoginAttemptSubject();
        }
        return instance;
    }

    public void attach(LoginAttemptObserver observer) {
        observers.add(observer); // SER335 LAB6 TASK1
    }

    public void notifyFailedLogin(LoginAttemptEvent event) {
        for (LoginAttemptObserver obs : observers) {
            obs.onFailedLogin(event); // SER335 LAB6 TASK1
        }
    }
}