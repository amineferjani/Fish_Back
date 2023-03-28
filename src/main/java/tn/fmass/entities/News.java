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
public class News {
    @Id
    @GeneratedValue
    private long id;
    private String titre;
    private String description;
    private String img1;
    private String img2;
    private String img3;
    @CreatedDate
    private Date created_date;
    @LastModifiedDate
    private Date last_updated;
    @ManyToOne
    @JsonIgnore
    private Manager manager;
}
