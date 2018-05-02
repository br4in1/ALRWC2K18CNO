/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.Service;

import com.alpha.Entite.Bet;
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
public class ServiceBet {
		public ArrayList<Bet> getListBet(String json) {

		ArrayList<Bet> listBets = new ArrayList<>();

		try {
			System.out.println(json);
			JSONParser j = new JSONParser();

			Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
			System.out.println(etudiants);

			List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");

			for (Map<String, Object> obj : list) {
				Bet t = new Bet();

				// System.out.println(obj.get("id"));
				
				float id = Float.parseFloat(obj.get("id").toString());

				System.out.println(id);
				t.setId((int) id);
				t.setPoints((int)Float.parseFloat(obj.get("points").toString()));
				t.setResult(obj.get("result").toString());
			//	t.setIdUser((int)Float.parseFloat(obj.get("idUser").toString()));
			//	t.setIdGame((int)Float.parseFloat(obj.get("idGame").toString()));
			//	t.setName(obj.get("name").toString());
			//	t.setCoach(obj.get("coach").toString());
			//	t.setPresident(obj.get("president").toString());
			//	t.setArea(obj.get("area").toString());
//				t.setGamesPlayed(Integer.parseInt(obj.get("gameplayed").toString()));
				/*t.setGoalScored(Integer.parseInt(obj.get("goalscored").toString()));
				t.setGoalAgainst(Integer.parseInt(obj.get("goalagainst").toString()));
				t.setParticipations(Integer.parseInt(obj.get("participations").toString()));
				t.setFifaDate(Date.valueOf(obj.get("fifadate").toString()));*/
				/*t.setWcGroup(obj.get("wcgroup").toString());
				
				t.setWin((int)Float.parseFloat(obj.get("win").toString()));
				t.setLoose((int)Float.parseFloat(obj.get("loose").toString()));
				t.setDraw((int)Float.parseFloat(obj.get("draw").toString()));
				t.setPoints((int)Float.parseFloat(obj.get("points").toString()));
				t.setFifaRank((int)Float.parseFloat(obj.get("fifarank").toString()));
				t.setFlagPhoto(obj.get("flagphoto").toString());
				t.setLogoPhoto(obj.get("logophoto").toString());
				t.setSquadPhoto(obj.get("squadphoto").toString());
				t.setDescriptionPhoto(obj.get("descriptionphoto").toString());
				t.setDescription(obj.get("description").toString());
				t.setWebsite(obj.get("website").toString());
				t.setVideo(obj.get("video").toString());*/
				
				
				
				
				System.out.println(t);
				listBets.add(t);

			}

		} catch (IOException ex) {
		}
		System.out.println(listBets);
		return listBets;
	}
		
	public void AjouterForum(Bet b) {
        ConnectionRequest con = new ConnectionRequest();


String Url = "http://127.0.0.1:8000/bet/betAddMobile?idUser="+b.getIdUser()+"&result="+b.getResult()+"&idGame="+b.getIdGame()+"";

   //     String Url = "http://localhost:8888/Animaux1/web/app_dev.php/Forum/AddForum?titre="+f.getTitre()+"&auteur="+LoggedUser.username+"&blog="+f.getBlog()+"&tags="+f.getTags()+"";

        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
	
	public ArrayList<Bet> listTeams2 = new ArrayList<>();
    
	 
    public ArrayList<Bet> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/bet/allBet");  

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceBet ser = new ServiceBet();
               // listTeams2 = ser.getListTeam(new String(con.getResponseData()));
			    listTeams2 = ser.getListBet(new String(con.getResponseData()));

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTeams2;
    }
	
}
