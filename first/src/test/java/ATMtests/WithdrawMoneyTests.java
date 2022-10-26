package ATMtests;

import exceptions.MoneyAmountException;
import io.qameta.allure.*;
import objects.ATM;
import objects.Cash;
import objects.DebitCard;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static objects.enums.Banks.SBER;
import static objects.enums.Currencies.RUB;

/**
 * Тест сьют для метода withdrawMoney
 */
@Epic("Методы банкомата")
@Feature("Снять деньги с карты")
public class WithdrawMoneyTests {
    @DataProvider
    public Object[][] withdrawMoneyPositiveData() {
        return new Object[][]{
                {new ATM(SBER, RUB, 100000), new DebitCard("MyCard", SBER, "1111222233334444", "1234", RUB, 10000), 1, new Cash(1, RUB)},
                {new ATM(SBER, RUB, 100000), new DebitCard("MyCard", SBER, "1111222233334444", "1234", RUB, 10000), 9999, new Cash(9999, RUB)},
                {new ATM(SBER, RUB, 100000), new DebitCard("MyCard", SBER, "1111222233334444", "1234", RUB, 10000), 10000, new Cash(10000, RUB)},
        };
    }

    @DataProvider
    public Object[][] withdrawMoneyAmountEqualToZeroLessThanZero() {
        return new Object[][]{
                {new ATM(SBER, RUB, 100000), new DebitCard("MyCard", SBER, "1111222233334444", "1234", RUB, 10000), 0},
                {new ATM(SBER, RUB, 100000), new DebitCard("MyCard", SBER, "1111222233334444", "1234", RUB, 10000), -1000}
        };
    }

    @DataProvider
    public Object[][] withdrawMoneyAmountGreaterThanATMLimit() {
        return new Object[][]{
                {new ATM(SBER, RUB, 1000), new DebitCard("MyCard", SBER, "1111222233334444", "1234", RUB, 10000), 1001},
                {new ATM(SBER, RUB, 1000), new DebitCard("MyCard", SBER, "1111222233334444", "1234", RUB, 10000), 10000}
        };
    }

    @Severity(SeverityLevel.CRITICAL)
    @TmsLink(value = "TL-679")
    @Story("Снятие денег со счета")
    @Description("Позитивная проверка работы метода withdrawMoney")
    @Owner(value = "Иванов Иван Иванович")
    @Test(dataProvider = "withdrawMoneyPositiveData", description = "Позитивная проверка работы метода withdrawMoney")
    public void testWithdrawMoneyPositive(ATM atm, DebitCard card, int sum, Cash expected) {
        Assert.assertEquals(atm.withdrawMoney(card, sum), expected);
    }

    @Severity(SeverityLevel.CRITICAL)
    @TmsLink(value = "TL-679")
    @Story("Снятие денег со счета")
    @Description("Негативная проверка работы метода withdrawMoney, expectedExceptionsMessageRegExp =\"the values cannot be equal to zero or less than zero\"")
    @Owner(value = "Иванов Иван Иванович")
    @Test(dataProvider = "withdrawMoneyAmountEqualToZeroLessThanZero", expectedExceptions = MoneyAmountException.class, expectedExceptionsMessageRegExp = "the values cannot be equal to zero or less than zero", description = "Негативная проверка работы метода withdrawMoney")
    public void testWithdrawMoneyAmountEqualToZeroLessThanZero(ATM atm, DebitCard card, int sum) {
        atm.withdrawMoney(card, sum);
    }

    @Severity(SeverityLevel.CRITICAL)
    @TmsLink(value = "TL-679")
    @Story("Снятие денег со счета")
    @Description("Негативная проверка работы метода withdrawMoney, expectedExceptionsMessageRegExp = \"ATM doesn't have enough money\"")
    @Owner(value = "Иванов Иван Иванович")
    @Test(dataProvider = "withdrawMoneyAmountGreaterThanATMLimit", expectedExceptions = MoneyAmountException.class, expectedExceptionsMessageRegExp = "ATM doesn't have enough money", description = "Негативная проверка работы метода withdrawMoney")
    public void testWithdrawMoneyAmountGreaterThanATMLimit(ATM atm, DebitCard card, int sum) {
        atm.withdrawMoney(card, sum);
    }
}
