package org.example.controller;

import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private static UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> list()
    {
        var users = userService.readAll();
        return users != null
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    private ResponseEntity<Boolean> addUser(@RequestBody User user)
    {
        return userService.create(user) == true
                ? new ResponseEntity<>(true, HttpStatus.CREATED)
                : new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

    @GetMapping("{id}")
    private ResponseEntity<User> getOneUser(@PathVariable Long id) {
        var user =  userService.read(id);
        return user != null && !user.isEmpty()
                ? new ResponseEntity<>(user.get(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable(name="id") long id, @RequestBody User user) {
        var userFromDbOpt = userService.read(id);
        if(userFromDbOpt.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        var userFromDb = userFromDbOpt.get();
        BeanUtils.copyProperties(user, userFromDb, "id");
        return userService.update(user, userFromDb.getId()) == true
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return userService.delete(id) == true
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
}
