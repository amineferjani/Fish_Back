package tn.fmass.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tn.fmass.entities.Manager;
import tn.fmass.entities.Sayed;
import tn.fmass.entities.Utilisateur;
import tn.fmass.services.UtilisateurService;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class UtilisateurController {
    private final UtilisateurService userService;
    //@PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @GetMapping("users/{cat}")
    public ResponseEntity<Object> getAllUsers(@PathVariable int cat){
if (cat==1)
        return ResponseEntity.ok(userService.getAllSayed());
else
    return ResponseEntity.ok(userService.getAllManager());
    }
    @PostMapping("sayed")
    public ResponseEntity<Utilisateur> addSayed(@RequestBody @Valid Sayed usr){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.addSayed(usr));
    }
    @PostMapping("manager")
    public ResponseEntity<Utilisateur> addSayed(@RequestBody @Valid Manager usr){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.addManager(usr));
    }
    @PutMapping("sayed/{id}")
    public ResponseEntity<Utilisateur> updateSayed(@RequestBody @Valid Sayed usr, @PathVariable Long id){
        return ResponseEntity.ok(userService.updateSayed(usr,id));
    }
    @PutMapping("manager/{id}")
    public ResponseEntity<Utilisateur> updateManager(@RequestBody @Valid Manager usr, @PathVariable Long id){
        return ResponseEntity.ok(userService.updateManager(usr,id));
    }
    @DeleteMapping("users/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable  Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok(true);
    }
}