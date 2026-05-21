package com.javautn.roma.shared.handler;
import com.javautn.roma.shared.dto.ErrorResponseDto;
import com.javautn.roma.shared.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleNotFoundException(NotFoundException exception){
        logger.warn(exception.getMessage());

        return ResponseEntity.status(404).body(
                new ErrorResponseDto(404, exception.getMessage())
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleValidation(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .findFirst()
                .orElse("Invalid request");

        return ResponseEntity.badRequest().body(
                new ErrorResponseDto(400, message)
        );
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponseDto> handleBadRequest(BadRequestException exception){
        logger.warn(exception.getMessage());

        return ResponseEntity.status(400).body(
                new ErrorResponseDto(400, exception.getMessage())
        );
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponseDto> handleConflict(ConflictException exception){
        logger.warn(exception.getMessage());

        return ResponseEntity.status(409).body(
                new ErrorResponseDto(409, exception.getMessage())
        );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponseDto> handleDataIntegrityViolation(DataIntegrityViolationException exception){
        logger.warn(exception.getMessage());

        return ResponseEntity.status(409).body(
                new ErrorResponseDto(409, "Database constraint violation")
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidRequestBody(HttpMessageNotReadableException exception){
        logger.warn(exception.getMessage());

        return ResponseEntity.status(400).body(
                new ErrorResponseDto(400, "Invalid request body")
        );
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorResponseDto> handleForbidden(ForbiddenException exception){
        logger.warn(exception.getMessage());

        return ResponseEntity.status(403).body(
                new ErrorResponseDto(403, exception.getMessage())
        );
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponseDto> handleUnauthorized(UnauthorizedException exception){
        logger.warn(exception.getMessage());

        return ResponseEntity.status(401).body(
                new ErrorResponseDto(401, exception.getMessage())
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleException(Exception ex){
        logger.error("Unexpected error occurred", ex);

        return ResponseEntity.status(500).body(
                new ErrorResponseDto(500, "Unexpected error occurred")
        );
    }

}
