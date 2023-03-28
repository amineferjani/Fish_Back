package tn.fmass.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.fmass.entities.News;
import tn.fmass.entities.Manager;
import tn.fmass.exceptions.ResourceNotFoundException;
import tn.fmass.repos.NewsRepo;
import tn.fmass.repos.UtilisateurRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsService {
    private final NewsRepo NewsRepo;
    private final UtilisateurRepo utilisateurRepo;
    public List<News> getAllNewss(){
        return NewsRepo.findAll();
    }
    public News getNews(long id){
        if(!NewsRepo.existsById(id))
            throw new ResourceNotFoundException("News avec id "+id+" inexistant !");
        return NewsRepo.findById(id).get();
    }
    public News addNews(News n,Long id){
        if(!utilisateurRepo.existsById(id))
            throw new ResourceNotFoundException("Manager avec id "+id+" inexistant !");
        Manager manager=(Manager)utilisateurRepo.findById(id).get();
        n.setManager(manager);
        return NewsRepo.save(n);
    }
    public News updateNews(News n,Long id){
        if(!NewsRepo.existsById(id))
            throw new ResourceNotFoundException("News avec id "+id+" inexistant !");
        n.setId(id);
        return NewsRepo.save(n);
    }
    public void deleteNews(Long id){
        if(!NewsRepo.existsById(id))
            throw new ResourceNotFoundException("News avec id "+id+" inexistant !");
        NewsRepo.deleteById(id);
    }
}
