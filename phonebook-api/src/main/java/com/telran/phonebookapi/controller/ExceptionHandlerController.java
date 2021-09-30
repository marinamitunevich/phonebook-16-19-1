package com.telran.phonebookapi.controller;

import com.telran.phonebookapi.dto.errorsdto.ErrorDto;
import com.telran.phonebookapi.dto.errorsdto.FieldErrorDto;
import com.telran.phonebookapi.service.exceptions.AddressNotFoundException;
import com.telran.phonebookapi.service.exceptions.ContactNotFoundException;
import com.telran.phonebookapi.service.exceptions.EmailNotFountException;
import com.telran.phonebookapi.service.exceptions.PhoneNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<FieldErrorDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        List<FieldErrorDto> fieldErrorDtos = new ArrayList<>();
        List<ObjectError> errors = e.getBindingResult().getAllErrors();

        for (ObjectError error : errors) {
            FieldErrorDto fieldErrorDto;
            if (error instanceof FieldError)
                fieldErrorDto = new FieldErrorDto(((FieldError) error).getField(), error.getDefaultMessage());
            else
                fieldErrorDto = new FieldErrorDto(error.getObjectName(), error.getDefaultMessage());
            fieldErrorDtos.add(fieldErrorDto);
        }

        return fieldErrorDtos;
    }

    @ExceptionHandler(ContactNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleContactNotFoundException(ContactNotFoundException e) {

        return new ErrorDto(e.getMessage());
    }

    @ExceptionHandler(EmailNotFountException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleEmailNotFoundException(EmailNotFountException e) {

        return new ErrorDto(e.getMessage());
    }

    @ExceptionHandler(AddressNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleAddressNotFoundException(AddressNotFoundException e) {

        return new ErrorDto(e.getMessage());
    }

    @ExceptionHandler(PhoneNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handlePhoneNotFoundException(PhoneNotFoundException e) {

        return new ErrorDto(e.getMessage());
    }
}
