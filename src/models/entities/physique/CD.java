package models.entities.physique;

import models.exception.SupportException;

import java.time.LocalDate;

public class CD extends Support{
    private String artiste;
    private int nbPistes;

    public String getArtiste() {
        return artiste;
    }

    public void setArtiste(String artiste) {
        this.artiste = artiste;
    }

    public int getNbPistes() {
        return nbPistes;
    }

    public void setNbPistes(int nbPistes) throws SupportException {
        if (nbPistes < 1 || nbPistes > 30) {
            throw new SupportException("Nombre de pistes hors bornes (1-30) !");
        }
        this.nbPistes = nbPistes;
    }

    public CD(String nom, String artiste, int nbPistes, int anneeSortie, double prix) throws SupportException {
        super(nom, anneeSortie, prix);
        setArtiste(artiste);
        setNbPistes(nbPistes);
    }

    @Override
    public String toString() {
        return "CD{id=" + getId() + ", nom='" + getNom() + "'} de " + getArtiste() + "";
    }
}
