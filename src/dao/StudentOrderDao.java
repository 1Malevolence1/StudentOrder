package dao;

import domain.StudentOrder;
import exception.DaoException;

import java.sql.SQLException;

public interface StudentOrderDao {
    Long saveStudentOrder(StudentOrder so) throws DaoException, SQLException;
}
