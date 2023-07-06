package tn.esprit.spring.restControllers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Bibliotheque;
import tn.esprit.spring.entities.Livre;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.services.IServices;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
public class restControllers {

    IServices iServices;
    @PostMapping("ajouterBibliotheque")
    Bibliotheque ajouterBibliotheque(@RequestBody Bibliotheque bibliotheque){
        return iServices.ajouterBibliotheque(bibliotheque);
    }
    @PostMapping("ajouterLecteurs")
    List<User> ajouterLecteurs(@RequestBody List<User> lecteurs){
        return iServices.ajouterLecteurs(lecteurs);
    }
    @PostMapping("ajouterLivreEtAuteurEtAffecterABiblio")
    Bibliotheque ajouterLivreEtAuteurEtAffecterABiblio(@RequestBody Livre livre, @RequestParam String nomBibliotheque){
        return iServices.ajouterLivreEtAuteurEtAffecterABiblio(livre,nomBibliotheque);
    }
    @PutMapping("affecterLivreALecteur")
    String  affecterLivreALecteur(@RequestParam long idLivre,@RequestParam long idLecteur){
        return iServices.affecterLivreALecteur(idLivre,idLecteur);
    }
    @PutMapping("modifierDateReservation")
    public void modifierDateReservation(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,@RequestParam long idLecteur){
        iServices.modifierDateReservation(date,idLecteur);
    }
    @PutMapping("rendreLivre")
    String rendreLivre(@RequestParam String nom,@RequestParam String prenom){
        return iServices.rendreLivre(nom,prenom);
    }

}
