package edu.umd.ese.microservices.template.repository;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

public class OrientDBEmbeddable {
    public static ODatabaseDocumentTx getDocumentDatabase() {
        // create a database in memory
//        OObjectDatabaseTx db = new OObjectDatabaseTx("memory:data").create();

        // create a database on disk
        ODatabaseDocumentTx db = new ODatabaseDocumentTx("local:target/docdata");
        if (db.exists()) {
            db = new ODatabaseDocumentTx("plocal:target/docdata").open("admin", "admin");
        } else {
            db.create();
        }
        return db;
    }

    public static OObjectDatabaseTx getObjectDatabase() {
        // create a database in memory
//        OObjectDatabaseTx db = new OObjectDatabaseTx("memory:data").create();

        // create a database on disk
        OObjectDatabaseTx db = new OObjectDatabaseTx("local:target/objectdata");
        if (db.exists()) {
            db = new OObjectDatabaseTx("plocal:target/objectdata").open("admin", "admin");
        } else {
            db.create();
        }

        db.getEntityManager().registerEntityClasses("edu.umd.ese.microservices.template.entity");
        return db;
    }

}