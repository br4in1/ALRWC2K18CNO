/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.Service;

import com.alpha.Entite.Bet;
import com.alpha.Entite.Game;
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
			//System.out.println(json);
			JSONParser j = new JSONParser();

			Map<String, Object> lBetMap = j.parseJSON(new CharArrayReader(json.toCharArray()));
			//System.out.println(lBetMap);

			List<Map<String, Object>> list = (List<Map<String, Object>>) lBetMap.get("root");

			for (Map<String, Object> obj : list) {
				Bet t = new Bet();

				// System.out.println(obj.get("id"));
				Map<Integer, Object> gameMap = (Map<Integer, Object>) obj.get("idGame");
				//Map<String,Object>awayteam=(Map<String,Object>)obj.get("awayteam");
				float id = Float.parseFloat(obj.get("id").toString());

				//System.out.println(id);
				t.setId((int) id);
				t.setPoints((int) Float.parseFloat(obj.get("points").toString()));
				t.setResult(obj.get("result").toString());
				//	t.setIdUser((int)Float.parseFloat(obj.get("idUser").toString()));
				t.setIdGame(gameMap);
				t.setIdUser((int) Float.parseFloat(obj.get("idUser").toString()));

				//System.out.println(t);
				listBets.add(t);

			}

		} catch (IOException ex) {
		}
		//System.out.println(listBets);
		return listBets;
	}

	public void AjouterForum(Bet b) {
		ConnectionRequest con = new ConnectionRequest();
		//System.out.println(b.getIdGame().get("idGame"));

		for (Integer key : b.getIdGame().keySet()) {
			//System.out.println("Key:" + key +" Value:" + b.getIdGame().get(key));// Get Key and value and count
			String Url = "http://127.0.0.1:8000/bet/betAddMobile?idUser=" + b.getIdUser() + "&result=" + b.getResult() + "&idGame=" + key + "";
			con.setUrl(Url);

			con.addResponseListener((e) -> {
				String str = new String(con.getResponseData());
				//System.out.println(str);
			});
			NetworkManager.getInstance().addToQueueAndWait(con);
		}

	}

	public ArrayList<Bet> listBets2 = new ArrayList<>();

	public ArrayList<Bet> getList2() {
		ConnectionRequest con = new ConnectionRequest();
		con.setUrl("http://127.0.0.1:8000/bet/allBet");

		con.addResponseListener(new ActionListener<NetworkEvent>() {
			@Override
			public void actionPerformed(NetworkEvent evt) {
				ServiceBet ser = new ServiceBet();
				// listTeams2 = ser.getListTeam(new String(con.getResponseData()));
				listBets2 = ser.getListBet(new String(con.getResponseData()));

			}
		});
		NetworkManager.getInstance().addToQueueAndWait(con);
		return listBets2;
	}

	public ArrayList<Game> getListGame(String json) {
		ArrayList<Game> listBets = new ArrayList<>();
		try {
			JSONParser j = new JSONParser();
			Map<String, Object> lBetMap = j.parseJSON(new CharArrayReader(json.toCharArray()));
			System.out.println(lBetMap);
			
			System.out.println(" Value:" + lBetMap.get("id"));// Get Key and value and count
			Game t = new Game();
			
			
			if(lBetMap.get("id")==null){
				t.setId(0);
				listBets.add(t);
			}
			else {
				t.setId((int) Float.parseFloat(lBetMap.get("id").toString()));
				listBets.add(t);
			}
			
		
		} catch (IOException ex) {
		}
		return listBets;
	}

	public ArrayList<Game> listBetsGame2 = new ArrayList<>();

	public ArrayList<Game> getList3(Integer id) {
		ConnectionRequest con = new ConnectionRequest();
		con.setUrl("http://127.0.0.1:8000/bet/betfindgame/"+id);

		con.addResponseListener(new ActionListener<NetworkEvent>() {
			@Override
			public void actionPerformed(NetworkEvent evt) {
				ServiceBet ser = new ServiceBet();
				listBetsGame2 = ser.getListGame(new String(con.getResponseData()));
			}
		});
		NetworkManager.getInstance().addToQueueAndWait(con);
		return listBetsGame2;
	}

}
