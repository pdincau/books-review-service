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

    public View() {
        this.zeroStars = 0;
        this.averageStars = 0.0;
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

    public void setZeroStars(Integer zeroStars) {
        this.zeroStars = zeroStars;
    }

    public void setOneStars(Integer oneStars) {
        this.oneStars = oneStars;
    }

    public void setTwoStars(Integer twoStars) {
        this.twoStars = twoStars;
    }

    public void setThreeStars(Integer threeStars) {
        this.threeStars = threeStars;
    }

    public void setFourStars(Integer fourStars) {
        this.fourStars = fourStars;
    }

    public void setFiveStars(Integer fiveStars) {
        this.fiveStars = fiveStars;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
