package views.facades;

import models.entities.in.Rayon;
import models.entities.physique.Support;
import models.exception.RayonException;
import models.exception.SupportException;
import models.specifications.Categorie;
import models.specifications.Genre;
import models.specifications.Type;
import views.utils.AffichageConsole;
import views.utils.LectureConsole;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * TEMPLATE — Implémentation console de IView.
 * Toute interaction avec System.in / System.out passe par ici.
 * Le Présenteur ne sait pas que c'est une console — il pourrait y avoir
 * une ViewSwingImpl ou ViewWebImpl sans changer une ligne du Présenteur.
 */
public class ViewConsoleImpl implements IView {

    private static final String TITRE_MENU = "MENU PRINCIPAL";

    @Override
    public void afficherMenuPrincipal(List<String> options) {
        AffichageConsole.afficherMenuEntoureAvecOptionSortie(options, TITRE_MENU);
    }

    @Override
    public int saisirChoixMenu(int tailleMenu) {
        return LectureConsole.lectureChoixInt(0, tailleMenu);
    }

    @Override
    public int saisirChoixListe(int tailleListe) {
        return LectureConsole.lectureChoixInt(1, tailleListe);
    }

    @Override
    public String saisirNom() {
        return LectureConsole.lectureChaineCaracteres("Nom : ");
    }

    @Override
    public String saisirAuteur() {
        return LectureConsole.lectureChaineCaracteres("Auteur : ");
    }

    @Override
    public String saisirArtiste() {
        return LectureConsole.lectureChaineCaracteres("Artiste : ");
    }

    @Override
    public String saisirRealisateur() {
        return LectureConsole.lectureChaineCaracteres("Realisateur : ");
    }

    @Override
    public int saisirCapacite() {
        AffichageConsole.afficherMessageSansSautLigne("Saisir capacité maximale du rayon (1-200) : ");
        return LectureConsole.lectureChoixInt(1, 200);
    }

    @Override
    public int saisirPistes() {
        AffichageConsole.afficherMessageSansSautLigne("Saisir nombre de pistes (1-30) : ");
        return LectureConsole.lectureChoixInt(1, 30);
    }

    @Override
    public int saisirAnnee(){
        AffichageConsole.afficherMessageSansSautLigne("Saisir l'année de sortie (1888-"+ LocalDate.now().getYear()+") : ");
        return LectureConsole.lectureChoixInt(1888, LocalDate.now().getYear());
    }

    @Override
    public double saisirPrix(){
        return LectureConsole.lectureDouble("Saisir le prix : ");
    }

    @Override
    public int saisirDuree(){
        AffichageConsole.afficherMessageSansSautLigne("Saisir la durée en minutes (1-300) : ");
        return LectureConsole.lectureChoixInt(1, 300);
    }

    public List<String> saisirActeurs() {
        List<String> acteurs = new ArrayList<>();
        while (true) {
            AffichageConsole.afficherMessageAvecSautLigne("Ajouter un acteur ? (o/n)");
            String rep = LectureConsole.lectureChaineCaracteres();
            if (rep.equalsIgnoreCase("n")) break;
            acteurs.add(LectureConsole.lectureChaineCaracteres("Nom de l'acteur : "));
        }
        return acteurs;
    }

    @Override
    public Categorie choisirCategorie(){
        Categorie[] categories = Categorie.values();
        List<String> menu = new ArrayList<>();
        for (Categorie c : categories) {
            menu.add(c.toString());
        }
        AffichageConsole.afficherMenuSimple(menu);

        int choix = LectureConsole.lectureChoixInt(1, menu.size());
        return categories[choix - 1];
    }

    @Override
    public Type choisirType(){
        Type[] types = Type.values();
        List<String> menu = new ArrayList<>();
        for (Type t : types) {
            menu.add(t.toString());
        }
        AffichageConsole.afficherMenuSimple(menu);

        int choix = LectureConsole.lectureChoixInt(1, menu.size());
        return types[choix - 1];
    }

    @Override
    public Genre choisirGenre(){
        Genre[] genres = Genre.values();
        List<String> menu = new ArrayList<>();
        for (Genre g : genres) {
            menu.add(g.toString());
        }
        AffichageConsole.afficherMenuSimple(menu);
        int choix = LectureConsole.lectureChoixInt(1, menu.size());
        return genres[choix - 1];
    }

    @Override
    public void afficherMessage(String message) {
        AffichageConsole.afficherMessageAvecSautLigne(message);
    }

    @Override
    public void afficherRayons(List<Rayon> rayons){
        if (rayons.isEmpty()) {
            AffichageConsole.afficherMessageAvecSautLigne("Aucun rayon.");
            return;
        }
        int index = 1;
        for (Rayon r : rayons) {
            AffichageConsole.afficherMessageAvecSautLigne(index + " - " + r.toString());
            index++;
        }
    }

    @Override
    public void afficherSupport(List<Support> supports) {
        if (supports.isEmpty()) {
            AffichageConsole.afficherMessageAvecSautLigne("Aucun support.");
            return;
        }
        int index = 1;
        for (Support s : supports) {
            AffichageConsole.afficherMessageAvecSautLigne(index + " - " + s.toString());
            index++;
        }
    }

    @Override
    public void afficherSupportDansRayon(List<Support> supports) {
        int index = 1;
        for (Support s : supports) {
            AffichageConsole.afficherMessageAvecSautLigne(
                    index + " - " + s.toString());
            index++;
        }
    }

}
