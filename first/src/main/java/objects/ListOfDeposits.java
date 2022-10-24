package objects;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import objects.enums.Currencies;
import java.util.Map;

@Slf4j
public class ListOfDeposits {
    private Map<String, Deposit> deposits;

    public ListOfDeposits(Map<String, Deposit> deposits) {
        this.deposits = deposits;
    }

    public Map<String, Deposit> getDeposits(){
        return deposits;
    }

    public void addDepositToList(Map<String, Deposit> deposits,@NonNull String title, int moneyAmount,@NonNull Currencies currency, DebitCard card) {
        deposits.put(title, new Deposit(title, moneyAmount, currency,card));
    }

    public int deleteDeposit(String key){
        DebitCard card = this.getDeposits().get(key).getCard();
        card.setMoneyAmount(card.getMoneyAmount() + this.getDeposits().get(key).getMoneyAmount());
        this.getDeposits().get(key).setMoneyAmount(0);
        deposits.remove(key);
        return card.getMoneyAmount();
    }
}
