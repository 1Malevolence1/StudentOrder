import dao.DictionaryDaoImpl;
import domain.CountryArea;
import exception.DaoException;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, DaoException {

        List<CountryArea> list = new DictionaryDaoImpl().findAreas("01___0000000");
        for (CountryArea s: list
             ) {
            System.out.println(s.getAreaId() + " " + s.getAreaName());
        }


    }
}