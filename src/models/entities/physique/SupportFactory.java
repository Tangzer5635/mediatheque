package models.entities.physique;

import models.exception.RayonException;
import models.exception.SupportException;
import models.specifications.Genre;

import java.util.List;

public class SupportFactory {
    private SupportFactory() {
    }

    public static Livre creerLivre(String nom, String auteur, Genre genre, int anneeSortie, double prix) throws SupportException {
        if (nom == null || nom.isBlank()) {
            throw new SupportException("Le nom ne peut pas être vide !");
        }

        return new Livre(nom, auteur, genre, anneeSortie, prix);
    }

    public static CD creerCd(String nom, String artiste, int nbPistes, int anneeSortie, double prix) throws SupportException {
        if (nom == null || nom.isBlank()) {
            throw new SupportException("Le nom ne peut pas être vide !");
        }
        return new CD(nom, artiste, nbPistes, anneeSortie, prix);
    }

    public static DVD creerDvd(String nom, String realisateur, int duree, List<String> acteurs, int anneeSortie, double prix) throws SupportException {
        if (nom == null || nom.isBlank()) {
            throw new SupportException("Le nom ne peut pas être vide !");
        }
        return new DVD(nom, realisateur, duree, acteurs, anneeSortie, prix);
    }
}