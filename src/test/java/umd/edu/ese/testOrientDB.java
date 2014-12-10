package umd.edu.ese;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import edu.umd.ese.person.entity.Address;
import edu.umd.ese.person.entity.Person;
import edu.umd.ese.microservices.template.repository.OrientDBEmbeddable;
import edu.umd.ese.microservices.template.repository.OrientHelper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lsymms
 * Date: 12/1/14
 * Time: 9:23 AM
 * To change this template use File | Settings | File Templates.
 */
public class testOrientDB {

    OObjectDatabaseTx db;
//
//    @Before
//    public void startDB() throws Exception {
//        orientDBServer = new OrientDBEmbeddable();
//    }
//
//    @After
//    public void stopDB() {
//        orientDBServer.shutdown();
//    }

    @Before
    public void initDb() {
        db = OrientDBEmbeddable.getObjectDatabase();
    }

    @Test
    public void testJsonDocument() {

        // OPEN THE DATABASE
        ODatabaseDocumentTx db = OrientDBEmbeddable.getDocumentDatabase();

        // CREATE A NEW DOCUMENT AND FILL IT
        ODocument doc = new ODocument("PersonDoc");
        doc.fromJSON("{ \n" +
                "  \"name\": \"Jengo\", \n" +
                "  \"surname\": \"Fett\",\n" +
                "  \"address\": {\n" +
                "    \"address line1\": \"56 Womp Rat Blvd.\",\n" +
                "    \"city\": \"Mos Isley\",\n" +
                "    \"state\": \"Hutt Province\",\n" +
                "    \"country\": \"Tatoine\"\n" +
                "  }\n" +
                "}");

        // SAVE THE DOCUMENT
        doc.save();

        List<Object> resultList = db.query(new OSQLSynchQuery<Object>("Select from PersonDoc where name='Jengo'"));
        System.out.println(resultList.get(0).toString());

        db.close();
    }

    @Test
    public void testObjectPersistence() {

        Person person = getTestPerson1();

        Person inserted = (Person) db.save(person);

        Person found = (Person) OrientHelper.findOne("select from Person where name='Han'", db);

        //Assert.assertEquals(person,inserted);

        Assert.assertEquals(person.toString(),found.toString());
        db.delete(found);
    }

    private Person getTestPerson1() {
        Person person = new Person();
        person.setName("Han");
        person.setSurname("Solo");
        Address address = new Address();
        address.setLine1("2727 Falcon Way");
        address.setCity("Galactic City");
        address.setState("District of the Republic");
        address.setCountry("Coroscant");
        person.setAddress(address);

        return person;
    }

    @Test
    public void testPersonJsonMapping() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        Person inserted = (Person) db.save(getTestPerson1());
        List<Person> people = new ArrayList<Person>();
        people.add(getTestPerson1());
        String json = mapper.writeValueAsString(people);
        System.out.println(json);
    }

    @Test
    public void testPersonFromDbJsonMapping() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Person inserted = (Person) db.save(getTestPerson1());
        List<Person> results = db.query(new OSQLSynchQuery<Person>("select from Person"));

        String json = mapper.writeValueAsString(results);
        System.out.println(json);
    }

    @After
    public void closeDb() {
        db.close();
    }
}
