package tn.fmass.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.fmass.entities.Annonce;

public interface AnnonceRepo extends JpaRepository<Annonce, Long> {
}
