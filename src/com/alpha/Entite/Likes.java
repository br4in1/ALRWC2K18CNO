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
public class Likes {
	
	 private int id;
    private int idUser;
	private int idPhoto; 

	
	 public Likes() {
	}
	 
	public Likes(int id, int idUser, int idPhoto) {
		this.id = id;
		this.idUser = idUser;
		this.idPhoto = idPhoto;
	}
	public Likes(int idUser, int idPhoto) {
		this.idUser = idUser;
		this.idPhoto = idPhoto;
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

	public int getIdPhoto() {
		return idPhoto;
	}

	public void setIdPhoto(int idPhoto) {
		this.idPhoto = idPhoto;
	}

	

	
	
	
	
}
