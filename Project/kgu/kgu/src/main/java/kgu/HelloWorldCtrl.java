package main.java.kgu;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloWorldCtrl {

    @GetMapping("/hello")
    public String helloworld() {
        return "hello!";
    }

    @RequestMapping("/map")
    public String getMap() {
        return "mainmap";
    }
}
