package dao;

import domain.*;
import exception.DaoException;

import java.util.List;

public interface DictionaryDao<T>{
    List<Street> findStreets(String pattern) throws DaoException;
    List<RegisterOffice> findRegisterOffice(String areaId) throws DaoException;
    List<PassportOffice> findPassportOffice(String areaId) throws DaoException;
    List<CountryArea> findAreas(String areaId) throws DaoException;


}
