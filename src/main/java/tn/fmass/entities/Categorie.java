package tn.fmass.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Categorie {
    @Id @GeneratedValue
    private long id;
    private String titre;
    private String description;
    private String logo;
    @OneToMany(mappedBy = "categorie")
    private List<Produit> produitList;
}
