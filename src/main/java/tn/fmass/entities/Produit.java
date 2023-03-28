package tn.fmass.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Produit {
    @Id @GeneratedValue
    private Long id;
    private String titre;
    private String description;
    private String marque;
    private String img1;
    private String img2;
    private String img3;
    private double prix;
    @CreatedDate
    private Date created_date;
    @LastModifiedDate
    private Date last_updated_date;
    @ManyToOne
    @JsonIgnore
    private Boutique boutique;
    @ManyToOne
    private Categorie categorie;
}
