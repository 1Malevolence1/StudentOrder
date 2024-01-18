package dao;

import config.Config;
import domain.*;
import exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class DictionaryDaoImpl<T> implements DictionaryDao{
    private static final String  GET_STREET = "SELECT street_code, " +
            "street_name FROM jc_street where UPPER(street_name) LIKE UPPER(?) ORDER BY street_code";

    private static final String  GET_PASSPORT = "SELECT * FROM " +
            "jc_passport_office WHERE p_office_area_id LIKE ?";

    private static final String  GET_REGISTER = "SELECT * FROM " +
            "jc_register_office WHERE r_office_area_id = ?";
    private static final String  GET_AREA = "select * from jc_country_struct where area_id like ? and area_id <> ?";




    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                Config.getProperty(Config.DB_URL),
                Config.getProperty(Config.DB_LOGIN),
                Config.getProperty(Config.DB_PASSWORD));
    }



    @Override
    public List<Street> findStreets(String pattern) throws DaoException {
        List<Street> result = new LinkedList<>();
       try(Connection con = getConnection();
          PreparedStatement stmt = con.prepareStatement(GET_STREET)) {  // cоздать запрос
           stmt.setString(1, "%" + pattern+"%");

           ResultSet rs = stmt.executeQuery(); // выплняет запрос
           while (rs.next()) {
               Street str = new Street(rs.getLong("street_code"), rs.getString("street_name"));
               result.add(str);
           }
       } catch (SQLException ex) {
           throw new DaoException(ex);
       }

        return result;
    }

    @Override
    public List<RegisterOffice> findRegisterOffice(String areaId) throws DaoException {
        List<RegisterOffice> result = new LinkedList<>();
        try(Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(GET_REGISTER)){  // cоздать запрос
            stmt.setString(1, "%" + areaId+"%");

            ResultSet rs = stmt.executeQuery(); // выплняет запрос
            while (rs.next()) {
                    RegisterOffice registerOffice = new RegisterOffice(
                            rs.getLong("r_office_id"),
                            rs.getString("r_office_area_id"),
                            rs.getString("r_office_name")
                    );
                    result.add(registerOffice);
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return result;
    }

    @Override
    public List<PassportOffice> findPassportOffice(String areaId) throws DaoException {
        List<PassportOffice> result = new LinkedList<>();
        try(Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(GET_PASSPORT)) {  // cоздать запрос
            stmt.setString(1, "%"+ areaId + "%");

            ResultSet rs = stmt.executeQuery(); // выплняет запрос
            while (rs.next()) {
                PassportOffice passportOffice = new PassportOffice(
                        rs.getLong("p_office_id"),
                        rs.getString("p_office_area_id"),
                        rs.getString("p_office_name")
                );
                result.add(passportOffice);
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return result;
    }

    @Override
    public List<CountryArea> findAreas(String areaId) throws DaoException {
        List<CountryArea> listCountryArea = new ArrayList<>();
        try(Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(GET_AREA)) {  // cоздать запрос

            String param1 = buildParma(areaId);
            String param2 = areaId;

            stmt.setString(1,param1);
            stmt.setString(2,param2);

            ResultSet rs = stmt.executeQuery(); // выплняет запрос
            while (rs.next()) {
                CountryArea countryArea = new CountryArea(
                        rs.getString("area_id"),
                        rs.getString("area_name")
                );
                listCountryArea.add(countryArea);
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return listCountryArea;
    }

    private String buildParma(String areaId) throws SQLException {
        if(areaId == null || areaId.trim().isEmpty()) {
            return "__0000000000";
        } else if (areaId.endsWith("00000000")) {
            return areaId.substring(0, 2) + "___0000000";
        } else if(areaId.endsWith("0000000")) {
            return areaId.substring(0, 5) + "___0000";
        } else if (areaId.endsWith("0000")) {
            return areaId.substring(0, 8) + "____";
        }
        throw new SQLException("Invaild parameter areaID: " + areaId);
    }

}
