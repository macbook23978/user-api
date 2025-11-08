package com.example.controller;

import com.example.model.UserMac;
import com.example.repository.UserMacRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserMacController {

    private final UserMacRepository userMacRepository;

    public UserMacController(UserMacRepository userMacRepository) {
        this.userMacRepository = userMacRepository;
    }

    // GET tutti gli utenti
    @GetMapping
    public List<UserMac> getAllUsers() {
        return userMacRepository.findAll();
    }

    // GET utente per ID
    @GetMapping("/{id}")
    public Optional<UserMac> getUserById(@PathVariable Long id) {
        return userMacRepository.findById(id);
    }

    // POST crea utente
    @PostMapping
    public UserMac createUser(@RequestBody UserMac userMac) {
        // createdAt e updatedAt gestiti automaticamente da @PrePersist
        return userMacRepository.save(userMac);
    }

    // PUT aggiorna utente
    @PutMapping("/{id}")
    public UserMac updateUser(@PathVariable Long id, @RequestBody UserMac newUserData) {
        return userMacRepository.findById(id)
                .map(user -> {
                    user.setFirstName(newUserData.getFirstName());
                    user.setLastName(newUserData.getLastName());
                    user.setEmail(newUserData.getEmail());
                    user.setAddress(newUserData.getAddress());
                    // updated_at gestito automaticamente da @PreUpdate
                    return userMacRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // DELETE elimina utente
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userMacRepository.deleteById(id);
        return "User deleted successfully";
    }
}
