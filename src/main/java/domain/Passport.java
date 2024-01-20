package domain;

import java.time.LocalDate;

public class Passport {
    private String series;
    private String number;

    private LocalDate issueDate;

    public Passport() {
    }

    public Passport(String series, String number, LocalDate issueDate) {
        this.series = series;
        this.number = number;
        this.issueDate = issueDate;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }
}
