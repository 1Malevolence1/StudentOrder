package domain;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class StudentOrder {
    private long studentOrderID;
    private Adult wife;
    private Adult husband;
    private List<Child> children;

    private LocalDateTime studentOrderDate;

    public LocalDateTime getStudentOrderDate() {
        return studentOrderDate;
    }

    public void setStudentOrderDate(LocalDateTime studentOrderDate) {
        this.studentOrderDate = studentOrderDate;
    }

    public StudentOrderStatus getStudentOrderStatus() {
        return studentOrderStatus;
    }

    public void setStudentOrderStatus(StudentOrderStatus studentOrderStatus) {
        this.studentOrderStatus = studentOrderStatus;
    }

    private StudentOrderStatus studentOrderStatus;
    private String marriageCertificateId;
    private RegisterOffice marriageOffice;

    public String getMarriageCertificateId() {
        return marriageCertificateId;
    }

    public void setMarriageCertificateId(String marriageCertificateId) {
        this.marriageCertificateId = marriageCertificateId;
    }

    public LocalDate getMarriageDate() {
        return marriageDate;
    }

    public void setMarriageDate(LocalDate marriageDate) {
        this.marriageDate = marriageDate;
    }

    private LocalDate marriageDate;

    public long getStudentOrderID() {
        return studentOrderID;
    }

    public void setStudentOrderID(long studentOrderID) {
        this.studentOrderID = studentOrderID;
    }

    public Adult getWife() {
        return wife;
    }

    public void setWife(Adult wife) {
        this.wife = wife;
    }

    public Adult getHusband() {
        return husband;
    }

    public void setHusband(Adult husband) {
        this.husband = husband;
    }


    public void addChild(Child child){
        if(children == null){
            children = new LinkedList<>() {
            };
        }
        children.add(child);

    }
    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> child) {
        this.children = child;
    }

    public RegisterOffice getMarriageOffice() {
        return marriageOffice;
    }

    public void setMarriageOffice(RegisterOffice marriageOffice) {
        this.marriageOffice = marriageOffice;
    }
}