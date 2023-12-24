package uz.pdp.online.springbootapplication.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.pdp.online.springbootapplication.dto.ErrorDTO;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleResourceNotFoundException(ResourceNotFoundException e, HttpServletRequest req) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, req.getRequestURI(), e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest req) {
        Map<String, List<String>> errorBody = e.getFieldErrors().stream()
                .collect(Collectors.groupingBy(FieldError::getField,
                        Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList())));

        return buildErrorResponse(HttpStatus.BAD_REQUEST, req.getRequestURI(), errorBody);
    }

    private ResponseEntity<ErrorDTO> buildErrorResponse(HttpStatus status, String path, Object body) {
        return ResponseEntity
                .status(status)
                .body(ErrorDTO.builder()
                        .errorPath(path)
                        .errorCode(status.value())
                        .errorBody(body)
                        .build());
    }
}
