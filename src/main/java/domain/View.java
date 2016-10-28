package domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;

public class View {

    private String isbn;
    private Double averageStars;
    private Integer votes;
    private Map<Integer, Integer> stars;

    public View() {
        this.votes = 0;
        this.averageStars = 0.0;
        this.stars = emptyStarsMap();
    }

    private void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    private void setAverageStars(Double averageStars) {
        this.averageStars = averageStars;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public void updateWith(Map values) {
        String isbn = (String) values.get("id");
        setIsbn(isbn);
        Double star = (Double) values.get("rate");
        Double newAverageStars = AverageCalculator.roundedAverage(star, averageStars, votes);
        setAverageStars(newAverageStars);
        updateCounters(star.intValue());
    }

    private void updateCounters(Integer star) {
        Integer previousNumber = stars.get(star);
        stars.replace(star, previousNumber + 1);
        votes += 1;
    }

    private Map<Integer, Integer> emptyStarsMap() {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        map.put(1, 0);
        map.put(2, 0);
        map.put(3, 0);
        map.put(4, 0);
        map.put(5, 0);
        return map;
    }
}
