package tn.fmass.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.fmass.entities.Categorie;

public interface CategorieRepo extends JpaRepository<Categorie, Long> {
}
