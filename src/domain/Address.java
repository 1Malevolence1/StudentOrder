package domain;

public class Address {
    private String pastCode;
    private Street street;

    private String building;

    private String extension;
    private String apartment;

    public Address() {

    }

    public Address(String pastCode, Street street, String building, String extension, String apartment) {
        this.pastCode = pastCode;
        this.street = street;
        this.building = building;
        this.extension = extension;
        this.apartment = apartment;
    }

    public String getPastCode() {
        return pastCode;
    }

    public void setPastCode(String pastCode) {
        this.pastCode = pastCode;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }
}
