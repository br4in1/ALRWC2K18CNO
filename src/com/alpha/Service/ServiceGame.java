/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.Service;

import com.alpha.Entite.Card;
import com.alpha.Entite.Game;
import com.alpha.Entite.Goal;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
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
	
	public ArrayList<Game> listGames2 = new ArrayList<Game>();
	public ArrayList<Card> listCards2 = new ArrayList<Card>();
	public ArrayList<Goal> listGoals2 = new ArrayList<Goal>();
	
	public ArrayList<Card> getCardsFromDb(int game_id){
		ConnectionRequest con = new ConnectionRequest();
		con.setUrl("http://127.0.0.1:8000/games/allGames");

		con.addResponseListener(new ActionListener<NetworkEvent>() {
			@Override
			public void actionPerformed(NetworkEvent evt) {
				ServiceGame ser = new ServiceGame();
			}
		});
		NetworkManager.getInstance().addToQueueAndWait(con);
		return listCards2;
	}
	
	public ArrayList<Goal> getGoalsFromDb(int game_id){
		ConnectionRequest con = new ConnectionRequest();
		con.setUrl("http://127.0.0.1:8000/games/allGames");

		con.addResponseListener(new ActionListener<NetworkEvent>() {
			@Override
			public void actionPerformed(NetworkEvent evt) {
				ServiceGame ser = new ServiceGame();
			}
		});
		NetworkManager.getInstance().addToQueueAndWait(con);
		return listGoals2;
	}
	
	public ArrayList<Game> getListGames(String json) throws ParseException {
		ArrayList<Game> listGames = new ArrayList<Game>();

		try {
			JSONParser j = new JSONParser();

			Map<String, Object> games = j.parseJSON(new CharArrayReader(json.toCharArray()));

			List<Map<String, Object>> list = (List<Map<String, Object>>) games.get("root");
			
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
				DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
				Date date = format.parse(obj.get("date").toString());
				g.setDate(date);
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
	
	//find goals by id game 
	
		public ArrayList<Goal> getListGoals(String json) {

		ArrayList<Goal> listGoals = new ArrayList<>();

		try {
			JSONParser j = new JSONParser();

			Map<String, Object> GoalMap = j.parseJSON(new CharArrayReader(json.toCharArray()));

			List<Map<String, Object>> list = (List<Map<String, Object>>) GoalMap.get("root");

			for (Map<String, Object> obj : list) {
				Goal g = new Goal();

				float id = Float.parseFloat(obj.get("id").toString());

				g.setId((int) id);
				g.setMinute((int)Float.parseFloat(obj.get("minute").toString()));
				
				listGoals.add(g);

			}

		} catch (IOException ex) {
		}
		return listGoals;
	}
	
	//
	public ArrayList<Goal> listGoals = new ArrayList<>();
	public ArrayList<Goal> getListgoalsByGame(Integer id) {
		ConnectionRequest con = new ConnectionRequest();
		con.setUrl("http://127.0.0.1:8000/games/findgoal/"+id);

		con.addResponseListener(new ActionListener<NetworkEvent>() {
			@Override
			public void actionPerformed(NetworkEvent evt) {
				ServiceGame ser = new ServiceGame();
				listGoals = ser.getListGoals(new String(con.getResponseData()));
			}
		});
		NetworkManager.getInstance().addToQueueAndWait(con);
		return listGoals;
	}
	
	
		//find cards by id game 
	
		public ArrayList<Card> getListCards(String json) {

		ArrayList<Card> listCards = new ArrayList<>();

		try {
			JSONParser j = new JSONParser();

			Map<String, Object> CardMap = j.parseJSON(new CharArrayReader(json.toCharArray()));

			List<Map<String, Object>> list = (List<Map<String, Object>>) CardMap.get("root");

			for (Map<String, Object> obj : list) {
				Card c = new Card();

				float id = Float.parseFloat(obj.get("id").toString());

				c.setId((int) id);
				c.setYellow((int)Float.parseFloat(obj.get("yellow").toString()));
				c.setRed((int)Float.parseFloat(obj.get("red").toString()));
				c.setMinute((int)Float.parseFloat(obj.get("minute").toString()));
				
				listCards.add(c);

			}

		} catch (IOException ex) {
		}
		return listCards;
	}
	
	

	public ArrayList<Card> listCards = new ArrayList<>();
	public ArrayList<Card> getListCardsByGame(Integer id) {
		ConnectionRequest con = new ConnectionRequest();
		con.setUrl("http://127.0.0.1:8000/games/findcard/"+id);

		con.addResponseListener(new ActionListener<NetworkEvent>() {
			@Override
			public void actionPerformed(NetworkEvent evt) {
				ServiceGame ser = new ServiceGame();
				listCards = ser.getListCards(new String(con.getResponseData()));
			}
		});
		NetworkManager.getInstance().addToQueueAndWait(con);
		return listCards;
	}
	public void bookTicket(int idGame,int idUser) {
		ConnectionRequest con = new ConnectionRequest();
		//System.out.println(b.getIdGame().get("idGame"));
			//System.out.println("Key:" + key +" Value:" + b.getIdGame().get(key));// Get Key and value and count
			String Url = "http://127.0.0.1:8000/games/ticket/" +idGame+"/" + idUser;
		
			con.setUrl(Url);

			con.addResponseListener((e) -> {
				String str = new String(con.getResponseData());
				//System.out.println(str);
			});
			NetworkManager.getInstance().addToQueueAndWait(con);
		

	}
	
}
