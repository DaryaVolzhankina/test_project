package objects.current;

import lombok.Builder;
import lombok.Data;

/**
 * A class with entities passed in response to the address /current
 */
@Data
@Builder
public class ResponseModel {

    /**
     * Константа типа Request, в которую передаются константы из класса Request
     */
    private final Request request;

    /**
     * Константа типа Current, в которую передаются константы из класса Current
     */
    private final Current current;

    /**
     * Константа типа Location, в которую передаются константы из класса Location
     */
    private final Location location;
}
