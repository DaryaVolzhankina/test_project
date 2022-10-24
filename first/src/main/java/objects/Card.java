package objects;

import exceptions.WrongPinCodeException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import objects.enums.Banks;
import objects.enums.Currencies;

import java.util.regex.Pattern;

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

    public abstract Cash withdrawMoney(int sum);

    public abstract int putMoney(Cash cash);

    public int checkMoneyAmount() {
        int moneyAmount = this.getMoneyAmount();
        log.info("checkMoneyAmount return " + moneyAmount);
        return moneyAmount;
    }
}
