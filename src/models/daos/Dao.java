package models.daos;

import models.entities.AbstractEntity;

import java.util.List;

/**
 * Interface générique CRUD pour toutes les entités.
 * T doit étendre AbstractEntity pour garantir la présence d'un ID.
 *
 * À NE PAS modifier — toutes les implémentations héritent de ce contrat.
 */
public interface Dao<T extends AbstractEntity> {

    /** Persiste l'entité et lui assigne un ID automatique. */
    void create(T entity);

    /** Retourne l'entité correspondant à l'ID, ou null si introuvable. */
    T read(Long id);

    /** Met à jour l'entité déjà persistée (doit avoir un ID). */
    void update(T entity);

    /** Supprime l'entité correspondant à l'ID. */
    void delete(Long id);

    /** Retourne true si un enregistrement avec cet ID existe. */
    boolean exist(Long id);

    /** Retourne le nombre total d'enregistrements. */
    long count();

    /** Retourne tous les enregistrements sous forme de liste non modifiable. */
    List<T> readAll();
}
