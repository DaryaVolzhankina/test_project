package ATMtests;

import io.qameta.allure.*;
import objects.ATM;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static objects.enums.Banks.SBER;
import static objects.enums.Currencies.RUB;

@Epic("Методы банкомата")
@Feature("Уменьшение лимита банкомата")
public class ReduceLimitTests {

    @DataProvider
    public Object[][] reduceLimitData() {
        return new Object[][]{
                {new ATM(SBER, RUB, 100000), 100000, 0},
                {new ATM(SBER, RUB, 100000), 99999, 1}
        };
    }

    @Severity(SeverityLevel.CRITICAL)
    @TmsLink(value = "TL-679")
    @Story("Увеличение лимита банкомата")
    @Description("Позитивная проверка работы метода reduceLimit")
    @Owner(value = "Иванов Иван Иванович")
    @Test(dataProvider = "reduceLimitData", description = "Позитивная проверка работы метода reduceLimit")
    public void testReduceLimit(ATM atm, int sum, int expected) {
        Assert.assertEquals(atm.reduceLimit(sum), expected);
    }
}
