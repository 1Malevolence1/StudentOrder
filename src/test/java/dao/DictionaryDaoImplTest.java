package dao;

import domain.PassportOffice;
import domain.Street;
import exception.DaoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class DictionaryDaoImplTest {







    @Test
    public void testsStreet() throws DaoException {
        List<Street> street = new DictionaryDaoImpl().findStreets("про");
        Assertions.assertTrue(street.size() == 6);
    }

    @Test
    public void testsPassportOffice() throws DaoException {
        List<PassportOffice> passportOfficesList = new DictionaryDaoImpl().findPassportOffice("01");
        Assertions.assertTrue(passportOfficesList.size() == 5);
    }

    @Test
    public void testRegisterOffice() throws DaoException {
        List<PassportOffice> registerOffice = new DictionaryDaoImpl().findRegisterOffice("01");
        Assertions.assertTrue(registerOffice.size() == 2);
    }


}