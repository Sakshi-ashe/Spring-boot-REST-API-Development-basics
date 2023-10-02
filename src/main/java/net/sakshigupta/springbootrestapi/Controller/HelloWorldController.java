package net.sakshigupta.springbootrestapi.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//📌@Controller //to make java class as Spring MVC controller
//📌@ResponseBody //object returned is automatically serialized into JSON and passed back into the HttpResponse object

@RestController //📌2 annotations in one @Controller, @ResponseBody
public class HelloWorldController {

    /*
        📍 Create Simple Spring Boot Rest API @GetMapping
        📌 @GetMapping :Get for retrieving resource data

        http://localhost:8080/hello-world
        Returns "Hello Sakshi"
    */
    @GetMapping("/hello-world") //HTTP GET Request .. http://localhost:8080/hello-world
    public String helloWorld(){
        return "Hello Sakshi";
    }
}
