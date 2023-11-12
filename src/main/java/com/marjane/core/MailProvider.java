package com.marjane.core;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;

/**
 * The MailProvider class provides a centralized instance of the JavaMail Session
 * for sending email using SMTP with authentication.
 */
@Slf4j
public class MailProvider {

    /**
     * -- GETTER --
     * Retrieves the JavaMail Session for sending emails.
     */
    @Getter
    private static volatile Session mailSession = null;

    static {
        if (mailSession == null) {
            synchronized (MailProvider.class) {
                if (mailSession == null) {
                    try {
                        log.info("Creating a JavaMail Session bean.");
                        mailSession = Session.getInstance(loadEmailProperties(), new Authenticator() {
                            @Override
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(
                                        dotenv.get("MAIL_USERNAME"),
                                        dotenv.get("MAIL_PASSWORD")
                                );
                            }
                        });
                    } catch (IOException e) {
                        throw new RuntimeException("Error loading email properties.", e);
                    }
                }
            }
        }
    }

    /**
     * Loads the email properties from the environment variables.
     *
     * @return The email properties.
     * @throws IOException If an error occurs while loading the properties.
     */
    private static Properties loadEmailProperties() throws IOException {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", dotenv.get("MAIL_SMTP_AUTH"));
        properties.put("mail.smtp.host", dotenv.get("MAIL_HOST"));
        properties.put("mail.smtp.port", dotenv.get("MAIL_PORT"));
        properties.put("mail.smtp.starttls.enable", dotenv.get("MAIL_SMTP_STARTTLS_ENABLE"));
        return properties;
    }
}