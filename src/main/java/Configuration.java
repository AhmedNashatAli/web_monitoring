import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.security.spec.ECField;
import java.util.Properties;

/**
 * Created by Ahmed Nashaat on 11/30/2017.
 */
public class Configuration implements Serializable {

    private String errorMessage;
    private String logMessage;
    private String url;
    private String method;
    private int periodInSeconds;

    public Configuration(String args[]) {
        Properties prop = getPropertiesFile();
        if (prop == null) {
            System.out.print("Sorry, unable to load configuration.\r\n");
            System.exit(0);
        }
        errorMessage = prop.getProperty("error.message");
        logMessage = prop.getProperty("log.message");
        url = prop.getProperty("url");
        method = prop.getProperty("method");
        periodInSeconds = Integer.parseInt(prop.getProperty("period.seconds"));
        if(args!=null&&args.length>0)overRideConfig(args);
    }

    private void overRideConfig(String args[]){
        for (String arg:args) {
            String splits[] = arg.split("=");
            String argName = splits[0].toLowerCase().trim();
            try {
                switch (argName) {
                    case "method":
                        method = splits[1];
                        break;
                    case "seconds":
                        periodInSeconds = Integer.parseInt(splits[1]);
                        break;
                    case "url":
                        url = splits[1];
                        break;
                    case "errormessage":
                        errorMessage = splits[1];
                        break;
                    default:
                        System.out.println("Error parsing no parameter for " + argName);
                }
            } catch (Exception e) {
                System.out.println("Error parsing no parameter for " + argName);
            }
        }
    }

    private Properties getPropertiesFile() {
        Properties prop = new Properties();
        InputStream input = null;

        try {

            String filename = "config.properties";
            input = Configuration.class.getClassLoader().getResourceAsStream(filename);
            if (input == null) {
                System.out.print("\r\nSorry, unable to find " + filename + "\r\n");
                System.exit(0);
            }
            prop.load(input);
            return prop;

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }

    public int getPeriodInMilSeconds() {
        return periodInSeconds*1000;
    }

    public String getLogMessage() {
        return logMessage;
    }
}
