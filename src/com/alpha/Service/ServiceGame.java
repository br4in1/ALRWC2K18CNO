/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.Service;

import com.alpha.Entite.Game;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author simo
 */
public class ServiceGame {
	
	public ArrayList<Game> getListGames(String json) throws ParseException {
		ArrayList<Game> listGames = new ArrayList<Game>();

		try {
			JSONParser j = new JSONParser();

			Map<String, Object> games = j.parseJSON(new CharArrayReader(json.toCharArray()));

			List<Map<String, Object>> list = (List<Map<String, Object>>) games.get("root");

			SimpleDateFormat format= new SimpleDateFormat("dd-MM-yyyy HH:mm");
			
			for (Map<String, Object> obj : list) {
				Game g = new Game();
				float id = Float.parseFloat(obj.get("id").toString());
				g.setId((int)id);
				Map<String,Object>homeMap=(Map<String,Object>)obj.get("hometeam");
				Map<String,Object>awayMap=(Map<String,Object>)obj.get("awayteam");
				Map<String,Object>stadiumMap=(Map<String,Object>)obj.get("stadium");
				
			//	Date date= format.parse(obj.get("date").toString());
				g.setHomeTeam(homeMap);		
				g.setAwayTeam(awayMap);
			//	g.setDate(format.parse(obj.get("date").toString()));
			//	System.out.println("date == "+g.getDate().toString());
				 g.setDate(new Date());
				g.setHighlights(obj.get("highlights").toString());
				g.setCost((int) Float.parseFloat(obj.get("cost").toString()));
				g.setReferee(obj.get("referee").toString());
				g.setResult(obj.get("result").toString());
				g.setSummary(obj.get("summary").toString());
				g.setStadium(stadiumMap);
				g.setSummaryPhoto(obj.get("summaryphoto").toString());
				listGames.add(g);
				
			}

		} catch (IOException ex) {
		}
		return listGames;

	}
	public ArrayList<Game> listGames2 = new ArrayList<Game>();

	public ArrayList<Game> getListGames() {
		ConnectionRequest con = new ConnectionRequest();
		con.setUrl("http://127.0.0.1:8000/games/allGames");

		con.addResponseListener(new ActionListener<NetworkEvent>() {
			@Override
			public void actionPerformed(NetworkEvent evt) {
				ServiceGame ser = new ServiceGame();
				try {
					listGames2 = ser.getListGames(new String(con.getResponseData()));
				} catch (ParseException ex) {
				}
				
			}
		});
		NetworkManager.getInstance().addToQueueAndWait(con);
		return listGames2;
	}
	
}
