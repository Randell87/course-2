package org.skypro.course2.exception;

public class AmountNotValidException extends RuntimeException {
    public AmountNotValidException(String message) {
        super(message);
    }
}
