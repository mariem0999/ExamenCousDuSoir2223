package tn.esprit.spring.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.repositories.BibliothequeRepo;
import tn.esprit.spring.repositories.LivreRepository;
import tn.esprit.spring.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@AllArgsConstructor
public class Services implements IServices {

    BibliothequeRepo bibliothequeRepo;
    LivreRepository livreRepository;
    UserRepository userRepository;

    @Override
    public Bibliotheque ajouterBibliotheque(Bibliotheque bibliotheque) {
        return bibliothequeRepo.save(bibliotheque);
    }

    @Override
    public List<User> ajouterLecteurs(List<User> lecteurs) {
        return (List<User>) userRepository.saveAll(lecteurs);
    }

    @Override
    public Bibliotheque ajouterLivreEtAuteurEtAffecterABiblio(Livre livre, String nomBibliotheque) {
        Bibliotheque bibliotheque = bibliothequeRepo.findBibliothequeByNom(nomBibliotheque);
        //bibliotheque parent
        //livre child
        // on affecte le child au parent
        bibliotheque.getLivres().add(livre);
        return bibliothequeRepo.save(bibliotheque);
    }

    @Override
    public String affecterLivreALecteur(long idLivre, long idLecteur) { // controle s'il a déjà un livre affecté
        Livre livre = livreRepository.findById(idLivre).get();//child
        User lecteur = userRepository.findById(idLecteur).get();// parent
        if (lecteur.getLivreL() == null) {
            // on affecte le child au parent
            livre.setReserve(true);
            livre.setDateReservation(new Date()); // la date et le reserve par defaut
            lecteur.setLivreL(livre);
            userRepository.save(lecteur);
            return "L'affectation du livre " + livre.getNom() + " au lecteur " + lecteur.getNom() + lecteur.getPrenom() + "est effectué avec succès !";
        }
        return "Le livre " + livre.getNom() + " est déjà reservé !";

    }

    @Override
    public void modifierDateReservation(Date date, long idLecteur) {
        User lecteur = userRepository.findById(idLecteur).get();
        lecteur.getLivreL().setDateReservation(date);
        livreRepository.save(lecteur.getLivreL());
    }


    @Override
    public String rendreLivre(String nom, String prenom) {
        String msg;
        User lecteur = userRepository.findUserByNomAndPrenom(nom, prenom); //parent
        Livre livre = lecteur.getLivreL(); //child
        if ((calculDiff(new Date(), livre.getDateReservation()) + 1) > 5) {
            lecteur.setEtat(Etat.BLOQUE);
            msg = "Lecteur bloqué => " + lecteur.getNom() + " " + lecteur.getPrenom();
        } else {
            msg = "Le lecteur " + lecteur.getNom() + " " + lecteur.getPrenom() + " a respecté la durée ! La désaffectation est effectuée avec succès";
        }
        lecteur.setLivreL(null);
        livre.setDateReservation(null);
        livre.setReserve(false);
        userRepository.save(lecteur);
        livreRepository.save(livre);
        return msg;
    }

    @Override
    public String afficher() { // la liste des lecteurs qui ont des abonnements 9rib youfew (mazelelhom moins de 2 jours)
        String msg = "";
        for (User lecteur : userRepository.findUserByRole(Role.LECTEUR)) {
            if ((calculDiff(lecteur.getDateFinAbonnement(), new Date()) + 1) <= 2) {
                msg+= "L'abonnement du lecteur "+ lecteur.getNom() + " " + lecteur.getPrenom()+" va etre expiré bientot \n";
            }
        }
        return msg;
    }

    public long calculDiff(Date date1, Date date2) {
        long diffInMillies = date1.getTime() - date2.getTime();
        return TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

}
