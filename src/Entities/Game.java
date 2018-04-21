/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author simo
 */
public class Game {
    private int id;
    private Date date;
    private String homeTeam;
    private String awayTeam;
    private String result;
    private String stadium;
    private String summary;
    private String summaryPhoto;
    private String Highlights;
    private String Referee;

    public Game(Date date, String homeTeam, String awayTeam, String result, String stadium, String summary, String summaryPhoto, String Highlights, String Referee) {
    
        this.date = date;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.result = result;
        this.stadium = stadium;
        this.summary = summary;
        this.summaryPhoto = summaryPhoto;
        this.Highlights = Highlights;
        this.Referee = Referee;
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

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
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
    
    
    
    
}

