package models.daos;

import models.entities.AbstractEntity;

import java.util.*;

/**
 * Implémentation en mémoire (HashMap) de Dao<T>.
 * Simule une base de données pour les phases de développement et de test.
 *
 * Pour passer à une vraie BDD : créer MonEntiteDaoImpl extends MonEntiteDao
 * avec une implémentation JDBC ou JPA — MemoirDao n'a pas besoin d'être modifié.
 *
 * À NE PAS modifier — hériter de cette classe dans vos DAO spécifiques.
 */
public class MemoirDao<T extends AbstractEntity> implements Dao<T> {

    protected final Map<Long, T> persist = new HashMap<>();
    private Long sequence = 0L;

    private Long nextId() {
        return sequence++;
    }

    @Override
    public void create(T entity) {
        entity.setId(nextId());
        persist.put(entity.getId(), entity);
    }

    @Override
    public T read(Long id) {
        return persist.get(id);
    }

    @Override
    public void update(T entity) {
        persist.put(entity.getId(), entity);
    }

    @Override
    public void delete(Long id) {
        persist.remove(id);
    }

    @Override
    public boolean exist(Long id) {
        return persist.containsKey(id);
    }

    @Override
    public long count() {
        return persist.size();
    }

    @Override
    public List<T> readAll() {
        return Collections.unmodifiableList(new ArrayList<>(persist.values()));
    }
}
