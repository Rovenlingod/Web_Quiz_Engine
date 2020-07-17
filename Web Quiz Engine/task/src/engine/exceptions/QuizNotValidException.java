package engine.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class QuizNotValidException extends RuntimeException {

    public QuizNotValidException() {
        super();
    }

    public QuizNotValidException(String message) {
        super(message);
    }
}
