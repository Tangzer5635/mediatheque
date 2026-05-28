package models.facades;

import models.entities.in.Rayon;
import models.entities.physique.CD;
import models.entities.physique.DVD;
import models.entities.physique.Livre;
import models.entities.physique.Support;
import models.exception.RayonException;
import models.exception.SupportException;

import java.util.List;

/**
 * TEMPLATE — Interface de la façade Modèle (couche MVP).
 * Le Présenteur ne connaît que cette interface — jamais les DAO directement.
 * Déclarer ici toutes les opérations métier exposées au Présenteur.
 *
 * Règles :
 * - Les méthodes qui peuvent échouer pour une raison métier déclarent throws XxxException.
 * - Les méthodes de lecture simples ne lèvent pas d'exception.
 */
public interface IModel {

    void ajouterRayon(Rayon rayon) throws RayonException;

    void ajouterLivre(Livre livre) throws SupportException;

    void ajouterCd(CD cd) throws SupportException;

    void ajouterDvd(DVD dvd) throws SupportException;

    List<Support> recupererSupportsDansRayon(Rayon rayon) throws RayonException;

    List<Livre> recupererTousLesLivres();

    List<CD> recupererTousLesCds();

    List<DVD> recupererToutLesDvds();

    List<Rayon> recupererRayons();

    void supprimer(Long id) throws SupportException;

    void mettreAJourRayon(Rayon rayon) throws RayonException;
}
