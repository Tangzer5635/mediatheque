package models.entities.in;

import models.entities.AbstractEntity;
import models.entities.physique.Support;
import models.exception.RayonException;
import models.specifications.Categorie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Rayon extends AbstractEntity {
    private String nom;
    private List<Long> supports;
    private int capaciteMax;
    private Categorie categorie;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Long> getSupports() {
        return Collections.unmodifiableList(supports);
    }

    public void setSupports(List<Long> supports) {
        this.supports = supports;
    }

    public int getCapaciteMax() {
        return capaciteMax;
    }

    public void setCapaciteMax(int capaciteMax) {
        this.capaciteMax = capaciteMax;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Rayon(String nom, Categorie categorie, int capaciteMax) {
        setNom(nom);
        setCapaciteMax(capaciteMax);
        setCategorie(categorie);
        this.supports = new ArrayList<>();
    }

    public void ajouterSupport(Support support) throws RayonException {
        if (supports.size() >= capaciteMax) {
            throw new RayonException("Le rayon est plein !");
        }
        if (supports.contains(support.getId())) {
            throw new RayonException("Support déjà présent dans le rayon !");
        }
        supports.add(support.getId());
    }

    public void retirerSupport(Long id) {
        supports.remove(id);
    }

    @Override
    public String toString() {
        return "Rayon{id=" + getId() + ", nom='" + getNom() + "'} et a " + getCapaciteMax() + " supports max";
    }
}
