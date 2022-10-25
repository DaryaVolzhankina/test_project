package DepositTests;

import io.qameta.allure.*;
import objects.DebitCard;
import objects.Deposit;
import objects.ListOfDeposits;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static objects.enums.Banks.SBER;
import static objects.enums.Currencies.*;

@Epic("Методы вкладов")
@Feature("Методы класса ListOfDeposits")
public class ListOfDepositsTests {

    @Story("Удалить вклад")
    @Description("Проверка работы метода deleteDeposit")
    @Owner(value = "Иванов Иван Иванович")
    @Test(description = "Проверка работы метода deleteDeposit")
    public void testDeleteDeposit() {
        //Создание данных
        ListOfDeposits listOfDeposits = new ListOfDeposits(new HashMap<String, Deposit>());
        Map<String, Deposit> deposits = listOfDeposits.getDeposits();
        listOfDeposits.addDepositToList(deposits, "MyDeposit1", 100000, RUB, new DebitCard("MyCard", SBER, "1111222233334444", "1234", RUB, 10000));
        listOfDeposits.addDepositToList(deposits, "MyDeposit2", 100000, RUB, new DebitCard("MyCard", SBER, "1111222233334444", "1234", RUB, 10000));
        DebitCard card = listOfDeposits.getDeposits().get("MyDeposit1").getCard();

        //Удаление вклада
        listOfDeposits.deleteDeposit("MyDeposit1");

        //Проверка данных
        Assert.assertEquals(card.getMoneyAmount(), 110000);
        Assert.assertEquals(listOfDeposits.getDeposits().keySet().toArray(), new String[]{"MyDeposit2"});
    }
}
