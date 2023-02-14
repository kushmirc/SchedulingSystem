package utility;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public interface TimeConversion {

    LocalDateTime zonedToLdtUtc(ZonedDateTime zonedDateTime);
}
