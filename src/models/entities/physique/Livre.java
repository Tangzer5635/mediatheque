package models.entities.physique;

import models.exception.SupportException;
import models.specifications.Genre;

import java.time.LocalDate;

public class Livre extends Support{
    private String auteur;
    private Genre genre;

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Livre(String nom, String auteur, Genre genre,int anneeSortie, double prix) throws SupportException {
        super(nom, anneeSortie, prix);
        setAuteur(auteur);
        setGenre(genre);
    }

    @Override
    public String toString() {
        return "Livre{id=" + getId() + ", nom='" + getNom() + "'} de " + getAuteur() + " du genre " + getGenre() + " ";
    }
}
