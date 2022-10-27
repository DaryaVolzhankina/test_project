package objects.current;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * A class with entities passed in response to the address /current
 */
@Data
@Builder
public class Current {

    /**
     * Returns one or more weather description texts associated with the current weather condition.
     */
    private final List<String> weatherDescriptions;

    /**
     * Returns the UTC time for when the returned whether data was collected.
     */
    private final String observationTime;

    /**
     * Returns the wind degree.
     */
    private final int windDegree;

    /**
     * Returns the visibility level in the selected unit. (Default: kilometers)
     */
    private final int visibility;

    /**
     * Returns one or more PNG weather icons associated with the current weather condition.
     */
    private final List<String> weatherIcons;

    /**
     * Returns the "Feels Like" temperature in the selected unit. (Default: Celsius)
     */
    private final int feelslike;

    /**
     * Returns the value is it night or day
     */
    private final String isDay;

    /**
     * Returns the wind direction.
     */
    private final String windDir;

    /**
     * Returns the air pressure in the selected unit. (Default: MB - millibar)
     */
    private final int pressure;

    /**
     * Returns the cloud cover level in percentage.
     */
    private final int cloudcover;

    /**
     * Returns the precipitation level in the selected unit. (Default: MM - millimeters)
     */
    private final int precip;

    /**
     * Returns the UV index associated with the current weather condition.
     */
    private final int uvIndex;

    /**
     * Returns the temperature in the selected unit. (Default: Celsius)
     */
    private final int temperature;

    /**
     * Returns the air humidity level in percentage.
     */
    private final int humidity;

    /**
     * Returns the wind speed in the selected unit. (Default: kilometers/hour)
     */
    private final int windSpeed;

    /**
     * Returns the universal weather condition code associated with the current weather condition.
     */
    private final int weatherCode;
}
