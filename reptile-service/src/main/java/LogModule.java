import app.reptile.api.LogWebService;
import app.reptile.service.LogService;
import app.reptile.web.LogWebServiceImpl;
import core.framework.module.Module;

import java.time.Duration;

/**
 * @author Neal
 */
public class LogModule extends Module {
    @Override
    protected void initialize() {
        bind(LogService.class);

        api().service(LogWebService.class, bind(LogWebServiceImpl.class));
        schedule().fixedRate("LogJob", bind(LogJob.class), Duration.ofHours(1));
    }
}
