package presenteur;

import models.entities.in.InFactory;
import models.entities.in.Rayon;
import models.entities.physique.*;
import models.exception.RayonException;
import models.exception.SupportException;
import models.facades.IModel;
import models.specifications.Categorie;
import models.specifications.Genre;
import models.specifications.Type;
import views.facades.IView;
import views.utils.AffichageConsole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Presenteur {

    private final IModel model;
    private final IView view;

    private static final int CHOIX_SORTIE = 0;

    private final List<String> MENU_PRINCIPAL = Arrays.asList(
            "Saisir un rayon",
            "Saisir un nouveau support",
            "Ajouter un support dans un rayon",
            "Afficher les supports d'un rayon",
            "Supprimer un support d'un rayon"
    );
    private final int TAILLE_MENU = MENU_PRINCIPAL.size();

    public Presenteur(IModel model, IView view) {
        this.model = model;
        this.view = view;
    }

    public void start() {
        int choix;
        do {
            view.afficherMenuPrincipal(MENU_PRINCIPAL);
            choix = view.saisirChoixMenu(TAILLE_MENU);
            gestionMenu(choix);
        } while (choix != CHOIX_SORTIE);
    }

    private void gestionMenu(int choix) {
        try {
            switch (choix) {
                case 1 -> saisirRayon();
                case 2 -> saisirNouveauSupport();
                case 3 -> ajouterSupportDansRayon();
                case 4 -> afficherSupportDansRayon();
                case 5 -> supprimerSupport();
                case CHOIX_SORTIE -> view.afficherMessage("Au revoir !");
            }
        } catch (RayonException e) {
            view.afficherMessage("Erreur : " + e.getMessage());
        } catch (SupportException e) {
            view.afficherMessage("Erreur : " + e.getMessage());
        }
    }

    // -------------------------------------------------------------------------
    // CHOIX 1 — Ajouter
    // -------------------------------------------------------------------------

    private void saisirRayon() throws RayonException {
        Categorie categorie = view.choisirCategorie();
        String nom = view.saisirNom();
        int capacite = view.saisirCapacite();
        Rayon rayon = InFactory.creerRayon(nom,categorie,capacite);
        model.ajouterRayon(rayon);
        view.afficherMessage("Rayon ajoutée avec succès !");
    }

    private void saisirNouveauSupport() throws SupportException {
        Type typeSupport = view.choisirType();
        String nom = view.saisirNom();
        int annee = view.saisirAnnee();
        double prix = view.saisirPrix();
        switch (typeSupport){
            case LIVRE -> {
                AffichageConsole.afficherMessageSansSautLigne("Saisir un livre");

                String auteur = view.saisirAuteur();
                Genre genre = view.choisirGenre();
                Livre livre = SupportFactory.creerLivre(nom, auteur, genre, annee, prix);
                model.ajouterLivre(livre);
                view.afficherMessage("Livre ajoutée avec succès !");
                break;
            }
            case CD -> {
                AffichageConsole.afficherMessageSansSautLigne("Saisir un CD");

                String artiste = view.saisirArtiste();
                int nbPistes = view.saisirPistes();
                CD cd = SupportFactory.creerCd(nom, artiste, nbPistes, annee, prix);
                model.ajouterCd(cd);
                view.afficherMessage("CD ajoutée avec succès !");
                break;
            }
            case DVD -> {
                AffichageConsole.afficherMessageSansSautLigne("Saisir DVD");

                String realisateur = view.saisirRealisateur();
                int duree = view.saisirDuree();
                List<String> acteurs = view.saisirActeurs();
                DVD dvd = SupportFactory.creerDvd(nom, realisateur, duree, acteurs, annee, prix);
                model.ajouterDvd(dvd);
                view.afficherMessage("DVD ajoutée avec succès !");
                break;
            }
        }

    }

    private void ajouterSupportDansRayon() throws SupportException, RayonException {
        List<Rayon> rayons = model.recupererRayons();
        view.afficherRayons(rayons);
        int choixRayons = view.saisirChoixListe(rayons.size());
        Rayon rayonChoisi = rayons.get(choixRayons - 1);

        AffichageConsole.afficherMessageAvecSautLigne("Choisir le type de support : ");
        Type typeSupport = view.choisirType();

        switch (typeSupport){
            case LIVRE -> {
                List<Livre> livres = model.recupererTousLesLivres();
                view.afficherSupport(new ArrayList<>(livres));
                int choix = view.saisirChoixListe(livres.size());
                rayonChoisi.ajouterSupport(livres.get(choix - 1));
            }
            case CD -> {
                List<CD> cds = model.recupererTousLesCds();
                view.afficherSupport(new ArrayList<>(cds));
                int choix = view.saisirChoixListe(cds.size());
                rayonChoisi.ajouterSupport(cds.get(choix - 1));
            }
            case DVD -> {
                List<DVD> dvds = model.recupererToutLesDvds();
                view.afficherSupport(new ArrayList<>(dvds));
                int choix = view.saisirChoixListe(dvds.size());
                rayonChoisi.ajouterSupport(dvds.get(choix - 1));
            }
        }
        model.mettreAJourRayon(rayonChoisi);
        AffichageConsole.afficherMessageAvecSautLigne("Support ajouté au rayon !");
    }


    // -------------------------------------------------------------------------
    // CHOIX 2 — Afficher
    // -------------------------------------------------------------------------


    private void afficherSupportDansRayon() throws RayonException, SupportException {
        List<Rayon> rayons = model.recupererRayons();
        view.afficherRayons(rayons);
        int choixRayons = view.saisirChoixListe(rayons.size());
        Rayon rayonChoisi = rayons.get(choixRayons - 1);

        List<Support> supports = model.recupererSupportsDansRayon(rayonChoisi);
        AffichageConsole.afficherMessageAvecSautLigne("Supports du rayon : " + rayonChoisi.getNom());
        view.afficherSupport(supports);

    }



    // -------------------------------------------------------------------------
    // CHOIX 3 — Supprimer
    // -------------------------------------------------------------------------

    private void supprimerSupport() throws RayonException, SupportException {
        List<Rayon> rayons = model.recupererRayons();
        view.afficherRayons(rayons);
        int choixRayons = view.saisirChoixListe(rayons.size());
        Rayon rayonChoisi = rayons.get(choixRayons - 1);

        List<Support> supports = model.recupererSupportsDansRayon(rayonChoisi);
        view.afficherSupportDansRayon(supports);
        int choix = view.saisirChoixListe(supports.size());
        Support supportChoisi = supports.get(choix - 1);
        model.supprimer(supportChoisi.getId());
        rayonChoisi.retirerSupport(supportChoisi.getId());
        model.mettreAJourRayon(rayonChoisi);
        view.afficherMessage("Support supprimé !");
    }
}
