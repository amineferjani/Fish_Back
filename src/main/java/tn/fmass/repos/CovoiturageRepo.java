package tn.fmass.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.fmass.entities.Covoiturage;

public interface CovoiturageRepo extends JpaRepository<Covoiturage, Long> {
}
