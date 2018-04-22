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
public class Opinions {
	private int id;
    private int idUser;
    private String Avis;
    private String nbreEtoiles;

	public Opinions() {
	}

	public Opinions(int id, int idUser, String Avis, String nbreEtoiles) {
		this.id = id;
		this.idUser = idUser;
		this.Avis = Avis;
		this.nbreEtoiles = nbreEtoiles;
	}
	public Opinions( int idUser, String Avis, String nbreEtoiles) {
		this.idUser = idUser;
		this.Avis = Avis;
		this.nbreEtoiles = nbreEtoiles;
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

	public String getAvis() {
		return Avis;
	}

	public void setAvis(String Avis) {
		this.Avis = Avis;
	}

	public String getNbreEtoiles() {
		return nbreEtoiles;
	}

	public void setNbreEtoiles(String nbreEtoiles) {
		this.nbreEtoiles = nbreEtoiles;
	}


	
}
