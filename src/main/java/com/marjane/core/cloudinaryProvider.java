package com.marjane.core;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * The Cloudinary class provides a centralized instance of the Cloudinary client
 * for interacting with the Cloudinary service.
 */
@Slf4j
@Component
public class cloudinaryProvider {

    /**
     * -- GETTER --
     *  Retrieves the Cloudinary client instance.
     */
    @Getter
    private static volatile Cloudinary cloudinary = null;

    private cloudinaryProvider() {
    }

    static {
        if (cloudinary == null) {
            synchronized (cloudinaryProvider.class) {
                if (cloudinary == null) {
                    log.info("Creating a Cloudinary bean.");
                    cloudinary = new Cloudinary(
                            ObjectUtils.asMap(
                                    "cloud_name", dotenv.get("CLOUDINARY_CLOUD_NAME"),
                                    "api_key", dotenv.get("CLOUDINARY_API_KEY"),
                                    "api_secret", dotenv.get("CLOUDINARY_API_SECRET")
                            )
                    );
                }
            }
        }
    }

}
