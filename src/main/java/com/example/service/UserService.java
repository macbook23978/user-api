package com.example.service;

import com.example.model.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // GET tutti gli utenti
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // GET utente per ID
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // POST nuovo utente
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // PUT aggiornamento intero
    public User updateUser(Long id, User user) {
        user.setId(id);
        return userRepository.save(user);
    }

    // PATCH aggiornamento parziale
    public User updatePartial(Long id, Map<String, Object> updates) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) return null;

        User user = optionalUser.get();
        updates.forEach((key, value) -> {
            switch (key) {
                case "firstName": user.setFirstName((String) value); break;
                case "lastName": user.setLastName((String) value); break;
                case "email": user.setEmail((String) value); break;
                case "address": user.setAddress((String) value); break;
            }
        });
        return userRepository.save(user);
    }

    // DELETE utente
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // Ricerca utenti per nome e/o cognome
    public List<User> searchUsers(String firstName, String lastName) {
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

