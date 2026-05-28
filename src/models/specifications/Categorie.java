package models.specifications;

public enum Categorie {
    LITTERATURE("Littérature"),
    MUSIQUE("Musique"),
    CINEMA("Cinéma"),
    JEUNESSE("Jeunesse"),
    DOCUMENTAIRE("Documentaire");

    private final String categorie;

    Categorie(String categorie) {
        this.categorie = categorie;
    }

    public String getCategorie() {
        return categorie;
    }
}
