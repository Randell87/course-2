package org.skypro.course2.exception;

public class AmountExceedsTotalException extends RuntimeException {
    public AmountExceedsTotalException(String message) {
        super(message);
    }
}
