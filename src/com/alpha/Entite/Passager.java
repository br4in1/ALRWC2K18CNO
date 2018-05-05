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
public class Passager {
    int id ; 
    int idUser ; 
    String nameUser ; 
    int idConv ; 
    int NbPlace; 
    public Passager(){}
    public Passager(int id, int idUser, String nameUser, int idConv, int NbPlace) {
        this.id = id;
        this.idUser = idUser;
        this.nameUser = nameUser;
        this.idConv = idConv;
        this.NbPlace = NbPlace;
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
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public int getIdConv() {
        return idConv;
    }

    public void setIdConv(int idConv) {
        this.idConv = idConv;
    }

    public int getNbPlace() {
        return NbPlace;
    }

    public void setNbPlace(int NbPlace) {
        this.NbPlace = NbPlace;
    }
    
}
