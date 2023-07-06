package tn.esprit.spring.entities;

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
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idLivre;
    @Column(unique = true)
    String nom;
    @Enumerated(EnumType.STRING)
    TypeLivre type;
    @Temporal(TemporalType.DATE)
    Date dateEmission;
    boolean reserve;
    @Temporal(TemporalType.DATE)
    Date dateReservation;
    // en tant qu'auteur
    @ManyToOne(cascade = CascadeType.ALL)
    User auteur;
    // en tant que lecteur
    @OneToOne(mappedBy = "livreL")
    User lecteurs;

}
