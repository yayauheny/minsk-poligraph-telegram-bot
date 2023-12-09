package by.yayauheny.printbot.util;

import by.yayauheny.printbot.exception.CannotUpdateException;
import by.yayauheny.printbot.exception.InvalidRequestException;
import by.yayauheny.printbot.exception.NoSuchEntityException;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({InvalidRequestException.class, NoSuchEntityException.class, CannotUpdateException.class})
    public ResponseEntity<String> handleRequestException(RuntimeException e) {
        ResponseStatus responseStatusAnnotation = AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class);

        if (responseStatusAnnotation != null) {
//            String reason = responseStatusAnnotation.reason();
            return ResponseEntity
                    .status(responseStatusAnnotation.value())
                    .body(e.getMessage());
        }

        return ResponseEntity
                .badRequest()
                .body(e.getMessage());
    }
}
