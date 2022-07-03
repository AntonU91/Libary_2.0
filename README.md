# Library_2.0
Library 2.0 is a small pet project, implemented on the basis of MVC pattern, which aims to consolidate the practical skills in working with the Spring Framework.

This project implements an electronic library account and consists of two main components: members and books. You can add new, modify, delete members or books. You can assign a book to a specific person, as well as see the books that are taken by a specific person. The forms for creating new members or changing existing members or books apply field validation by specified conditions.

Library 2.0 is a logical continuation of Library 1.0 (https://github.com/AntonU91/Libary_1.0) In Library 2.0, the JDBC Template has been replaced by Spring Data JPA. A book search has been added to the navigation bar. In the Books section, pagination and sorting books by title has been added. Also, if a book is leased for more than a certain period of time (10 days) this book will be highlighted in red in the person-info.html view. The project uses the following technologies: Spring MVC, Spring Data JPA, Thymeleaf, Hibernate Validator, PostgreSQL, Bootstrap.
