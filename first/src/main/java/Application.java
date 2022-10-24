import exceptions.*;
import objects.*;
import objects.enums.Banks;
import objects.enums.Currencies;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        ATM atm = new ATM(Banks.SBER, Currencies.RUB, 15000);
        Card card = new CreditCard("MyCard", Banks.SBER, "1111222233334444", "1234", Currencies.RUB, 10000, 10000, 10001, 1.1f);
        Cash cash = new Cash(1000, Currencies.RUB);

        boolean continueApp1 = true;
        boolean continueApp2 = false;

        while (continueApp1) {
            try {
                atm.checkBank(card);
            } catch (WrongBankException e) {
                e.printStackTrace();
                break;
            }

            try {
                atm.checkCurrency(card);
            } catch (WrongCurrencyException e) {
                e.printStackTrace();
                break;
            }

            Scanner s = new Scanner(System.in);
            String pin = s.nextLine();

            try {
                atm.checkPinCode(card, pin);
                continueApp2 = true;
            } catch (WrongPinCodeException e) {
                e.printStackTrace();
            }

            while (continueApp2) {
                boolean isCreditCard = card instanceof CreditCard;
                if (isCreditCard) {
                    System.out.println("1 - withdraw, 2 - put, 3 - check money amount, 4 - exit, 5 - check credit limit");
                } else {
                    System.out.println("1 - withdraw, 2 - put, 3 - check money amount, 4 - exit");
                }
                int choice = s.nextInt();
                switch (choice) {
                    case 1:
                        int sum = s.nextInt();
                        try {
                            atm.withdrawMoney(card, sum);
                            atm.reduceLimit(sum);
                        } catch (MoneyAmountException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case 2:
                        try {
                            atm.putMoney(card, cash);
                            atm.increaseLimit(cash.getSum());
                        } catch (MoneyAmountException | WrongCurrencyException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case 3:
                        atm.checkMoneyAmount(card);
                        break;
                    case 4:
                        continueApp1 = false;
                        continueApp2 = false;
                        break;
                    case 5:
                        if (isCreditCard) {
                            atm.checkCreditLimit((CreditCard) card);
                            break;
                        } else {
                            throw new WrongActionException("you entered the wrong number");
                        }
                    default:
                        throw new WrongActionException("you entered the wrong number");
                }
            }
        }
    }
}
