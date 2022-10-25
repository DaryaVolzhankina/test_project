package ATMtests;

import exceptions.WrongPinCodeException;
import io.qameta.allure.*;
import objects.ATM;
import objects.DebitCard;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static objects.enums.Banks.SBER;
import static objects.enums.Currencies.RUB;

@Epic("Методы банкомата")
@Feature("Проверка пин-кода")
public class CheckPinCodeTest {
    @DataProvider
    public Object[][] checkPinCodePositiveData() {
        return new Object[][]{
                {new ATM(SBER, RUB, 100000), new DebitCard("MyCard", SBER, "1111222233334444", "1234", RUB, 10000), "1234", true},
                {new ATM(SBER, RUB, 100000), new DebitCard("MyCard", SBER, "1111222233334444", "4563", RUB, 10000), "4563", true}
        };
    }

    @DataProvider
    public Object[][] checkPinCodeNegativeData() {
        return new Object[][]{
                {new ATM(SBER, RUB, 100000), new DebitCard("MyCard", SBER, "1111222233334444", "1234", RUB, 10000), " "},
                {new ATM(SBER, RUB, 100000), new DebitCard("MyCard", SBER, "1111222233334444", "1234", RUB, 10000), ""},
                {new ATM(SBER, RUB, 100000), new DebitCard("MyCard", SBER, "1111222233334444", "1234", RUB, 10000), "123"},
                {new ATM(SBER, RUB, 100000), new DebitCard("MyCard", SBER, "1111222233334444", "1234", RUB, 10000), "1234 "},
                {new ATM(SBER, RUB, 100000), new DebitCard("MyCard", SBER, "1111222233334444", "1234", RUB, 10000), "1235"}
        };
    }

    @Severity(SeverityLevel.CRITICAL)
    @TmsLink(value = "TL-679")
    @Story("Проверка данных карты")
    @Description("Позитивная проверка работы метода checkPinCode")
    @Owner(value = "Иванов Иван Иванович")
    @Test(dataProvider = "checkPinCodePositiveData", description = "Подходящий пин-код")
    public void testPinCodePositive(ATM atm, DebitCard card, String pin, boolean expected) {
        Assert.assertEquals(atm.checkPinCode(card, pin), expected);
    }

    @Severity(SeverityLevel.CRITICAL)
    @TmsLink(value = "TL-679")
    @Story("Проверка данных карты")
    @Description("Негативная проверка работы метода checkPinCode")
    @Owner(value = "Иванов Иван Иванович")
    @Test(dataProvider = "checkPinCodeNegativeData", expectedExceptions = WrongPinCodeException.class, expectedExceptionsMessageRegExp = "invalid pin code", description = "Невалидный пин-код")
    public void testCheckPinCodeException(ATM atm, DebitCard card, String pin) throws WrongPinCodeException {
        atm.checkPinCode(card, pin);
    }
}
