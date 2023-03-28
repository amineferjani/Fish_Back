package tn.fmass.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.fmass.entities.Boutique;

public interface BoutiqueRepo extends JpaRepository<Boutique, Long> {
}
