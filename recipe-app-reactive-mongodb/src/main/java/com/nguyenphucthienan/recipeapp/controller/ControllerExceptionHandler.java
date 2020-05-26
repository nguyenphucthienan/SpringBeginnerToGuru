package com.nguyenphucthienan.recipeapp.controller;

import com.nguyenphucthienan.recipeapp.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.thymeleaf.exceptions.TemplateInputException;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFoundException.class, TemplateInputException.class})
    public String handleNotFoundException(Exception exception, Model model) {
        log.error(exception.getMessage());
        model.addAttribute("exception", exception);
        return "404";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WebExchangeBindException.class)
    public String handleNumberFormatException(Exception exception, Model model) {
        log.error(exception.getMessage());
        model.addAttribute("exception", exception);
        return "400";
    }
}
