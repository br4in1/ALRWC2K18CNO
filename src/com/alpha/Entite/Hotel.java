/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.Entite;
/**
 *
 * @author Sof
 */
public class Hotel {
     private int id ; 
    private String nom ; 
    private double geolat ; 
    private double geolong ; 
    private int nbEtoiles ; 
    private String Link ; 
    private String Image ; 
    private String City ; 
    public Hotel()
    {}
    public Hotel(int id, String nom, double geolat, double geolong, int nbEtoiles, String Link, String Image, String City) {
        this.id = id;
        this.nom = nom;
        this.geolat = geolat;
        this.geolong = geolong;
        this.nbEtoiles = nbEtoiles;
        this.Link = Link;
        this.Image = Image;
        this.City = City;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public double getGeolat() {
        return geolat;
    }

    public double getGeolong() {
        return geolong;
    }

    public int getNbEtoiles() {
        return nbEtoiles;
    }

    public String getLink() {
        return Link;
    }

    public String getImage() {
        return Image;
    }

    public String getCity() {
        return City;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setGeolat(double geolat) {
        this.geolat = geolat;
    }

    public void setGeolong(double geolong) {
        this.geolong = geolong;
    }

    public void setNbEtoiles(int nbEtoiles) {
        this.nbEtoiles = nbEtoiles;
    }

    public void setLink(String Link) {
        this.Link = Link;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public void setCity(String City) {
        this.City = City;
    }

    @Override
    public String toString() {
        return "Hotel{" + "id=" + id + ", nom=" + nom + ", geolat=" + geolat + ", geolong=" + geolong + ", nbEtoiles=" + nbEtoiles + ", Link=" + Link + ", Image=" + Image + ", City=" + City + '}';
    }
    
    
    
    
   
    
       
       
    
    
}
