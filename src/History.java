import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

  public class History {
    private List<String> history;

    public History() {
      history = new ArrayList<>();
    }

    // Метод для добавления новой операции в историю
    public void addTransaction(String fromCurr, String toCurr, double amount) {
      LocalDateTime dateTime = LocalDateTime.now(); //получаем актуальное время
      String transaction = String.format("%s/%s/%s/%.2f", dateTime, fromCurr, toCurr, amount);
      history.add(transaction);
    }

    // Метод для получения всей истории
    public List<String> getHistory() {
      return history;
    }
}
