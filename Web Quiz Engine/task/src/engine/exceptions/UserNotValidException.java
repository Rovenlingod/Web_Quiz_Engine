package engine.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UserNotValidException extends RuntimeException {

    public UserNotValidException() {
    }

    public UserNotValidException(String message) {
        super(message);
    }
}
