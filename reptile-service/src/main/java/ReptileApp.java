import core.framework.module.App;
import core.framework.module.SystemModule;
import core.framework.mongo.module.MongoConfig;
import domain.Restaurant;

/**
 * @author Neal
 */
public class ReptileApp extends App {
    @Override
    protected void initialize() {
        load(new SystemModule("sys.properties"));

        MongoConfig config = config(MongoConfig.class);
        config.uri(requiredProperty("sys.mongo.uri"));
        config.collection(Restaurant.class);

        load(new ReptileModule());
    }
}
