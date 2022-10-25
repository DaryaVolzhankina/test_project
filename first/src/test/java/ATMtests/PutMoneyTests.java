package ATMtests;

import exceptions.MoneyAmountException;
import exceptions.WrongCurrencyException;
import io.qameta.allure.*;
import objects.ATM;
import objects.Cash;
import objects.DebitCard;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static objects.enums.Banks.SBER;
import static objects.enums.Currencies.*;

/**
 * Тест сьют для метода putMoney
 */
@Epic("Методы банкомата")
@Feature("Добавление денег на счет")
public class PutMoneyTests {
    @DataProvider
    public Object[][] putMoneyPositiveData() {
        return new Object[][]{
                {new ATM(SBER, RUB, 100000), new DebitCard("MyCard",SBER, "1111222233334444", "1234", RUB, 10000), new Cash(1, RUB), 10001},
                {new ATM(SBER, RUB, 100000), new DebitCard("MyCard",SBER, "1111222233334444", "1234", RUB, 10000), new Cash(10000, RUB), 20000}
        };
    }

    @DataProvider
    public Object[][] putMoneyMoneyAmountException() {
        return new Object[][]{
                {new ATM(SBER, RUB, 100000), new DebitCard("MyCard",SBER, "1111222233334444", "1234", RUB, 10000), new Cash(0, RUB)},
                {new ATM(SBER, RUB, 100000), new DebitCard("MyCard",SBER, "1111222233334444", "1234", RUB, 10000), new Cash(-10000, RUB)}
        };
    }

    @DataProvider
    public Object[][] putMoneyWrongCurrencyException() {
        return new Object[][]{
                {new ATM(SBER, RUB, 100000), new DebitCard("MyCard",SBER, "1111222233334444", "1234", RUB, 10000), new Cash(100, USD)},
                {new ATM(SBER, RUB, 100000), new DebitCard("MyCard",SBER, "1111222233334444", "1234", RUB, 10000), new Cash(100, EUR)}
        };
    }

    @Severity(SeverityLevel.CRITICAL)
    @TmsLink(value = "TL-679")
    @Story("Положить деньги на счет")
    @Description("Позитивная проверка работы метода putMoney")
    @Owner(value = "Иванов Иван Иванович")
    @Test(dataProvider = "putMoneyPositiveData", description = "Позитивная проверка работы метода putMoney")
    public void testPutMoneyPositiveData(ATM atm, DebitCard card, Cash cash, int expected) {
        Assert.assertEquals(atm.putMoney(card, cash), expected);
    }

    @Severity(SeverityLevel.CRITICAL)
    @TmsLink(value = "TL-679")
    @Story("Положить деньги на счет")
    @Description("Негативная проверка работы метода putMoney, эксепшен WrongCurrencyException")
    @Owner(value = "Иванов Иван Иванович")
    @Test(dataProvider = "putMoneyWrongCurrencyException", expectedExceptions = WrongCurrencyException.class, expectedExceptionsMessageRegExp = "the ATM does not accept this currency", description = "Негативная проверка работы метода putMoney")
    public void testPutMoneyWrongCurrencyException(ATM atm, DebitCard card, Cash cash) {
        atm.putMoney(card, cash);
    }

    @Severity(SeverityLevel.CRITICAL)
    @TmsLink(value = "TL-679")
    @Story("Положить деньги на счет")
    @Description("Негативная проверка работы метода putMoney, эксепшен MoneyAmountException")
    @Owner(value = "Иванов Иван Иванович")
    @Test(dataProvider = "putMoneyMoneyAmountException", expectedExceptions = MoneyAmountException.class, expectedExceptionsMessageRegExp = "the amount cannot be less than zero or equal to zero", description = "Негативная проверка работы метода putMoney")
    public void testPutMoneyMoneyAmountException(ATM atm, DebitCard card, Cash cash) {
        atm.putMoney(card, cash);
    }
}
