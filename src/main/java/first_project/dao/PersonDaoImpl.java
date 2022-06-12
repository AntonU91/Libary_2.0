package first_project.dao;

import first_project.models.Book;
import first_project.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDaoImpl implements PersonDao {

    RowMapper<Person> personRowMapper = (ResultSet resultSet, int rowNum) ->
            new Person(resultSet.getInt("id"),
                    resultSet.getString("full_name"),
                    resultSet.getInt("year_of_birthday"));

    RowMapper<Book> bookRowMapper = (ResultSet resultSet, int rowNum) -> new Book(resultSet.getInt("id"),
            resultSet.getString("title"),
            resultSet.getString("author"),
            resultSet.getInt("year_of_publication"),
            resultSet.getInt("person_id"));

    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public PersonDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Person getPersonById(int id) {
        String sqlQuery = "select * from postgres.project_1.person where id=?";
        return jdbcTemplate.query(sqlQuery, new Object[]{id}, personRowMapper).stream().findAny().orElse(null);
    }

    @Override
    public List<Person> getAllPeople() {
        List<Person> personList = new ArrayList<>();
        String sqlQuery = "select * from postgres.project_1.person ";
        return jdbcTemplate.query(sqlQuery, personRowMapper);
    }

    @Override
    public void addPerson(Person person) {
        String sqlQuery = "insert into postgres.project_1.person (full_name, year_of_birthday)  values (?,?)";
        jdbcTemplate.update(sqlQuery, person.getFullName(), person.getYearOfBirthday());

    }

    @Override
    public void updatePerson(int id, Person updatedPerson) {
        String sqlQuery = "update postgres.project_1.person set full_name=?, year_of_birthday=? where id=?";
        jdbcTemplate.update(sqlQuery, updatedPerson.getFullName(), updatedPerson.getYearOfBirthday(), id);

    }

    @Override
    public void deletePerson(int id) {
        String sqlQuery = " delete from postgres.project_1.person where id=?";
        jdbcTemplate.update(sqlQuery, id);
    }

    @Override
    public Optional<Person> getPersonByFullName (String fullName) {
        String sqlQuery = "select * from postgres.project_1.person where full_name=?";
         return Optional.ofNullable( jdbcTemplate.query(sqlQuery, new Object[] {fullName}, personRowMapper).stream().findAny().orElse(null));
    }

    @Override
    public List<Book> getBooks(int id) {
        String sql = "select * from postgres.project_1.book where person_id =?";
        return jdbcTemplate.query(sql, new Object[] {id}, bookRowMapper);
    }


}
