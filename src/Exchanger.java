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
    rates.putIfAbsent(fromCurrency,new HashMap<>()); // добавления базовой валюты в коллекцию, если она отсутствует --> создаем внешнюю карту
    rates.get(fromCurrency).put(toCurrency, rate); // добавляет сменную валюту и курс, связывая его с ключом от внешней карты --> создание внутренней карты и связка
  }

  // метод для получения курса обмена между двумя валютами
  public double getExchangeRate(String fromCurr, String toCurr) {
    return rates.getOrDefault(fromCurr, new HashMap<>()).getOrDefault(toCurr, 0.0);
  }
  //сначала пытаемся получить внутреннюю карту по ключу внешней карты --> если такой нет --> пустая карта
  //из внутренней карты пытаемся получить курс обмена --> если курса нет --> возвращаем 0.0

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
