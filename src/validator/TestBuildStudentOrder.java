package validator;

import dao.DictionaryDaoImpl;
import dao.StudentDaoImpl;
import dao.StudentOrderDao;
import domain.*;


import javax.sql.ConnectionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

public class TestBuildStudentOrder {
    public static void main(String[] args) throws Exception {
        StudentOrder studentOrder = buildStudentOrder(10);
        StudentOrderDao dao = new StudentDaoImpl();
        Long id = dao.saveStudentOrder(studentOrder);
        System.out.println(id);
       // dao.saveStudentOrder(s);
        }
        private static long saveStudentOrder(StudentOrder studentOrder){
        long answer = 199;
            System.out.println("saveStudentOrder");
            return answer;
        }
        public static StudentOrder buildStudentOrder(long id){
            StudentOrder studentOrder = new StudentOrder();
            studentOrder.setStudentOrderID(id);
            studentOrder.setMarriageCertificateId("" + (12346000+id));
            studentOrder.setMarriageDate(LocalDate.of(2016,7,4));

            RegisterOffice ro = new RegisterOffice(1L, "", "");
            studentOrder.setMarriageOffice(ro);

            Street street = new Street(1L, "First street");
            Address address = new Address("195000", street, "12", "", "142  ");

            Adult husband = new Adult("Петров", "Виктор", "Сергеевич",LocalDate.of(1997,8,24), address);
            husband.setPassportSeria("" + (1000+id));
            husband.setPassportNumber("" + (10000 + id));
            husband.setIssueDate(LocalDate.of(2017, 4, 5));
            PassportOffice po1 = new PassportOffice(1L,"","");
            husband.setStudentID("" + (10000 + id));
            husband.setAddress(address);

            Adult wife = new Adult("Петрова", "Вероника", "Андреева",LocalDate.of(1998,3,12), address);
            wife.setPassportSeria("" + (2000+id));
            wife.setPassportNumber("" + (20000 + id));
            wife.setIssueDate(LocalDate.of(2018, 3, 12));
            PassportOffice po2 = new PassportOffice(2L,"","");
            wife.setStudentID("" + (20000 + id));
            wife.setAddress(address);


            Child child1 = new Child("Петрова", "Ирина", "Викторона",LocalDate.of(2018,6,29));
            child1.setCertificateNumber("" + (30000 + id));
            child1.setIssueDate(LocalDate.of(2018,7,19));
            RegisterOffice ro2 = new RegisterOffice(2L,"","");
            child1.setIssueDepartment(ro2);
            studentOrder.setHusband(husband);
            studentOrder.setWife(wife);
            child1.setAddress(address);

            Child child2 = new Child("Петров", "Евгений", "Викторович",LocalDate.of(2018,6,29));
            child2.setCertificateNumber("" + (30000 + id));
            child2.setIssueDate(LocalDate.of(2018,7,19));
            RegisterOffice ro3 = new RegisterOffice(2L,"","");
            child2.setIssueDepartment(ro3);
            studentOrder.setHusband(husband);
            studentOrder.setWife(wife);
            child2.setAddress(address);

            return studentOrder;
        }
    }


