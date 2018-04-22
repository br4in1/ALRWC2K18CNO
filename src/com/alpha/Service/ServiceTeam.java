/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.Service;

import com.alpha.Entite.Team;
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
public class ServiceTeam {

	public ArrayList<Team> getListTeam(String json) {

		ArrayList<Team> listTeams = new ArrayList<>();

		try {
			System.out.println(json);
			JSONParser j = new JSONParser();

			Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
			System.out.println(etudiants);

			List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");

			for (Map<String, Object> obj : list) {
				Team t = new Team();

				// System.out.println(obj.get("id"));
				float id = Float.parseFloat(obj.get("id").toString());

				System.out.println(id);
				t.setId((int) id);
				System.out.println(t);
				listTeams.add(t);

			}

		} catch (IOException ex) {
		}
		System.out.println(listTeams);
		return listTeams;
	}
	
	public ArrayList<Team> listTeams2 = new ArrayList<>();
    
	 
    public ArrayList<Team> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
		con.setUrl("http://41.226.11.243:10004/tasks/");  
       // con.setUrl("http://localhost/projet/ALRWC2K18/web/app_dev.php/team/allTeam");  

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceTeam ser = new ServiceTeam();
                listTeams2 = ser.getListTeam(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTeams2;
    }

}
