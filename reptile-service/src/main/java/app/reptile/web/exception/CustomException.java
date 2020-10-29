package app.reptile.web.exception;

import core.framework.log.ErrorCode;
import core.framework.log.Severity;

/**
 * @author Neal
 */
public class CustomException extends RuntimeException implements ErrorCode {
    @Override
    public String errorCode() {
        return "CUSTOM_EXCEPTION";
    }

    @Override
    public Severity severity() {
        return Severity.ERROR;
    }
}
