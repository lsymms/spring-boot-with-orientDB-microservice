package edu.umd.ese.microservices.template.repository;

import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class OrientHelper {

    public static List<Object> insertAll(Collection<Object> objectsToSave, OObjectDatabaseTx db) {
        List<Object> ret = null;
        for(Object objectToSave : objectsToSave) {
            if (ret == null) ret = new ArrayList<Object>();
            ret.add(db.detach(db.save(objectToSave)));
        }
        return ret;
    }

    public static Object findOne(String query, OObjectDatabaseTx db) {
        Object ret = null;
        List<Object>results = db.query(new OSQLSynchQuery<Object>(query));
        if (results.size() == 1) {
            ret = db.detach(results.get(0));
        }
        return ret;
    }
    
}
