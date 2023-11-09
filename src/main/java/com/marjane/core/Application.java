package com.marjane.core;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.startup.Tomcat;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;

@Slf4j
@Configuration
public class Application implements Closeable {
    private static final int PORT = getPort();
    private static final Tomcat tomcat = new Tomcat();

    /**
     * Starts the Tomcat server with the specified configuration.
     */
    public static void start() {
        log.info("Starting Tomcat server...");
        try {
            tomcat.setBaseDir(createTempDir());
            tomcat.setPort(PORT);
            tomcat.getConnector();
            tomcat.getHost().setAppBase(".");
            tomcat.addWebapp("/", ".");
            tomcat.start();
            log.warn("Tomcat server started on port " + PORT);
            System.out.println("\n\n\t\t -------------> MARJANE \n\n\n");

            Runtime.getRuntime().addShutdownHook(new ShutdownHook());

            tomcat.getServer().await();
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
            tomcat.getServer().stop();
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
        try {
            if (port != null) {
                return Integer.parseInt(port);
            }
        } catch (NumberFormatException e) {
            log.error("Invalid SERVER_PORT format: " + port, e);
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

    /**
     * A shutdown hook to ensure that Tomcat is stopped gracefully on application shutdown.
     */
    public static class ShutdownHook extends Thread {
        @Override
        public void run() {
            Application.stop();
        }
    }
}