package validator;

public interface Validator<T,E> {
    public T check(E so);

}
