package objects.current;

import lombok.Builder;
import lombok.Data;

/**
 * A class with entities passed in response to the address /current
 */
@Data
@Builder
public class Location {

    /**
     * Returns the local time of the location used for this request.
     */
    private final String localtime;

    /**
     * Returns the UTC offset (in hours) of the timezone associated with the location used for this request
     */
    private final String utcOffset;

    /**
     * Returns the country name associated with the location used for this request.
     */
    private final String country;

    /**
     * Returns the local time (as UNIX timestamp) of the location used for this request.
     */
    private final int localtimeEpoch;

    /**
     * Returns the name of the location used for this request.
     */
    private final String name;

    /**
     * Returns the timezone ID associated with the location used for this request.
     */
    private final String timezoneId;

    /**
     * Returns the longitude coordinate associated with the location used for this request.
     */
    private final String lon;

    /**
     * Returns the latitude coordinate associated with the location used for this request.
     */
    private final String lat;

    /**
     * Returns the region name associated with the location used for this request.
     */
    private final String region;
}
