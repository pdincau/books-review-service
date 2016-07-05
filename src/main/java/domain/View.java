package domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class View {

    private String isbn;
    private Integer zeroStars;
    private Double averageStars;
    private Integer oneStars;
    private Integer twoStars;
    private Integer threeStars;
    private Integer fourStars;
    private Integer fiveStars;
    private Integer votes;

    public View() {
        this.votes = 0;
        this.averageStars = 0.0;
        this.zeroStars = 0;
        this.oneStars = 0;
        this.twoStars = 0;
        this.threeStars = 0;
        this.fourStars = 0;
        this.fiveStars = 0;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setAverageStars(Double averageStars) {
        this.averageStars = averageStars;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
