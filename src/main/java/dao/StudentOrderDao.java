package dao;

import domain.StudentOrder;
import exception.DaoException;

import java.sql.SQLException;
import java.util.List;

public interface StudentOrderDao {
    void saveStudentOrder(StudentOrder so) throws DaoException, SQLException;
    List<StudentOrder> getStudentOrder() throws DaoException, SQLException;
}
