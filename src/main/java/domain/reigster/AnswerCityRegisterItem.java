package domain.reigster;

import domain.Person;

public class AnswerCityRegisterItem {

    public enum CityStatus{
        YES, NO, ERROR;
    }
    public static class CityError{
        private String code;
        private String text;

        public CityError(String code, String text) {
            this.code = code;
            this.text = text;
        }
    }
    private CityStatus status;
    private Person person;
    private CityError cityError;

    public CityStatus getStatus() {
        return status;
    }

    public void setStatus(CityStatus status) {
        this.status = status;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public CityError getCityError() {
        return cityError;
    }

    public void setCityError(CityError cityError) {
        this.cityError = cityError;
    }

    public AnswerCityRegisterItem(CityStatus status, Person person, CityError cityError) {
        this.status = status;
        this.person = person;
        this.cityError = cityError;
    }

    public AnswerCityRegisterItem(CityStatus status, Person person) {
        this.status = status;
        this.person = person;
    }
}
