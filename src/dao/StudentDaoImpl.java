package dao;

import config.Config;
import domain.*;
import exception.DaoException;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class StudentDaoImpl implements StudentOrderDao {
    private static final String INSERT_ODER =
            "INSERT INTO jc_student_order(" +
                    " student_order_status, student_order_date, h_sur_name, " +
                    " h_give_name, h_patronymic, h_date_of_birth, h_passport_seria, " +
                    " h_passport_number, h_passport_date, h_passport_office_id, h_post_index, " +
                    " h_street_code, h_building, h_extension, h_apartment, h_university_id, h_student_number," +
                    " w_sur_name, w_give_name, w_patronymic, w_date_of_birth, w_passport_seria, " +
                    " w_passport_number, w_passport_date, w_passport_office_id, w_post_index, " +
                    " w_street_code, w_building, w_extension, w_apartment, w_university_id, w_student_number, " +
                    " certificate_id, register_office_id, marriage_date) " +
                    " VALUES (?, ?, ?, " +
                    " ?, ?, ?, ?, " +
                    " ?, ?, ?, ?, " +
                    " ?, ?, ?, ?, ?, ?, " +
                    " ?, ?, ?, ?, ?, " +
                    " ?, ?, ?, ?, " +
                    " ?, ?, ?, ?, ?, ?," +
                    "?, ?, ?)";


    private static final String INSERT_CHILD = "INSERT INTO jc_student_child(" +
            "student_order_id, c_sur_name, c_give_name, c_patronymic, c_date_of_birth, c_certificate_seria, c_certificate_date, c_register_office_id, c_post_index, c_street_code, c_building, c_extension, c_apartment)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";



    private static  final String SELECT_ORDER = "SELECT * FROM jc_student_order WHERE student_order_status = 0 ORDER BY student_order_date";
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                Config.getProperty(Config.DB_URL),
                Config.getProperty(Config.DB_LOGIN),
                Config.getProperty(Config.DB_PASSWORD));
    }
    private PreparedStatement getStmt(Connection connection, String string) throws SQLException {
        return connection.prepareStatement(string);
    }

    private PreparedStatement getStmt(Connection connection, String string, String[] strings) throws SQLException {
        return connection.prepareStatement(string, strings);
    }
    @Override
    public Long saveStudentOrder(StudentOrder so) throws DaoException, SQLException {
        long result = -1L;
        try (Connection connection = getConnection();
             PreparedStatement stmt = getStmt(connection, INSERT_ODER, new String[]{"student_order_id"})) {
            // Header
            stmt.setInt(1, StudentOrderStatus.START.ordinal());
            stmt.setTimestamp(2, java.sql.Timestamp.valueOf(LocalDateTime.now()));
            // Husband

            setParamsAdult( stmt, 3, so.getHusband());
            setParamsAdult(stmt,18,so.getWife());


            // Marriage
            stmt.setString(33, so.getMarriageCertificateId());
            stmt.setLong(34, so.getMarriageOffice().getOfficeId());
            stmt.setDate(35,java.sql.Date.valueOf(so.getMarriageDate()));

            stmt.executeUpdate();

            ResultSet gkRs = stmt.getGeneratedKeys();
            if(gkRs.next()){
                result = gkRs.getLong(1);
            }

            saveChildren( so, result,getStmt(connection,INSERT_CHILD));

        gkRs.close();
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return result;
    }

    @Override
    public List<StudentOrder> getStudentOrder() throws DaoException, SQLException {
        List<StudentOrder> listStudentOrder = new LinkedList<>();
        try (Connection connection = getConnection();
             PreparedStatement stmt = getStmt(connection, SELECT_ORDER)){

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                StudentOrder studentOrder = new StudentOrder();
                fillStudentOrder(rs, studentOrder);
            }


        }catch (SQLException ex) {
            throw new DaoException();
        }
        return listStudentOrder;
    }

    private void fillStudentOrder(ResultSet rs, StudentOrder studentOrder) throws SQLException {
        studentOrder.setStudentOrderID(rs.getLong("student_order_id"));
        studentOrder.setStudentOrderDate(rs.getTimestamp("student_order_date").toLocalDateTime());
        studentOrder.setStudentOrderStatus(StudentOrderStatus.fromValue(rs.getInt("student_order_status")));
    }

    private void setParamsAdult( PreparedStatement stmt, int start, Adult adult) throws SQLException {
        setParamsForPerson(stmt, start, adult);
        stmt.setString(start + 4, adult.getPassportSeria());
        stmt.setString(start + 5, adult.getPassportNumber());
        stmt.setDate(start + 6, Date.valueOf(adult.getIssueDate()));
        stmt.setLong(start + 7, 6);
        setParamsForAddress(stmt, start + 8, adult);
        setParamsForUniversity(stmt, start + 13, adult);
    }



    private void saveChildren(StudentOrder so, Long soId, PreparedStatement stmt) throws SQLException {
        for (Child child: so.getChildren()
             ) {
                stmt.setLong(1, soId);
                setParamsForChild(stmt, child);
                stmt.addBatch(); // накапливание пакета. Улучшает производительность
        }
        stmt.executeBatch();
    }

    private void setParamsForChild(PreparedStatement stmt, Child child) throws SQLException {
        setParamsForPerson(stmt, 2, child);
        stmt.setString(6,child.getCertificateNumber());
        stmt.setDate(7, java.sql.Date.valueOf(child.getIssueDate()));
        stmt.setLong(8, child.getIssueDepartment().getOfficeId());
        setParamsForAddress(stmt, 9, child );

    }

    private void setParamsForUniversity(PreparedStatement stmt, int start, Adult adult) throws SQLException {
        University university = adult.getUniversity();
        stmt.setLong(start, university.getUniversityId());
        stmt.setString(start + 1, adult.getStudentID());
    }


    private static void setParamsForAddress(PreparedStatement stmt, int start, Person person) throws SQLException {
        Address address = person.getAddress();
        stmt.setString(start, address.getPastCode());
        stmt.setLong(start + 1, address.getStreet().getStreetCode());
        stmt.setString(start + 2, address.getBuilding());
        stmt.setString(start + 3, address.getExtension());
        stmt.setString(start + 4, address.getApartment());
    }

    private static void setParamsForPerson(PreparedStatement stmt, int start, Person person) throws SQLException {
        stmt.setString(start, person.getSurname());
        stmt.setString(start + 1, person.getName());
        stmt.setString(start + 2, person.getPatronymic());
        stmt.setDate(start + 3, Date.valueOf(person.getDageOfBirth()));
    }
}
