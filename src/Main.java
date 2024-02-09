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
    Exchanger exchangeRate = new Exchanger(); // создаем базу данных, которая будет хранить в себе обменные курсы

    // Добавление курсов обмена для разных валют в базу
    exchangeRate.addExchangeRate(Currency.USD.getName(), Currency.EUR.getName(), 0.93);
    exchangeRate.addExchangeRate(Currency.USD.getName(), Currency.JPY.getName(), 149.30);
    exchangeRate.addExchangeRate(Currency.USD.getName(), Currency.GBP.getName(), 0.79);
    exchangeRate.addExchangeRate(Currency.USD.getName(), Currency.RUB.getName(), 91.18);

    exchangeRate.addExchangeRate(Currency.EUR.getName(), Currency.USD.getName(), 1.08);
    exchangeRate.addExchangeRate(Currency.EUR.getName(), Currency.JPY.getName(), 160.93);
    exchangeRate.addExchangeRate(Currency.EUR.getName(), Currency.GBP.getName(), 0.85);
    exchangeRate.addExchangeRate(Currency.EUR.getName(), Currency.RUB.getName(), 98.28);

    exchangeRate.addExchangeRate(Currency.JPY.getName(), Currency.USD.getName(), 0.0067);
    exchangeRate.addExchangeRate(Currency.JPY.getName(), Currency.EUR.getName(), 0.0062);
    exchangeRate.addExchangeRate(Currency.JPY.getName(), Currency.GBP.getName(), 0.0053);
    exchangeRate.addExchangeRate(Currency.JPY.getName(), Currency.RUB.getName(), 0.61);

    exchangeRate.addExchangeRate(Currency.GBP.getName(), Currency.USD.getName(), 1.26);
    exchangeRate.addExchangeRate(Currency.GBP.getName(), Currency.JPY.getName(), 188.36);
    exchangeRate.addExchangeRate(Currency.GBP.getName(), Currency.EUR.getName(), 1.17);
    exchangeRate.addExchangeRate(Currency.GBP.getName(), Currency.RUB.getName(), 115.04);

    exchangeRate.addExchangeRate(Currency.RUB.getName(), Currency.USD.getName(), 0.011);
    exchangeRate.addExchangeRate(Currency.RUB.getName(), Currency.JPY.getName(), 1.64);
    exchangeRate.addExchangeRate(Currency.RUB.getName(), Currency.GBP.getName(), 0.0087);
    exchangeRate.addExchangeRate(Currency.RUB.getName(), Currency.EUR.getName(), 0.010);

    printCurrencies(); // вывод валют

    int choice;
    do {
      System.out.println(
          "\nWould you like to make a transaction (1), see your history (2) or finish any transaction (0)? ");
      choice = scanner.nextInt();

      switch (choice) {
        case 1:
          Currency srcCurr = askAboutCurrency("Enter the currency you want to exchange: ");
          System.out.print("\nEnter the amount you want to change: ");
          double amount = scanner.nextDouble();
          scanner.nextLine();
          Currency trgCurr = askAboutCurrency("Enter the currency you want to have: ");

          String deal = exchangeRate.convertCurrency(amount, srcCurr.getName(), trgCurr.getName());
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


