package app.reptile.api;

import core.framework.api.web.service.GET;
import core.framework.api.web.service.Path;

/**
 * @author Neal
 */
public interface LogWebService {
    @GET
    @Path("/log/info")
    void info();

    @GET
    @Path("/log/action")
    void action();

    @GET
    @Path("/log/warning")
    void warning();

    @GET
    @Path("/log/error")
    void error();

    @GET
    @Path("/log/executor/error")
    void executorError();

    @GET
    @Path("/log/custom-exception")
    void customException();

}
