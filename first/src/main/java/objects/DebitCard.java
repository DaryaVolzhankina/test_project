package objects;

import exceptions.MoneyAmountException;
import lombok.extern.slf4j.Slf4j;
import objects.enums.Banks;
import objects.enums.Currencies;

/**
 * Class with debit card methods and variables
 */
@Slf4j
public class DebitCard extends Card {

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
    public DebitCard(String title, Banks bank, String cardNumber, String pinCode, Currencies currency, int moneyAmount) {
        super(title, bank, pinCode, currency, moneyAmount, cardNumber);
    }

    /**
     * method of withdrawing money from the card
     *
     * @param sum - withdrawn amount
     * @return cash
     */
    @Override
    public Cash withdrawMoney(int sum) throws MoneyAmountException {

        if (sum > this.getMoneyAmount()) {
            log.warn("not enough money on the card");
            throw new MoneyAmountException("not enough money on the card");
        } else {
            this.setMoneyAmount(this.getMoneyAmount() - sum);
            Cash cash = new Cash(sum, this.getCurrency());
            log.info("withdrawMoney return " + cash);
            return cash;
        }
    }

    /**
     * method of depositing money on the card
     *
     * @param cash - amount deposited
     * @return the amount of money in the card
     */
    @Override
    public int putMoney(Cash cash) throws MoneyAmountException {
        this.setMoneyAmount(this.getMoneyAmount() + cash.getSum());
        log.info("putMoney return " + this.getMoneyAmount());
        return this.getMoneyAmount();
    }
}
