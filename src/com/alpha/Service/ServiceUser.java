/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.Service;

import com.alpha.Entite.Product;
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
import org.json.simple.JSONObject;
import com.alpha.Entite.SimpleUser;
import com.google.gson.Gson;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Moez
 */
public class ServiceUser {

	public SimpleUser getUserEntity(String json) {
		SimpleUser u = null;
		try {
			JSONParser j = new JSONParser();

			Map<String, Object> obj = j.parseJSON(new CharArrayReader(json.toCharArray()));
			if (obj.size() == 0) {
				return null;
			}
			u = new SimpleUser();
			float id = Float.parseFloat(obj.get("id").toString());
			u.setId((int) id);
			u.setUsername(obj.get("username").toString());
			u.setFidaelitypoints((int) Float.parseFloat(obj.get("fidelityPoints").toString()));
			u.setEmail(obj.get("email").toString());
			u.setNationality(obj.get("nationality").toString());
			u.setProfilepicture("" + obj.get("profilePicture"));
			
		} catch (IOException ex) {
		}
		return u;
	}

	public SimpleUser U;
	public String ans;

	public SimpleUser CheckLoginData(String username, String password) {
		ConnectionRequest con = new ConnectionRequest();
		con.setUrl("http://127.0.0.1:8000/api/user/checklogindata/username/" + username + "/password/" + password);
		System.out.println(con.getUrl());

		con.addResponseListener(new ActionListener<NetworkEvent>() {
			@Override
			public void actionPerformed(NetworkEvent evt) {
				ServiceUser ser = new ServiceUser();
				U = ser.getUserEntity(new String(con.getResponseData()));
			}
		});
		NetworkManager.getInstance().addToQueueAndWait(con);
		return U;
	}

	public SimpleUser getUserData(int user_id) {
		ConnectionRequest con = new ConnectionRequest();
		con.setUrl("http://127.0.0.1:8000/api/users/" + user_id);

		con.addResponseListener(new ActionListener<NetworkEvent>() {
			@Override
			public void actionPerformed(NetworkEvent evt) {
				ServiceUser ser = new ServiceUser();
				U = ser.getUserEntity(new String(con.getResponseData()));
			}
		});
		NetworkManager.getInstance().addToQueueAndWait(con);
		return U;
	}
	
	public void LoginUserWithFacebook(SimpleUser u){
		Gson gson = new Gson();
		String userJsonString = gson.toJson(u);
		ConnectionRequest con = new ConnectionRequest();
		con.setUrl("http://127.0.0.1:8000/api/users/facebookuserlogin");
		con.setPost(true);
		con.addArgument("u", userJsonString);
		con.addResponseListener(new ActionListener<NetworkEvent>() {
			@Override
			public void actionPerformed(NetworkEvent evt) {
				
			}
		});
		NetworkManager.getInstance().addToQueueAndWait(con);
	}
	
	public String SignUpUser(SimpleUser u){
		ans = "OK";
		Gson gson = new Gson();
		String userJsonString = gson.toJson(u);
		ConnectionRequest con = new ConnectionRequest();
		con.setUrl("http://127.0.0.1:8000/api/users/signup");
		con.setPost(true);
		con.addArgument("u", userJsonString);
		con.addResponseListener(new ActionListener<NetworkEvent>() {
			@Override
			public void actionPerformed(NetworkEvent evt) {
				JSONParser j = new JSONParser();
				try {
					Map<String, Object> obj = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
					System.out.println(obj.get("success").toString());
					if(obj.get("success").toString().equals("false")){
						ans = obj.get("reason").toString();
					}
				} catch (IOException ex) {
				}
			}
		});
		NetworkManager.getInstance().addToQueueAndWait(con);
		System.out.println("ans == "+ans);
		return ans;
	}

}
