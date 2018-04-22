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
public class Stade {
      private int id ; 
    private String nom ; 
    private double geolat ; 
    private double geolong ; 
    private int Capacity ;
    private String Image ; 
    private String City ; 
	public Stade(){}
    public Stade(int id, String nom, double geolat, double geolong, int Capacity, String Image, String City) {
        this.id = id;
        this.nom = nom;
        this.geolat = geolat;
        this.geolong = geolong;
        this.Capacity = Capacity;
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

    public int getCapacity() {
        return Capacity;
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

    public void setCapacity(int Capacity) {
        this.Capacity = Capacity;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public void setCity(String City) {
        this.City = City;
    }
    
    
    
}
