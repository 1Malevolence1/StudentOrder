package domain;

import java.time.LocalDate;

public class Adult extends Person {
    private String passportSeria;



    private String studentID;

    private University university;

    private PassportOffice passportOffice;
    private String passprotNumber;
    private LocalDate issueDate;

    private String passportNumber;

    private RegisterOffice issueDepartment;

    public Adult(){
        super();

    }

    public Adult(String surname, String giveName, String patronymic, LocalDate dateOfBirth, Address address
    ) {
        super(surname, giveName, patronymic, dateOfBirth, address);
    }

    public RegisterOffice getRegisterOffice() {
        return issueDepartment;
    }

    public void setRegisterOffice(RegisterOffice issueDepartment) {
        this.issueDepartment = issueDepartment;
    }

    public String getPassportSeria() {
        return passportSeria;
    }

    public void setPassportSeria(String passportSeria) {
        this.passportSeria = passportSeria;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }


    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
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
}
