package edu.umd.ese.person;

import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import edu.umd.ese.person.entity.Person;
import edu.umd.ese.microservices.template.repository.OrientDBEmbeddable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "Person", description = "Person API")
@RequestMapping("/example")
public class Controller {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${message}")
    private String message;
    /*
     * This leaves a connection open which I believe locks the database.  Nice way to prevent unauth access.
     * You can also get the db in a try block and close on finally if you want multiple controllers
     */

    private OObjectDatabaseTx db;

    @RequestMapping(value="/",
                    method= RequestMethod.GET)
    public String index() {
        logger.info("asdf");
        return message;
    }

    @RequestMapping(value = "/people",
                    method = RequestMethod.POST,
                    headers = {"Content-type=application/json"})
    @ApiOperation(value = "Insert a new Person to the People dataset", notes = "Returns Person with DB handle info")
    public ResponseEntity<Person> insertPerson(@RequestBody Person person) {
        Person ret = null;
        ret = getDatabase().save(person);

        return new ResponseEntity<Person>(person, HttpStatus.OK);
    }

    @RequestMapping(value = "/people",
                    method = RequestMethod.GET)
    @ApiOperation(value = "Gell all People", notes = "Returns Person with DB handle info")
    public List<Person> getPeople() {
        List<Person>    ret = (List<Person>)getDatabase().query(new OSQLSynchQuery("select from Person"));
        return ret;
    }

    @RequestMapping(value = "/person",
            method = RequestMethod.PUT)
    @ApiOperation(value = "Updates a Person", notes = "Returns Person with DB handle info")
    public List<Person> updatePerson() {
        List<Person>    ret = (List<Person>)getDatabase().query(new OSQLSynchQuery("select from Person"));
        return ret;
    }

    private OObjectDatabaseTx getDatabase() {
        if (db == null) {
            db = OrientDBEmbeddable.getObjectDatabase();
        }
        return db;
    }

}
