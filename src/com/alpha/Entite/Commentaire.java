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
public class Commentaire {

	private int id;
	private String text;
	private int idUser;
	private int idPhoto;

	public Commentaire() {
	}

	public Commentaire(int idUser, int idPhoto, String text) {
		this.idUser = idUser;
		this.idPhoto = idPhoto;
		this.text = text;

	}

	public Commentaire(int id, String text, int idUser, int idPhoto) {
		this.id = id;
		this.text = text;
		this.idUser = idUser;
		this.idPhoto = idPhoto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getIdPhoto() {
		return idPhoto;
	}

	public void setIdPhoto(int idPhoto) {
		this.idPhoto = idPhoto;
	}

}
