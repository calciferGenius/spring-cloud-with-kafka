package com.sn.saba_source.exception;/**
 * Created by pc-001 on 7/12/18.
 */

/**
 * Created by Kier pc-001 on 7/12/18.
 */

public class JodaDateUtilsException extends GenericException {

    public JodaDateUtilsException() {
        super("Failed with date operation");
    }

    public JodaDateUtilsException(String error) {
        super(error);
    }
}
