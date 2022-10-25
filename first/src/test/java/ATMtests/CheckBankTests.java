package ATMtests;

import exceptions.WrongBankException;
import io.qameta.allure.*;
import objects.ATM;
import objects.CreditCard;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static objects.enums.Banks.SBER;
import static objects.enums.Banks.TINKOFF;
import static objects.enums.Currencies.RUB;

@Epic("Методы банкомата")
@Feature("Проверка банкомата")
public class CheckBankTests {

    @DataProvider
    public Object[][] checkBankPositiveData() {
        return new Object[][]{
                {new ATM(SBER, RUB, 100000), new CreditCard("MyCard", SBER, "1111222233334444", "1234", RUB, 10000, 10000, 10000, 1.1f), true},
                {new ATM(TINKOFF, RUB, 100000), new CreditCard("MyCard", TINKOFF, "1111222233334444", "1234", RUB, 10000, 10000, 10000, 1.1f), true}
        };
    }

    @DataProvider
    public Object[][] checkBankNegativeData() {
        return new Object[][]{
                {new ATM(SBER, RUB, 100000), new CreditCard("MyCard", TINKOFF, "1111222233334444", "1234", RUB, 10000, 10000, 10000, 1.1f)},
                {new ATM(TINKOFF, RUB, 100000), new CreditCard("MyCard", SBER, "1111222233334444", "1234", RUB, 10000, 10000, 10000, 1.1f)}};
    }

    @Severity(SeverityLevel.CRITICAL)
    @TmsLink(value = "TL-679")
    @Story("Проверка данных карты")
    @Description("Позитивная проверка работы метода checkBank")
    @Owner(value = "Иванов Иван Иванович")
    @Test(dataProvider = "checkBankPositiveData", description = "Позитивная проверка метода checkBank")
    public void testCheckBankPositive(ATM atm, CreditCard card, boolean expected) {
        Assert.assertEquals(atm.checkBank(card), expected);
    }

    @Severity(SeverityLevel.CRITICAL)
    @TmsLink(value = "TL-679")
    @Story("Проверка данных карты")
    @Description("Негативная проверка работы метода checkBank")
    @Owner(value = "Иванов Иван Иванович")
    @Test(dataProvider = "checkBankNegativeData", expectedExceptions = WrongBankException.class, expectedExceptionsMessageRegExp = "Card of another bank", description = "Негативная проверка метода checkBank")
    public void testCheckBankException(ATM atm, CreditCard card) throws WrongBankException {
        atm.checkBank(card);
    }
}
