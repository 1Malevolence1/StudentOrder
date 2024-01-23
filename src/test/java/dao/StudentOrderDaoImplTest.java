package dao;

import domain.*;
import exception.DaoException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class StudentOrderDaoImplTest {


    @BeforeAll
    public static void startUp() throws SQLException, URISyntaxException, IOException {
        DBInit.startUp();
    }

    @Test
  public void saveStudentOrder() throws SQLException, DaoException {
        StudentOrder studentOrder = buildStudentOrder(10);
         Long id = new StudentOrderDaoImpl().saveStudentOrder(studentOrder);
    }

    @Test
    public void saveStudentOrderError() throws SQLException, DaoException {
        StudentOrder studentOrder = buildStudentOrder(10);
        studentOrder.setHusband(null);
        Long id = new StudentOrderDaoImpl().saveStudentOrder(studentOrder);
    }

    @Test
    void getStudentOrder() {

    }

    public static StudentOrder buildStudentOrder(long id){
        StudentOrder studentOrder = new StudentOrder();
        studentOrder.setStudentOrderID(id);
        studentOrder.setMarriageCertificateId("" + (12346000+id));
        studentOrder.setMarriageDate(LocalDate.of(2016,7,4));

        RegisterOffice ro = new RegisterOffice(1L, "", "");
        studentOrder.setRegisterOffice(ro);

        Street street = new Street(1L, "First street");
        Address address = new Address("195000", street, "12", "", "142  ");

        Passport husband_passport = new Passport("" + (1000+id), "" + (10000 + id), LocalDate.of(2017, 4, 5));

        Adult husband = new Adult("Петров", "Виктор", "Сергеевич",LocalDate.of(1997,8,24), address,husband_passport);
        PassportOffice po1 = new PassportOffice(1L,"","");
        husband.setPassportOffice(po1);
        husband.setStudentID("" + (10000 + id));
        husband.setUniversity(new University(2L,""));
        husband.setStudentID("HH12345");
        husband.setAddress(address);
        husband.setPassport(husband_passport);

        Passport wife_passport = new Passport("" + (2000+id),"" + (2000+id), LocalDate.of(2018, 3, 12));
        Adult wife = new Adult("Петрова", "Вероника", "Андреева",LocalDate.of(1998,3,12), address, wife_passport);
        PassportOffice po2 = new PassportOffice(2L,"","");
        wife.setPassportOffice(po2);
        wife.setStudentID("" + (20000 + id));
        wife.setStudentID("WW12345");
        wife.setUniversity(new University(1L,""));
        wife.setAddress(address);
        wife.setPassport(wife_passport);


        Child child1 = new Child("Петрова", "Ирина", "Викторона",LocalDate.of(2018,6,29));
        child1.setCertificateNumber("" + (30000 + id));
        child1.setIssueDate(LocalDate.of(2018,7,19));
        RegisterOffice ro2 = new RegisterOffice(2L,"","");
        child1.setRegisterOffice(ro2);
        studentOrder.setHusband(husband);
        studentOrder.setWife(wife);
        child1.setAddress(address);

        Child child2 = new Child("Петров", "Евгений", "Викторович",LocalDate.of(2018,6,29));
        child2.setCertificateNumber("" + (30000 + id));
        child2.setIssueDate(LocalDate.of(2018,7,19));
        RegisterOffice ro3 = new RegisterOffice(2L,"","");
        child2.setRegisterOffice(ro3);
        child2.setAddress(address);

        studentOrder.addChild(child1);
        studentOrder.addChild(child2);
        studentOrder.setHusband(husband);
        studentOrder.setWife(wife);


        return studentOrder;
    }

}