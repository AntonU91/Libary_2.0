package first_project.dao;

import first_project.models.Book;
import first_project.models.Person;

import java.util.List;
import java.util.Optional;


public interface PersonDao {
    public Person getPersonById (int id);
    public List<Person> getAllPeople ();
    public void addPerson (Person person);
    public void updatePerson (int id, Person updatedPerson);
    public void deletePerson (int id);
    public Optional<Person> getPersonByFullName (String fullName);
    public List<Book> getBooks (int id);


}
