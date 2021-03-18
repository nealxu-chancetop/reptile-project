import core.framework.scheduler.Job;
import core.framework.scheduler.JobContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Neal
 */
public class LogJob implements Job {
    private final Logger logger = LoggerFactory.getLogger(LogJob.class);

    @Override
    public void execute(JobContext context) {
        logger.info("this is a job info");
    }
}
