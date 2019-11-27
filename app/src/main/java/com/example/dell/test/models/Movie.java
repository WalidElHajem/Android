package com.example.dell.test.models;

public class Movie {

    private String name;
    private String text;
    private String photoId;


    public Movie(String name, String text, String photoId) {
        this.name = name;
        this.text = text;
        this.photoId = photoId;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public String getPhotoId() {
        return photoId;
    }

}