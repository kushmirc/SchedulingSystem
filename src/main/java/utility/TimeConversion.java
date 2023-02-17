package utility;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

/** Interface TimeConversion is used to create a method for converting date and time values.
 * It is a functional interface and has one method for converting a LocalDateTime value to a ZonedDateTime value.
 * @author Kush Mirchandani*/
public interface TimeConversion {

    /** This method is used to convert a LocalDateTime value to a ZonedDateTime value.
     * @param localDateTime the localDateTime value to be converted*/
    ZonedDateTime ldtToZoned(LocalDateTime localDateTime);
}
