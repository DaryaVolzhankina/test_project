package ATMtests;

import io.qameta.allure.*;
import objects.ATM;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static objects.enums.Banks.SBER;
import static objects.enums.Currencies.RUB;

@Epic("Методы банкомата")
@Feature("Увеличение лимита банкомата")
public class IncreaseLimitTests {

    @DataProvider
    public Object[][] increaseLimitData() {
        return new Object[][]{
                {new ATM(SBER, RUB, 100000), 1, 100001},
                {new ATM(SBER, RUB, 0), 1, 1}
        };
    }

    @Severity(SeverityLevel.CRITICAL)
    @TmsLink(value = "TL-679")
    @Story("Увеличение лимита банкомата")
    @Description("Позитивная проверка работы метода increaseLimit")
    @Owner(value = "Иванов Иван Иванович")
    @Test(dataProvider = "increaseLimitData", description = "увеличение лимита банкомата")
    public void testIncreaseLimit(ATM atm, int sum, int expected) {
        Assert.assertEquals(atm.increaseLimit(sum), expected);
    }
}
