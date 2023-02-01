package utility;

import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

public class RBMain {

    public static void language() {

        try{
        ResourceBundle rb = ResourceBundle.getBundle("Nat", Locale.getDefault());

        if(Locale.getDefault().getLanguage().equals("es") || Locale.getDefault().getLanguage().equals("fr"));
            System.out.println(rb.getString("hello") + " " + rb.getString("world"));
    }
        catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        }

    public static void zone() {
        ZoneId zone = ZoneId.systemDefault();
        System.out.println(zone);
    }
}
