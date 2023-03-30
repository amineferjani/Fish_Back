package tn.fmass.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="CAT",length = 10)
@EntityListeners(AuditingEntityListener.class)
public abstract class Utilisateur {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min=5,max=25)
    @NotBlank(message = "Le nom est obligatoire!")
    private String name;
    @Size(max=20)
    @NotBlank(message = "Le login est obligatoire!")
    @Column(unique=true)
    private String login;
    @Size(max=20)
    @NotBlank(message = "Le mot de passe est obligatoire!")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Size(max=40)
    @Column(unique=true)
    @NotBlank(message = "L'email est obligatoire!")
    @Email
    private String email;
    @Size(max=20)
    @NotBlank(message = "Le téléphone est obligatoire!")
    @Column(unique=true)
    private String telephone;
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date created_date;
    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date last_updated_date;
}
