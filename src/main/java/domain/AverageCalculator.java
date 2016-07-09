package domain;

public class AverageCalculator {

    public static Double roundedAverage(Double value, Double currentAverage, Integer numberOfItems) {
        double newAverage = (currentAverage * numberOfItems + value) / (numberOfItems + 1);
        return Double.valueOf(Math.round(newAverage * 2) / 2.0f);
    }
}
