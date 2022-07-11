package in.ankitapps.expensetrackerapi.controller;

import in.ankitapps.expensetrackerapi.entity.User;
import in.ankitapps.expensetrackerapi.entity.UserModel;
import in.ankitapps.expensetrackerapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService userService;



    @RequestMapping(method = RequestMethod.GET,value = "/profile")
    public User readUser(){
        return (userService.readUser());
    }

    @RequestMapping(method = RequestMethod.POST,value="/profile")
    public User updateUser(@RequestBody UserModel user){
        return (userService.updateUser(user));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(method=RequestMethod.DELETE,value="/deactivate")
    public void deleteUser(){
        userService.deleteUser();
    }
}
