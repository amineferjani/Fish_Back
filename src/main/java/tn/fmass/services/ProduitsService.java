package tn.fmass.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.fmass.entities.Boutique;
import tn.fmass.entities.Categorie;
import tn.fmass.entities.Produit;
import tn.fmass.exceptions.ResourceNotFoundException;
import tn.fmass.repos.BoutiqueRepo;
import tn.fmass.repos.CategorieRepo;
import tn.fmass.repos.ProduitRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProduitsService {
    private final ProduitRepo pr;
    private final BoutiqueRepo br;

    private final CategorieRepo cr;

    public Produit getProduit(long id){
        if(!pr.existsById(id))
            throw new ResourceNotFoundException("Produit avec id "+id+" inexistant !");
        return pr.findById(id).get();
    }

    public List<Produit> getProduitBoutique(long id){
        if(!br.existsById(id))
            throw new ResourceNotFoundException("Boutique avec id "+id+" inexistant !");
        Boutique b=br.findById(id).get();
        return pr.findProduitByBoutique(b);
    }
    public List<Produit> getProduitBoutiqueCategorie(long idb,long idc){
        if(!br.existsById(idb))
            throw new ResourceNotFoundException("Boutique avec id "+idb+" inexistant !");
        if(!cr.existsById(idc))
            throw new ResourceNotFoundException("Catégorie avec id "+idc+" inexistant !");
        Boutique b=br.findById(idc).get();
        Categorie c=cr.findById(idc).get();
        return pr.findProduitByBoutiqueAndCategorie(b,c);
    }

    public Produit addProduit(Produit p, long idb, long idc){
        if(!br.existsById(idb))
            throw new ResourceNotFoundException("Boutique avec id "+idb+" inexistant !");
        if(!cr.existsById(idc))
            throw new ResourceNotFoundException("Categorie avec id "+idb+" inexistant !");
        Boutique b=br.findById(idb).get();
        Categorie c=cr.findById(idc).get();
        p.setBoutique(b);
        p.setCategorie(c);
        return pr.save(p);
    }
    public Produit updateProduit(Produit p,long idp, long idb, long idc){
        if(!pr.existsById(idp))
            throw new ResourceNotFoundException("Produit avec id "+idb+" inexistant !");
        if(!br.existsById(idb))
            throw new ResourceNotFoundException("Boutique avec id "+idb+" inexistant !");
        if(!cr.existsById(idc))
            throw new ResourceNotFoundException("Categorie avec id "+idb+" inexistant !");
        p.setId(idp);
        Boutique b=br.findById(idb).get();
        Categorie c=cr.findById(idc).get();
        p.setBoutique(b);
        p.setCategorie(c);
        return pr.save(p);
    }
    public void deleteProduit(long id){
        if(!pr.existsById(id))
            throw new ResourceNotFoundException("Produit avec id "+id+" inexistant !");
        pr.deleteById(id);
    }

}
