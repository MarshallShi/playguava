package com.dianping.customer.report.biz.exceptions;

/**
 * Created by wenjie.cao on 14-9-3.
 */
public class ValidateException extends RuntimeException{
    public ValidateException() {
    }

    public ValidateException(String message) {
        super(message);
    }

    public ValidateException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidateException(Throwable cause) {
        super(cause);
    }
}
