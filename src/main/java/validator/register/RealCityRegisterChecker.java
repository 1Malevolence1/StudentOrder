package validator.register;

import domain.Adult;
import domain.Person;
import domain.reigster.CityRegisterResponse;
import exception.CityRegisterException;
import exception.TransportException;

public class RealCityRegisterChecker{

    private static final String SER_PASSPORT_HUSBAND = "1000";
    private static final String SER_PASSPORT_WIFE = "2000";
    private static final String TRANSPORT1= "2000";
    private static final String TRANSPORT2 = "2000";
    public CityRegisterResponse checkPerson(Person person) throws CityRegisterException, TransportException {
        if(person instanceof Adult){
            Adult adult = (Adult) person;
            if(adult.getPassport().getSeries().equals(SER_PASSPORT_HUSBAND) ||
                    adult.getPassport().getSeries().equals(SER_PASSPORT_WIFE)){
                CityRegisterResponse res = new CityRegisterResponse();
                res.setExisting(true);
                res.setTemporal(false);
            }
            else {
                throw new CityRegisterException("1","Fake error");
            }
        }
        return null;
    }
}
