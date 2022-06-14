package second_project.service;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import second_project.models.Book;
import second_project.models.Person;
import org.springframework.stereotype.Service;
import second_project.repositories.PersonRepo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.*;

@Service
@Transactional(readOnly = true)
public class PersonServiceImpl implements PersonService {
    private final PersonRepo personRepo;
    private final EntityManager em;

    @Autowired
    public PersonServiceImpl(PersonRepo personRepo, EntityManagerFactory emf, EntityManager em) {
        this.personRepo = personRepo;
        this.em = em;
    }

    @Override
    public Person getPersonById(int id) {
        Optional<Person> foundedPerson = personRepo.findById(id);
        return foundedPerson.orElse(null);
    }

    @Override
    public List<Person> getAllPeople() {
        return personRepo.findAll();
    }

    @Override
    @Transactional
    public void addPerson(Person person) {
        personRepo.save(person);
    }

    @Override
    @Transactional
    public void updatePerson(int id, Person updatedPerson) {
        updatedPerson.setId(id);
        personRepo.save(updatedPerson);

    }

    @Override
    @Transactional
    public void deletePerson(int id) {
        personRepo.deleteById(id);

    }

    @Override
    public Person getPersonByFullName(String fullName) {
        Optional<Person> foundPerson = Optional.ofNullable(personRepo.findByFullName(fullName));
        return foundPerson.orElse(null);
    }

    @Override
    public List<Book> getBooks(int id) {
        // Session session = em.unwrap(Session.class);
        // return session.createQuery("from Book  join fetch  Person  on Book.owner.id=Person.id where Book.owner.id=:id").setParameter("id", id).getResultList();
        Optional<Person> person = personRepo.findById(id);
        if (person.isPresent()) {
            Hibernate.initialize(person.get().getBooks());
            return person.get().getBooks();
        } else {
            return Collections.emptyList();
        }


    }
}
