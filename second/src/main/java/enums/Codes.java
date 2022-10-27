package enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Список с кодами ответа
 */
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
public enum Codes {
    invalid_missing_access_key(101),
    missing_query(601),
    invalid_unit(606),
    request_failed(615);

    private final int value;
}
