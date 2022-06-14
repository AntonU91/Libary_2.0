package second_project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import second_project.models.Book;
import second_project.models.Person;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Repository
public interface PersonRepo extends JpaRepository<Person,Integer> {
    Person findByFullName(@NotEmpty(message = "Person full name must not be empty") @Size(min = 3, message = " Your full name should have more than 2 letters") String fullName);
}
