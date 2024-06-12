package projeto.desafio.forumHub.infra.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({RegisterException.class})
    public ResponseEntity<?> registerExceptionHandler(RegisterException exc) {
        DefaultExceptionResponse response = new DefaultExceptionResponse(LocalDateTime.now(), 400, exc.getMessage());
        return ResponseEntity.badRequest().body(response);
    }


    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<?> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exc) {
        List<FieldError> erros = exc.getFieldErrors();

        return ResponseEntity.badRequest().body(
                erros.stream().map(FieldExceptionResponse::new).toList());
    }
}


record DefaultExceptionResponse(LocalDateTime timestamp,
                                Integer status,
                                String error){}
record FieldExceptionResponse(String field, String message) {
    public FieldExceptionResponse(FieldError error) {
        this(error.getField(), error.getDefaultMessage());
    }
}
