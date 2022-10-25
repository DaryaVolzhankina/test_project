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

/**
 * Class with ATM variables and methods
 */
@Data
@Slf4j
public class ATM {

    /**
     * currency
     */
    private final Currencies currency;

    /**
     * bank
     */
    private final Banks bank;

    /**
     * the amount of money in the ATM
     */
    private int limit;

    /**
     * Constructor for creating an instance of the class
     *
     * @param bank     - which bank cards are accepted
     * @param currency - ATM currency
     * @param limit    - the amount of money in the ATM
     */
    public ATM(@NonNull Banks bank, @NonNull Currencies currency, int limit) {
        this.currency = currency;
        this.bank = bank;
        this.limit = limit;

        if (limit < 0) {
            throw new MoneyAmountException("the ATM limit cannot be less than zero");
        }
    }

    /**
     * Method for bank verification
     *
     * @param card - карта
     * @return is the card bank correct
     */
    public boolean checkBank(Card card) throws WrongBankException {
        if (bank.equals(card.getBank())) {
            log.info("check bank was successful");
            return bank.equals(card.getBank());
        } else {
            log.warn("Card of another bank");
            throw new WrongBankException("Card of another bank");
        }
    }

    /**
     * Method for PIN code verification
     *
     * @param card - card
     * @param pin  - pin code
     * @return is the pin code correct
     */
    public boolean checkPinCode(Card card, @NonNull String pin) throws WrongPinCodeException {
        String regex = "^\\d{4}$";

        if (!Pattern.matches(regex, pin) || !card.getPinCode().equals(pin)) {
            throw new WrongPinCodeException("invalid pin code");
        }

        log.info("check pin code was successful");
        return card.getPinCode().equals(pin);
    }

    /**
     * Method for currency verification
     *
     * @param card - card
     * @return is the currency correct
     */
    public boolean checkCurrency(Card card) throws WrongCurrencyException {
        if (currency.equals(card.getCurrency())) {
            log.info("check currency was successful");
            return currency.equals(card.getCurrency());
        } else {
            log.warn("The ATM issues another currency");
            throw new WrongCurrencyException("The ATM issues another currency");
        }
    }

    /**
     * method for reducing the amount of money in an ATM
     *
     * @param sum - withdrawn amount
     * @return the amount of money in the ATM
     */
    public int reduceLimit(int sum) {
        int currentLimit = this.getLimit() - sum;
        this.setLimit(currentLimit);
        log.warn("reduceLimit return " + currentLimit);
        return currentLimit;
    }

    /**
     * method for increasing the amount of money in an ATM
     *
     * @param sum - amount deposited
     * @return the amount of money in the ATM
     */
    public int increaseLimit(int sum) {
        int currentLimit = this.getLimit() + sum;
        this.setLimit(currentLimit);
        log.warn("increaseLimit return " + currentLimit);
        return currentLimit;
    }

    /**
     * method of withdrawing money from the card
     *
     * @param sum  - withdrawn amount
     * @param card - card
     * @return cash
     */
    public Cash withdrawMoney(Card card, int sum) throws MoneyAmountException {
        if (sum <= 0) {
            log.warn("the values cannot be equal to zero or less than zero");
            throw new MoneyAmountException("the values cannot be equal to zero or less than zero");
        } else if (sum > this.getLimit()) {
            log.warn("ATM doesn't have enough money");
            throw new MoneyAmountException("ATM doesn't have enough money");
        }
        return card.withdrawMoney(sum);
    }

    /**
     * method of depositing money on the card
     *
     * @param cash - amount deposited
     * @param card - card
     * @return the amount of money in the card
     */
    public int putMoney(Card card, Cash cash) throws WrongCurrencyException, MoneyAmountException {
        if (!this.getCurrency().equals(cash.getCurrency())) {
            log.warn("The objects.ATM only issues " + this.getCurrency());
            throw new WrongCurrencyException("the ATM does not accept this currency");
        } else if (cash.getSum() <= 0) {
            log.warn("the amount cannot be less than zero or equal to zero ");
            throw new MoneyAmountException("the amount cannot be less than zero or equal to zero");
        }
        return card.putMoney(cash);
    }

    /**
     * method of checking money on the card
     *
     * @param card - card
     * @return the amount of money in the card
     */
    public int checkMoneyAmount(Card card) {
        return card.checkMoneyAmount();
    }

    /**
     * credit limit verification method
     *
     * @param card - card
     * @return creditLimit
     */
    public int checkCreditLimit(CreditCard card) {
        return card.checkCreditLimit();
    }
}

