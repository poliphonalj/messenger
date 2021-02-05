


package org.progmatic.messenger.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//@ControllerAdvice

public class MyControllerAdvice {
    private static final Logger log = LoggerFactory.getLogger(MyControllerAdvice.class);

    @ExceptionHandler(Exception.class)
    public String handleErrors(Exception ex, Model model) {
        log.error("error", ex);
        model.addAttribute("exceptionMessage", ex.getMessage() + " -------" + ex.getCause());
        return "error";
    }
}
