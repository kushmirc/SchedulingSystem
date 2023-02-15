package utility;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

/** Interface TimeConversion is used to create a method for converting date and time values.
 * It is a functional interface and has one method for converting a ZonedDateTime value to a LocalDateTime value.
 * @author Kush Mirchandani*/
public interface TimeConversion {

    /** This method is used to convert a ZonedDateTime value to a LocalDateTime value.
     * @param zonedDateTime the zonedDateTime value to be converted*/
    LocalDateTime zonedToLdtUtc(ZonedDateTime zonedDateTime);
}
