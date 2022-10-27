package objects.apiError;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse{
	private final boolean success;
	private final Error error;
}
