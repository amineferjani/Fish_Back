package tn.fmass.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.fmass.entities.Boutique;
import tn.fmass.services.BoutiqueService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class BoutiqueController {
    private final BoutiqueService boutiqueService;
    @PostMapping("boutiques/{id}")
    public ResponseEntity<Boutique> addBoutique(@RequestBody Boutique b, @PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(boutiqueService.addBoutique(b,id));
    }
    @GetMapping ("boutiques")
    public ResponseEntity<List<Boutique>> getAllBoutiques(){
        return ResponseEntity.ok(boutiqueService.getAllBoutiques());
    }
    @GetMapping ("boutiques/{id}")
    public ResponseEntity<Boutique> getBoutique(@PathVariable long id){
        return ResponseEntity.ok(boutiqueService.getBoutique(id));
    }
    @PutMapping("boutiques/{id}")
    public ResponseEntity<Boutique> updateBoutique(@RequestBody Boutique b,@PathVariable Long id){
        return ResponseEntity.ok(boutiqueService.updateBoutique(b,id));
    }
    @DeleteMapping("boutiques/{id}")
    public ResponseEntity<Boolean> deleteBoutique(@PathVariable Long id){
        boutiqueService.deleteBoutique(id);
        return ResponseEntity.ok(true);
    }
}
