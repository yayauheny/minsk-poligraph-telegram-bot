package by.yayauheny.printbot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoSuchEntityException extends RuntimeException {

    public NoSuchEntityException() {
        super("Resource not found");
    }

    public NoSuchEntityException(String message) {
        super(message);
    }
}
