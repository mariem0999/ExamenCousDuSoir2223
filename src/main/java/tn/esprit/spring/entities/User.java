package tn.esprit.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idUser;
    String nom;
    String prenom;
    String nationalite;
    @Enumerated(EnumType.STRING)
    Role role;
    @Temporal(TemporalType.DATE)
    Date dateDebutAbonnement;
    @Temporal(TemporalType.DATE)
    Date dateFinAbonnement;
    @Enumerated(EnumType.STRING)
    Etat etat;
    // en tant qu'auteur
    @JsonIgnore
    @OneToMany(mappedBy = "auteur")
    List<Livre> livresA;
    // en tant que lecteur
    @OneToOne
    @JsonIgnore
    Livre livreL;
}
