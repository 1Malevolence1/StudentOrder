package validator.register;

import domain.Person;
import domain.reigster.AnswerCityRegister;
import domain.Child;
import domain.reigster.AnswerCityRegisterItem;
import domain.reigster.CityRegisterResponse;
import domain.StudentOrder;
import exception.CityRegisterException;
import exception.TransportException;
import validator.Validator;

public class CityRegisterValidator implements Validator<AnswerCityRegister, StudentOrder> {
    private static final String IN_CODE = "NO_GRN";
    private RealCityRegisterChecker realCityRegisterChecker;

    public CityRegisterValidator() {
        realCityRegisterChecker = new RealCityRegisterChecker();
    }

    @Override
    public AnswerCityRegister check(StudentOrder so) {
        AnswerCityRegister ans = new AnswerCityRegister();
        ans.addItem(checkPerson(so.getHusband()));
        ans.addItem(checkPerson(so.getWife()));
        for (Child child : so.getChildren()
        ) {
            ans.addItem(checkPerson(child));
        }
        return ans;
    }

    private AnswerCityRegisterItem checkPerson(Person person) {
        AnswerCityRegisterItem.CityStatus status = null;
        AnswerCityRegisterItem.CityError error = null;

        try {
            CityRegisterResponse hans = realCityRegisterChecker.checkPerson(person);
            status = hans.isExisting() ? AnswerCityRegisterItem.CityStatus.YES :
                    AnswerCityRegisterItem.CityStatus.NO;
        } catch (CityRegisterException e) {
            status = AnswerCityRegisterItem.CityStatus.ERROR;
            error = new AnswerCityRegisterItem.CityError(e.getCode(), e.getMessage());
            System.out.println("Ошибка");

        } catch (TransportException e) {
            status = AnswerCityRegisterItem.CityStatus.ERROR;
            error = new AnswerCityRegisterItem.CityError(IN_CODE, e.getMessage());
            throw new RuntimeException(e);
        }
        AnswerCityRegisterItem item = new AnswerCityRegisterItem(status, person, error);

        return item;
    }
}
