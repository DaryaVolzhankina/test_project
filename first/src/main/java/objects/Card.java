package objects;

import exceptions.WrongPinCodeException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import objects.enums.Banks;
import objects.enums.Currencies;

import java.util.regex.Pattern;

/**
 * Class with card methods and variables
 */
@Data
@AllArgsConstructor
@Slf4j
public abstract class Card {

    private final Currencies currency;
    private final Banks bank;
    private final String cardNumber;
    private final String pinCode;
    private final String title;
    private int moneyAmount;

    /**
     * Constructor for creating an instance of the class
     *
     * @param bank        - which bank serves the card
     * @param currency    - card currency
     * @param title       - name of the card
     * @param pinCode     - pin code
     * @param moneyAmount - amount of money in the account
     * @param cardNumber  - card number
     */
    public Card(@NonNull String title, @NonNull Banks bank, @NonNull String pinCode, @NonNull Currencies currency, int moneyAmount, @NonNull String cardNumber) {
        this.currency = currency;
        this.bank = bank;
        this.moneyAmount = moneyAmount;
        this.cardNumber = cardNumber;
        this.pinCode = pinCode;
        this.title = title;

        String regex = "^\\d{4}$";

        if (!Pattern.matches(regex, pinCode)) {
            throw new WrongPinCodeException("invalid pin code");
        }
    }

    /**
     * method of withdrawing money from the card
     *
     * @param sum - withdrawn amount
     * @return cash
     */
    public abstract Cash withdrawMoney(int sum);

    /**
     * method of depositing money on the card
     *
     * @param cash - amount deposited
     * @return the amount of money in the card
     */
    public abstract int putMoney(Cash cash);

    /**
     * method of checking money on the card
     *
     * @return the amount of money in the card
     */
    public int checkMoneyAmount() {
        int moneyAmount = this.getMoneyAmount();
        log.info("checkMoneyAmount return " + moneyAmount);
        return moneyAmount;
    }
}
