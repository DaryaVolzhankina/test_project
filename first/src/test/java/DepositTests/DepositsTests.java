package DepositTests;

import exceptions.MoneyAmountException;
import io.qameta.allure.*;
import objects.DebitCard;
import objects.Deposit;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static objects.enums.Banks.SBER;
import static objects.enums.Currencies.RUB;

@Epic("Методы вкладов")
@Feature("Методы класса Deposit")
public class DepositsTests {

    @DataProvider
    public Object[][] checkMoneyAmountData() {
        return new Object[][]{
                {new Deposit("MyDeposit", 100000, RUB, new DebitCard("MyCard", SBER, "1111222233334444", "1234", RUB, 10000)), 100000},
                {new Deposit("MyDeposit", 0, RUB, new DebitCard("MyCard", SBER, "1111222233334444", "1234", RUB, 10000)), 0},
        };
    }

    @DataProvider
    public Object[][] topUpDepositFromCardPositiveData() {
        return new Object[][]{
                {new Deposit("MyDeposit", 100000, RUB, new DebitCard("MyCard", SBER, "1111222233334444", "1234", RUB, 10000)), 9999, 109999, 1},
                {new Deposit("MyDeposit", 0, RUB, new DebitCard("MyCard", SBER, "1111222233334444", "1234", RUB, 10000)), 1, 1, 9999},
                {new Deposit("MyDeposit", 0, RUB, new DebitCard("MyCard", SBER, "1111222233334444", "1234", RUB, 10000)), 10000, 10000, 0},
        };
    }

    @DataProvider
    public Object[][] topUpDepositFromCardNegativeData() {
        return new Object[][]{
                {new Deposit("MyDeposit", 100000, RUB, new DebitCard("MyCard", SBER, "1111222233334444", "1234", RUB, 10000)), 0},
                {new Deposit("MyDeposit", 100000, RUB, new DebitCard("MyCard", SBER, "1111222233334444", "1234", RUB, 10000)), -1},
                {new Deposit("MyDeposit", 100000, RUB, new DebitCard("MyCard", SBER, "1111222233334444", "1234", RUB, 0)), 1},
                {new Deposit("MyDeposit", 100000, RUB, new DebitCard("MyCard", SBER, "1111222233334444", "1234", RUB, -1)), 1},
                {new Deposit("MyDeposit", 100000, RUB, new DebitCard("MyCard", SBER, "1111222233334444", "1234", RUB, 10)), 11},
        };
    }

    @Story("Проверить баланс вклада")
    @Description("Проверка работы метода checkMoneyAmount")
    @Owner(value = "Иванов Иван Иванович")
    @Test(dataProvider = "checkMoneyAmountData", description = "Проверка работы метода checkMoneyAmount")
    public void testPutMoneyDebitPositive(Deposit deposit, int expected) {
        Assert.assertEquals(deposit.checkMoneyAmount(), expected);
    }

    @Story("Пополнить баланс вклада")
    @Description("Проверка работы метода topUpDepositFromCard")
    @Owner(value = "Иванов Иван Иванович")
    @Test(dataProvider = "topUpDepositFromCardPositiveData", description = "Проверка работы метода topUpDepositFromCard")
    public void testTopUpDepositFromCardPositive(Deposit deposit, int sum, int expectedDepositMoneyAmount, int expectedCardMoneyAmount) {
        Assert.assertEquals(deposit.topUpDepositFromCard(sum), expectedDepositMoneyAmount);
        Assert.assertEquals(deposit.getCard().getMoneyAmount(), expectedCardMoneyAmount);
    }

    @Story("Пополнить баланс вклада")
    @Description("Проверка работы метода topUpDepositFromCard")
    @Owner(value = "Иванов Иван Иванович")
    @Test(dataProvider = "topUpDepositFromCardNegativeData", description = "Проверка работы метода topUpDepositFromCard",
            expectedExceptions = MoneyAmountException.class, expectedExceptionsMessageRegExp = "not enough money")
    public void testTopUpDepositFromCardNegative(Deposit deposit, int sum) {
        deposit.topUpDepositFromCard(sum);
    }
}
