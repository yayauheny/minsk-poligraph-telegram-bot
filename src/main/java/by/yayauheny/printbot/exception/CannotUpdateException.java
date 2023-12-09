package by.yayauheny.printbot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CannotUpdateException extends RuntimeException {

    public CannotUpdateException() {
        super("Exception during update");
    }

    public CannotUpdateException(String message) {
        super(message);
    }
}
