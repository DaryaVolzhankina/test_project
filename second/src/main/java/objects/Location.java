package objects;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Location{
	private final String localtime;
	private final String utcOffset;
	private final String country;
	private final int localtimeEpoch;
	private final String name;
	private final String timezoneId;
	private final String lon;
	private final String region;
	private final String lat;

}
