package org.matusikl.exception;

import java.io.Serializable;

public class DataNotFoundException extends RuntimeException implements Serializable {

    public DataNotFoundException(String message){
        super(message);
    }

}
