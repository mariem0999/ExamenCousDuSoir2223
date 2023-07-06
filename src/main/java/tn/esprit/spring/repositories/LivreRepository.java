package tn.esprit.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.Livre;

@Repository
public interface LivreRepository extends CrudRepository<Livre, Long> {
}
