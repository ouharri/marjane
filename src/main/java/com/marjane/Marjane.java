package com.marjane;

import com.marjane.core.Application;

/**
 * The main class of the Marjane application responsible for starting the Tomcat server.
 */
public class Marjane {

    /**
     * The main method that launches the application by calling the static start method of the Application class.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        Application.start();
    }
}
