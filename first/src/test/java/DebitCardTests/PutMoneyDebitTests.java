package DebitCardTests;

import io.qameta.allure.*;
import objects.Cash;
import objects.DebitCard;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static objects.enums.Banks.SBER;
import static objects.enums.Currencies.RUB;

@Epic("Методы дебетовой карты")
@Feature("Положить деньги на дебетовку")
public class PutMoneyDebitTests {
    @DataProvider
    public Object[][] putMoneyDebitPositiveData() {
        return new Object[][]{
                {new DebitCard("MyCard", SBER, "1111222233334444", "1234", RUB, 10000), new Cash(1, RUB), 10001},
                {new DebitCard("MyCard", SBER, "1111222233334444", "1234", RUB, 10000), new Cash(9999, RUB), 19999},
                {new DebitCard("MyCard", SBER, "1111222233334444", "1234", RUB, 10000), new Cash(10000, RUB), 20000},
        };
    }

    @TmsLink(value = "TL-679")
    @Story("Положить деньги на счет")
    @Description("Позитивная проверка работы метода putMoney")
    @Owner(value = "Иванов Иван Иванович")
    @Test(dataProvider = "putMoneyDebitPositiveData", description = "Позитивная проверка работы метода putMoney")
    public void testPutMoneyDebitPositive(DebitCard card, Cash sum, int expected) {
        Assert.assertEquals(card.putMoney(sum), expected);
    }
}
