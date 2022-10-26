package objects;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import objects.enums.Currencies;

/**
 * Class with Cash methods and variables
 */
@Data
@Builder
public class Cash {
    private int sum;
    private final Currencies currency;

    /**
     * Constructor for creating an instance of the class
     *
     * @param sum     - amount of money
     * @param currency - monetary currency
     */
    public Cash(int sum, @NonNull Currencies currency) {
        this.sum = sum;
        this.currency = currency;
    }
}
