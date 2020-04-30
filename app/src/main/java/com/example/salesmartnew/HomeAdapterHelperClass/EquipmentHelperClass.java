package com.example.salesmartnew.HomeAdapterHelperClass;

public class EquipmentHelperClass {

    int image;
    String titleCard, descriptionCard;


    public EquipmentHelperClass(int image, String titleCard, String descriptionCard) {
        this.image = image;
        this.titleCard = titleCard;
        this.descriptionCard = descriptionCard;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setTitleCard(String titleCard) {
        this.titleCard = titleCard;
    }

    public void setDescriptionCard(String descriptionCard) {
        this.descriptionCard = descriptionCard;
    }


    public int getImage() {
        return image;
    }

    public String getTitleCard() {
        return titleCard;
    }

    public String getDescriptionCard() {
        return descriptionCard;
    }
}
