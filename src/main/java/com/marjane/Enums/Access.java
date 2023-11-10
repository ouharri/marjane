package com.marjane.Enums;

import jakarta.persistence.AttributeConverter;
import com.marjane.models.User;
import lombok.Getter;

/**
 * This enumeration represents existing roles of the {@link User}.
 *
 * @author Ouharri Outman
 * @version 1.0
 */
@Getter
public enum Access {
    USER("U"),
    MANAGER("M"),
    ADMINISTRATOR("A"),
    SUPER_ADMINISTRATOR("S");

    private final String code;

    Access(String code) {
        this.code = code;
    }

    public static Access fromCode(String code) {
        if (code.equals("U")) return USER;
        if (code.equals("M")) return MANAGER;
        if (code.equals("A")) return ADMINISTRATOR;
        if (code.equals("S")) return SUPER_ADMINISTRATOR;

        throw new IllegalArgumentException();
    }

    /**
     * Converts enumeration objects into strings to save in the data storage.
     *
     * @author Ouharri Outman
     * @version 1.0
     */
    public static class AccessConverter implements AttributeConverter<Access, String> {

        @Override
        public String convertToDatabaseColumn(Access access) {
            return access == null ? null : access.getCode();
        }

        @Override
        public Access convertToEntityAttribute(String code) {
            return code == null ? null : fromCode(code);
        }
    }
}
