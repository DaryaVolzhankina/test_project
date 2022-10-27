package objects.current;

import lombok.Builder;
import lombok.Data;

/**
 * A class with entities passed in response to the address /current
 */
@Data
@Builder
public class Request {

    /**
     * Returns the unit identifier used for this request:
     */
    private final String unit;

    /**
     * Returns the exact location identifier query used for this request.
     */
    private final String query;

    /**
     * Returns the ISO-Code of the language used for this request.
     */
    private final String language;

    /**
     * Returns the ISO-Code of the language used for this request.
     */
    private final String type;
}
