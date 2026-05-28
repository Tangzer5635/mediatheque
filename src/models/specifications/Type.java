package models.specifications;

public enum Type {
    LIVRE("Livre"),
    CD("CD"),
    DVD("DVD");

    private final String type;

    Type(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
