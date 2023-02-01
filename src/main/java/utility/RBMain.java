package utility;

import java.util.Locale;
import java.util.ResourceBundle;

public class RBMain {

    public static void main(String[] args) {

        ResourceBundle rb = ResourceBundle.getBundle("utility/Nat", Locale.getDefault());

        if(Locale.getDefault().getLanguage().equals("es_ES") || Locale.getDefault().getLanguage().equals("fr"));
            System.out.println(rb.getString("hello") + " " + rb.getString("world"));
    }
}
