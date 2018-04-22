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
        //con.setUrl("http://localhost/projet/ALRWC2K18/web/app_dev.php/player/allPlayer");  
		con.setUrl("http://41.226.11.243:10004/tasks/");  


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
