import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Ahmed Nashaat on 11/28/2017.
 */
public class Application {
    static String[] args;
    public static void main(String args[]) throws InterruptedException {
        Application.args=args;
        Runnable monitor = new Runnable() {
            public void run() {
                Configuration config = new Configuration(Application.args);
                Connection connection=Connection.getInstance(config.getUrl(),config.getMethod());
                while (true){
                    try {

                        int code=connection.getStatus();
                        MessageFormat mf = new MessageFormat(config.getLogMessage());
                        System.out.println(mf.format(config.getLogMessage(), config.getUrl(),code ));
                        if(code!=200){
                            mf = new MessageFormat(config.getErrorMessage());
                            Notification.displayError(mf.format(config.getErrorMessage(), config.getUrl()));
                        }
                        Thread.sleep(config.getPeriodInMilSeconds());

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };


        new Thread(monitor).start();
    }

}
