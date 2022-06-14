package second_project.service;

import org.springframework.stereotype.Service;
import second_project.models.Book;
import second_project.models.Person;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PersonService {
    public Person getPersonById (int id);
    public List<Person> getAllPeople ();
    public void addPerson (Person person);
    public void updatePerson (int id, Person updatedPerson);
    public void deletePerson (int id);
    public Person getPersonByFullName (String fullName);
    public List<Book> getBooks (int id);
}
