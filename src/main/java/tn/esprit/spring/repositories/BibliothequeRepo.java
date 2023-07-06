package tn.esprit.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.Bibliotheque;

@Repository
public interface BibliothequeRepo extends CrudRepository<Bibliotheque, Long> {
    Bibliotheque findBibliothequeByNom(String nom);
}
