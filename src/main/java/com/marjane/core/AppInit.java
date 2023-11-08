package com.marjane.core;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.startup.Tomcat;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;

/**
 * This class manages the initialization and shutdown of the Tomcat server for the application.
 */
@Slf4j
@Component
public class AppInit implements Closeable {
    private static final int PORT = getPort();
    final static Tomcat tomcat = new Tomcat();

    /**
     * Starts the Tomcat server with the specified configuration.
     */
    public static void start() {
        try {
            tomcat.setBaseDir(createTempDir());
            tomcat.setPort(PORT);
            tomcat.getConnector();
            tomcat.getHost().setAppBase(".");
            tomcat.addWebapp("/", ".");
            tomcat.start();
            tomcat.getServer().await();
            Runtime.getRuntime()
                    .addShutdownHook(new ShutdownHook());
        } catch (Exception e) {
            log.error("Error while starting Tomcat", e);
        }
    }

    /**
     * Stops the running Tomcat server.
     */
    public static void stop() {
        log.info("Stopping Tomcat ...");
        try {
            tomcat.stop();
        } catch (Exception e) {
            log.error("Error while stopping Tomcat", e);
        }
    }

    /**
     * Retrieve the port number from environment variables or use a default port.
     *
     * @return The port number of the Tomcat server.
     */
    private static int getPort() {
        String port = dotenv.get("SERVER_PORT");
        if (port != null) {
            try {
                return Integer.parseInt(port);
            } catch (NumberFormatException e) {
                log.error("Invalid SERVER_PORT format: " + port, e);
            }
        }
        return 8080;
    }

    /**
     * Create a temporary directory for Tomcat.
     *
     * @return The absolute path of the temporary directory.
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    private static @NotNull String createTempDir() {
        try {
            File tempDir = File.createTempFile("tomcat.", "." + PORT);
            tempDir.delete();
            tempDir.mkdir();
            tempDir.deleteOnExit();
            return tempDir.getAbsolutePath();
        } catch (IOException ex) {
            log.error("Unable to create tempDir. java.io.tmpdir is set to " + System.getProperty("java.io.tmpdir"), ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void close() {
        stop();
    }
}