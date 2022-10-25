package ATMtests;

import exceptions.WrongCurrencyException;
import io.qameta.allure.*;
import objects.ATM;
import objects.CreditCard;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static objects.enums.Banks.SBER;
import static objects.enums.Banks.TINKOFF;
import static objects.enums.Currencies.RUB;
import static objects.enums.Currencies.USD;

@Epic("Методы банкомата")
@Feature("Проверка валюты")
public class CheckCurrencyTests {
    @DataProvider
    public Object[][] checkCurrencyPositiveData() {
        return new Object[][]{
                {new ATM(SBER, RUB, 100000), new CreditCard("MyCard", SBER, "1111222233334444", "1234", RUB, 10000, 10000, 10000, 1.1f), true},
                {new ATM(TINKOFF, USD, 100000), new CreditCard("MyCard", TINKOFF, "1111222233334444", "1234", USD, 10000, 10000, 10000, 1.1f), true}
        };
    }

    @DataProvider
    public Object[][] checkCurrencyNegativeData() {
        return new Object[][]{
                {new ATM(SBER, RUB, 100000), new CreditCard("MyCard", SBER, "1111222233334444", "1234", USD, 10000, 10000, 10000, 1.1f)},
                {new ATM(TINKOFF, USD, 100000), new CreditCard("MyCard", TINKOFF, "1111222233334444", "1234", RUB, 10000, 10000, 10000, 1.1f)}
        };
    }

    @Severity(SeverityLevel.CRITICAL)
    @TmsLink(value = "TL-679")
    @Story("Проверка данных карты")
    @Description("Позитивная проверка работы метода checkCurrency")
    @Owner(value = "Иванов Иван Иванович")
    @Test(dataProvider = "checkCurrencyPositiveData", description = "Позитивная проверка")
    public void testCheckBankPositive(ATM atm, CreditCard card, boolean expected) {
        Assert.assertEquals(atm.checkCurrency(card), expected);
    }

    @Severity(SeverityLevel.CRITICAL)
    @TmsLink(value = "TL-679")
    @Story("Проверка данных карты")
    @Description("Негативная проверка работы метода checkCurrency")
    @Owner(value = "Иванов Иван Иванович")
    @Test(dataProvider = "checkCurrencyNegativeData", expectedExceptions = WrongCurrencyException.class, expectedExceptionsMessageRegExp = "The ATM issues another currency", description = "Негативная проверка")
    public void testCheckBankException(ATM atm, CreditCard card) throws WrongCurrencyException {
        atm.checkCurrency(card);
    }
}
