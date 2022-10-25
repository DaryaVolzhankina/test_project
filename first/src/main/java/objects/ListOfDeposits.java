package objects;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import objects.enums.Currencies;

import java.util.Map;

/**
 * A class with methods for a list of deposits
 */
@Slf4j
public class ListOfDeposits {
    private Map<String, Deposit> deposits;

    /**
     * Constructor for creating an instance of the class
     *
     * @param deposits - list of deposits
     */
    public ListOfDeposits(Map<String, Deposit> deposits) {
        this.deposits = deposits;
    }

    public Map<String, Deposit> getDeposits() {
        return deposits;
    }

    /**
     * method of adding deposits to the list
     */
    public void addDepositToList(Map<String, Deposit> deposits, @NonNull String title, int moneyAmount, @NonNull Currencies currency, DebitCard card) {
        deposits.put(title, new Deposit(title, moneyAmount, currency, card));
    }

    /**
     * Deposit closing method
     *
     * @return the amount of money on the card
     */
    public int deleteDeposit(String key) {
        DebitCard card = this.getDeposits().get(key).getCard();
        card.setMoneyAmount(card.getMoneyAmount() + this.getDeposits().get(key).getMoneyAmount());
        this.getDeposits().get(key).setMoneyAmount(0);
        deposits.remove(key);
        return card.getMoneyAmount();
    }
}
