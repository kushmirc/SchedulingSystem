package utility;

import java.sql.SQLException;

/** Interface DisplayNotification is used to create a display notification method.
 * It is a functional interface and has one method for displaying a notification.
 * @author Kush Mirchandani*/
public interface DisplayNotification {

    /** This method is used to display a confirmation window. */
    void confirmation() throws SQLException;
}
