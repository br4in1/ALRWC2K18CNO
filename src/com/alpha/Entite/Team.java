/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.Entite;

import java.util.Date;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Moez
 */
public class Team {
    private int id ; //1
    private String name ; //2 -
    private String coach ; //3 -
    private String president  ; //4 -
    private String area ; //5 -
    private int gamesPlayed ;//6
    private int goalScored ;//7
    private int goalAgainst ; //8
    private int participations ;//9
    private Date fifaDate ; //10
    private String wcGroup ; //11
    private int win ;//12
    private int loose ;//13 
    private int draw ;//14
    private int points ;//15
    private int fifaRank ;//16
    private String flagPhoto ; //17
    private String logoPhoto ;//18
    private String squadPhoto ;//19
    private String descriptionPhoto ; //20
    private String description ;//21
    private String website ;//22
    private String video ;//23
	
	public Team (){}

    public Team(int id, String name, String coach, String president, String area, int gamesPlayed, int goalScored, int goalAgainst, int participations, Date fifaDate, String wcGroup, int win, int loose, int draw, int points, int fifaRank, String flagPhoto, String logoPhoto, String squadPhoto, String descriptionPhoto, String description, String website, String video) {
        this.id = id;
        this.name = name;
        this.coach = coach;
        this.president = president;
        this.area = area;
        this.gamesPlayed = gamesPlayed;
        this.goalScored = goalScored;
        this.goalAgainst = goalAgainst;
        this.participations = participations;
        this.fifaDate = fifaDate;
        this.wcGroup = wcGroup;
        this.win = win;
        this.loose = loose;
        this.draw = draw;
        this.points = points;
        this.fifaRank = fifaRank;
        this.flagPhoto = flagPhoto;
        this.logoPhoto = logoPhoto;
        this.squadPhoto = squadPhoto;
        this.descriptionPhoto = descriptionPhoto;
        this.description = description;
        this.website = website;
        this.video = video;
    }

    
    public Team(String name, String coach, String president, String area, int gamesPlayed, int goalScored, int goalAgainst, int participations, Date fifaDate, String wcGroup, int win, int loose, int draw, int points, int fifaRank, String flagPhoto, String logoPhoto, String squadPhoto, String descriptionPhoto, String description, String website, String video) {
       
        this.name = name;
        this.coach = coach;
        this.president = president;
        this.area = area;
        this.gamesPlayed = gamesPlayed;
        this.goalScored = goalScored;
        this.goalAgainst = goalAgainst;
        this.participations = participations;
        this.fifaDate = fifaDate;
        this.wcGroup = wcGroup;
        this.win = win;
        this.loose = loose;
        this.draw = draw;
        this.points = points;
        this.fifaRank = fifaRank;
        this.flagPhoto = flagPhoto;
        this.logoPhoto = logoPhoto;
        this.squadPhoto = squadPhoto;
        this.descriptionPhoto = descriptionPhoto;
        this.description = description;
        this.website = website;
        this.video = video;
    }
     public Team(String name, String coach, String president, String area,int participations, Date fifaDate, String wcGroup, int fifaRank, String flagPhoto, String logoPhoto, String squadPhoto, String descriptionPhoto, String description, String website, String video) {
     
        this.name = name;
        this.coach = coach;
        this.president = president;
        this.area = area;
        this.participations = participations;
        this.fifaDate = fifaDate;
        this.wcGroup = wcGroup;
        this.fifaRank = fifaRank;
        this.flagPhoto = flagPhoto;
        this.logoPhoto = logoPhoto;
        this.squadPhoto = squadPhoto;
        this.descriptionPhoto = descriptionPhoto;
        this.description = description;
        this.website = website;
        this.video = video;
    }
              //String name,    String coach,  String president, String area, int gamesPlayed, int goalScored, int goalAgainst, int participations, Date fifaDate, String wcGroup, int win, int loose, int draw, int points, int fifaRank, String flagPhoto, String logoPhoto, String squadPhoto, String descriptionPhoto, String description, String website, String video
   // public Team(String string, String string0, String string1, String string2, int aInt,       int aInt0,      int aInt1,       int aInt2,          Date date,     String string3, int aInt3, int aInt4, int aInt5, int aInt6, String string4, String string5, String string6, String string7, String string8, String string9, String string10) {
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   // }

    
   
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public String getPresident() {
        return president;
    }

    public void setPresident(String president) {
        this.president = president;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public int getGoalScored() {
        return goalScored;
    }

    public void setGoalScored(int goalScored) {
        this.goalScored = goalScored;
    }

    public int getGoalAgainst() {
        return goalAgainst;
    }

    public void setGoalAgainst(int goalAgainst) {
        this.goalAgainst = goalAgainst;
    }

    public int getParticipations() {
        return participations;
    }

    public void setParticipations(int participations) {
        this.participations = participations;
    }

    public Date getFifaDate() {
        return fifaDate;
    }

    public void setFifaDate(Date fifaDate) {
        this.fifaDate = fifaDate;
    }

    public String getWcGroup() {
        return wcGroup;
    }

    public void setWcGroup(String wcGroup) {
        this.wcGroup = wcGroup;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public int getLoose() {
        return loose;
    }

    public void setLoose(int loose) {
        this.loose = loose;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getFifaRank() {
        return fifaRank;
    }

    public void setFifaRank(int fifaRank) {
        this.fifaRank = fifaRank;
    }

    public String getFlagPhoto() {
        return flagPhoto;
    }

    public void setFlagPhoto(String flagPhoto) {
        this.flagPhoto = flagPhoto;
    }

    public String getLogoPhoto() {
        return logoPhoto;
    }

    public void setLogoPhoto(String logoPhoto) {
        this.logoPhoto = logoPhoto;
    }

    public String getSquadPhoto() {
        return squadPhoto;
    }

    public void setSquadPhoto(String squadPhoto) {
        this.squadPhoto = squadPhoto;
    }

    public String getDescriptionPhoto() {
        return descriptionPhoto;
    }

    public void setDescriptionPhoto(String descriptionPhoto) {
        this.descriptionPhoto = descriptionPhoto;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
	
	public ImageView getImage(){
		ImageView img = new ImageView();
		img.setImage(new Image(flagPhoto));
		img.setFitHeight(50);
		img.setFitWidth(100);
		return img;
	}

	@Override
	public String toString() {
		return "Team{" + "id=" + id + ", name=" + name + ", coach=" + coach + ", president=" + president + ", area=" + area + ", gamesPlayed=" + gamesPlayed + ", goalScored=" + goalScored + ", goalAgainst=" + goalAgainst + ", participations=" + participations + ", fifaDate=" + fifaDate + ", wcGroup=" + wcGroup + ", win=" + win + ", loose=" + loose + ", draw=" + draw + ", points=" + points + ", fifaRank=" + fifaRank + ", flagPhoto=" + flagPhoto + ", logoPhoto=" + logoPhoto + ", squadPhoto=" + squadPhoto + ", descriptionPhoto=" + descriptionPhoto + ", description=" + description + ", website=" + website + ", video=" + video + '}';
	}

	
}
     