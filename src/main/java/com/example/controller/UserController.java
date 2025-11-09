package com.example.controller;

import com.example.model.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // --- GET tutti gli utenti
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // --- GET singolo utente per ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // --- POST nuovo utente
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    // --- PUT aggiornamento completo utente
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return userRepository.findById(id)
            .map(user -> {
                user.setFirstName(updatedUser.getFirstName());
                user.setLastName(updatedUser.getLastName());
                user.setEmail(updatedUser.getEmail());
                user.setAddress(updatedUser.getAddress());
                userRepository.save(user);
                return ResponseEntity.ok(user);
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // --- PATCH aggiornamento parziale utente
    @PatchMapping("/{id}")
    public ResponseEntity<User> patchUser(@PathVariable Long id, @RequestBody User updatedFields) {
        return userRepository.findById(id)
            .map(user -> {
                if (updatedFields.getFirstName() != null) user.setFirstName(updatedFields.getFirstName());
                if (updatedFields.getLastName() != null) user.setLastName(updatedFields.getLastName());
                if (updatedFields.getEmail() != null) user.setEmail(updatedFields.getEmail());
                if (updatedFields.getAddress() != null) user.setAddress(updatedFields.getAddress());
                userRepository.save(user);
                return ResponseEntity.ok(user);
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // --- DELETE utente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        return userRepository.findById(id)
            .map(user -> {
                userRepository.delete(user);
                return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // --- GET ricerca utenti per nome/cognome
    @GetMapping("/search")
    public List<User> searchUsers(@RequestParam(required = false) String firstName,
                                  @RequestParam(required = false) String lastName) {
        if (firstName != null && lastName != null) {
            return userRepository.findByFirstNameAndLastName(firstName, lastName);
        } else if (firstName != null) {
            return userRepository.findByFirstName(firstName);
        } else if (lastName != null) {
            return userRepository.findByLastName(lastName);
        } else {
            return userRepository.findAll();
        }
    }
}

