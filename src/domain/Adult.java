package domain;

import java.time.LocalDate;

public class Adult extends Person {

    private Passport passport;

    private String studentID;

    private University university;

    private PassportOffice passportOffice;


    public Adult(){
        super();

    }

    public Adult(String surname, String giveName, String patronymic, LocalDate dateOfBirth, Address address, Passport passport
    ) {
        super(surname, giveName, patronymic, dateOfBirth, address);
        this.passport = passport;
    }


    public PassportOffice getPassportOffice() {
        return passportOffice;
    }


    public void setPassportOffice(PassportOffice passportOffice) {
        this.passportOffice = passportOffice;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }
}
