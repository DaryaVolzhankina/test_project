package objects;

import exceptions.MoneyAmountException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import objects.enums.Banks;
import objects.enums.Currencies;

/**
 * Class with credit card methods and variables
 */
@Slf4j
public class CreditCard extends Card {

    @Getter
    @Setter
    private int creditLimit;

    @Getter
    private final int maxCreditLimit;

    @Getter
    private final float interestRate;

    /**
     * Constructor for creating an instance of the class
     *
     * @param bank        - which bank serves the card
     * @param currency    - card currency
     * @param title       - name of the card
     * @param pinCode     - pin code
     * @param moneyAmount - amount of money in the account
     * @param creditLimit  - card number
     * @param cardNumber  - card number
     * @param creditLimit  - credit limit
     * @param maxCreditLimit  - max credit limit
     * @param interestRate  - interest rate
     */
    public CreditCard(String title,Banks bank, String cardNumber, String pinCode, Currencies currency, int moneyAmount, int creditLimit, int maxCreditLimit,float interestRate) throws MoneyAmountException {
        super(title, bank, pinCode, currency, moneyAmount, cardNumber);
        this.creditLimit = creditLimit;
        this.maxCreditLimit = maxCreditLimit;
        this.interestRate = interestRate;

        if (maxCreditLimit < creditLimit) {
            throw new MoneyAmountException("Credit limit cannot be greater than the maximum value");
        }
    }

    /**
     * method of withdrawing money from the card
     *
     * @param sum - withdrawn amount
     * @return cash
     */
    @Override
    public Cash withdrawMoney(int sum) throws MoneyAmountException {

        if (sum > (this.getMoneyAmount() + this.getCreditLimit())) {
            log.warn("not enough money on the card");
            throw new MoneyAmountException("not enough money on the card");
        } else {
            if (sum > this.getMoneyAmount()) {
                this.setCreditLimit(this.getMoneyAmount() + this.getCreditLimit() - sum);
                this.setMoneyAmount(0);
            } else {
                this.setMoneyAmount(this.getMoneyAmount() - sum);
            }
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
        if (this.maxCreditLimit > this.creditLimit) {
            int requiredAmount = this.maxCreditLimit - this.creditLimit;
            if (requiredAmount < cash.getSum()) {
                int remainingAmount = cash.getSum() - requiredAmount;
                this.setCreditLimit(this.getCreditLimit() + requiredAmount);
                this.setMoneyAmount(getMoneyAmount() + remainingAmount);
            } else {
                this.setCreditLimit(this.getCreditLimit() + cash.getSum());
            }
        } else {
            this.setMoneyAmount(this.getMoneyAmount() + cash.getSum());
        }
        log.info("putMoney return " + this.getMoneyAmount());
        return this.getMoneyAmount();
    }

    /**
     * credit limit verification method
     *
     * @return creditLimit
     */
    public int checkCreditLimit() {
        int creditLimit = this.getCreditLimit();
        log.info("checkCreditLimit return " + creditLimit);
        return creditLimit;
    }

    /**
     * the method of checking the debt on the loan
     *
     * @return loan debt
     */
    public int checkLoanDebt() {
        int loanDebt = this.maxCreditLimit - this.creditLimit;
        log.info("checkCreditLimit return " + loanDebt);
        return loanDebt;
    }
}
