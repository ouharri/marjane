package com.marjane;

import com.marjane.Core.dotenv;
import org.apache.catalina.startup.Tomcat;

import java.io.File;
import java.io.IOException;

public class Marjane {
    private static final int PORT = getPort();

    public static void main(String[] args) throws Exception {
        System.out.println(dotenv.get("SERVER_PORT"));
        System.out.println(dotenv.get("JWT_SECRET"));

        final Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir(createTempDir());
        tomcat.setPort(PORT);
        tomcat.getConnector();
        tomcat.getHost().setAppBase(".");
        tomcat.addWebapp("/", ".");
        tomcat.start();
        tomcat.getServer().await();
    }

    private static int getPort() {
        String port = dotenv.get("SERVER_PORT");
        if (port != null) {
            return Integer.parseInt(port);
        }
        return 8080;
    }

    /**
     * Returns the absolute temp dir for given servlet container.
     *
     * @return The temp dir for given servlet container.
     */
    private static String createTempDir() {
        try {
            File tempDir = File.createTempFile("tomcat.", "." + PORT);
            tempDir.delete();
            tempDir.mkdir();
            tempDir.deleteOnExit();
            return tempDir.getAbsolutePath();
        } catch (IOException ex) {
            throw new RuntimeException(
                    "Unable to create tempDir. java.io.tmpdir is set to " + System.getProperty("java.io.tmpdir"), ex
            );
        }
    }
}