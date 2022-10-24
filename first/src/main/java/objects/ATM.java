package objects;

import exceptions.MoneyAmountException;
import exceptions.WrongBankException;
import exceptions.WrongCurrencyException;
import exceptions.WrongPinCodeException;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import objects.enums.Banks;
import objects.enums.Currencies;

import java.util.regex.Pattern;

@Data
@Slf4j
public class ATM {
    private final Currencies currency;
    private final Banks bank;
    private int limit;


    public ATM(@NonNull Banks bank, @NonNull Currencies currency, int limit) {
        this.currency = currency;
        this.bank = bank;
        this.limit = limit;

        if(limit < 0){
            throw new MoneyAmountException("the ATM limit cannot be less than zero");
        }
    }

    public boolean checkBank(Card card) throws WrongBankException {
        if (bank.equals(card.getBank())) {
            log.info("check bank was successful");
            return bank.equals(card.getBank());
        } else {
            log.warn("Card of another bank");
            throw new WrongBankException("Card of another bank");
        }
    }

    public boolean checkPinCode(Card card, @NonNull String pin) throws WrongPinCodeException{
        String regex = "^\\d{4}$";

        if (!Pattern.matches(regex, pin) || !card.getPinCode().equals(pin)) {
            throw new WrongPinCodeException("invalid pin code");
        }

        log.info("check pin code was successful");
        return card.getPinCode().equals(pin);
    }

    public boolean checkCurrency(Card card) throws WrongCurrencyException {
        if (currency.equals(card.getCurrency())) {
            log.info("check currency was successful");
            return currency.equals(card.getCurrency());
        } else {
            log.warn("The ATM issues another currency");
            throw new WrongCurrencyException("The ATM issues another currency");
        }
    }

    public int reduceLimit(int sum) {
        int currentLimit = this.getLimit() - sum;
        this.setLimit(currentLimit);
        log.warn("reduceLimit return " + currentLimit);
        return currentLimit;
    }

    public int increaseLimit(int sum) {
        int currentLimit = this.getLimit() + sum;
        this.setLimit(currentLimit);
        log.warn("increaseLimit return " + currentLimit);
        return currentLimit;
    }

    public Cash withdrawMoney(Card card, int sum) throws MoneyAmountException{
        if (sum <= 0) {
            log.warn("the values cannot be equal to zero or less than zero");
            throw new MoneyAmountException("the values cannot be equal to zero or less than zero");
        } else if (sum > this.getLimit()) {
            log.warn("ATM doesn't have enough money");
            throw new MoneyAmountException("ATM doesn't have enough money");
        }
        return card.withdrawMoney(sum);
    }

    public int putMoney(Card card, Cash cash) throws WrongCurrencyException, MoneyAmountException{
        if (!this.getCurrency().equals(cash.getCurrency())) {
            log.warn("The objects.ATM only issues " + this.getCurrency());
            throw new WrongCurrencyException("the ATM does not accept this currency");
        } else if (cash.getSum() <= 0) {
            log.warn("the amount cannot be less than zero or equal to zero ");
            throw new MoneyAmountException("the amount cannot be less than zero or equal to zero");
        }
        return card.putMoney(cash);
    }

    public int checkMoneyAmount(Card card){
        return card.checkMoneyAmount();
    }
    public int checkCreditLimit(CreditCard card){
       return card.checkCreditLimit();
    }
}

