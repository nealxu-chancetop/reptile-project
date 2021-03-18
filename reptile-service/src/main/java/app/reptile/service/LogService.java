package app.reptile.service;

import app.reptile.web.exception.CustomException;
import core.framework.async.Executor;
import core.framework.inject.Inject;
import core.framework.log.ActionLogContext;
import core.framework.log.Markers;
import core.framework.util.Threads;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

/**
 * @author Neal
 */
public class LogService {
    private final Logger logger = LoggerFactory.getLogger(LogService.class);
    @Inject
    Executor executor;

    public void info() {
        logger.trace("this is a trace message -- Don't output");
        logger.info("this is a info message");
    }

    public void action() {
        logger.info("this is a action message");
        ActionLogContext.put("log_key_1", "key1");
        ActionLogContext.put("log_key_2", "key2");
        //start
        //call service1
        ActionLogContext.track("response_data_size", 100L);
        //doing something
        ActionLogContext.track("response_data_size", 50L);
    }

    public void warning() {
        logger.warn("this is a warning message1");
        logger.warn(Markers.errorCode("WARN_ERROR_CODE"), "this is a warning message2");
    }

    public void error() {
        logger.error(Markers.errorCode("LOG_SERVICE_ERROR"), "this is a error message");
    }

    public void executorError() {
        logger.info("this is a execute message");
        executor.submit("execute-log-job", () -> {
            logger.info("inner job execute-log-job run");
            Threads.sleepRoughly(Duration.ofSeconds(2)); //mock method invoke
            executor.submit("execute-inner-log-job", () -> logger.error(Markers.errorCode("LOG_INNER_JOB_ERROR"), "log inner job error"));
        });
    }

    public void customException() {
        throw new CustomException();
    }
}
