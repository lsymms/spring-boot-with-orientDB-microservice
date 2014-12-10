package edu.umd.ese.microservices.template.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Id;

/*
 * This base class tells jackson to ignore the handler which is part of all orient DB proxy objects
 */

@JsonIgnoreProperties({"handler"})
public class OrientDBObject {
    @Id
    private String id;

    public String getId() {
        return id;
    }
}
