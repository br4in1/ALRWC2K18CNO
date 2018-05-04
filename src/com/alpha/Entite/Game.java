/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.Entite;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author simo
 */
public class Game {
    private int id;
    private Date date;
    private Map<String,Object> homeTeam;
    private Map<String,Object> awayTeam;
    private String result;
    private Map<String,Object> stadium;
    private String summary;
    private String summaryPhoto;
    private String Highlights;
    private String Referee;
	private int cost;

    public Game(Date date, Map<String,Object> homeTeam, Map<String,Object> awayTeam, String result, Map<String,Object> stadium, String summary, String summaryPhoto, String Highlights, String Referee, int cost) {
    
        this.date = date;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.result = result;
        this.stadium = stadium;
        this.summary = summary;
        this.summaryPhoto = summaryPhoto;
        this.Highlights = Highlights;
        this.Referee = Referee;
		this.cost = cost;
    }

	public Game() {
	}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Map<String,Object> getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Map<String,Object> homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Map<String,Object> getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Map<String,Object> awayTeam) {
        this.awayTeam = awayTeam;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Map<String,Object> getStadium() {
        return stadium;
    }

    public void setStadium(Map<String,Object> stadium) {
        this.stadium = stadium;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSummaryPhoto() {
        return summaryPhoto;
    }

    public void setSummaryPhoto(String summaryPhoto) {
        this.summaryPhoto = summaryPhoto;
    }

    public String getHighlights() {
        return Highlights;
    }

    public void setHighlights(String Highlights) {
        this.Highlights = Highlights;
    }

    public String getReferee() {
        return Referee;
    }

    public void setReferee(String Referee) {
        this.Referee = Referee;
    }

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
	

	@Override
	public String toString() {
		return "Game{" + "id=" + id + ", date=" + date + ", homeTeam=" + homeTeam + ", awayTeam=" + awayTeam + ", result=" + result + ", stadium=" + stadium + ", summary=" + summary + ", summaryPhoto=" + summaryPhoto + ", Highlights=" + Highlights + ", Referee=" + Referee + '}';
	}
   
    
}

