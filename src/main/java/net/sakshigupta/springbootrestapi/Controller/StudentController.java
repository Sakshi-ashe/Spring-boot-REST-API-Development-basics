package net.sakshigupta.springbootrestapi.Controller;

import net.sakshigupta.springbootrestapi.Bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("student") //📍 define base url at class level
public class StudentController {

    /*
        📍 Spring Boot Rest API returns Java bean

        http://localhost:8080/student
        Returns {"id":1,"firstName":"Sakshi","lastName":"Gupta"}
    */
    @GetMapping()
    public Student getStudent(){
        Student student = new Student(1, "Sakshi", "Gupta");
        return student;
    }


    /*
        📍 Spring Boot Rest API returns List

         http://localhost:8080/student/students
         Returns [{"id":1,"firstName":"Sakshi","lastName":"Gupta"},{"id":2,"firstName":"Bruce","lastName":"Wayne"}]
    */
    @GetMapping("students")
    public List<Student> getStudents(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Sakshi", "Gupta"));
        students.add(new Student(2, "Bruce", "Wayne"));

        return students;
    }


    /*
        📍 Spring Boot Rest API with path variable
        {id} - URI template variable
        📌@PathVariable : used on a method argument to bind it to the value of a URI template variable

        http://localhost:8080/student/4
        Returns {"id":4,"firstName":"Sakshi","lastName":"Gupta"}
    */
    @GetMapping("{id}")
    public Student studentPathVariable(@PathVariable int id){
        return new Student(id, "Sakshi", "Gupta");
    }

    /*
        📍 Spring Boot Rest API with path variable : when name of method argument is different than URI
        📌@PathVariable("id")

        http://localhost:8080/student/5/jai%20bansal
        Returns {"id":5,"firstName":"jai","lastName":"bansal"}
    */
    @GetMapping("{id}/{name}")
    public Student studentPathVariable2(@PathVariable("id") int studentId,
                                        @PathVariable("name") String studentName){
        return new Student(studentId, studentName.split(" ")[0], studentName.split(" ")[1]);
    }

     /*
        📍 Spring Boot Rest API with Request Param
        📌Query Parameter/Request Param : ?id=1

        http://localhost:8080/student/query?id=2
        Returns {"id":2,"firstName":"Sakshi","lastName":"Gupta"}
      */
    @GetMapping("query")
    public Student studentRequestVariable(@RequestParam int id){
        return new Student(id, "Sakshi", "Gupta");
    }

    /*
        📍 Spring Boot Rest API with Multiple Request Param
        📌 '&' in queries

        http://localhost:8080/student/query2?id=7&fn=Sakshi&ln=Gupta
        {"id":7,"firstName":"Sakshi","lastName":"Gupta"}
     */
    @GetMapping("query2")
    public Student studentRequestVariable2(@RequestParam int id, @RequestParam("fn") String firstName, @RequestParam("ln") String lastName){
        return new Student(id, firstName, lastName);
    }

    /*
        📍 Spring Boot Rest API that handles http POST request
        📌 @PostMapping: Post for creating new resources
        📌 @RequestBody : converts Json passed in Http Request payload automatically to Java object

        Open Postman : new request
        POST http://localhost:8080/student/create
        Body -> raw -> json
        {
            "id" : 1,
            "firstName" : "sakshi",
            "lastName" : "gupta"
        }

        Returns: {"id":1,"firstName":"sakshi","lastName":"gupta"}
        Response: 200 OK
     */
    @PostMapping("create")
    public Student createStudent(@RequestBody Student student){
        return student;
    }

    /*
        📍 POST call that returns status code
        📌 @ResponseStatus: by default status sent on success is 200 OK

        Body
        {
            "id" : 1,
            "lastName" : "gupta"
        }
        Returns:- {"id":1,"firstName":null,"lastName":"gupta"}
        Response: 201 CREATED
     */
    @PostMapping("create2")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent2(@RequestBody Student student){
        return student;
    }

    /*
        📍 Spring Boot Rest API that handles http PUT request
        📌 PutMapping : Updating existing resources

        PUT http://localhost:8080/student/update/1
        body: {
            "lastName" : "LNU"
        }
        Returned: {"id":0,"firstName":null,"lastName":"LNU"} //Note we need db to actually create & persist student first
    */
    @PutMapping("update/{id}")
    public Student updateStudent(@RequestBody Student student, @PathVariable("id") int studentId){
        return student;
    }

    /*
        📍 Spring Boot Rest API that handles http DELETE request
        📌 DeleteMapping : Deleting existing resources

        DELETE http://localhost:8080/student/delete/3
        Returned: Deleted student successfully!
    */
    @DeleteMapping("delete/{id}")
    public String deleteStudent(@PathVariable int id){
        return "Deleted student successfully!";
    }

    /*
        📍 Using Spring ResponseEntity to manipulate the HTTP response
        📌ResponseEntity: represents the whole HTTP response: status code, body, headers. Or any combination of these.
        We can use it as a return type with any kind of request Get, Post, Put, Delete
        We can skip setting ResponseStatus.
     */
    @GetMapping("responseEntity")
    public ResponseEntity<Student> getStudent2(){
        Student student = new Student(1, "Sakshi", "Gupta");

        //return new ResponseEntity<>(student, HttpStatus.OK);
        return ResponseEntity.ok()
                .header("custom-header", "sakshi")
                .body(student);
    }

}
