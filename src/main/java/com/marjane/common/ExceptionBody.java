package com.marjane.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * This class is used to wrap exception messages into easy to read JSON.
 *
 * @author Maksym Panov
 * @version 1.0
 */
@Getter
@Setter
@AllArgsConstructor
public class ExceptionBody {
    private String message;
}
