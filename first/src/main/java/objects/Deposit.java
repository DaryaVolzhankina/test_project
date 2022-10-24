package objects;

import exceptions.MoneyAmountException;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import objects.enums.Currencies;

@Data
@Slf4j
public class Deposit {
    private final Currencies currency;
    private final String title;
    private final DebitCard card;
    private int moneyAmount;

    public Deposit(@NonNull String title, int moneyAmount,@NonNull Currencies currency, @NonNull DebitCard card){
        this.currency = currency;
        this.title = title;
        this.moneyAmount = moneyAmount;
        this.card = card;
    }

    public int checkMoneyAmount() {
        int moneyAmount = this.getMoneyAmount();
        log.info("checkMoneyAmount return " + moneyAmount);
        return moneyAmount;
    }

    public int putMoney(DebitCard card, int sum) throws MoneyAmountException {
        if(card.getMoneyAmount()<=0 || sum <=0 || card.getMoneyAmount()<sum){
            throw new MoneyAmountException("not enough money");
        }else{
            this.setMoneyAmount(this.getMoneyAmount() + sum);
            return this.getMoneyAmount();
        }
    }
}
