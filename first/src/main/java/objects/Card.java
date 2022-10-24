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
    private int moneyAmount;

    public Card(@NonNull Banks bank, @NonNull String cardNumber, @NonNull String pinCode, @NonNull Currencies currency, int moneyAmount) {
        this.currency = currency;
        this.bank = bank;
        this.moneyAmount = moneyAmount;
        this.cardNumber = cardNumber;
        this.pinCode = pinCode;

        String regex = "^\\d{4}$";

        if (!Pattern.matches(regex, pinCode)) {
            throw new WrongPinCodeException("invalid pin code");
        }
    }

    public abstract Cash withdrawMoney(int sum);
    public abstract int putMoney(Cash cash);
    public int checkMoneyAmount(){
        int moneyAmount = this.getMoneyAmount();
        log.info("checkMoneyAmount return " + moneyAmount);
        return moneyAmount;
    };
}
