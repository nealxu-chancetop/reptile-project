import core.framework.inject.Inject;
import core.framework.scheduler.Job;
import core.framework.scheduler.JobContext;
import service.ReptileService;

/**
 * @author Neal
 */
public class FetchJob implements Job {
    @Inject
    ReptileService reptileService;

    @Override
    public void execute(JobContext context) throws Exception {
        reptileService.fetchRestaurant();
//        reptileService.fetchMenu();
    }
}
