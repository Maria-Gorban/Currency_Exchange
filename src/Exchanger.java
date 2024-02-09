import java.util.HashMap;
import java.util.Map;

public class Exchanger{

private Map<String, Map<String, Double>> rates;
//коллекция-карта
// внешняя карта: ключ типа String --> базовая валюта
// значения типа Map<String, Double> = внутренняя карта: ключ типа String --> валюта, в которую переводим
// значения типа Double --> сам курс перевода


  public Exchanger(){
    rates = new HashMap<>();
  }

  //метод для добавления определенной трансакции
  public void addExchangeRate(String fromCurrency, String toCurrency, double rate) {
    rates.putIfAbsent(fromCurrency,new HashMap<>()); // добавления валюты в коллекцию, если она отсутствует
    rates.get(fromCurrency).put(toCurrency, rate); // позволяет добавлять или обновлять курсы обмена внутри коллекции
  }

  // метод для получения курса обмена между двумя валютами
  public double getExchangeRate(String fromCurr, String toCurr) {
    return rates.getOrDefault(fromCurr, new HashMap<>()).getOrDefault(toCurr, 0.0);
  }
  //сначала пытаемся получить внутреннюю карту для базовой валюты из внешней карты --> если такой нет --> пустая карта
  // пытаемся получить курс обмена для целевой валюты из внутренней карты --> если курса нет --> возвращаем 0.0

  //метод для конвертации валют
  public String convertCurrency(double amount, String fromCurr, String toCurr) {
    double exchangeRate = getExchangeRate(fromCurr, toCurr); //получаем обменный курс для определенных валют
    if (fromCurr.equals(toCurr)) { //если введена одинаковая валюта в обоих случаях, то вернется отношение 1:1
      return String.format("%.2f %s = %.2f %s", amount, fromCurr, amount, toCurr);
    } else {
      double convertedAmount = amount * exchangeRate;
      return String.format("%.2f %s = %.2f %s", amount, fromCurr, convertedAmount, toCurr);
    }
  }
}
