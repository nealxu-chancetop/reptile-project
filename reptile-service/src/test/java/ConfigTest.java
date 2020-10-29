import org.junit.jupiter.api.Test;

import static core.framework.test.Assertions.assertConfDirectory;

/**
 * @author mort
 */
class ConfigTest extends IntegrationTest {
    @Test
    void conf() {
        assertConfDirectory().overridesDefaultResources();
    }
}
