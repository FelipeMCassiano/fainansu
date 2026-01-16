package felipemcassiano.fainansu.global;

import felipemcassiano.fainansu.global.exceptions.ErrorMessageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorMessageDTO> handleNotFound(Exception ex) {
        return new ResponseEntity<>(new ErrorMessageDTO(ex.getMessage()), HttpStatus.NOT_FOUND);
    }
}