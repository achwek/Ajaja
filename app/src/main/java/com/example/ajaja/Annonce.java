package com.example.ajaja;

public class Annonce {

    private String title;
    private  String description;
    private  String categorie;
    private  String sousCategorie;
    private  String gouvernorat;
    private  String delegation;
    private  String prix;
    private String tel;
    private  String adresse;
    private String imageUri;
    public Annonce(){}

    public Annonce(String title, String description, String categorie, String sousCategorie,
                   String gouvernorat, String delegation, String prix, String tel, String adresse,
                   String imageUri) {
        this.title = title;
        this.description = description;
        this.categorie = categorie;
        this.sousCategorie = sousCategorie;
        this.gouvernorat = gouvernorat;
        this.delegation = delegation;
        this.prix = prix;
        this.tel = tel;
        this.adresse = adresse;
        this.imageUri = imageUri;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getSousCategorie() {
        return sousCategorie;
    }

    public void setSousCategorie(String sousCategorie) {
        this.sousCategorie = sousCategorie;
    }

    public String getGouvernorat() {
        return gouvernorat;
    }

    public void setGouvernorat(String gouvernorat) {
        this.gouvernorat = gouvernorat;
    }

    public String getDelegation() {
        return delegation;
    }

    public void setDelegation(String delegation) {
        this.delegation = delegation;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
