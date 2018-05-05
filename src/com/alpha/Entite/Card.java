/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.Entite;

/**
 *
 * @author simo
 */
public class Card {

	private int id;
	private Game game;
	private Player player;
	private int yellow;
	private int red;
	private int minute;

	public Card() {
	}

	public Card(int id, Game game, Player player, int yellow, int red, int minute) {
		this.id = id;
		this.game = game;
		this.player = player;
		this.yellow = yellow;
		this.red = red;
		this.minute = minute;
	}

	public Card(int id, Game game, Player player, int yellow, int red) {
		this.id = id;
		this.game = game;
		this.player = player;
		this.yellow = yellow;
		this.red = red;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getYellow() {
		return yellow;
	}

	public void setYellow(int yellow) {
		this.yellow = yellow;
	}

	public int getRed() {
		return red;
	}

	public void setRed(int red) {
		this.red = red;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}
	

	@Override
	public String toString() {
		return "Card{" + "id=" + id + ", game=" + game + ", player=" + player + ", yellow=" + yellow + ", red=" + red + ", minute=" + minute + '}';
	}

	

}
