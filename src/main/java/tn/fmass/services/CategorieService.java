package tn.fmass.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.fmass.entities.Categorie;
import tn.fmass.exceptions.ResourceNotFoundException;
import tn.fmass.repos.CategorieRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategorieService {
    private final CategorieRepo rc;

    public List<Categorie> getAllCategories(){
        return rc.findAll();
    }
    public Categorie getCategorie(long id){
        if(!rc.existsById(id))
            throw new ResourceNotFoundException("Catégorie avec id "+id+" inexistant !");
        return rc.findById(id).get();
    }
    public Categorie addCategorie(Categorie c){
        return rc.save(c);
    }
    public Categorie updateCategorie(Categorie c,long id){
        if(!rc.existsById(id))
            throw new ResourceNotFoundException("Catégorie avec id "+id+" inexistant !");
        c.setId(id);
        return rc.save(c);
    }
    public void deleteCategorie(long id){
        if(!rc.existsById(id))
            throw new ResourceNotFoundException("Catégorie avec id "+id+" inexistant !");
        rc.deleteById(id);
    }
}
