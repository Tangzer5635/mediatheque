package start;

import models.facades.IModel;
import models.facades.ModelImpl;
import presenteur.Presenteur;
import views.facades.IView;
import views.facades.ViewConsoleImpl;

/**
 * Point d'entrée de l'application.
 * Instancie les trois couches MVP et démarre le Présenteur.
 *
 * Aucun try/catch ici : start() gère toutes les exceptions en interne
 * et les affiche via la Vue.
 */
public class Main {
    public static void main(String[] args) {
        IModel model = new ModelImpl();
        IView view = new ViewConsoleImpl();
        Presenteur presenteur = new Presenteur(model, view);
        presenteur.start();
    }
}
