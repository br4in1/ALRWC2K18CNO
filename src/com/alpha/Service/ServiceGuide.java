/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.Service;

import com.alpha.Entite.Hotel;
import com.alpha.Entite.Stadium;
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
		con.setUrl("http://localhost/alrwc2k18/web/app_dev.php/guide/AfficherToutStadeMobile");

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
		con.setUrl("http://localhost/alrwc2k18/web/app_dev.php/guide/AfficherToutHotelMobile");

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
	
}
