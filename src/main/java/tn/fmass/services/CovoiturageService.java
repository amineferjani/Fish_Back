package tn.fmass.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.fmass.entities.Covoiturage;
import tn.fmass.entities.Sayed;
import tn.fmass.exceptions.ResourceNotFoundException;
import tn.fmass.repos.CovoiturageRepo;
import tn.fmass.repos.UtilisateurRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CovoiturageService {
    private final CovoiturageRepo cr;
    private final UtilisateurRepo ur;

    public List<Covoiturage> getAllcovoiturages(){
        return cr.findAll();
    }
    public Covoiturage getCovoiturage(long id){
        if(!cr.existsById(id))
            throw new ResourceNotFoundException("Covoiturage avec id "+id+" inexistant !");
        return cr.findById(id).get();
    }
    public Covoiturage addCovoiturage(Covoiturage c,Long id){
        if(!ur.existsById(id))
            throw new ResourceNotFoundException("Utilisateur avec id "+id+" inexistant !");
        Sayed sayed=(Sayed)ur.findById(id).get();
        c.setSayed(sayed);
        return cr.save(c);
    }
    public Covoiturage updateCovoiturage(Covoiturage c,Long id){
        if(!cr.existsById(id))
            throw new ResourceNotFoundException("Covoiturage avec id "+id+" inexistant !");
        c.setId(id);
        return cr.save(c);
    }
    public void deleteCovoiturage(Long id){
        if(!cr.existsById(id))
            throw new ResourceNotFoundException("Covoiturage avec id "+id+" inexistant !");
        cr.deleteById(id);
    }
}
