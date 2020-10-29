import core.framework.test.module.AbstractTestModule;

/**
 * @author mort
 */
public class TestModule extends AbstractTestModule {
    @Override
    protected void initialize() {
        load(new ReptileApp());
    }
}
