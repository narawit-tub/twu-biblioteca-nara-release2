package com.twu.biblioteca.bibliotecharelease2.domain;

public class LibaryMedia {
    private String maker;
    private String productName;
    private String publicationYear;
    private Boolean isAvailable;
    private LibaryMedia.Media_type mediaType;
    private String checkedOutUser;

    public enum Media_type {
        Book,
        Movie
    }

    public LibaryMedia() {}

    public LibaryMedia(String maker, String productName, String publicationYear) {
        this.maker = maker;
        this.productName = productName;
        this.publicationYear = publicationYear;
        this.isAvailable = true;
    }

    public String getMaker() {
        return maker;
    }

    public String getProductName() {
        return productName;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public Media_type getMediaType() {
        return mediaType;
    }

    public String getCheckedOutUser() {
        return checkedOutUser;
    }

    public void setCheckedOutUser(String checkedOutUser) {
        this.checkedOutUser = checkedOutUser;
    }

    public void setMediaType(Media_type mediaType) {
        this.mediaType = mediaType;
    }

    public void setAvailable(Boolean available) {
        this.isAvailable = available;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
