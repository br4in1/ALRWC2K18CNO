/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.Service;

import com.alpha.Entite.Player;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Moez
 */
public class ServicePlayer {

	public ArrayList<Player> getListPlayer(String json) {

		ArrayList<Player> listPlayers = new ArrayList<>();

		try {
			System.out.println(json);
			JSONParser j = new JSONParser();

			Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
			System.out.println(etudiants);

			
			List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");

			for (Map<String, Object> obj : list) {
				Player p = new Player();

				// System.out.println(obj.get("id"));
				float id = Float.parseFloat(obj.get("id").toString());

				
				System.out.println(id);
				p.setId((int) id);
				p.setName(obj.get("name").toString());

				p.setLastName(obj.get("lastname").toString());
			//	p.setAge(Integer.parseInt(obj.get("age").toString()));
				p.setClub(obj.get("club").toString());
				p.setNation(obj.get("nation").toString());
			//	p.setHeight(Double.parseDouble(obj.get("height").toString()));
			//	p.setWeight(Double.parseDouble(obj.get("weight").toString()));
				p.setPosition(obj.get("position").toString());
			//	p.setGoals(Integer.parseInt(obj.get("goals").toString()));
				p.setDescription(obj.get("description").toString());//profilePhoto
				p.setProfilePhoto(obj.get("profilephoto").toString());
				p.setBlanketPhoto(obj.get("blanketphoto").toString());
				p.setDescriptionPhoto(obj.get("descriptionphoto").toString());
				p.setFbLink(obj.get("fblink").toString());
				p.setTwitterLink(obj.get("twitterlink").toString());
				//p.setShirtNb(Integer.parseInt(obj.get("shirtNb").toString()));
				p.setVideo(obj.get("video").toString());
				
				System.out.println(p);
				listPlayers.add(p);

			}

		} catch (IOException ex) {
		}
		System.out.println(listPlayers);
		return listPlayers;

	}
	public  ArrayList<Player> listPlayers2 = new ArrayList<>();
    
	 
    public ArrayList<Player> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/player/allPlayer");  

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServicePlayer ser = new ServicePlayer();
                listPlayers2 = ser.getListPlayer(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listPlayers2;
    }

}
