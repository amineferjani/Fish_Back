package tn.fmass.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.fmass.entities.Covoiturage;
import tn.fmass.services.CovoiturageService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class CovoiturageController {
    private final CovoiturageService CovoiturageService;
    @PostMapping("Covoiturages/{id}")
    public ResponseEntity<Covoiturage> addCovoiturage(@RequestBody Covoiturage c, @PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CovoiturageService.addCovoiturage(c,id));
    }
    @GetMapping ("Covoiturages")
    public ResponseEntity<List<Covoiturage>> getAllCovoiturages(){
        return ResponseEntity.ok(CovoiturageService.getAllcovoiturages());
    }
    @GetMapping ("Covoiturages/{id}")
    public ResponseEntity<Covoiturage> getCovoiturage(@PathVariable long id){
        return ResponseEntity.ok(CovoiturageService.getCovoiturage(id));
    }
    @PutMapping("Covoiturages/{id}")
    public ResponseEntity<Covoiturage> updateCovoiturage(@RequestBody Covoiturage c,@PathVariable Long id){
        return ResponseEntity.ok(CovoiturageService.updateCovoiturage(c,id));
    }
    @DeleteMapping("Covoiturages/{id}")
    public ResponseEntity<Boolean> deleteCovoiturage(@PathVariable Long id){
        CovoiturageService.deleteCovoiturage(id);
        return ResponseEntity.ok(true);
    }
}
