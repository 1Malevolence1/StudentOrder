package validator;

import dao.DictionaryDaoImpl;
import domain.*;


import javax.sql.ConnectionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class TestBuildStudentOrder {
    public static void main(String[] args) throws Exception {

        List<PassportOffice> d1 = new DictionaryDaoImpl().findPassportOffice("01");

        for (PassportOffice s: d1
        ) {
            System.out.println(s.getOfficeId() + " " + s.getOfficeAreaId() + " " + s.getOfficeName());
        }



        }
        public static StudentOrder buildStudentOrder(){
            StudentOrder studentOrder = new StudentOrder();
            Street street = new Street(1L, "First street");
            Address address = new Address("195000", street, "56", "Нежнова", "36");
            return studentOrder;
        }
    }


