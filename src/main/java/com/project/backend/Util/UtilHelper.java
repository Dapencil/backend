package com.project.backend.Util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UtilHelper {

    public static ResponseEntity exceptionMapper(Exception e){
        if(e instanceof IllegalArgumentException)
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        else if(e instanceof NoSuchElementException)
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
