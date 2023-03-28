package tn.fmass.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.fmass.entities.Annonce;
import tn.fmass.services.AnnonceService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class AnnonceController {
    private final AnnonceService annonceService;
    @PostMapping("annonces/{id}")
    public ResponseEntity<Annonce> addAnnonce(@RequestBody Annonce a, @PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(annonceService.addAnnonce(a,id));
    }
    @GetMapping ("annonces")
    public ResponseEntity<List<Annonce>> getAllAnnonces(){
        return ResponseEntity.ok(annonceService.getAllAnnonces());
    }
    @GetMapping ("annonces/{id}")
    public ResponseEntity<Annonce> getAnnonce(@PathVariable long id){
        return ResponseEntity.ok(annonceService.getAnnonce(id));
    }
    @PutMapping("annonces/{id}")
    public ResponseEntity<Annonce> updateAnnonce(@RequestBody Annonce a,@PathVariable Long id){
        return ResponseEntity.ok(annonceService.updateAnnonce(a,id));
    }
    @DeleteMapping("annonces/{id}")
    public ResponseEntity<Boolean> deleteAnnonce(@PathVariable Long id){
        annonceService.deleteAnnonce(id);
        return ResponseEntity.ok(true);
    }
}
