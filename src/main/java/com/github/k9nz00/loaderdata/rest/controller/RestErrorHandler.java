package com.github.k9nz00.loaderdata.rest.controller;

import org.springdoc.api.ErrorMessage;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorMessage handleError(final AccessDeniedException e) {
        return new ErrorMessage(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorMessage handleError(final IllegalArgumentException e) {
        return new ErrorMessage(e.getMessage());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException e,
                                                                  final HttpHeaders headers,
                                                                  final HttpStatus status,
                                                                  final WebRequest request) {
        return new ResponseEntity<>(new ErrorMessage(getErrorMessage(e)), HttpStatus.BAD_REQUEST);
    }

    private String getErrorMessage(final BindException e) {
        Map<String, Set<String>> errorsMap = e.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.groupingBy( //собираем ошибки в маппу
                                FieldError::getField, //имя филда с ошибкой будет ключем мапы
                                Collectors.mapping(DefaultMessageSourceResolvable::getDefaultMessage, Collectors.toSet())// а значением дефолтное сообщение об ошибке. Причем дальше указывается в какую коллекцию будут сгруппированы сообщения
                        )
                );

        List<String> errorsString = errorsMap.entrySet().stream()
                .map(error -> String.format("%s : [%s]", error.getKey(), String.join("; ", error.getValue())))
                .collect(Collectors.toList());

        errorsString.add(e.getBindingResult().getAllErrors().stream()
                .filter(objectError -> !(objectError instanceof FieldError))
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "))
        );
        return String.join("; ", errorsString);
    }
}
