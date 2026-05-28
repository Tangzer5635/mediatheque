package models.entities.physique;

import models.exception.SupportException;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class DVD extends Support{
    private String realisateur;
    private int duree;
    private List<String> acteurs;

    public String getRealisateur() {
        return realisateur;
    }

    public void setRealisateur(String realisateur) {
        this.realisateur = realisateur;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) throws SupportException {
        if (duree < 1 || duree > 300) {
            throw new SupportException("Durée hors bornes (1-300 minutes) !");
        }
        this.duree = duree;
    }

    public List<String> getActeurs() {
        return Collections.unmodifiableList(acteurs);
    }

    public void setActeurs(List<String> acteurs) throws SupportException {
        if (acteurs == null || acteurs.isEmpty()) {
            throw new SupportException("Un DVD doit avoir au moins un acteur !");
        }
        this.acteurs = acteurs;
    }

    public DVD(String nom, String realisateur, int duree, List<String> acteurs, int anneeSortie, double prix) throws SupportException {
        super(nom, anneeSortie, prix);
        setRealisateur(realisateur);
        setDuree(duree);
        setActeurs(acteurs);
    }

    @Override
    public String toString() {
        return "DVD{id=" + getId() + ", nom='" + getNom() + "'} de " + getRealisateur() + "";
    }
}
