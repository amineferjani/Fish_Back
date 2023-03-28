package tn.fmass.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.fmass.entities.Categorie;
import tn.fmass.services.CategorieService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class CategorieController {

    private final CategorieService cs;
    @GetMapping("categories")
    public ResponseEntity<List<Categorie>> getAllCategories(){
        return ResponseEntity.ok(cs.getAllCategories());
    }
    @GetMapping("categories/{id}")
    public ResponseEntity<Categorie> getCategorie(@PathVariable long id){
        return ResponseEntity.ok(cs.getCategorie(id));
    }
    @PostMapping("categories")
    public ResponseEntity<Categorie> addCategorie(@RequestBody Categorie c){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cs.addCategorie(c));
    }
    @PutMapping("categories/{id}")
    public ResponseEntity<Categorie> updateCategorie(@RequestBody Categorie c,@PathVariable long id){
        return ResponseEntity.ok(cs.updateCategorie(c,id));
    }
    @DeleteMapping("categories/{id}")
    public ResponseEntity<Boolean> deleteCategorie(@PathVariable Long id){
        cs.deleteCategorie(id);
        return ResponseEntity.ok(true);
    }
}
