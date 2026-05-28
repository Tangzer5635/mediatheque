package views.facades;

import models.entities.in.Rayon;
import models.entities.physique.Support;
import models.exception.RayonException;
import models.exception.SupportException;
import models.specifications.Categorie;
import models.specifications.Genre;
import models.specifications.Type;

import java.util.List;

/**
 * TEMPLATE — Interface de la Vue (couche MVP).
 * Le Présenteur ne connaît que cette interface — jamais l'implémentation concrète.
 *
 * Deux familles de méthodes :
 * - Affichage (void) : montrer des données à l'utilisateur
 * - Saisie (retour non void) : récupérer une entrée de l'utilisateur
 *
 * Règles importantes :
 * - saisirChoixMenu(n) : autorise 0 (option "sortir") jusqu'à n
 * - saisirChoixListe(n) : autorise 1 jusqu'à n (jamais 0 dans une liste)
 */
public interface IView {

    // --- Menu ---

    void afficherMenuPrincipal(List<String> options);

    int saisirChoixMenu(int tailleMenu);

    int saisirChoixListe(int tailleListe);

    // --- Saisies ---

    String saisirNom();

    String saisirAuteur();

    String saisirArtiste();

    String saisirRealisateur();

    int saisirPistes();

    int saisirAnnee();

    double saisirPrix();

    int saisirCapacite();

    int saisirDuree();

    List<String> saisirActeurs();

    Categorie choisirCategorie();

    Type choisirType();

    Genre choisirGenre();


    // --- Affichages ---

    void afficherMessage(String message);

    void afficherRayons(List<Rayon> rayons);
    void afficherSupport(List<Support> supports);
    void afficherSupportDansRayon(List<Support> supports);
}
