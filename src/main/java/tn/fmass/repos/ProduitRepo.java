package tn.fmass.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.fmass.entities.Boutique;
import tn.fmass.entities.Categorie;
import tn.fmass.entities.Produit;

import java.util.List;

public interface ProduitRepo extends JpaRepository<Produit, Long> {
    List<Produit> findProduitByBoutique(Boutique b);
    List<Produit> findProduitByBoutiqueAndCategorie(Boutique b, Categorie c);
}
