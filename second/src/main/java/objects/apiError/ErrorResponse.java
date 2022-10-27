package objects.apiError;

import lombok.Builder;
import lombok.Data;

/**
 * A class with entities passed in response to an error in the request
 */
@Data
@Builder
public class ErrorResponse{
	private final boolean success;
	private final Error error;
}
