import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Ahmed Nashaat on 11/30/2017.
 */
public class Connection {
    private static volatile Connection instance = null;

    private HttpURLConnection connection;

    private Connection(String url,String method){
        this.connection = getHttpURLConnection(url,method);
    }
    public void setConnection(HttpURLConnection connection){
        this.connection = connection;
    }

    public  int getStatus() {
        int code = -1;
        try {
            connection.disconnect();
            connection.connect();
            code = connection.getResponseCode();
            return code;
        } catch (Exception e) {
            return code;
        }
    }

    private  HttpURLConnection getHttpURLConnection(String url,String method)  {
        try {
            URL siteURL = new URL(url);
            connection= (HttpURLConnection) siteURL
                    .openConnection();
            connection.setRequestMethod(method);

        } catch (Exception e) {
           return null;
        }

        return connection;
    }
    public static Connection getInstance(String url,String method) {
        if (instance == null) {
            synchronized(Connection.class) {
                if (instance == null) {
                    instance = new Connection(url,method);
                }
            }
        }
        return instance;
    }
    public static void  clear() {
        instance=null;
    }
}
