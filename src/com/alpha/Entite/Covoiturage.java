/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.Entite;

import java.util.Date;

/**
 *
 * @author Sof
 */
public class Covoiturage {
    int id ;
    int idUser ;
    String NameUser ;
    String Voiture ;
    String Couleur ; 
    String Langue ; 
    Date DateDepart ; 
    String Depart ; 
    String Destination  ; 
    String Duree; 
    int Kilometrage;
    int NbPlaceTot;
    int NbPlaceRestantes ; 
    String Bagage ; 
    float Smoking; 
    int PrixPlace; 
    int  NumCompteBancaire ; 
    public Covoiturage()
    {}
    public Covoiturage(int id, int idUser, String NameUser, String Voiture, String Couleur, String Langue, Date DateDepart, String Depart, String Destination, String Duree, int Kilometrage, int NbPlaceTot, int NbPlaceRestantes, String Bagage, float Smoking, int PrixPlace, int NumCompteBancaire) {
        this.id = id;
        this.idUser = idUser;
        this.NameUser = NameUser;
        this.Voiture = Voiture;
        this.Couleur = Couleur;
        this.Langue = Langue;
        this.DateDepart = DateDepart;
        this.Depart = Depart;
        this.Destination = Destination;
        this.Duree = Duree;
        this.Kilometrage = Kilometrage;
        this.NbPlaceTot = NbPlaceTot;
        this.NbPlaceRestantes = NbPlaceRestantes;
        this.Bagage = Bagage;
        this.Smoking = Smoking;
        this.PrixPlace = PrixPlace;
        this.NumCompteBancaire = NumCompteBancaire;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNameUser() {
        return NameUser;
    }

    public void setNameUser(String NameUser) {
        this.NameUser = NameUser;
    }

    public String getVoiture() {
        return Voiture;
    }

    public void setVoiture(String Voiture) {
        this.Voiture = Voiture;
    }

    public String getCouleur() {
        return Couleur;
    }

    public void setCouleur(String Couleur) {
        this.Couleur = Couleur;
    }

    public String getLangue() {
        return Langue;
    }

    public void setLangue(String Langue) {
        this.Langue = Langue;
    }

    public Date getDateDepart() {
        return DateDepart;
    }

    public void setDateDepart(Date DateDepart) {
        this.DateDepart = DateDepart;
    }

    public String getDepart() {
        return Depart;
    }

    public void setDepart(String Depart) {
        this.Depart = Depart;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String Destination) {
        this.Destination = Destination;
    }

    public String getDuree() {
        return Duree;
    }

    public void setDuree(String Duree) {
        this.Duree = Duree;
    }

    public int getKilometrage() {
        return Kilometrage;
    }

    public void setKilometrage(int Kilometrage) {
        this.Kilometrage = Kilometrage;
    }

    public int getNbPlaceTot() {
        return NbPlaceTot;
    }

    public void setNbPlaceTot(int NbPlaceTot) {
        this.NbPlaceTot = NbPlaceTot;
    }

    public int getNbPlaceRestantes() {
        return NbPlaceRestantes;
    }

    public void setNbPlaceRestantes(int NbPlaceRestantes) {
        this.NbPlaceRestantes = NbPlaceRestantes;
    }

    public String getBagage() {
        return Bagage;
    }

    public void setBagage(String Bagage) {
        this.Bagage = Bagage;
    }

    public float getSmoking() {
        return Smoking;
    }

    public void setSmoking(float Smoking) {
        this.Smoking = Smoking;
    }

    public int getPrixPlace() {
        return PrixPlace;
    }

    public void setPrixPlace(int PrixPlace) {
        this.PrixPlace = PrixPlace;
    }

    public int getNumCompteBancaire() {
        return NumCompteBancaire;
    }

    public void setNumCompteBancaire(int NumCompteBancaire) {
        this.NumCompteBancaire = NumCompteBancaire;
    }
    
    
}
