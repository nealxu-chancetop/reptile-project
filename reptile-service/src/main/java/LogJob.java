import app.reptile.service.LogService;
import core.framework.inject.Inject;
import core.framework.scheduler.Job;
import core.framework.scheduler.JobContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Neal
 */
public class LogJob implements Job {
    private final Logger logger = LoggerFactory.getLogger(LogJob.class);
    @Inject
    LogService logService;

    @Override
    public void execute(JobContext context) throws Exception {
        logger.info("this is a job info");
        logService.jobException();
    }
}
