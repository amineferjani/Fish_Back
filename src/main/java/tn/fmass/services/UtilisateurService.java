package tn.fmass.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.fmass.entities.Manager;
import tn.fmass.entities.Sayed;
import tn.fmass.entities.Utilisateur;
import tn.fmass.exceptions.ResourceNotFoundException;
import tn.fmass.repos.UtilisateurRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UtilisateurService {
    private final UtilisateurRepo repo;


    public Utilisateur addSayed(Sayed usr){
        return repo.save(usr);
    }
    public Utilisateur addManager(Manager usr){
        return repo.save(usr);
    }

    public List<Utilisateur> getAllSayed(){
        return repo.findAllSayed();
    }
    public List<Utilisateur> getAllManager(){
        return repo.findAllManager();
    }

    public void deleteUser(Long id){
        if(!repo.existsById(id))
            throw new ResourceNotFoundException("Utilisateur avec id "+id+" inexistant !");
        repo.deleteById(id);
    }
    public Utilisateur updateSayed(Sayed usr,Long id){
        if(!repo.existsById(id))
            throw new ResourceNotFoundException("Utilisateur avec id "+id+" inexistant !");
            usr.setId(id);
        return repo.save(usr);
    }
    public Utilisateur updateManager(Manager usr,Long id){
        if(!repo.existsById(id))
            throw new ResourceNotFoundException("Utilisateur avec id "+id+" inexistant !");
        usr.setId(id);
        return repo.save(usr);
    }


    public Boolean auth(String email, String password) {
        if(!repo.existsByEmail(email))
            return false;
        else{
            Utilisateur usr=repo.findByEmail(email);
            if(usr.getPassword().equals(password))
                return true;
            return false;
        }
    }
}
