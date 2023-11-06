package com.marjane.configuration;

import com.marjane.Core.dotenv;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.startup.Tomcat;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * Cette classe gère l'initialisation et l'arrêt du serveur Tomcat pour l'application.
 */
@Slf4j
@Component
public class AppInit {

    private static final int PORT = getPort();
    final static Tomcat tomcat = new Tomcat();

    /**
     * Démarre le serveur Tomcat avec la configuration spécifiée.
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
        } catch (Exception e) {
            log.error("Erreur lors du démarrage de Tomcat", e);
        }
    }

    /**
     * Arrête le serveur Tomcat en cours d'exécution.
     */
    public static void stop() {
        try {
            tomcat.stop();
        } catch (Exception e) {
            log.error("Erreur lors de l'arrêt de Tomcat", e);
        }
    }

    /**
     * Récupère le numéro de port à partir des variables d'environnement ou utilise un port par défaut.
     *
     * @return Le numéro de port du serveur Tomcat.
     */
    private static int getPort() {
        String port = dotenv.get("SERVER_PORT");
        if (port != null) {
            try {
                return Integer.parseInt(port);
            } catch (NumberFormatException e) {
                log.error("Format de SERVER_PORT invalide : " + port, e);
            }
        }
        return 8080;
    }

    /**
     * Crée un répertoire temporaire pour Tomcat.
     *
     * @return Le chemin absolu du répertoire temporaire.
     */
    private static String createTempDir() {
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
}