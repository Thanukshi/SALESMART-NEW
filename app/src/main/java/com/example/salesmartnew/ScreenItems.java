package com.example.salesmartnew;

public class ScreenItems {

    String Tittle, Description;
    int welcomeImage;

    public ScreenItems(String tittle, String description, int welcomeImage) {
        Tittle = tittle;
        Description = description;
        this.welcomeImage = welcomeImage;
    }

    //generate setters
    public void setTittle(String tittle) {
        Tittle = tittle;
    }

    public void setDescription(String description) {
        Description = description;
    }


    //generate getters
    public void setWelcomeImage(int welcomeImage) {
        this.welcomeImage = welcomeImage;
    }

    public String getTittle() {
        return Tittle;
    }

    public String getDescription() {
        return Description;
    }

    public int getWelcomeImage() {
        return welcomeImage;
    }
}
