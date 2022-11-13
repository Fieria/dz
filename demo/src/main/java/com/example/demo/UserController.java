package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class UserController {
    private final List<User> users = new ArrayList<>();

    //curl -X POST -H "Content-Type: application/json" -d {name} -d {age} http://localhost:8080/users
    /*@PostMapping("users")
    public ResponseEntity<Void> addUser(@RequestBody String na)
    {
    users.add(new User(na));
    return ResponseEntity.accepted().build();
    }*/


    //curl -X POST -H "Content-Type: application/json" -d '{"name":"Polina","age":16}' http://localhost:8080/users
    @PostMapping("users")
    public ResponseEntity<Void> addUser(@RequestBody User user) {
        users.add(user);
        return ResponseEntity.accepted().build();
    }

    //curl -X DELETE http://localhost:8080/users/{index}
    @DeleteMapping("users/{index}")
    public ResponseEntity<Void> deleteUser(@PathVariable("index") Integer index) {
        users.remove((int) index);
        return ResponseEntity.noContent().build();
    }

    //curl http://localhost:8080/users/{index}
    @GetMapping("users/{index}")
    public ResponseEntity<User> getUser(@PathVariable("index") Integer index) {
        return ResponseEntity.ok(users.get(index));
    }

    //curl http://localhost:8080/users
    @GetMapping("users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(users);
    }

    //curl -X PUT -H "Content-Type: application/json" -d {age} http://localhost:8080/users/{index}/age
    @PutMapping("users/{index}/age")
    public ResponseEntity<Void> updateAge(@PathVariable("index") Integer i, @RequestBody Integer age) {
        users.get(i).setAge(age);
        return ResponseEntity.accepted().build();
    }
}
