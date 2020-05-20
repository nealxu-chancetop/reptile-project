import core.framework.http.HTTPClient;
import core.framework.http.HTTPClientBuilder;
import core.framework.module.Module;
import service.ReptileService;

import java.time.Duration;

/**
 * @author Neal
 */
public class ReptileModule extends Module {
    @Override
    protected void initialize() {
        HTTPClient client = new HTTPClientBuilder().timeout(Duration.ofSeconds(30)).build();
        ReptileService reptileService = new ReptileService(client);
        bind(reptileService);

        schedule().fixedRate("FetchJob", bind(FetchJob.class), Duration.ofHours(1));
    }
}
