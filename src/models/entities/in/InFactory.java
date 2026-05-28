package models.entities.in;

import models.exception.RayonException;
import models.specifications.Categorie;

public class InFactory {
    private InFactory() {
    }

    public static Rayon creerRayon(String nom, Categorie categorie, int capaciteMax) throws RayonException {
        if (nom == null || nom.isBlank()) {
            throw new RayonException("Le nom ne peut pas être vide !");
        }
        return new Rayon(nom, categorie, capaciteMax);
    }
}
