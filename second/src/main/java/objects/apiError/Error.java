package objects.apiError;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Error{
	private final int code;
	private final String type;
	private final String info;
}
