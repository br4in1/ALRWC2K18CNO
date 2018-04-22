/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.Entite;
/**
 *
 * @author raiiz
 */
public class Newsletter {
    private int id;
    private String email;
    private String contenu;
    private int Preferences;

    /*0-All 1-Seuelement Equipes 2-Seulement matchs 3-Rien */
    public Newsletter() {
    }

    public Newsletter(int id, String email, String contenu, int Preferences) {
        this.id = id;
        this.email = email;
        this.contenu = contenu;
        this.Preferences = Preferences;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public int getPreferences() {
        return Preferences;
    }

    public void setPreferences(int Preferences) {
        this.Preferences = Preferences;
    }

    @Override
    public String toString() {
        return "Newsletter{" + "id=" + id + ", email=" + email + ", contenu=" + contenu + ", Preferences=" + Preferences + '}';
    }
}
