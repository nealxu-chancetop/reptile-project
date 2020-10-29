package app.reptile.web;

import app.reptile.api.LogWebService;
import app.reptile.service.LogService;
import core.framework.inject.Inject;

/**
 * @author Neal
 */
public class LogWebServiceImpl implements LogWebService {
    @Inject
    LogService logService;

    @Override
    public void info() {
        logService.info();
    }

    @Override
    public void action() {
        logService.action();
    }

    @Override
    public void warning() {
        logService.warning();
    }

    @Override
    public void error() {
        logService.error();
    }

    @Override
    public void executorError() {
        logService.executorError();
    }

    @Override
    public void customException() {
        logService.customException();
    }
}
