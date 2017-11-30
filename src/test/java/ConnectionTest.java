
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.bio.SocketConnector;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by Ahmed Nashaat on 11/30/2017.
 */
public class ConnectionTest {
    Server server;
    private Integer PORT = 12345;
    private String HOST="localhost";

    @Before
    public void setUp() throws Exception {
        server = new Server(PORT);
        new WebAppContext(server, "src/main/resources/webapp/", "/" );
        SocketConnector connector = new SocketConnector();
        connector.setMaxIdleTime(1000 * 60 * 60);
        connector.setSoLingerTime(-1);
        connector.setPort(PORT);
        connector.setHost(HOST);
        server.setConnectors(new Connector[]{connector});
        server.setStopAtShutdown( true );

        server.start();
        PORT = connector.getLocalPort();
    }

    @After
    public void serverDown() throws Exception {
        server.stop();
    }


    @Test
    public void testSuccessGet() throws Exception
    {
        Connection.clear();
        Connection connection=Connection.getInstance("http://"+HOST+":"+PORT,"GET");
        Assert.assertEquals( connection.getStatus(), 200);

    }
    @Test
    public void testFaliureGet() throws Exception
    {
        Connection.clear();
        Connection connection=Connection.getInstance("http://"+HOST,"GET");
        Assert.assertEquals( connection.getStatus(), -1);

    }
    @Test
    public void testSuccessPost() throws Exception
    {
        Connection.clear();
        Connection connection=Connection.getInstance("http://"+HOST+":"+PORT,"POST");
        Assert.assertEquals( connection.getStatus(), 200);

    }
    @Test
    public void testFaliurePost() throws Exception
    {
        Connection.clear();
        Connection connection=Connection.getInstance("http://"+HOST,"POST");
        Assert.assertEquals( connection.getStatus(), -1);

    }
}
