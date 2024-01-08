package dao;

import config.Config;
import domain.Adult;
import domain.Street;
import domain.StudentOrder;
import domain.StudentOrderStatus;
import exception.DaoException;

import java.sql.*;

public class StudentDaoIml implements StudentOrderDao {
    private static final String INSERT_ODER =
            "INSERT INTO jc_student_order(" +
                    " student_order_status, student_order_date, h_sur_name, " +
                    " h_given_name, h_patronymic, h_date_of_birth, h_passport_seria, " +
                    " h_passport_number, h_passport_date, h_passport_office_id, h_post_index, " +
                    " h_street_code, h_building, h_extension, h_apartment, h_university_id, h_student_number, " +
                    " w_sur_name, w_given_name, w_patronymic, w_date_of_birth, w_passport_seria, " +
                    " w_passport_number, w_passport_date, w_passport_office_id, w_post_index, " +
                    " w_street_code, w_building, w_extension, w_apartment, w_university_id, w_student_number, " +
                    " certificate_id, register_office_id, marriage_date)" +
                    " VALUES (?, ?, ?, " +
                    " ?, ?, ?, ?, " +
                    " ?, ?, ?, ?, " +
                    " ?, ?, ?, ?, ?, ?, " +
                    " ?, ?, ?, ?, ?, " +
                    " ?, ?, ?, ?, " +
                    " ?, ?, ?, ?, ?, ?, " +
                    " ?, ?, ?);";


//
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                Config.getProperty(Config.DB_URL),
                Config.getProperty(Config.DB_LOGIN),
                Config.getProperty(Config.DB_PASSWORD));
    }
    @Override
    public Long saveStudentOrder(StudentOrder so) throws DaoException {
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(INSERT_ODER)) {
            // Header
            stmt.setInt(1, StudentOrderStatus.START.ordinal());
            stmt.setTimestamp(2, java.sql.Timestamp.valueOf(so.getStudentOrderDate()));
            // Husband
            Adult husband = so.getHusband();
            stmt.setString(3, husband.getSurname());
            stmt.setString(4, husband.getName());
            stmt.setString(5, husband.getPatronymic());
            stmt.setDate(6, java.sql.Date.valueOf(so.getHusband().getDageOfBirth()));
            stmt.setString(7, husband.getPassportSeria());
            stmt.setString(8, husband.getPassportNumber());
            stmt.setDate(9, java.sql.Date.valueOf(husband.getIssueDate()));
           /*  stmt.setLong(10, husband.g;

            ResultSet rs = stmt.executeQuery(); // выплняет запрос
            while (rs.next()) {
                Street str = new Street(rs.getLong("street_code"), rs.getString("street_name"));
                result.add(str);
            }*/
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return 24l;
    }
}
