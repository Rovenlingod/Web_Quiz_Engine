package engine.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class QuizForbiddenException extends RuntimeException {

    public QuizForbiddenException() {
    }

    public QuizForbiddenException(String message) {
        super(message);
    }
}
