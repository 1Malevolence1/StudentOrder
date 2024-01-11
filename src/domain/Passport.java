package domain;

public class Passport {
    private String series;
    private String number;

    public Passport() {
    }

    public Passport(String series, String number) {
        this.series = series;
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
