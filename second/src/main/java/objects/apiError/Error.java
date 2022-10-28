package objects.apiError;

import lombok.Builder;
import lombok.Data;

/**
 * A class with entities passed in response to an error in the request
 */
@Data
@Builder
public class Error{

	/**
	 * Returns the code number
	 */
	private final int code;

	/**
	 * Returns the error type
	 */
	private final String type;

	/**
	 * Returns the error info
	 */
	private final String info;
}
