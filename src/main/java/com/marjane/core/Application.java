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
    private static volatile File tempDir = null;

    /**
     * Starts the Tomcat server with the specified configuration.
     */
    public static void start() {
        log.info("Starting Tomcat server...");
        try {
            configureTomcat();
            tomcat.start();
            log.info("Tomcat server started on port " + PORT);
            Runtime.getRuntime().addShutdownHook(new ShutdownHook());
            printSignature();
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
            cleanupTempDir();
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
            return (port != null) ? Integer.parseInt(port) : 8080;
        } catch (NumberFormatException e) {
            log.error("Invalid SERVER_PORT format: " + port, e);
            return 8080;
        }
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
            log.error("Unable to create tempDir.", ex);
            throw new RuntimeException(ex);
        }
    }

    /**
     * Configures the Tomcat instance.
     */
    private static void configureTomcat() {
        tomcat.setBaseDir(createTempDir());
        tomcat.setPort(PORT);
        tomcat.getConnector();
        tomcat.getConnector().setURIEncoding("UTF-8");
        tomcat.getHost().setAppBase(".");
        tomcat.addWebapp("/", ".");
        tomcat.getHost().setAutoDeploy(true);
    }

    /**
     * Print an App and Developer signature to the console.
     */
    public static void printSignature() {
        System.out.print("\u001b[2J");
        System.out.print("\u001b[H");
        System.out.println("\n\n\t\t\t\t -------------> MARJANE");
        System.out.println("\t\t\t\t                  by @ouharri.outman");
        System.out.println("\t\t\t\t                     and @ossalhe.mohamed <-------------\n\n\n\n");
    }

    /**
     * Cleanup temporary directory.
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    private static void cleanupTempDir() {
        if (tempDir != null) {
            tempDir.delete();
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