package com.twu.biblioteca.bibliotecharelease2.domain;

public class Movie extends LibaryMedia implements LibaryMediaInterface{

    private Double rating;

    public Double getRating() {
        return rating;
    }

    public Movie(String maker, String productName, String publicationYear, Double rating) {
        super(maker, productName, publicationYear);
        this.rating = rating;
        setMediaType(Media_type.Movie);
    }

    @Override
    public String getInformationDetail() {
        return String.format("%s (%s) by %s rating: %s - %s", this.getProductName(), this.getPublicationYear(), this.getMaker(), this.getRating() ,this.getAvailable() ? "available" : "not available");
    }
}
