package com.clearbill.peermanager.ms.exceptions;

import com.clearbill.peermanager.ms.dto.error.ErrorDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;
import java.util.Collections;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorDTO> exceptionHandler(CustomerNotFoundException exception , HttpServletRequest request){

        ErrorDTO errorDTO = new ErrorDTO();

        LOGGER.error("Validation error :"+ exception.getMessage());

        LOGGER.error(Arrays.toString(exception.getStackTrace()));

        errorDTO.includeErrorType("Not Found").includePath(request.getRequestURI()).includeError(Collections.singletonList(exception.getMessage()));

        LOGGER.error(errorDTO.toString());

        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FriendShipExistException.class)
    public ResponseEntity<ErrorDTO> exceptionHandler(FriendShipExistException exception , HttpServletRequest request){

        ErrorDTO errorDTO = new ErrorDTO();

        LOGGER.error("Validation error :"+ exception.getMessage());

        LOGGER.error(Arrays.toString(exception.getStackTrace()));

        errorDTO.includeErrorType("Bad Request").includePath(request.getRequestURI()).includeError(Collections.singletonList(exception.getMessage()));

        LOGGER.error(errorDTO.toString());

        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidFriendShipActionException.class)
    public ResponseEntity<ErrorDTO> exceptionHandler(InvalidFriendShipActionException exception , HttpServletRequest request){

        ErrorDTO errorDTO = new ErrorDTO();

        LOGGER.error("Validation error :"+ exception.getMessage());

        LOGGER.error(Arrays.toString(exception.getStackTrace()));

        errorDTO.includeErrorType("Bad Request").includePath(request.getRequestURI()).includeError(Collections.singletonList(exception.getMessage()));

        LOGGER.error(errorDTO.toString());

        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FriendShipNotFoundException.class)
    public ResponseEntity<ErrorDTO> exceptionHandler(FriendShipNotFoundException exception , HttpServletRequest request){

        ErrorDTO errorDTO = new ErrorDTO();

        LOGGER.error("Validation error :"+ exception.getMessage());

        LOGGER.error(Arrays.toString(exception.getStackTrace()));

        errorDTO.includeErrorType("Not Found").includePath(request.getRequestURI()).includeError(Collections.singletonList(exception.getMessage()));

        LOGGER.error(errorDTO.toString());

        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }
}
