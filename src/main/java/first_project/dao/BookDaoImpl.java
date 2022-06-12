package first_project.dao;

import first_project.models.Book;
import first_project.models.Person;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Component
public class BookDaoImpl implements BookDao {

    RowMapper<Book> bookRowMapper = (ResultSet resultSet, int rowNum) -> new Book(resultSet.getInt("id"),
            resultSet.getString("title"),
            resultSet.getString("author"),
            resultSet.getInt("year_of_publication"),
            resultSet.getInt("person_id"));

    RowMapper<Person> personRowMapper = (ResultSet resultSet, int rowNum) ->
            new Person(resultSet.getInt("id"),
                    resultSet.getString("full_name"),
                    resultSet.getInt("year_of_birthday"));

    private final JdbcTemplate jdbcTemplate;

    public BookDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Book getBookById(int id) {
        String sqlQuery = "select * from project_1.book where id=?";
        return jdbcTemplate.query(sqlQuery, new Object[]{id}, bookRowMapper).stream().findAny().orElse(null);
    }

    @Override
    public List<Book> getAllBooks() {
        String sqlQuery = "select * from project_1.book";
        return jdbcTemplate.query(sqlQuery, bookRowMapper);
    }

    @Override
    public void addBook(Book book) {
        String sqlQuery = " insert into project_1.book(title, author, year_of_publication)  values (?,?,?)";
        jdbcTemplate.update(sqlQuery, book.getTitle(), book.getAuthor(), book.getYearOfPublication());

    }

    @Override
    public void updateBook(int id, Book updatedBook) {
        String sql = "update  postgres.project_1.book set title=?, author=?, year_of_publication=? where id=?";
        jdbcTemplate.update(sql, updatedBook.getTitle(), updatedBook.getAuthor(), updatedBook.getYearOfPublication(), id);

    }

    @Override
    public void deleteBook(int id) {
        String sql = "delete from postgres.project_1.book where id=?";
        jdbcTemplate.update(sql, id);
    }

    public void setOwnerForBook(int bookId, Person person) {
       // int personId = person.getId();
        String sql = "update  project_1.book set person_id=?  where id=?";
        jdbcTemplate.update(sql, person.getId(), bookId);
    }

    @Override
    public Person getOwner(int bookId) {
        Book book = getBookById(bookId);
        int personId = book.getPersonId();
        String sqlQuery = "select * from postgres.project_1.person where id=?";
        return jdbcTemplate.query(sqlQuery, new Object[]{personId}, personRowMapper).stream().findAny().orElse(null);
    }

    @Override
    public void releaseBookFromTheOwner(int id) {
        String sqlQuery = "update project_1.book  set person_id=? where person_id=?";
        jdbcTemplate.update(sqlQuery, null, id);
    }


}
