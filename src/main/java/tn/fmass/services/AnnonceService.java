package tn.fmass.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.fmass.entities.Annonce;
import tn.fmass.entities.Sayed;
import tn.fmass.exceptions.ResourceNotFoundException;
import tn.fmass.repos.AnnonceRepo;
import tn.fmass.repos.UtilisateurRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnnonceService {
    private final AnnonceRepo ar;
    private final UtilisateurRepo ur;
    public List<Annonce> getAllAnnonces(){
        return ar.findAll();
    }
    public Annonce getAnnonce(long id){
        if(!ar.existsById(id))
            throw new ResourceNotFoundException("Annonce avec id "+id+" inexistant !");
        return ar.findById(id).get();
    }
    public Annonce addAnnonce(Annonce a,Long id){
        if(!ur.existsById(id))
            throw new ResourceNotFoundException("Utilisateur avec id "+id+" inexistant !");
        Sayed sayed =(Sayed) ur.findById(id).get();
        a.setSayed(sayed);
        return ar.save(a);
    }
    public Annonce updateAnnonce(Annonce a,Long id){
        if(!ar.existsById(id))
            throw new ResourceNotFoundException("Annonce avec id "+id+" inexistant !");
        a.setId(id);
        return ar.save(a);
    }
    public void deleteAnnonce(Long id){
        if(!ar.existsById(id))
            throw new ResourceNotFoundException("Annonce avec id "+id+" inexistant !");
        ar.deleteById(id);
    }
}
