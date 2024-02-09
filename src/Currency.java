public enum Currency {
  USD("usd"),
  EUR("eur"),
  JPY("jpy"),
  GBP("gbp"),
  RUB("rub");

  private String name;

  Currency(String name) { //конструктор с параметром
    this.name = name;
  }

  public String getName() { // геттер
    return name;
  }

  // метод для поиска введенной валюты
  // если ввод некорректен, будет выброшено исключение
  public static Currency findCurrency(String cur) throws IllegalArgumentException {
    for (Currency currency : Currency.values()) {
      if (currency.getName().equalsIgnoreCase(cur)) {
        return currency;
      }
    }
    throw new IllegalArgumentException("incorrect input");
  }
}
