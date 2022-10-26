package objects;

import exceptions.MoneyAmountException;
import exceptions.WrongCurrencyException;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import objects.enums.Currencies;

/**
 * Class with deposit methods and variables
 */
@Data
@Slf4j
public class Deposit {
    private final Currencies currency;
    private final String title;
    private final DebitCard card;
    private int moneyAmount;

    /**
     * Constructor for creating an instance of the class
     *
     * @param currency    - deposit currency
     * @param title       - name of the deposit
     * @param moneyAmount - amount of money in the deposit
     * @param card - the card to which the deposit is linked
     */
    public Deposit(@NonNull String title, int moneyAmount, @NonNull Currencies currency, @NonNull DebitCard card) {
        this.currency = currency;
        this.title = title;
        this.moneyAmount = moneyAmount;
        this.card = card;

        if (!card.getCurrency().equals(currency)) {
            throw new WrongCurrencyException("currencies don't match");
        }
    }

    /**
     * Method of checking the deposit balance
     *
     * @return amount of money in the deposit
     */
    public int checkMoneyAmount() {
        int moneyAmount = this.getMoneyAmount();
        log.info("checkMoneyAmount return " + moneyAmount);
        return moneyAmount;
    }

    /**
     * deposit replenishment method
     *
     * @return amount of money in the deposit
     */
    public int topUpDepositFromCard(int sum) throws MoneyAmountException {
        if (card.getMoneyAmount() <= 0 || sum <= 0 || card.getMoneyAmount() < sum) {
            throw new MoneyAmountException("not enough money");
        } else {
            this.setMoneyAmount(this.getMoneyAmount() + sum);
            card.setMoneyAmount(card.getMoneyAmount() - sum);
            return this.getMoneyAmount();
        }
    }
}
