package objects.apiError;

import lombok.Builder;
import lombok.Data;

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
