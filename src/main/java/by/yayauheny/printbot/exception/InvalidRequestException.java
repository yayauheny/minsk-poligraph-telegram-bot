package by.yayauheny.printbot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidRequestException extends RuntimeException {

    public InvalidRequestException() {
        super("Invalid request, provide valid data");
    }

    public InvalidRequestException(String message) {
        super(message);
    }
}
