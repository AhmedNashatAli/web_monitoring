
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * Created by Ahmed Nashaat on 11/30/2017.
 */
public class ConfigrationTest {
    Configuration configration;
    @Before
    public void setUp() throws Exception {
        configration=new Configuration(new String[]{});
    }

    @Test
    public void testConfigurationNotNull() throws Exception
    {
     Assert.assertNotNull(configration.getErrorMessage());
     Assert.assertNotNull(configration.getLogMessage());
     Assert.assertNotNull(configration.getMethod());
     Assert.assertNotNull(configration.getUrl());
     Assert.assertNotNull(configration.getPeriodInMilSeconds());
    }
    @Test
    public void testConfigurationAfterPassingParams() throws Exception
    {
        configration=new Configuration(new String[]{"url=http://localhost:9090/","method=POST","seconds=5","errormessage=test"});
        Assert.assertEquals(configration.getUrl(),"http://localhost:9090/");
        Assert.assertEquals(configration.getMethod(),"POST");
        Assert.assertEquals(configration.getPeriodInMilSeconds(),5000);
        Assert.assertEquals(configration.getErrorMessage(),"test");
    }

}
