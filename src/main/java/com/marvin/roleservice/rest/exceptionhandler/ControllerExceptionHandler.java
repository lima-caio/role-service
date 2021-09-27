package com.marvin.roleservice.rest.exceptionhandler;

import com.marvin.roleservice.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.marvin.roleservice.rest.dto.ResponseErrorDto;

import java.util.StringJoiner;

import static net.logstash.logback.argument.StructuredArguments.kv;
import static org.springframework.http.HttpStatus.*;

/**
 * Controller Exception Handler to map exceptions to {@link ResponseErrorDto}.
 */
@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    /**
     * Resolves {@link MethodArgumentNotValidException} to send as {@link ResponseErrorDto} with status 400.
     *
     * @param exception exception.
     * @return {@link ResponseErrorDto} containing {@link ResponseErrorDto}.
     */
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseErrorDto> handleBadRequest(MethodArgumentNotValidException exception) {
        final StringJoiner stringJoiner = new StringJoiner(", ");

        exception.getBindingResult().getFieldErrors().forEach(fieldError ->
            stringJoiner.add(fieldError.getField() + " [" + fieldError.getDefaultMessage() + "]"));

        final String message = stringJoiner.toString();

        log.error("{} was captured with {}", kv("exception", exception.getClass().getSimpleName()), kv("message", message));

        return ResponseEntity
            .status(BAD_REQUEST)
            .body(ResponseErrorDto.builder().message(message).build());
    }

    /**
     * Resolves {@link NotFoundException} to send as {@link ResponseErrorDto} with status 404.
     *
     * @param exception exception.
     * @return {@link ResponseErrorDto} containing {@link ResponseErrorDto}.
     */
    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseErrorDto> handleNotFound(Exception exception) {
        return buildResponseEntity(exception, NOT_FOUND);
    }

    /**
     * Resolves {@link NotFoundException} to send as {@link ResponseErrorDto} with status 415.
     *
     * @param exception exception.
     * @return {@link ResponseErrorDto} containing {@link ResponseErrorDto}.
     */
    @ResponseStatus(UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ResponseErrorDto> handleUnsupportedMediaType(Exception exception) {
        return buildResponseEntity(exception, UNSUPPORTED_MEDIA_TYPE);
    }

    /**
     * Resolves {@link Exception} unexpected to send as {@link ResponseErrorDto} with status 500.
     *
     * @param exception exception.
     * @return {@link ResponseErrorDto} containing {@link ResponseErrorDto}.
     */
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseErrorDto> handleInternalServerError(Exception exception) {
        return buildResponseEntity(exception, INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ResponseErrorDto> buildResponseEntity(Exception exception, HttpStatus httpStatus) {
        final String errorMessage = exception.getMessage();

        log.error("{} was captured with {}", kv("exception", exception.getClass().getSimpleName()), kv("errorMessage", errorMessage));

        return ResponseEntity
            .status(httpStatus)
            .body(ResponseErrorDto.builder().message(errorMessage).build());
    }
}
