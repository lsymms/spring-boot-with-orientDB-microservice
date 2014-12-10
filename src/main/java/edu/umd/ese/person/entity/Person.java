package edu.umd.ese.person.entity;

import edu.umd.ese.microservices.template.entity.OrientDBObject;

/*
 * Do not use this class directly, get a new object using db.newInstance(Person.class);
 * You can can also have it invoke parameterized constructors like db.newInstance(Person.class,"Antoni","Gaudi");
 */
public class Person extends OrientDBObject {
    private String name;
    private String surname;
    private Address address;

    public Person() {}

    public Person(String name, String surname){
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    // this is JSON but we'll rely on jackson to handle JSON to/from Object mapping
    public String toString() {
        return "{ \"name\": \"" + getName() +"\", " +
                "\"surname\": " + getSurname() +"\", " +
                "\"address\": " + getAddress() +"\" " +
                "}";

    }
}
