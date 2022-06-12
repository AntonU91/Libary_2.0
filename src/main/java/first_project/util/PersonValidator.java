package first_project.util;

import first_project.models.Person;
import first_project.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator  implements Validator {

    private final PersonService personService;


    @Autowired
    public PersonValidator(PersonService personService) {
        this.personService = personService;
    }

    @Override
    // указывает какой клас будет обслуживать этот валидатор
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person =  (Person) target;
        // посмотреть есть ли такой человек в базе данных
        if(personService.getPersonByFullName(person.getFullName()).isPresent()) {
            errors.rejectValue("fullName", "", "This full name is already taken");

        }

    }
}
