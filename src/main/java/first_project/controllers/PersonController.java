package first_project.controllers;

import first_project.dao.PersonDao;
import first_project.models.Book;
import first_project.models.Person;
import first_project.service.PersonService;
import first_project.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PersonController {
    private final PersonService personService;
    private final PersonValidator personValidator;



    @Autowired
    public PersonController(PersonService personService, PersonValidator personValidator) {
        this.personService = personService;
        this.personValidator = personValidator;
    }

    @GetMapping("/people")
    public String showAllPeople(Model model) {
        model.addAttribute("people", personService.getAllPeople());
        return "person/people";
    }

    @GetMapping("/people/new")
    public String newPerson(@ModelAttribute("newPerson") Person person) {

        //personService.addPerson(person);
        return "person/registered-form";
    }

    @PostMapping("/creat-new-person")
    public String createNewPerson(@ModelAttribute("newPerson") @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "person/registered-form";
        }
        personService.addPerson(person);
        return "redirect:/people";
    }

    @GetMapping("/people/{id}")
    public String showPersonInfo(@PathVariable String id, Model model) {
        List<Book> bookList;
        bookList = personService.getBooks(Integer.parseInt(id));
        model.addAttribute("person", personService.getPersonById(Integer.parseInt(id)));
        model.addAttribute("bookList", bookList);
        System.out.println(bookList);
        return "person/person-info";

    }

    @GetMapping("/people/{id}/edit")
    public String updatePersonInfo(@PathVariable int id, Model model) {
        Person person = personService.getPersonById(id);
        model.addAttribute("person", person);

        return "person/update-person";
    }

    @PatchMapping("/people/{id}")
    public String executeUpdatingPersonInfo(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, @PathVariable String id) {
        if (bindingResult.hasErrors()) return "person/update-person";
        personService.updatePerson(Integer.parseInt(id), person);
        return "redirect:/people";

    }

    @DeleteMapping("/delete-person/{id}")
    public String deletePerson(@PathVariable String id) {
        personService.deletePerson(Integer.parseInt(id));
        return "redirect:/people";
    }


}
