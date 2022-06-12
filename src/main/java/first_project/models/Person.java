package first_project.models;


import org.springframework.stereotype.Component;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@Component
public class Person {

//   LocalDate currentDate = LocalDate.now();
// int maximumBirthYearAllowed = currentDate.getYear() - 14;
    private int id;

    @NotEmpty(message = "Person full name must not be empty")
    @Size(min = 3, message = " Your full name should have more than 2 letters")
    private String fullName;

    //@NotEmpty(message = " This field must not be empty")
    @Min(value = 1920, message = "Year of birth should not be less than 1920")
    private int yearOfBirthday;

    public Person() {
    }

    public Person(int id, String fullName, int yearOfBirthday) {
        this.id = id;
        this.fullName = fullName;
        this.yearOfBirthday = yearOfBirthday;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBirthday() {
        return yearOfBirthday;
    }

    public void setYearOfBirthday(int yearOfBirthday) {
        this.yearOfBirthday = yearOfBirthday;
    }


    @Override
    public String toString() {
        return "Person{" +
                "fullName='" + fullName + '\'' +
                ", yearOfBirthday=" + yearOfBirthday +
                '}';
    }
}
