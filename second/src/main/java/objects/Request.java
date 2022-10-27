package objects;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Request{
	private final String unit;
	private final String query;
	private final String language;
	private final String type;
}
