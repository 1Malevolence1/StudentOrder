import dao.DictionaryDaoImpl;
import domain.Adult;
import domain.CountryArea;
import domain.StudentOrder;
import exception.DaoException;
import org.postgresql.Driver;

import javax.sql.ConnectionEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws SQLException, DaoException {

        List<CountryArea> list = new DictionaryDaoImpl().findAreas("01___0000000");
        for (CountryArea s: list
             ) {
            System.out.println(s.getAreaId() + " " + s.getAreaName());
        }


    }
}