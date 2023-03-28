package tn.fmass.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.fmass.entities.Utilisateur;
import java.util.List;


public interface UtilisateurRepo extends JpaRepository<Utilisateur, Long> {

    @Query("SELECT u FROM Sayed u ")
    List<Utilisateur> findAllSayed();
    @Query("SELECT u FROM Manager u ")
    List<Utilisateur> findAllManager();
}
