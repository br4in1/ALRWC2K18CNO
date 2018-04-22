/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.Entite;


/**
 *
 * @author dell
 */
public class Gallery {

    private int id;
    private int idUser;
	private String ville ; 
    private String lieu;
    private String description;
    private String image;
    private String etat ; 


	public Gallery() {
		
	}
   
    public Gallery(int id,int idUser,String ville , String lieu,String description,String image,String etat) {
        this.id = id;
        this.idUser = idUser;
		this.ville=ville;
        this.lieu = lieu;
        this.description = description;
        this.image = image;
        this.etat = etat;
	}
	public Gallery(int idUser,String ville , String lieu,String description,String image,String etat) {
        this.idUser = idUser;
		this.ville=ville ; 
        this.lieu = lieu;
        this.description = description;
        this.image = image;
        this.etat = etat;
	}
	
	 public Gallery(String etat) {
        this.etat = etat;
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

   

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getAvis() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}