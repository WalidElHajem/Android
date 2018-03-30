package com.example.dell.test.models;

public class Movies {


   public String name;
   public String text;
   public int photoId;

   public Movies(String name, String text, int photoId) {
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

    public int getPhotoId() {
        return photoId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }
}