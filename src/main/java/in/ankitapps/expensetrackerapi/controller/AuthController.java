package in.ankitapps.expensetrackerapi.controller;

import in.ankitapps.expensetrackerapi.entity.AuthModel;
import in.ankitapps.expensetrackerapi.entity.User;
import in.ankitapps.expensetrackerapi.entity.UserModel;
import in.ankitapps.expensetrackerapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(method = RequestMethod.POST,value="/login")
    public ResponseEntity<HttpStatus> login(@RequestBody AuthModel authModel){

        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authModel.getEmail(),authModel.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return (new ResponseEntity<HttpStatus>(HttpStatus.OK));
    }

    @RequestMapping(method = RequestMethod.POST,value = "/register")
    public ResponseEntity<User> save(@Valid @RequestBody UserModel user){
        return (new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED));
    }
}
