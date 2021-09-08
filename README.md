## Dördüncü hafta ödevi son teslim tarihi : 06.08.2021(Gelecek hafta pazartesi) - Saat =>  23:30

![homework](https://user-images.githubusercontent.com/45206582/131386439-6727321a-5a50-4c20-9413-ea4013013434.PNG)

# Installation and usage

1. git clone https://github.com/113-GittiGidiyor-Java-Spring-Bootcamp/fourth-homework-hakandrmz.git
2. cd fourth-homework-hakandrmz/Homework-04
3. mvn spring-boot:run

# Testing endpoints

http://localhost:8080/swagger-ui.html

h2 database user info: http://localhost:8080/h2-console/
username: sa
password: password

# Used for implementation

1. Java 8
2. H2 Database
3. Swagger
4. Maven
5. Spring Data JPA
6. Lombok
7. Mapstruct
8. Spring Boot

# Folder Structure of Project

- /bootstrap  - load data on start
- /config     - swagger configs
- /controller - endpoints
- /dto        - data between layers
- /entity     - database models
- /exception  - specific exceptions
- /mappers    - dto to entity mapper
- /repository - for CrudRepository
- /service    - service layer between repository and controller 
- /utils      - error message constants

# Controller Endpoints
### Courses
- POST    /api/course/add 
- DELETE  /api/course/delete/{id}
- PUT     /api/course/update
- GET     /api/courses
- GET     /api/courses/{id}
### Logs
- GET     /logs
- GET     /logs/search/{keyword}
### Students
- POST    /api/student/add
- POST    /api/student/add/{courseId} 
- DELETE  /api/student/delete/{id}
- PUT     /api/student/update
- GET     /api/students
- GET     /api/students/search/{keyword}
### Instructors
- POST    /api/instructor/add
- DELETE  /api/instructor/delete/{id}
- PUT     /api/instructor/update
- GET     /api/instructors
- GET     /api/instructors/{id}

# Rules of Project

1. Student age must be greater than 18 and less than 40
2. Number of students must be less than 20
3. Instructor phone number must be unique
4. Course code is must be unique




