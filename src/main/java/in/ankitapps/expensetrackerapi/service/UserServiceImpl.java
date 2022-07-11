package in.ankitapps.expensetrackerapi.service;

import in.ankitapps.expensetrackerapi.entity.User;
import in.ankitapps.expensetrackerapi.entity.UserModel;
import in.ankitapps.expensetrackerapi.exceptions.ItemsAlreadyExistsException;
import in.ankitapps.expensetrackerapi.exceptions.ResourceNotFoundException;
import in.ankitapps.expensetrackerapi.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    public User createUser(UserModel user){
        if(userRepository.existsByEmail(user.getEmail())){
            throw new ItemsAlreadyExistsException("Email already registered for this email id "+user.getEmail());
        }
        User newUser=new User();
        BeanUtils.copyProperties(user,newUser);
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        return (userRepository.save(newUser));
    }

    public User readUser(){
        Long userId= getLoggedInUser().getId();
        Optional<User> user=userRepository.findById(userId);
        if(user.isPresent()) {
            return (user.get());
        }

        throw new ResourceNotFoundException("User is not found for id "+userId);
    }

    public User updateUser(UserModel user){
        User existingUser=readUser();

        existingUser.setName(user.getName()!=null? user.getName() : existingUser.getName());

        existingUser.setEmail(user.getEmail()!=null? user.getEmail() : existingUser.getEmail());

        existingUser.setPassword(user.getPassword()!=null? bcryptEncoder.encode(user.getPassword()) : existingUser.getPassword());

        existingUser.setAge(user.getAge()!=null? user.getAge() : existingUser.getAge());

        return (userRepository.save(existingUser));
    }

    public void deleteUser(){
        User existingUser=readUser();
        userRepository.delete(existingUser);
    }


    public User getLoggedInUser(){
       Authentication authentication= SecurityContextHolder.getContext().getAuthentication();

       String email=authentication.getName();

       return (userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("user not found for this email "+email)));
    }
}
