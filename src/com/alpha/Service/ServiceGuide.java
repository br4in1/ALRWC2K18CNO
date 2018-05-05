/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.Service;

import com.alpha.Entite.Covoiturage;
import com.alpha.Entite.Divertissement;
import com.alpha.Entite.Hotel;
import com.alpha.Entite.Passager;
import com.alpha.Entite.Stadium;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Sof
 */
public class ServiceGuide {
	
	public ArrayList<Stadium> getListStadiumss(String json) {

		ArrayList<Stadium> listProducts = new ArrayList<Stadium>();

		try {
			JSONParser j = new JSONParser();

			Map<String, Object> stadiums = j.parseJSON(new CharArrayReader(json.toCharArray()));

			List<Map<String, Object>> list = (List<Map<String, Object>>) stadiums.get("root");

			for (Map<String, Object> obj : list) {
				Stadium p = new Stadium();
				float id = Float.parseFloat(obj.get("id").toString());
				p.setId((int)id);
				p.setName(obj.get("name").toString());
				p.setCity(obj.get("city").toString());
				p.setCapacity((int)Float.parseFloat(obj.get("capacity").toString()));
				p.setGetLat((double)Float.parseFloat(obj.get("geolat").toString()));
				p.setGetLong((double)Float.parseFloat(obj.get("geolong").toString()));
				p.setPhoto(obj.get("image").toString());
				listProducts.add(p);
				
			}

		} catch (IOException ex) {
		}
		return listProducts;

	}
	public ArrayList<Stadium> listStade2 = new ArrayList<Stadium>();

	public ArrayList<Stadium> getListStade() {
		ConnectionRequest con = new ConnectionRequest();
		con.setUrl("http://127.0.0.1:8000/guide/AfficherToutStadeMobile");

		con.addResponseListener(new ActionListener<NetworkEvent>() {
			@Override
			public void actionPerformed(NetworkEvent evt) {
				ServiceGuide ser = new ServiceGuide();
				System.out.println(new String (con.getResponseData()));
				listStade2 = ser.getListStadiumss(new String(con.getResponseData()));
				
			}
		});
		NetworkManager.getInstance().addToQueueAndWait(con);
		return listStade2;
	}
	
	
	
	
	
	public ArrayList<Hotel> getListHotelss(String json) {

		ArrayList<Hotel> listProducts = new ArrayList<Hotel>();

		try {
			JSONParser j = new JSONParser();

			Map<String, Object> hotels = j.parseJSON(new CharArrayReader(json.toCharArray()));

			List<Map<String, Object>> list = (List<Map<String, Object>>) hotels.get("root");

			for (Map<String, Object> obj : list) {
				Hotel p = new Hotel();
				float id = Float.parseFloat(obj.get("id").toString());
				p.setId((int)id);
				p.setNom(obj.get("nom").toString());
				p.setCity(obj.get("city").toString());
				p.setNbEtoiles((int)Float.parseFloat(obj.get("nbetoiles").toString()));
				p.setGeolat((double)Float.parseFloat(obj.get("geolat").toString()));
				p.setGeolong((double)Float.parseFloat(obj.get("geolong").toString()));
				p.setImage(obj.get("image").toString());
				p.setLink(obj.get("tripadvisorlink").toString());
				
				listProducts.add(p);
				
			}

		} catch (IOException ex) {
		}
		return listProducts;

	}
	public ArrayList<Hotel> listhotel2 = new ArrayList<Hotel>();

	public ArrayList<Hotel> getListHotel() {
		ConnectionRequest con = new ConnectionRequest();
		con.setUrl("http://127.0.0.1:8000/guide/AfficherToutHotelMobile");

		con.addResponseListener(new ActionListener<NetworkEvent>() {
			@Override
			public void actionPerformed(NetworkEvent evt) {
				ServiceGuide ser = new ServiceGuide();
				System.out.println(new String (con.getResponseData()));
				listhotel2 = ser.getListHotelss(new String(con.getResponseData()));
				
			}
		});
		NetworkManager.getInstance().addToQueueAndWait(con);
		return listhotel2;
	}
        
        
        
        
        
        public ArrayList<Covoiturage> getListCarpooling(String json) {

		ArrayList<Covoiturage> listProducts = new ArrayList<Covoiturage>();

		try {
			JSONParser j = new JSONParser();

			Map<String, Object> covoiturages = j.parseJSON(new CharArrayReader(json.toCharArray()));

			List<Map<String, Object>> list = (List<Map<String, Object>>) covoiturages.get("root");

			for (Map<String, Object> obj : list) {
				Covoiturage p = new Covoiturage();
				float id = Float.parseFloat(obj.get("id").toString());
				p.setId((int)id);
				p.setBagage(obj.get("bagage").toString());
				p.setCouleur(obj.get("couleur").toString());
                                Map<String, Object> dateDepart = (Map<String, Object>) obj.get("dateDepart");
                                String d = dateDepart.get("timestamp").toString();
                                float ts = Float.parseFloat(d);
                                Date dat = new Date((long) ts *1000);
                                
				p.setDateDepart(dat);
				p.setDepart(obj.get("depart").toString());
				p.setDestination(obj.get("destination").toString());
				p.setDuree(obj.get("duree").toString());
                                float id_user = Float.parseFloat(obj.get("idUser").toString());
				p.setIdUser((int) id_user);
                                  float kilo = Float.parseFloat(obj.get("idUser").toString());
				
                                p.setKilometrage((int)kilo);
				p.setLangue(obj.get("langage").toString());
				p.setNameUser(obj.get("nameUser").toString());
                                 float placetot = Float.parseFloat(obj.get("nbPlaceTot").toString());
				 float placerest = Float.parseFloat(obj.get("nbPlaceRestantes").toString());
				
				p.setNbPlaceRestantes((int)placerest);
				p.setNbPlaceTot((int)placetot);
                                 float numcomte = Float.parseFloat(obj.get("numCompteBancaire").toString());
				
				p.setNumCompteBancaire((int)numcomte);
				 float prixPlace = Float.parseFloat(obj.get("prixPlace").toString());
				
                                p.setPrixPlace((int)prixPlace);
                                
				p.setSmoking(obj.get("smoking").toString());
				p.setVoiture(obj.get("voiture").toString());
				
				listProducts.add(p);
				
			}

		} catch (IOException ex) {
		}
		return listProducts;

	}
	public ArrayList<Covoiturage> listCarpooling2 = new ArrayList<Covoiturage>();

	public ArrayList<Covoiturage> getListCarpooling() {
		ConnectionRequest con = new ConnectionRequest();
		con.setUrl("http://127.0.0.1:8000/guide/AfficherToutCarpooling");

		con.addResponseListener(new ActionListener<NetworkEvent>() {
			@Override
			public void actionPerformed(NetworkEvent evt) {
				ServiceGuide ser = new ServiceGuide();
				System.out.println(new String (con.getResponseData()));
				listCarpooling2 = ser.getListCarpooling(new String(con.getResponseData()));
				
			}
		});
		NetworkManager.getInstance().addToQueueAndWait(con);
		return listCarpooling2;
	}
        
        
        
        
        
        
        public ArrayList<Passager> getListPassager(String json) {

		ArrayList<Passager> listProducts = new ArrayList<Passager>();

		try {
			JSONParser j = new JSONParser();

			Map<String, Object> passagers = j.parseJSON(new CharArrayReader(json.toCharArray()));

			List<Map<String, Object>> list = (List<Map<String, Object>>) passagers.get("root");

			for (Map<String, Object> obj : list) {
				Passager p = new Passager();
				float id = Float.parseFloat(obj.get("id").toString());
                                float idConv = Float.parseFloat(obj.get("idConv").toString());
                                float PLace = Float.parseFloat(obj.get("nbPlace").toString());
                                float idUser = Float.parseFloat(obj.get("iduser").toString());
                              
				p.setId((int)id);
				p.setIdUser((int)idUser);
				p.setIdConv((int)idConv);
				
				p.setNameUser(obj.get("nameuser").toString());
				p.setNbPlace((int)PLace);
				
				listProducts.add(p);
				
			}

		} catch (IOException ex) {
		}
		return listProducts;

	}
	public ArrayList<Passager> listPassager2 = new ArrayList<Passager>();

	public ArrayList<Passager> getListPassager() {
		ConnectionRequest con = new ConnectionRequest();
		con.setUrl("http://127.0.0.1:8000/guide/AfficherTousPasagerMobile");

		con.addResponseListener(new ActionListener<NetworkEvent>() {
			@Override
			public void actionPerformed(NetworkEvent evt) {
				ServiceGuide ser = new ServiceGuide();
				System.out.println(new String (con.getResponseData()));
				listPassager2 = ser.getListPassager(new String(con.getResponseData()));
				
			}
		});
		NetworkManager.getInstance().addToQueueAndWait(con);
		return listPassager2;
	}
        
        public void ajoutPassager(Passager g) {
		ConnectionRequest con = new ConnectionRequest();
		String Url = "http://127.0.0.1:8000/guide/approuvermobile/"+g.getIdConv()+"/"+g.getIdUser()+"/"+g.getNbPlace()+"/Tmar"; 
		
		con.setUrl(Url);
		con.addResponseListener((e) -> {
			String str = new String(con.getResponseData());
			System.out.println(str);
		});
		NetworkManager.getInstance().addToQueueAndWait(con);
	}
	
	



	
}
