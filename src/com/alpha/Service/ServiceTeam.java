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
			//System.out.println(json);
			JSONParser j = new JSONParser();

			Map<String, Object> lTeamMap = j.parseJSON(new CharArrayReader(json.toCharArray()));
		//	System.out.println(lTeamMap);

			List<Map<String, Object>> list = (List<Map<String, Object>>) lTeamMap.get("root");

			for (Map<String, Object> obj : list) {
				Team t = new Team();

				// System.out.println(obj.get("id"));
				float id = Float.parseFloat(obj.get("id").toString());

				//System.out.println(id);
				t.setId((int) id);
				t.setName(obj.get("name").toString());
				t.setCoach(obj.get("coach").toString());
				t.setPresident(obj.get("president").toString());
				t.setArea(obj.get("area").toString());

				t.setWcGroup(obj.get("wcgroup").toString());
				
				t.setWin((int)Float.parseFloat(obj.get("win").toString()));
				t.setLoose((int)Float.parseFloat(obj.get("loose").toString()));
				t.setDraw((int)Float.parseFloat(obj.get("draw").toString()));
				t.setPoints((int)Float.parseFloat(obj.get("points").toString()));
				t.setFifaRank((int)Float.parseFloat(obj.get("fifarank").toString()));
				t.setFlagPhoto(obj.get("flagphoto").toString());
				/*t.setLogoPhoto(obj.get("logophoto").toString());
				t.setSquadPhoto(obj.get("squadphoto").toString());
				t.setDescriptionPhoto(obj.get("descriptionphoto").toString());
				t.setDescription(obj.get("description").toString());
				t.setWebsite(obj.get("website").toString());
				t.setVideo(obj.get("video").toString());
				*/
				
				
				
			//	System.out.println(t);
				listTeams.add(t);

			}

		} catch (IOException ex) {
		}
		//System.out.println(listTeams);
		return listTeams;
	}
	
	public ArrayList<Team> listTeams2 = new ArrayList<>();
    
	 
    public ArrayList<Team> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/team/allTeam");  

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceTeam ser = new ServiceTeam();
               // listTeams2 = ser.getListTeam(new String(con.getResponseData()));
			    listTeams2 = ser.getListTeam(new String(con.getResponseData()));

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTeams2;
    }

}
