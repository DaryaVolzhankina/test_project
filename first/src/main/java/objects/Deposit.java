package objects;

import exceptions.MoneyAmountException;
import exceptions.WrongCurrencyException;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import objects.enums.Currencies;

/**
 * Класс с методами и переменными вклада
 */
@Data
@Slf4j
public class Deposit {
    private final Currencies currency;
    private final String title;
    private final DebitCard card;
    private int moneyAmount;

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
     * Метод проверки баланса вклада
     *
     * @return количество денег на вкладе
     */
    public int checkMoneyAmount() {
        int moneyAmount = this.getMoneyAmount();
        log.info("checkMoneyAmount return " + moneyAmount);
        return moneyAmount;
    }

    /**
     * Метод пополнения вклада с карты
     *
     * @return количество денег на вкладе
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
