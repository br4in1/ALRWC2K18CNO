/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.Entite;

import java.util.Map;

/**
 *
 * @author Moez
 */
public class Bet {
	int id ;
	Map<Integer,Object> idGame ;
	String result ;
	int idUser ;
	int points ;

	public Bet() {
		
	}
	public Bet(Map<Integer,Object> idGame, String result, int idUser) {
		this.idGame = idGame;
		this.result = result;
		this.idUser = idUser;

	}
	public Bet(int id, Map<Integer,Object> idGame, String result, int idUser, int points) {
		this.id = id;
		this.idGame = idGame;
		this.result = result;
		this.idUser = idUser;
		this.points = points;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Map<Integer,Object> getIdGame() {
		return idGame;
	}

	public void setIdGame(Map<Integer,Object> idGame) {
		this.idGame = idGame;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	@Override
	public String toString() {
		return "Bet{" + "id=" + id + ", idGame=" + idGame + ", result=" + result + ", idUser=" + idUser + ", points=" + points + '}';
	}
	
	
	
}
