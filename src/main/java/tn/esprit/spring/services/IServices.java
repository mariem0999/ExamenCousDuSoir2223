package tn.esprit.spring.services;

import tn.esprit.spring.entities.Bibliotheque;
import tn.esprit.spring.entities.Livre;
import tn.esprit.spring.entities.User;

import java.util.Date;
import java.util.List;

public interface IServices { //14
    Bibliotheque ajouterBibliotheque(Bibliotheque bibliotheque); // 1 = 0.5 l'objet + 0.5 save
    List<User> ajouterLecteurs(List<User> lecteurs);// 1 = 0.5 l'objet + 0.5 saveAll
    Bibliotheque ajouterLivreEtAuteurEtAffecterABiblio(Livre livre, String nomBibliotheque);// 2.5 = 2 cascade (0.5 pour chaque) + find (1) + affectation (0.5)
    String affecterLivreALecteur(long idLivre, long idLecteur); //2.5 =  2 find (1) + condition(1) + la date et le reserve par defaut(0.5)
    void modifierDateReservation(Date date, long idLecteur);// 1 = find(0.5) + modif(0.25) + save(0.25)
    String rendreLivre(String nom, String prenom); // 3 = find(1) + condition(1) + deaffectation (1)
    String afficher(); // 2 = @Schedular (1) + traitement (1) ==> findAll(0.25) + traitement (0.75)

    //AOP /1 = @Around(0.5) + traitement (0.5) ==> 0.25 Date + 0.25 Executon Time
}
