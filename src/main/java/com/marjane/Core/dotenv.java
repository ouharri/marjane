package com.marjane.Core;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.extern.slf4j.Slf4j;

/**
 * The `env` class provides access to environment variables loaded from a .env file.
 */
@Slf4j
public class dotenv {
    private static Dotenv dotenv = null;

    private dotenv() {
    }

    static {
        synchronized (com.marjane.Core.dotenv.class) {
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