package utility;

import java.util.Locale;
import java.util.ResourceBundle;

public class RBMain {

    public static void main(String[] args) {

        ResourceBundle rb = ResourceBundle.getBundle("Nat", Locale.getDefault());

        if(Locale.getDefault().getLanguage().equals("es") || Locale.getDefault().getLanguage().equals("fr"));
            System.out.println(rb.getString("hello") + " " + rb.getString("world"));
    }
}
