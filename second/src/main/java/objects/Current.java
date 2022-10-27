package objects;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Current{
	private final List<String> weatherDescriptions;
	private final String observationTime;
	private final int windDegree;
	private final int visibility;
	private final List<String> weatherIcons;
	private final int feelslike;
	private final String isDay;
	private final String windDir;
	private final int pressure;
	private final int cloudcover;
	private final int precip;
	private final int uvIndex;
	private final int temperature;
	private final int humidity;
	private final int windSpeed;
	private final int weatherCode;
}
