package views.utils;

import java.util.List;

/**
 * Utilitaire d'affichage console.
 * Toutes les sorties System.out passent par ici — ne pas appeler System.out directement
 * dans ViewConsoleImpl ou le Présenteur.
 */
public final class AffichageConsole {

    public static final char COIN   = '+';
    public static final char H_LINE = '-';
    public static final char V_LINE = '|';

    private AffichageConsole() {}

    public static void afficherMessageAvecSautLigne(String msg) {
        System.out.println(msg);
    }

    public static void afficherMessageSansSautLigne(String msg) {
        System.out.print(msg);
    }

    /**
     * Affiche un menu numéroté simple avec "CHOIX : " à la fin.
     * <pre>
     *  1 - option a
     *  2 - option b
     * CHOIX :
     * </pre>
     */
    public static void afficherMenuSimple(List<String> options) {
        for (int i = 0; i < options.size(); i++) {
            System.out.printf("%2d - %s%n", i + 1, options.get(i).toLowerCase());
        }
        System.out.print("CHOIX : ");
    }

    /**
     * Affiche un menu encadré avec option "0 - sortir".
     * <pre>
     * +------- MENU PRINCIPAL -------+
     * |  1 - option a                |
     * |  0 - sortir                  |
     * +------------------------------+
     * CHOIX :
     * </pre>
     */
    public static void afficherMenuEntoureAvecOptionSortie(List<String> options, String nomMenu) {
        int maxLen = options.stream().mapToInt(String::length).max().orElse(0);
        maxLen = Math.max(maxLen, nomMenu.length());
        int largeur = maxLen + 12;

        afficherLigneTitre(nomMenu, largeur);
        for (int i = 0; i < options.size(); i++) {
            afficherLigneOption(i + 1, options.get(i), largeur);
        }
        afficherLigneOption(0, "sortir", largeur);
        afficherLigneBas(largeur);
        System.out.print("CHOIX : ");
    }

    private static void afficherLigneTitre(String titre, int largeur) {
        int pad = (largeur - titre.length()) / 2 - 1;
        String tirets = H_LINE + "-".repeat(Math.max(0, pad));
        System.out.println(COIN + tirets + " " + titre + " " + tirets + COIN);
    }

    private static void afficherLigneOption(int num, String option, int largeur) {
        String contenu = String.format(" %2d - %s", num, option);
        int espaces = largeur - contenu.length();
        System.out.println(V_LINE + contenu + " ".repeat(Math.max(0, espaces)) + V_LINE);
    }

    private static void afficherLigneBas(int largeur) {
        System.out.println(COIN + "-".repeat(largeur) + COIN);
    }
}
