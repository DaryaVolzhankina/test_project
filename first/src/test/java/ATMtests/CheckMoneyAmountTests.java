package ATMtests;

import io.qameta.allure.*;
import objects.ATM;
import objects.Card;
import objects.CreditCard;
import objects.DebitCard;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static objects.enums.Banks.SBER;
import static objects.enums.Banks.TINKOFF;
import static objects.enums.Currencies.RUB;

@Epic("Методы банкомата")
@Feature("Просмотр суммы денег на счете")
public class CheckMoneyAmountTests {
    @DataProvider
    public Object[][] checkMoneyAmountData() {
        return new Object[][]{
                {new ATM(SBER, RUB, 100000), new CreditCard("MyCard", SBER, "1111222233334444", "1234", RUB, 10000, 20000, 20000, 1.1f), 10000},
                {new ATM(TINKOFF, RUB, 100000), new DebitCard("MyCard", TINKOFF, "1111222233334444", "1234", RUB, 0), 0}
        };
    }

    @Severity(SeverityLevel.CRITICAL)
    @TmsLink(value = "TL-679")
    @Story("Проверка денег на счету")
    @Description("Позитивная проверка работы метода checkMoneyAmount")
    @Owner(value = "Иванов Иван Иванович")
    @Test(dataProvider = "checkMoneyAmountData", description = "проверка денег на счету")
    public void testCheckMoneyAmountPositive(ATM atm, Card card, int expected) {
        Assert.assertEquals(atm.checkMoneyAmount(card), expected);
    }
}
