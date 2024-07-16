package sys.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.time.LocalTime;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(TodoAPIException.class)
    public ResponseEntity<ErrorDetails> handelTODOAPIException(TodoAPIException exception,
                                                               WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);

    };
}
