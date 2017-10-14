import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.yhguodu.iot.common.exception.IotException;
import org.yhguodu.iot.sdk.KeyProperties;


import static org.junit.Assert.assertEquals;

/**
 * Created by Administrator on 2017/10/14.
 */
public class KeyPropertiesTest {

    private KeyProperties properties;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        properties = new KeyProperties("src/test/resources/application.properties");
    }

    @Test
    public void testLoadProperties() throws IotException {
//        expectedException.expect(IotException.class);
//        expectedException.expectMessage(ExceptionMeta.FileNotFound.getMessage());
        properties.loadProperties();
        assertEquals("localhost",properties.getProperty("iot.server.host","111"));
    }

    @Test
    public void testStroreProperties() throws IotException {
        properties.addProperty("hello","world");
        properties.loadProperties();
        properties.saveToPropertiesFile();
    }
}
