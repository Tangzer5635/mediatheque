package models.daos.in;

import models.daos.Dao;
import models.entities.in.Rayon;
import models.exception.RayonException;

public interface RayonDao extends Dao<Rayon> {
    void update(Rayon rayon);
}
