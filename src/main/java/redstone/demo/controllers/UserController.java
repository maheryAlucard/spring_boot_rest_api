package redstone.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import redstone.demo.Forms.UserAuthForm;
import redstone.demo.Forms.UserJWTResult;
import redstone.demo.models.User;
import redstone.demo.repositories.UserRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/loging")
    public UserJWTResult authUser(@RequestBody UserAuthForm authForm) {
        User foundUser = null;
        foundUser = userRepository.findByUserName(authForm.getUsername());

        if (foundUser != null) {
            UserJWTResult result = new UserJWTResult(foundUser, "my-token-item");
            if (authForm.getPassword().equals(foundUser.getPassword())) {
                return result;
            } else
                throw new DataNotFoundException(
                        "Utilisateur introuvable ou mot de passe incorrect: (incorrect) : " + authForm.getPassword()
                                + " " + foundUser.getPassword());
        } else
            throw new DataNotFoundException(
                    "Utilisateur introuvable ou mot de passe incorrect: (null) :" + authForm.getUsername());
    }

    @PostMapping
    public User saveUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PostMapping("/reset")
    public UserJWTResult resetUserPassword(@RequestBody UserAuthForm authForm) {
        User foundUser = null;
        foundUser = userRepository.findByEmail(authForm.getEmail());
        if (foundUser != null) {
            UserJWTResult result = new UserJWTResult(foundUser, "my-token-item");
            return result;
        } else
            throw new DataNotFoundException("Utilisateur introuvable ou mot de passe incorrect");
    }

    // Custom exception for data not found
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class DataNotFoundException extends RuntimeException {
        public DataNotFoundException(String message) {
            super(message);
        }
    }

    @GetMapping
    public Iterable<User> findAllUsers() {
        Iterable<User> users = userRepository.findAll();
        users.forEach(user -> {
            user.setPassword(null);
        });
        return users;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable(value = "id") long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            return ResponseEntity.ok().body(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public Boolean deleteUserById(@PathVariable(value = "id") long id) {
        userRepository.deleteById(id);
        return true;
    }

    @DeleteMapping
    public Boolean deleteUserByIds(@RequestBody Iterable<Long> ids) {
        userRepository.deleteAllById(ids);
        return true;
    }
}
