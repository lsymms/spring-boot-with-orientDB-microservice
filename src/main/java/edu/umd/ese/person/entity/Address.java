package edu.umd.ese.person.entity;

import edu.umd.ese.microservices.template.entity.OrientDBObject;

// do not use this class directly, get a new object using db.newInstance(Address.class);
public class Address extends OrientDBObject {
    private String line1;
    private String city;
    private String state;
    private String country;

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    // this is JSON but we'll rely on jackson to handle JSON to/from Object mapping
    public String toString() {
        return "{ \"line1\": \"" + getLine1() +"\", " +
                "\"city\": " + getCity() +"\", " +
                "\"state\": " + getState() +"\", " +
                "\"country\": " + getCountry() +"\" " +
                "}";

    }
}
