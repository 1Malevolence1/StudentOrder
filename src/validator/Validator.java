package validator;

import domain.StudentOrder;
import exception.CityRegisterException;

public interface Validator<T,E> {
    public T check(E so);

}
