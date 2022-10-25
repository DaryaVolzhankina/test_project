package DebitCardTests;

import exceptions.MoneyAmountException;
import io.qameta.allure.*;
import objects.Cash;
import objects.DebitCard;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static objects.enums.Banks.SBER;
import static objects.enums.Currencies.RUB;

@Epic("Методы дебетовой карты")
@Feature("Снять деньги с дебетовки")
public class WithdrawMoneyDebitTests {

    @DataProvider
    public Object[][] withdrawMoneyDebitPositiveData() {
        return new Object[][]{
                {new DebitCard("MyCard", SBER, "1111222233334444", "1234", RUB, 10000), 1, new Cash(1, RUB)},
                {new DebitCard("MyCard", SBER, "1111222233334444", "1234", RUB, 10000), 9999, new Cash(9999, RUB)},
                {new DebitCard("MyCard", SBER, "1111222233334444", "1234", RUB, 10000), 10000, new Cash(10000, RUB)},
        };
    }

    @DataProvider
    public Object[][] withdrawMoneyDebitNegativeData() {
        return new Object[][]{
                {new DebitCard("MyCard", SBER, "1111222233334444", "1234", RUB, 10000), 10001}
        };
    }

    @DataProvider
    public Object[][] moneyAmountAfterMoneyWithdrawal() {
        return new Object[][]{
                {new DebitCard("MyCard", SBER, "1111222233334444", "1234", RUB, 10000), 1, 9999},
                {new DebitCard("MyCard", SBER, "1111222233334444", "1234", RUB, 10000), 9999, 1},
                {new DebitCard("MyCard", SBER, "1111222233334444", "1234", RUB, 10000), 10000, 0}
        };
    }

    @Severity(SeverityLevel.CRITICAL)
    @TmsLink(value = "TL-679")
    @Story("Снятие денег со счета")
    @Description("Позитивная проверка работы метода withdrawMoney")
    @Owner(value = "Иванов Иван Иванович")
    @Test(dataProvider = "withdrawMoneyDebitPositiveData", description = "Позитивная проверка работы метода withdrawMoney")
    public void testWithdrawMoneyDebitPositive(DebitCard card, int sum, Cash expected) {
        Assert.assertEquals(card.withdrawMoney(sum), expected);
    }

    @Severity(SeverityLevel.CRITICAL)
    @TmsLink(value = "TL-679")
    @Story("Снятие денег со счета")
    @Description("Негативная проверка работы метода withdrawMoney")
    @Owner(value = "Иванов Иван Иванович")
    @Test(dataProvider = "withdrawMoneyDebitNegativeData", expectedExceptions = MoneyAmountException.class, expectedExceptionsMessageRegExp = "not enough money on the card", description = "Негативная проверка работы метода withdrawMoney")
    public void testWithdrawMoneyDebitNegative(DebitCard card, int sum) throws MoneyAmountException {
        card.withdrawMoney(sum);
    }

    @Severity(SeverityLevel.CRITICAL)
    @TmsLink(value = "TL-679")
    @Story("Снятие денег со счета")
    @Description("Проверка изменения суммы на счете")
    @Owner(value = "Иванов Иван Иванович")
    @Test(dataProvider = "moneyAmountAfterMoneyWithdrawal", description = "Проверка изменения суммы на счете")
    public void testMoneyAmountAfterMoneyWithdrawal(DebitCard card, int sum, int expected) {
        card.withdrawMoney(sum);
        Assert.assertEquals(card.getMoneyAmount(), expected);
    }

}
