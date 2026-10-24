
import java.time.Duration;
import java.time.LocalDateTime;

class FeeCalculator {
    public double calculateFee(int minutes, double rate) {
        int hours = minutes / 60;
        if (minutes % 60 != 0) hours++;
        return hours * rate;
    }

    public double calculateFee(LocalDateTime in,
                               LocalDateTime out,
                               double rate) {
        long minutes = Duration.between(in, out).toMinutes();
        if (minutes < 0) {
            throw new IllegalArgumentException(
                    "Exit cannot precede entry");
        }

        return calculateFee((int) minutes, rate);
    }
}

public class Task08_Polymorphism {
    public static void main(String[] args) {
        FeeCalculator fc = new FeeCalculator();

        System.out.println(fc.calculateFee(130, 30));

        LocalDateTime in =
                LocalDateTime.of(2026, 10, 14, 9, 0);
        LocalDateTime out =
                LocalDateTime.of(2026, 10, 14, 11, 10);

        System.out.println(fc.calculateFee(in, out, 30));
    }
}
