package CreditCardTests;

import exceptions.MoneyAmountException;
import io.qameta.allure.*;
import objects.Cash;
import objects.CreditCard;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static objects.enums.Banks.SBER;
import static objects.enums.Currencies.RUB;

/**
 * Тест сьют для метода withdrawMoney
 */
@Epic("Методы кредитной карты")
@Feature("Снять деньги с кредитки")
public class WithdrawMoneyCreditTests {

    @DataProvider
    public Object[][] withdrawMoneyCreditPositiveData() {
        return new Object[][]{
                {new CreditCard("MyCard", SBER, "1111222233334444", "1234", RUB, 10000, 10000, 10001, 1.1f), 1, new Cash(1, RUB)},
                {new CreditCard("MyCard", SBER, "1111222233334444", "1234", RUB, 10000, 10000, 10001, 1.1f), 9999, new Cash(9999, RUB)},
                {new CreditCard("MyCard", SBER, "1111222233334444", "1234", RUB, 10000, 10000, 10001, 1.1f), 10000, new Cash(10000, RUB)},
        };
    }

    @DataProvider
    public Object[][] sumGreaterThanMoneyAmount() {
        return new Object[][]{
                {new CreditCard("MyCard", SBER, "1111222233334444", "1234", RUB, 10000, 10000, 10001, 1.1f), 10001, 0, 9999},
                {new CreditCard("MyCard", SBER, "1111222233334444", "1234", RUB, 1, 10000, 10001, 1.1f), 10001, 0, 0},
                {new CreditCard("MyCard", SBER, "1111222233334444", "1234", RUB, 0, 10000, 10001, 1.1f), 10000, 0, 0},
                {new CreditCard("MyCard", SBER, "1111222233334444", "1234", RUB, 0, 10000, 10001, 1.1f), 9999, 0, 1}
        };
    }

    @DataProvider
    public Object[][] sumLessThanMoneyAmount() {
        return new Object[][]{
                {new CreditCard("MyCard", SBER, "1111222233334444", "1234", RUB, 10000, 10000, 10001, 1.1f), 9999, 1, 10000},
                {new CreditCard("MyCard", SBER, "1111222233334444", "1234", RUB, 1, 10000, 10001, 1.1f), 1, 0, 10000}
        };
    }

    @DataProvider
    public Object[][] withdrawMoneyCreditNegativeData() {
        return new Object[][]{
                {new CreditCard("MyCard", SBER, "1111222233334444", "1234", RUB, 10000, 10000, 10001, 1.1f), 20001},
                {new CreditCard("MyCard", SBER, "1111222233334444", "1234", RUB, 0, 1, 10001, 1.1f), 2}
        };
    }

    @Severity(SeverityLevel.CRITICAL)
    @TmsLink(value = "TL-679")
    @Story("Снятие денег со счета")
    @Description("Позитивная проверка работы метода withdrawMoney")
    @Owner(value = "Иванов Иван Иванович")
    @Test(dataProvider = "withdrawMoneyCreditPositiveData", description = "Позитивная проверка работы метода withdrawMoney")
    public void testWithdrawMoneyCreditPositive(CreditCard card, int sum, Cash expected) {
        Assert.assertEquals(card.withdrawMoney(sum), expected);
    }

    @Severity(SeverityLevel.CRITICAL)
    @TmsLink(value = "TL-679")
    @Story("Снятие денег со счета")
    @Description("Проверка изменения кредитного лимита и суммы денег при sum > moneyAmount")
    @Owner(value = "Иванов Иван Иванович")
    @Test(dataProvider = "sumGreaterThanMoneyAmount", description = "Проверка изменения кредитного лимита и суммы денег при sum > moneyAmount")
    public void testWithdrawMoneySumGreaterThanMoneyAmount(CreditCard card, int sum, int expectedMoneyAmount, int expectedCreditLimit) {
        card.withdrawMoney(sum);
        Assert.assertEquals(card.getMoneyAmount(), expectedMoneyAmount);
        Assert.assertEquals(card.getCreditLimit(), expectedCreditLimit);
    }

    @Severity(SeverityLevel.CRITICAL)
    @TmsLink(value = "TL-679")
    @Story("Снятие денег со счета")
    @Description("Проверка изменения суммы денег при sum < moneyAmount")
    @Owner(value = "Иванов Иван Иванович")
    @Test(dataProvider = "sumLessThanMoneyAmount", description = "Проверка изменения суммы денег при sum < moneyAmount")
    public void testWithdrawMoneySumLessThanMoneyAmount(CreditCard card, int sum, int expectedMoneyAmount, int expectedCreditLimit) {
        card.withdrawMoney(sum);
        Assert.assertEquals(card.getMoneyAmount(), expectedMoneyAmount);
        Assert.assertEquals(card.getCreditLimit(), expectedCreditLimit);
    }

    @Severity(SeverityLevel.CRITICAL)
    @TmsLink(value = "TL-679")
    @Story("Снятие денег со счета")
    @Description("Негативная проверка работы метода withdrawMoney")
    @Owner(value = "Иванов Иван Иванович")
    @Test(dataProvider = "withdrawMoneyCreditNegativeData", expectedExceptions = MoneyAmountException.class, expectedExceptionsMessageRegExp = "not enough money on the card", description = "Негативная проверка работы метода withdrawMoney")
    public void testWithdrawMoneyCreditNegative(CreditCard card, int sum) throws MoneyAmountException {
        card.withdrawMoney(sum);
    }
}
