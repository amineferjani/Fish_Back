package tn.fmass.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor @NoArgsConstructor
@DiscriminatorValue("SAYED")
public class Sayed extends Utilisateur {
    private boolean active;
    @JsonIgnore
    @OneToMany(mappedBy = "sayed")
    private List<Annonce> annonceList;
    @JsonIgnore
    @OneToMany(mappedBy = "sayed")
    private List<Covoiturage> covoiturageList;
}
