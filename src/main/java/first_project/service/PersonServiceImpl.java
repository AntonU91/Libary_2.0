package first_project.service;

import first_project.dao.PersonDao;
import first_project.models.Book;
import first_project.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {
    private final PersonDao personDao;

    @Autowired
    public PersonServiceImpl(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public Person getPersonById(int id) {
        return personDao.getPersonById(id);
    }

    @Override
    public List<Person> getAllPeople() {
        return personDao.getAllPeople();
    }

    @Override
    public void addPerson(Person person) {
        personDao.addPerson(person);
    }

    @Override
    public void updatePerson(int id, Person updatedPerson) {
        personDao.updatePerson(id, updatedPerson);

    }

    @Override
    public void deletePerson(int id) {
        personDao.deletePerson(id);

    }

    @Override
    public Optional<Person> getPersonByFullName(String fullName) {
        return personDao.getPersonByFullName(fullName);
    }

    @Override
    public List<Book> getBooks(int id) {
       return personDao.getBooks(id);
    }


}
