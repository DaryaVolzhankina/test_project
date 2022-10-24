package objects;

import exceptions.MoneyAmountException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import objects.enums.Banks;
import objects.enums.Currencies;

@Slf4j
public class CreditCard extends Card {

    @Getter
    @Setter
    private int creditLimit;

    @Getter
    private final int maxCreditLimit;

    public CreditCard(Banks bank, String cardNumber, String pinCode, Currencies currency, int moneyAmount, int creditLimit, int maxCreditLimit) throws MoneyAmountException {
        super(bank, cardNumber, pinCode, currency, moneyAmount);
        this.creditLimit = creditLimit;
        this.maxCreditLimit = maxCreditLimit;

        if (maxCreditLimit < creditLimit) {
            throw new MoneyAmountException("Credit limit cannot be greater than the maximum value");
        }
    }

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

    public int checkCreditLimit() {
        int creditLimit = this.getCreditLimit();
        log.info("checkCreditLimit return " + creditLimit);
        return creditLimit;
    }
}
