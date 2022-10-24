package objects;

import exceptions.MoneyAmountException;
import lombok.extern.slf4j.Slf4j;
import objects.enums.Banks;
import objects.enums.Currencies;

@Slf4j
public class DebitCard extends Card {

    public DebitCard(Banks bank, String cardNumber, String pinCode, Currencies currency, int moneyAmount) {
        super(bank, cardNumber, pinCode, currency, moneyAmount);
    }

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

    @Override
    public int putMoney(Cash cash) throws MoneyAmountException {
        this.setMoneyAmount(this.getMoneyAmount() + cash.getSum());
        log.info("putMoney return " + this.getMoneyAmount());
        return this.getMoneyAmount();
    }
}
