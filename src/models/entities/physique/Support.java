package models.entities.physique;

import models.entities.AbstractEntity;
import models.exception.SupportException;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Support extends AbstractEntity {
    private String nom;
    private int anneeSortie;
    private double prix;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) throws SupportException {
        if (nom == null || nom.isBlank()) {
            throw new SupportException("Le nom ne peut pas être vide !");
        }
        this.nom = nom;
    }

    public int getAnneeSortie() {
        return anneeSortie;
    }

    public void setAnneeSortie(int anneeSortie) throws SupportException {
        int anneeActuelle = LocalDate.now().getYear();
        if (anneeSortie < 1888 || anneeSortie > anneeActuelle) {
            throw new SupportException("Année hors bornes (1888 - " + anneeActuelle + ") !");
        }
        this.anneeSortie = anneeSortie;
    }


    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) throws SupportException {
        if (prix < 0) {
            throw new SupportException("Le prix ne peut pas être négatif !");
        }
        this.prix = prix;
    }

    public Support(String nom, int anneeSortie, double prix) throws SupportException {
        setNom(nom);
        setAnneeSortie(anneeSortie);
        setPrix(prix);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Support support = (Support) o;
        return Objects.equals(nom, support.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nom);
    }
}
