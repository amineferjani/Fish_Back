package tn.fmass.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@DiscriminatorValue("MANAGER")
public class Manager extends Utilisateur{
private boolean access;
@OneToMany(mappedBy = "manager")
@JsonIgnore
private List<Boutique> boutiqueList;
@OneToMany(mappedBy = "manager")
@JsonIgnore
private List<News> newsList;
}
