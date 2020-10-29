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
        logger.info("this is a info message");
    }

    public void action() {
        logger.info("this is a action message");
        ActionLogContext.put("log_key_1", "key1");
        ActionLogContext.put("log_key_2", "key2");
        ActionLogContext.track("query_use_time", 100L);
        //doing something
        ActionLogContext.track("query_use_time", 50L);
    }

    public void warning() {
        logger.warn("this is a warning message");
    }

    public void error() {
        logger.error(Markers.errorCode("LOG_SERVICE_ERROR"), "this is a error message");
    }

    public void jobException() {
//        throw new MethodNotAllowedException("Can't use this method");
    }

    public void executorError() {
        executor.submit("execute-log-job", () -> {
            logger.info("hello chancetop");
            Threads.sleepRoughly(Duration.ofSeconds(2));
            logger.error(Markers.errorCode("LOG_INNER_JOB_ERROR"), "log inner job error");
        });
    }

    public void customException() {
        throw new CustomException();
    }
}
