package models.entities;

import java.util.Objects;

/**
 * Classe de base pour toutes les entités du domaine.
 * L'identifiant est assigné automatiquement par le DAO lors du create().
 */
public abstract class AbstractEntity {
    private Long id;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity that = (AbstractEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
