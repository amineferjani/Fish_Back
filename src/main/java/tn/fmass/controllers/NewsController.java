package tn.fmass.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.fmass.entities.News;
import tn.fmass.services.NewsService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class NewsController {
    private final NewsService NewsService;
    @PostMapping("Newss/{id}")
    public ResponseEntity<News> addNews(@RequestBody News n, @PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(NewsService.addNews(n,id));
    }
    @GetMapping ("Newss")
    public ResponseEntity<List<News>> getAllNewss(){
        return ResponseEntity.ok(NewsService.getAllNewss());
    }
    @GetMapping ("Newss/{id}")
    public ResponseEntity<News> getNews(@PathVariable long id){
        return ResponseEntity.ok(NewsService.getNews(id));
    }
    @PutMapping("Newss/{id}")
    public ResponseEntity<News> updateNews(@RequestBody News n,@PathVariable Long id){
        return ResponseEntity.ok(NewsService.updateNews(n,id));
    }
    @DeleteMapping("Newss/{id}")
    public ResponseEntity<Boolean> deleteNews(@PathVariable Long id){
        NewsService.deleteNews(id);
        return ResponseEntity.ok(true);
    }
}
