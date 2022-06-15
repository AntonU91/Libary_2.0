package second_project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import second_project.models.Book;
import second_project.models.Person;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer> {
    public Book findByTitleStartingWith (String typedString);
}
