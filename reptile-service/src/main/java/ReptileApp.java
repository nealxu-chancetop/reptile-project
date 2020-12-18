import app.reptile.web.handler.CustomErrorHandler;
import core.framework.module.App;
import core.framework.module.SystemModule;

/**
 * @author Neal
 */
public class ReptileApp extends App {
    @Override
    protected void initialize() {
        load(new SystemModule("sys.properties"));
        executor().add();
        http().errorHandler(bind(CustomErrorHandler.class));

        load(new LogModule());
    }
}
