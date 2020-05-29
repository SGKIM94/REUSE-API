package reuse.aspect;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reuse.storage.StorageException;

@Slf4j
@ControllerAdvice
public class ErrorExceptionControllerAdvice {
    @ExceptionHandler(Exception.class)
    public ResponseEntity handleMethodException(Exception e) {
        e.printStackTrace();
        log.error(String.valueOf(e.getCause()));

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(StorageException.class)
    public ResponseEntity handleStorageException(StorageException e) {
        e.printStackTrace();
        log.error(String.valueOf(e.getCause()));

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        e.printStackTrace();
        return ResponseEntity.badRequest().body(errorMessage);
    }
}
