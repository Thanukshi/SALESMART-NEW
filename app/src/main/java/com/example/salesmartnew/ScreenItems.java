package com.example.salesmartnew;

public class ScreenItems {

    String Tittle, Description;
    int WelcomeImage;

    public ScreenItems(String tittle, String description, int welcomeImage) {
        Tittle = tittle;
        Description = description;
        WelcomeImage = welcomeImage;
    }

    //generate setters
    public void setTittle(String tittle) {
        Tittle = tittle;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setWelcomeImage(int welcomeImage) {
        WelcomeImage = welcomeImage;
    }


    public String getTittle() {
        return Tittle;
    }

    public String getDescription() {
        return Description;
    }

    public int getWelcomeImage() {
        return WelcomeImage;
    }
}

