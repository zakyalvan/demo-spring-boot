package id.tomatech.demo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/people")
public class PersonController {
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(PersonController.class);
	
	private Map<Long, Person> database = new HashMap<>();
	
	public PersonController() {
		Person subhan = new Person(1l, "Subhan", "subhan@example.com", "MALE");
		Person zulfa = new Person(2l, "Zulfa", "subhan@example.com", "MALE");
		Person sarah = new Person(3l, "Sarah", "subhan@example.com", "MALE");
		
		database.put(subhan.getId(), subhan);
		database.put(zulfa.getId(), zulfa);
		database.put(sarah.getId(), sarah);
	}
	
	/**
	 * List all people.
	 * 
	 * @return
	 */
	@RequestMapping(value= "/list", method=RequestMethod.GET)
	public Collection<Person> listPerson() {
		LOGGER.info("List people");
		return database.values();
	}
	
	/**
	 * Retrieve person by id.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET, value="/detail/{id}")
	public HttpEntity<Person> detailPerson(@PathVariable Long id) {
		LOGGER.info("Retrieve person with id {}", id);
		if(!database.containsKey(id)) {
			return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Person>(database.get(id), HttpStatus.OK);
	}
}
