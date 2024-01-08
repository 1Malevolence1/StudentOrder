package domain;

import java.time.LocalDate;

abstract public class Person {
    private String name;
    private String surname;
    private String patronymic;

    private LocalDate dageOfBirth;

    private Address address;

    public Person(String name, String surname, String patronymic, LocalDate dageOfBirth, Address address
                  ) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.dageOfBirth = dageOfBirth;
        this.address = address;
    }

    public Person() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surName) {
        this.surname = surName;
    }

    public String getPatronymic() {
        return patronymic;
    }



    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public LocalDate getDageOfBirth() {
        return dageOfBirth;
    }

    public void setDageOfBirth(LocalDate dageOfBirth) {
        this.dageOfBirth = dageOfBirth;
    }



    public void setStudentID(String studentID) {
        studentID = studentID;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
