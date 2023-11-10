package com.marjane.core;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.startup.Tomcat;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;

@Slf4j
@Component
public final class Application implements Closeable {
    private static final int PORT = getPort();
    private static final Tomcat tomcat = new Tomcat();
    private volatile static File tempDir = null;

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
            Runtime.getRuntime().addShutdownHook(new ShutdownHook());
            Signature();
            tomcat.getServer().await();
        } catch (Exception e) {
            log.error("Error while starting Tomcat", e);
        }
    }

    /**
     * Stops the running Tomcat server.
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void stop() {
        log.info("Stopping Tomcat ...");
        try {
            if (tempDir != null) {
                tempDir.delete();
            }
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
            tempDir = File.createTempFile("tomcat.", "." + PORT);
            tempDir.delete();
            tempDir.mkdir();
            tempDir.deleteOnExit();
            return tempDir.getAbsolutePath();
        } catch (IOException ex) {
            log.error("Unable to create tempDir. java.io.tmpdir is set to " + System.getProperty("java.io.tmpdir"), ex);
            throw new RuntimeException(ex);
        }
    }

    /**
     * Print an App and Developer signature to the console.
     */
    private static void Signature() {
        System.out.print("\u001b[2J");
        System.out.print("\u001b[H");
        System.out.println("\n\n\t\t -------------> MARJANE");
        System.out.println("\t\t                  by @ouharri.outman");
        System.out.println("\t\t                     and @ossalhe.mohamed <-------------\n\n\n");
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