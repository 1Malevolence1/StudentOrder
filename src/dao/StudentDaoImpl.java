package dao;

import config.Config;
import domain.*;
import exception.DaoException;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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



    private static  final String SELECT_ORDER =  "SELECT so.*, ro.r_office_area_id, ro.r_office_name, " +
            "po_h.p_office_area_id as h_p_office_area_id, " +
            "po_h.p_office_name as h_p_office_name, " +
            "po_w.p_office_area_id as w_p_office_area_id, " +
            "po_w.p_office_name as w_p_office_name " +
            "FROM jc_student_order so " +
            "INNER JOIN jc_register_office ro ON ro.r_office_id = so.register_office_id " +
            "INNER JOIN jc_passport_office po_h ON po_h.p_office_id = so.h_passport_office_id " +
            "INNER JOIN jc_passport_office po_w ON po_w.p_office_id = so.w_passport_office_id " +
            "WHERE student_order_status = ? ORDER BY student_order_date";


    private static final String SELECT_CHILD =
            "SELECT soc.*, ro.r_office_area_id, ro.r_office_name " +
                    "FROM jc_student_child soc " +
                    "INNER JOIN jc_register_office ro ON ro.r_office_id = soc.c_register_office_id " +
                    "WHERE soc.student_order_id IN ";
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
            stmt.setLong(34, so.getRegisterOffice().getOfficeId());
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
    public List<StudentOrder> getStudentOrder() throws SQLException {
        List<StudentOrder> listStudentOrder = new LinkedList<>();
        try (Connection connection = getConnection();
             PreparedStatement stmt = getStmt(connection, SELECT_ORDER)){

            stmt.setInt(1, StudentOrderStatus.START.ordinal());
            ResultSet rs = stmt.executeQuery();

            List<Long> ids = new LinkedList<>()
                    ;
            while (rs.next()) {
                StudentOrder studentOrder = new StudentOrder();
                fillStudentOrder(rs, studentOrder);
                fillWedding(rs,studentOrder);

                Adult husband = fillingAdult(rs, "h_");
                Adult wife = fillingAdult(rs, "w_");
                studentOrder.setHusband(husband);
                studentOrder.setWife(wife);

                ids.add(studentOrder.getStudentOrderID());
                listStudentOrder.add(studentOrder);
            }

            findChildren(connection, listStudentOrder);


        }catch (SQLException ex) {
            throw new SQLException();
        }
        return listStudentOrder;
    }

    private void findChildren(Connection connection, List<StudentOrder> listStudentOrder) throws SQLException {
        String string = "(" + listStudentOrder.stream().map(studentOrder -> String.valueOf(studentOrder.getStudentOrderID())).collect(Collectors.joining(",")) + ")";

        try(PreparedStatement stmt = getStmt(connection, SELECT_CHILD + string)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                System.out.println(rs.getLong(1) + ":" + rs.getString(3));
            }
        }
    }

    private Adult fillingAdult(ResultSet rs, String pref) throws SQLException {
        Adult adult = new Adult();
        adult.setSurname(rs.getString(pref + "sur_name"));
        adult.setName(rs.getString(pref + "give_name"));
        adult.setPatronymic(rs.getString(pref + "patronymic"));
        adult.setDageOfBirth(rs.getDate(pref + "date_of_birth").toLocalDate());

        Passport passport = new Passport(rs.getString(pref + "passport_seria"), rs.getString(pref + "passport_number"),
                rs.getDate(pref + "passport_date").toLocalDate());
        adult.setPassport(passport);

        PassportOffice passportOffice = new PassportOffice(rs.getLong(pref + "passport_office_id"), "","");
        adult.setPassportOffice(passportOffice);

        Address address = new Address();
        Street street = new Street(rs.getLong(pref + "street_code"), "");
        address.setStreet(street);
        address.setPastCode(rs.getString(pref + "post_index"));
        address.setBuilding(rs.getString(pref + "building"));
        address.setExtension(rs.getString(pref + "extension"));
        address.setApartment(rs.getString(pref + "apartment"));
        adult.setAddress(address);

        University university = new University(rs.getLong(pref + "university_id"), "");
        adult.setUniversity(university);
        adult.setStudentID(rs.getString(pref + "student_number"));

        return adult;
    }

    private void fillWedding(ResultSet rs, StudentOrder studentOrder) throws SQLException {
        studentOrder.setMarriageCertificateId(rs.getString("certificate_id"));
        studentOrder.setMarriageDate(rs.getDate("marriage_date").toLocalDate());

        Long id = rs.getLong("register_office_id");
        String areaId = rs.getString("r_office_area_id");
        String officeName = rs.getString("r_office_name");

        RegisterOffice office = new RegisterOffice(id,areaId,officeName);
        studentOrder.setRegisterOffice(office);
    }

    private void fillStudentOrder(ResultSet rs, StudentOrder studentOrder) throws SQLException {
        studentOrder.setStudentOrderID(rs.getLong("student_order_id"));
        studentOrder.setStudentOrderDate(rs.getTimestamp("student_order_date").toLocalDateTime());
        studentOrder.setStudentOrderStatus(StudentOrderStatus.fromValue(rs.getInt("student_order_status")));
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

    private void setParamsAdult( PreparedStatement stmt, int start, Adult adult) throws SQLException {
        setParamsForPerson(stmt, start, adult);
        setPassportForAdult(stmt, start + 4, adult);
        setParamsPassportOffice(stmt, start + 7, adult);
        setParamsForAddress(stmt, start + 8, adult);
        setParamsForUniversity(stmt, start + 13, adult);
    }

  private void setParamsPassportOffice(PreparedStatement stmt, int start, Adult adult) throws SQLException{
      PassportOffice passportOffice = adult.getPassportOffice();
      stmt.setLong(start, passportOffice.getOfficeId());
  }



    private void setPassportForAdult(PreparedStatement stmt, int start, Adult adult) throws SQLException {
        Passport passport = adult.getPassport();
        stmt.setString(start, passport.getSeries());
        stmt.setString(start + 1, passport.getNumber());
        stmt.setDate(start + 2, Date.valueOf(passport.getIssueDate()));
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
