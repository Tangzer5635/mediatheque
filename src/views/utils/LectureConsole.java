package views.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Utilitaire de lecture console.
 * Encapsule Scanner et garantit des saisies valides (boucle jusqu'à valeur correcte).
 * Ne jamais utiliser Scanner directement dans ViewConsoleImpl.
 */
public final class LectureConsole {

    private static final Scanner CLAVIER = new Scanner(System.in);

    private LectureConsole() {}

    /**
     * Lit un entier compris entre min et max inclus.
     * Reboucle avec "Choix invalide" jusqu'à saisie valide.
     */
    public static int lectureChoixInt(int min, int max) {
        int choix;
        while (true) {
            try {
                choix = Integer.parseInt(CLAVIER.nextLine().trim());
                if (choix >= min && choix <= max) return choix;
                System.out.println("Choix invalide (entre " + min + " et " + max + ")");
            } catch (NumberFormatException e) {
                System.out.println("Choix invalide");
            }
        }
    }

    /** Lit une chaîne non vide après avoir affiché l'entête. */
    public static String lectureChaineCaracteres(String entete) {
        System.out.print(entete);
        return lectureChaineCaracteres();
    }

    /** Lit une ligne quelconque. */
    public static String lectureChaineCaracteres() {
        return CLAVIER.nextLine();
    }

    /** Lit un double. Reboucle avec "Choix invalide" jusqu'à saisie valide. */
    public static double lectureDouble(String entete) {
        System.out.print(entete);
        while (true) {
            try {
                return Double.parseDouble(CLAVIER.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Choix invalide");
            }
        }
    }

    /** Lit une LocalDate au format donné. Reboucle si la date est invalide. */
    public static LocalDate lectureLocalDate(String entete, String pattern) {
        System.out.print(entete);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(pattern);
        while (true) {
            try {
                return LocalDate.parse(CLAVIER.nextLine().trim(), fmt);
            } catch (DateTimeParseException e) {
                System.out.println("Date invalide (format attendu : " + pattern + ")");
            }
        }
    }
}
