package tn.esprit.spring.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Bibliotheque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idBiblio;
    String nom;
    String adresse;
    long telephone;
    @OneToMany(cascade = CascadeType.ALL)
    List<Livre> livres;
}
