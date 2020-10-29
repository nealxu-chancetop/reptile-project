package app.reptile.web.handler;

import app.reptile.web.exception.CustomException;
import core.framework.api.http.HTTPStatus;
import core.framework.web.ErrorHandler;
import core.framework.web.Request;
import core.framework.web.Response;

import java.util.Optional;

/**
 * @author Neal
 */
public class CustomErrorHandler implements ErrorHandler {
    @Override
    public Optional<Response> handle(Request request, Throwable e) {
        if (e instanceof CustomException) {
            return Optional.of(Response.empty().status(HTTPStatus.BAD_REQUEST));
        }
        return Optional.empty();
    }
}
