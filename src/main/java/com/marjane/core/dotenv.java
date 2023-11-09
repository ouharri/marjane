package com.marjane.core;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

/**
 * The `env` class provides access to environment variables loaded from a .env file.
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class dotenv {
    volatile static Dotenv dotenv = null;

    static {
        log.info("Loading environment variables ...");
        synchronized (com.marjane.core.dotenv.class) {
            if (dotenv == null) {
                dotenv = Dotenv.configure()
                        .ignoreIfMalformed()
                        .ignoreIfMissing()
                        .load();
            }
        }
    }

    /**
     * Get the value of an environment variable by its key.
     *
     * @param key The key of the environment variable.
     * @return The value of the environment variable, or null if not found.
     */
    public static String get(String key) {
        return dotenv.get(key);
    }
}