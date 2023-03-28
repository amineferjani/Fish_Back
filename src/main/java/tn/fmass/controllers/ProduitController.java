package tn.fmass.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.fmass.entities.Produit;
import tn.fmass.services.ProduitsService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class ProduitController {

    private final ProduitsService ps;

    @GetMapping("produits/{id}")
    public ResponseEntity<List<Produit>>  getProduitBoutique(@PathVariable long id){
        return ResponseEntity.ok(ps.getProduitBoutique(id));
    }
    @GetMapping("produits/{idb}/{idc}")
    public ResponseEntity<List<Produit>>  getProduitBoutiqueCategorie(@PathVariable long idb,@PathVariable long idc){
        return ResponseEntity.ok(ps.getProduitBoutiqueCategorie(idb,idc));
    }
    @GetMapping("produit/{id}")
    public ResponseEntity<Produit> getProduit(@PathVariable long id){
        return ResponseEntity.ok(ps.getProduit(id));
    }
    @PostMapping("produits/{idb}/{idc}")
    public ResponseEntity<Produit> addProduit(@RequestBody Produit p,@PathVariable long idb,
                                              @PathVariable long idc){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ps.addProduit(p,idb,idc));
    }
    @PutMapping("produits/{idp}/{idb}/{idc}")
    public ResponseEntity<Produit> updateProduit(@RequestBody Produit p,@PathVariable long idp,
                                                 @PathVariable long idb,@PathVariable long idc){
        return ResponseEntity.status(HttpStatus.OK)
                .body(ps.updateProduit(p,idp,idb,idc));
    }
    @DeleteMapping("produits/{id}")
    public ResponseEntity<Boolean> deleteProduit(@PathVariable Long id){
        ps.deleteProduit(id);
        return ResponseEntity.ok(true);
    }
}
