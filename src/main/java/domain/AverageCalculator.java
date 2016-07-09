package domain;

public class AverageCalculator {

    public static Double roundedAverage(Double value, Double currentAverage, Integer numberOfItems) {
        Double newAverage = (currentAverage * numberOfItems + value) / (numberOfItems + 1);
        return roundToClosestHalf(newAverage);
    }

    private static Double roundToClosestHalf(Double value) {
        return Double.valueOf(Math.round(value * 2) / 2.0f);
    }
}
