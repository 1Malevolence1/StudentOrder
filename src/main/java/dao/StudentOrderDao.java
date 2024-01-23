package dao;

import domain.StudentOrder;
import exception.DaoException;

import java.sql.SQLException;
import java.util.List;

public interface StudentOrderDao {
    Long saveStudentOrder(StudentOrder so) throws DaoException, SQLException;
    List<StudentOrder> getStudentOrder() throws DaoException, SQLException;
}
