package br.com.ivan.news.usecase.port;

public enum Category {

    BUSINESS("business"),
    ENTERTAINMENT("entertainment"),
    GENERAL("general"),
    HEALTH("health"),
    SCIENCE("science"),
    SPORTS("sports"),
    TECHNOLOGY("technology");

    private final String category;
    Category(String category) {
        this.category = category;
    }

    public static Category of(String category) {

        for (Category categories : Category.values()) {
            if (category.equals(categories.category)) {
                return categories;
            }
        }
        throw new IllegalArgumentException("Does not exist this category");
    }
}
