/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.Entite;

import java.util.Date;

import java.util.TimeZone;
/**
 *
 * @author Sof
 */
public class Divertissement {
    private int id ; 
    private String nom ; 
    private double geolat ; 
    private double geolong ; 
    private TimeZone heureOuverture ; 
    private TimeZone heureFermeture ; 
    private String Link ; 
    private String Image ; 
    private String City ; 
	public Divertissement()
	{}
    public Divertissement(int id, String nom, double geolat, double geolong, TimeZone heureOuverture, TimeZone heureFermeture, String Link, String Image, String City) {
        this.id = id;
        this.nom = nom;
        this.geolat = geolat;
        this.geolong = geolong;
        this.heureOuverture = heureOuverture;
        this.heureFermeture = heureFermeture;
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

    public TimeZone getHeureOuverture() {
        return heureOuverture;
    }

    public TimeZone getHeureFermeture() {
        return heureFermeture;
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

    public void setHeureOuverture(TimeZone heureOuverture) {
        this.heureOuverture = heureOuverture;
    }

    public void setHeureFermeture(TimeZone heureFermeture) {
        this.heureFermeture = heureFermeture;
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
    
    
    
}
