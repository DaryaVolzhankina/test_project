package ATMtests;

import io.qameta.allure.*;
import objects.ATM;
import objects.CreditCard;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static objects.enums.Banks.SBER;
import static objects.enums.Currencies.RUB;

/**
 * Тест сьют для checkCreditLimit
 */
@Epic("Методы банкомата")
@Feature("Просмотр кредитного лимита")
public class CheckCreditLimitTests {

    @DataProvider
    public Object[][] checkCreditLimitData() {
        return new Object[][]{
                {new ATM(SBER, RUB, 100000), new CreditCard("MyCard", SBER, "1111222233334444", "1234", RUB, 10000, 19999, 20000, 1.1f), 19999},
                {new ATM(SBER, RUB, 100000), new CreditCard("MyCard", SBER, "1111222233334444", "1234", RUB, 10000, 20000, 20000, 1.1f), 20000}
        };
    }

    @Severity(SeverityLevel.CRITICAL)
    @TmsLink(value = "TL-679")
    @Story("Проверка кредитного лимита")
    @Description("Позитивная проверка работы метода checkCreditLimit")
    @Owner(value = "Иванов Иван Иванович")
    @Test(dataProvider = "checkCreditLimitData", description = "проверка кредитного лимита карты")
    public void testCheckCreditLimitPositive(ATM atm, CreditCard card, int expected) {
        Assert.assertEquals(atm.checkCreditLimit(card), expected);
    }
}
