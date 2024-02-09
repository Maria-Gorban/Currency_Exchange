
import java.util.List;
import java.util.Scanner;

public class Main {

  public static void printCurrencies() {
    Currency[] currencies = Currency.values();
    System.out.println("Currencies of our bank");
    int i = 1;
    for (Currency currency : currencies) {
      System.out.println(i + ". " + currency);
      i++;
    }
  }

  public static Currency askAboutCurrency(String message) {
    Scanner scanner = new Scanner(System.in);
    while (true) {
      System.out.print("\n" + message);
      try {
        return Currency.findCurrency(scanner.nextLine());
      } catch (IllegalArgumentException e) {
        System.out.println("Error: " + e.getMessage());
      }
    }
  }

  public static void main(String[] args) {
    History transactions = new History(); // объект класса History, который будет хранить в себе историю трансакций
    Scanner scanner = new Scanner(System.in);
    Exchanger exchangeRates = new Exchanger(); // создаем базу данных, которая будет хранить в себе обменные курсы

    // Добавление курсов обмена для разных валют в базу
    exchangeRates.addExchangeRate(Currency.USD.getName(), Currency.EUR.getName(), 0.93);
    exchangeRates.addExchangeRate(Currency.USD.getName(), Currency.JPY.getName(), 149.30);
    exchangeRates.addExchangeRate(Currency.USD.getName(), Currency.GBP.getName(), 0.79);
    exchangeRates.addExchangeRate(Currency.USD.getName(), Currency.RUB.getName(), 91.18);

    exchangeRates.addExchangeRate(Currency.EUR.getName(), Currency.USD.getName(), 1.08);
    exchangeRates.addExchangeRate(Currency.EUR.getName(), Currency.JPY.getName(), 160.93);
    exchangeRates.addExchangeRate(Currency.EUR.getName(), Currency.GBP.getName(), 0.85);
    exchangeRates.addExchangeRate(Currency.EUR.getName(), Currency.RUB.getName(), 98.28);

    exchangeRates.addExchangeRate(Currency.JPY.getName(), Currency.USD.getName(), 0.0067);
    exchangeRates.addExchangeRate(Currency.JPY.getName(), Currency.EUR.getName(), 0.0062);
    exchangeRates.addExchangeRate(Currency.JPY.getName(), Currency.GBP.getName(), 0.0053);
    exchangeRates.addExchangeRate(Currency.JPY.getName(), Currency.RUB.getName(), 0.61);

    exchangeRates.addExchangeRate(Currency.GBP.getName(), Currency.USD.getName(), 1.26);
    exchangeRates.addExchangeRate(Currency.GBP.getName(), Currency.JPY.getName(), 188.36);
    exchangeRates.addExchangeRate(Currency.GBP.getName(), Currency.EUR.getName(), 1.17);
    exchangeRates.addExchangeRate(Currency.GBP.getName(), Currency.RUB.getName(), 115.04);

    exchangeRates.addExchangeRate(Currency.RUB.getName(), Currency.USD.getName(), 0.011);
    exchangeRates.addExchangeRate(Currency.RUB.getName(), Currency.JPY.getName(), 1.64);
    exchangeRates.addExchangeRate(Currency.RUB.getName(), Currency.GBP.getName(), 0.0087);
    exchangeRates.addExchangeRate(Currency.RUB.getName(), Currency.EUR.getName(), 0.010);

    printCurrencies(); // вывод валют

    int choice;
    do {
      System.out.println(
          "\nWould you like to make a transaction (1), view your history (2) or finish any transaction (0)? ");
      choice = scanner.nextInt();

      switch (choice) {
        case 1:
          Currency srcCurr = askAboutCurrency("Enter the currency you want to exchange: ");
          System.out.print("\nEnter the amount you want to change: ");
          double amount = scanner.nextDouble();
          scanner.nextLine();
          Currency trgCurr = askAboutCurrency("Enter the currency you want to have: ");

          String deal = exchangeRates.convertCurrency(amount, srcCurr.getName(), trgCurr.getName());
          System.out.println("\n" + deal);
          transactions.addTransaction(srcCurr.getName(), trgCurr.getName(), amount);
          break;

        case 2:
          List<String> allTransactions = transactions.getHistory();
          for (String transaction : allTransactions) {
            System.out.println(transaction);
          }
          break;
      }
    } while (choice != 0);
    System.out.println("Thank you!");
  }
}


