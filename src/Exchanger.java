import java.util.HashMap;
import java.util.Map;

public class Exchanger{

private Map<String, Map<String, Double>> rates;


  public Exchanger(){
    rates = new HashMap<>();
  }

  //метод для добавления определенной трансакции
  public void addExchangeRate(String fromCurrency, String toCurrency, double rate) {
    rates.putIfAbsent(fromCurrency,new HashMap<>()); // добавления новой валюты в коллекцию, если она отсутствует
    rates.get(fromCurrency).put(toCurrency, rate); // позволяет добавлять или обновлять курсы обмена  внутри коллекции
  }

  // метод для получения курса обмена валют
  public double getExchangeRate(String fromCurr, String toCurr) {
    return rates.getOrDefault(fromCurr, new HashMap<>()).getOrDefault(toCurr, 0.0);
  }

  //метод для конвертации валют
  public String convertCurrency(double amount, String fromCurr, String toCurr) {
    double exchangeRate = getExchangeRate(fromCurr, toCurr);
    if (fromCurr.equals(toCurr)) {
      return String.format("%.2f %s = %.2f %s", amount, fromCurr, amount, toCurr);
    } else {
      double convertedAmount = amount * exchangeRate;
      return String.format("%.2f %s = %.2f %s", amount, fromCurr, convertedAmount, toCurr);
    }
  }
}
