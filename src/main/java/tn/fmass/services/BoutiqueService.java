package tn.fmass.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.fmass.entities.Boutique;
import tn.fmass.entities.Manager;
import tn.fmass.exceptions.ResourceNotFoundException;
import tn.fmass.repos.BoutiqueRepo;
import tn.fmass.repos.UtilisateurRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoutiqueService {
    private final BoutiqueRepo boutiqueRepo;
    private final UtilisateurRepo utilisateurRepo;
public List<Boutique> getAllBoutiques(){
    return boutiqueRepo.findAll();
}
public Boutique getBoutique(long id){
    if(!boutiqueRepo.existsById(id))
        throw new ResourceNotFoundException("Boutique avec id "+id+" inexistant !");
    return boutiqueRepo.findById(id).get();
}
    public Boutique addBoutique(Boutique b,Long id){
        if(!utilisateurRepo.existsById(id))
            throw new ResourceNotFoundException("Manager avec id "+id+" inexistant !");
        Manager manager=(Manager)utilisateurRepo.findById(id).get();
        b.setManager(manager);
        return boutiqueRepo.save(b);
    }
    public Boutique updateBoutique(Boutique b,Long id){
        if(!boutiqueRepo.existsById(id))
            throw new ResourceNotFoundException("Boutique avec id "+id+" inexistant !");
        b.setId(id);
        return boutiqueRepo.save(b);
    }
    public void deleteBoutique(Long id){
        if(!boutiqueRepo.existsById(id))
            throw new ResourceNotFoundException("Boutique avec id "+id+" inexistant !");
        boutiqueRepo.deleteById(id);
    }
}
