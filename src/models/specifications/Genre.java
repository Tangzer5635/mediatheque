package models.specifications;

public enum Genre {
    ROMAN("Roman"),
    SCIENCE_FICTION("Science-fiction"),
    BIOGRAPHIE("Biographie"),
    JEUNESSE("Jeunesse"),
    BD("Bande dessinée"),
    POLICIER("Policier");


    private final String genre;

    Genre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }
}
