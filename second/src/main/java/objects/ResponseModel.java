package objects;

import lombok.Builder;
import lombok.Data;
import objects.Current;
import objects.Location;
import objects.Request;

@Data
@Builder
public class ResponseModel{
	private final Request request;
	private final Current current;
	private final Location location;
}
