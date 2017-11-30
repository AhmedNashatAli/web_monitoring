import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

/**
 * Created by Ahmed Nashaat on 11/30/2017.
 */
public class Notification {
    public static void displayError(String message){
        JOptionPane pane = new JOptionPane(message, JOptionPane.ERROR_MESSAGE);
        JDialog dialog= pane.createDialog(pane, ""+ Calendar.getInstance().getTime());
        dialog.setVisible(true);

    }
}
