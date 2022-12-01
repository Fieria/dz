package users;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import java.util.List;

@RestController
public class UserController {
    private final List<User> users = new ArrayList<User>();
    Long max_id = Long.valueOf(0);


    //curl http://localhost:8080/users
    @GetMapping("users/all")
    public ResponseEntity<List<String>> getAllUsers() {
        List<String> s = new ArrayList<String>();
        for (User u: users){
            s.add(u.toString());
        }
        return ResponseEntity.ok(s);
    }


    //curl -X POST -H "Content-Type: application/json" -d '{"name":"Polina","password": "56", "age": 16}' http://localhost:8080/users?repeatPassword=56
    @PostMapping("users")
    public ResponseEntity<Void> addUser(@RequestBody User user, @RequestParam String repeatPassword) {
        if(user.getPassword().equals(repeatPassword)){
            for (User u : users){
                if (u.getName().equals(user.getName())) return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            max_id++;
            user.setId(max_id);
            users.add(user);
            return ResponseEntity.accepted().build();
        }
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    //curl http://localhost:8080/users?id=239
    @GetMapping("users")
    public ResponseEntity<String> getUser(@RequestParam Integer id) {
        for (User u : users){
            if (u.getId() == id) return ResponseEntity.ok(u.toString());
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    //curl -X DELETE http://localhost:8080/users?id=239
    @DeleteMapping("users")
    public ResponseEntity<Void> deleteUser(@RequestParam Integer id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                users.remove(i);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    /*curl -X PUT -H "Content-Type: application/json" -d '{"name":"Polina","password": "ghj", "age": 16}'
    http://localhost:8080/users?id=239&password=ghj */
    @PutMapping("users")
    public ResponseEntity<User> updateUser(@RequestParam Integer id, @RequestBody User user, @RequestParam String repeatPassword) {

        if (repeatPassword.equals(user.getPassword())) {
            for (User u : users) {
                if (u.getName().equals(user.getName()) && u.getId() != user.getId()) // проверка, что такого name не существует у других пользователей
                    return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getId() == id) {
                    users.remove(i);
                    users.add(i, user);
                    return new ResponseEntity<>(HttpStatus.OK);
                }
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    //curl http://localhost:8080/users/age?age=5
    @GetMapping("users/age")
    public ResponseEntity<List<User>> getUsersByAge(@RequestParam int age) {
        List<User> filtered_users = new ArrayList<User>();
        for (User u: users) {
            if ((u.getAge() >= age - 5) && (u.getAge() <= age + 5)) filtered_users.add(u);
        }
        return ResponseEntity.ok(filtered_users);
    }
}


