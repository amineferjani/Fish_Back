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
public class Covoiturage {
    @Id @GeneratedValue
    private long id;
    private String depart;
    private String arrivee;
    private String description;
    private String telephone;
    private Date dt_depart;
    private double prix;
    private int nb_place;
    @CreatedDate
    private Date created_date;
    @LastModifiedDate
    private Date updated_date;
    @ManyToOne
    @JsonIgnore
    private Sayed sayed;
}
