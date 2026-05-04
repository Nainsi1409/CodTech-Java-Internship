package com.codtech.crud;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/users")
public class userController {

    List<User> users = new ArrayList<>();

    
    @PostMapping
    public String addUser(@RequestBody User user) {
        users.add(user);
        return "User added successfully";
    }

  
    @GetMapping
    public List<User> getUsers() {
        return users;
    }

   
    @PutMapping("/{id}")
    public String updateUser(@PathVariable int id, @RequestBody User newUser) {
        for (User user : users) {
            if (user.getId() == id) {
                user.setName(newUser.getName());
                return "User updated";
            }
        }
        return "User not found";
    }

   
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {
        users.removeIf(user -> user.getId() == id);
        return "User deleted";
    }
}