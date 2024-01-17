package domain;

import java.time.LocalDate;

public class Child extends Person {
    private String certificateNumber;
    private LocalDate issueDate;
    private RegisterOffice registerOffice;

    public Child(String name, String surName, String middleName, LocalDate dageOfBirth, Address address) {
        super(name, surName, middleName, dageOfBirth,address);

    }

    public Child(String name, String surname, String patronymic, LocalDate dageOfBirth) {
        super(name, surname, patronymic, dageOfBirth);
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public RegisterOffice getRegisterOffice() {
        return registerOffice;
    }

    public void setRegisterOffice(RegisterOffice registerOffice) {
        this.registerOffice = registerOffice;
    }

}
