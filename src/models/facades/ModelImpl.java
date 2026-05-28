package models.facades;

import models.daos.in.RayonDao;
import models.daos.in.RayonDaoImpl;
import models.daos.physique.*;
import models.entities.in.Rayon;
import models.entities.physique.*;
import models.exception.RayonException;
import models.exception.SupportException;
import models.specifications.Categorie;
import models.specifications.Genre;

import java.util.List;
import java.util.stream.Collectors;

public class ModelImpl implements IModel {

    private final RayonDao rayonDao = new RayonDaoImpl();
    private final SupportDao supportDao = new SupportDaoImpl();

    public ModelImpl() {
        init();
    }

    private void init() {
        try {
            Rayon rayon = new Rayon("RAYON DAMSO", Categorie.MUSIQUE,1);
            ajouterRayon(rayon);

            Livre livre = SupportFactory.creerLivre("Le petit prince", "Antoine de Saint-Exupéry", Genre.ROMAN, 1943, 19.99);
            ajouterLivre(livre);
        } catch (SupportException s) {
            System.out.println("Erreur init : " + s.getMessage());
        } catch (RayonException e) {
            System.out.println("Erreur init : " + e.getMessage());
        }
    }

    @Override
    public void ajouterRayon(Rayon rayon) throws RayonException{
        List<Rayon> existants = rayonDao.readAll();

        boolean doublon = existants.stream()
                .anyMatch(r -> r.getNom().equalsIgnoreCase(rayon.getNom())
                        && r.getCategorie() == rayon.getCategorie());
        if (doublon) {
            throw new RayonException("Un rayon avec ce nom et cette catégorie existe déjà !");
        }

        long nbParCategorie = existants.stream()
                .filter(r -> r.getCategorie() == rayon.getCategorie())
                .count();
        if (nbParCategorie >= 3) {
            throw new RayonException("Maximum 3 rayons par catégorie !");
        }
        rayonDao.create(rayon);
    }

    @Override
    public void ajouterLivre(Livre livre) throws SupportException {
        boolean doublon = supportDao.readAll().stream()
                .anyMatch(s -> s.getNom().equalsIgnoreCase(livre.getNom()));
        if (doublon) throw new SupportException("Un support avec ce nom existe déjà !");
        supportDao.create(livre);
    }

    @Override
    public void ajouterCd(CD cd) throws SupportException {
        boolean doublon = supportDao.readAll().stream()
                .anyMatch(s -> s.getNom().equalsIgnoreCase(cd.getNom()));
        if (doublon) throw new SupportException("Un support avec ce nom existe déjà !");
        supportDao.create(cd);
    }

    @Override
    public void ajouterDvd(DVD dvd) throws SupportException {
        boolean doublon = supportDao.readAll().stream()
                .anyMatch(s -> s.getNom().equalsIgnoreCase(dvd.getNom()));
        if (doublon) throw new SupportException("Un support avec ce nom existe déjà !");
        supportDao.create(dvd);
    }

    @Override
    public List<Support> recupererSupportsDansRayon(Rayon rayon) {
        List<Long> ids = rayon.getSupports();
        return supportDao.readAll().stream()
                .filter(s -> ids.contains(s.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Livre> recupererTousLesLivres() {
        return supportDao.readAll().stream()
                .filter(s -> s instanceof Livre)
                .map(s -> (Livre) s)
                .collect(Collectors.toList());
    }

    @Override
    public List<CD> recupererTousLesCds() {
        return supportDao.readAll().stream()
                .filter(s -> s instanceof CD)
                .map(s -> (CD) s)
                .collect(Collectors.toList());
    }

    @Override
    public List<DVD> recupererToutLesDvds() {
        return supportDao.readAll().stream()
                .filter(s -> s instanceof DVD)
                .map(s -> (DVD) s)
                .collect(Collectors.toList());
    }

    @Override
    public List<Rayon> recupererRayons(){
        return rayonDao.readAll();
    }

    @Override
    public void supprimer(Long id) throws SupportException {
        if (!supportDao.exist(id)) {
            throw new SupportException("Support introuvable pour l'id : " + id);
        }
        supportDao.delete(id);
    }

    @Override
    public void mettreAJourRayon(Rayon rayon) throws RayonException {
        rayonDao.update(rayon);
    }
}
