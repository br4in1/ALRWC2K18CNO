/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.Service;

import com.alpha.Entite.Gallery;
import com.alpha.Entite.Likes;
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
 * @author dell
 */
public class ServiceLikes {
	
		public void LikedPhoto(Likes l) {
		ConnectionRequest con = new ConnectionRequest();
		String Url = "http://127.0.0.1:8000/gallery/photo/like/mobile?iduser="+l.getIdUser()+"&idphoto="+l.getIdPhoto(); 
		con.setUrl(Url);
		con.addResponseListener((e) -> {
			String str = new String(con.getResponseData());
			System.out.println(str);
		});
		NetworkManager.getInstance().addToQueueAndWait(con);
	}
		public void DislikedPhoto(Likes l) {
		ConnectionRequest con = new ConnectionRequest();
		String Url = "http://127.0.0.1:8000/gallery/photo/dislike/mobile?iduser="+l.getIdUser()+"&idphoto="+l.getIdPhoto(); 
		con.setUrl(Url);
		con.addResponseListener((e) -> {
			String str = new String(con.getResponseData());
			System.out.println(str);
		});
		NetworkManager.getInstance().addToQueueAndWait(con);
	}
	
	public void likesPhoto(int id) {
		ConnectionRequest con = new ConnectionRequest();
		String Url = "http://127.0.0.1:8000/gallery/photo/comment/mobile/="+id; 
		con.setUrl(Url);
		con.addResponseListener((e) -> {
			String str = new String(con.getResponseData());
			System.out.println(str);
		});
		NetworkManager.getInstance().addToQueueAndWait(con);
	}
	
	
	
	
	public Gallery getListGallery(String json) {

		Gallery listGallery = new Gallery();


		try {
			JSONParser j = new JSONParser();

			Map<String, Object> G1 = j.parseJSON(new CharArrayReader(json.toCharArray()));

			List<Map<String, Object>> list = (List<Map<String, Object>>) G1.get("root");

			for (Map<String, Object> obj : list) {
				Gallery p = new Gallery();
				float id = Float.parseFloat(obj.get("nb").toString());
				listGallery.setLikes((int) id);
			}

		} catch (IOException ex) {
		}
		return listGallery;
	}
	
	public Gallery listGallery1 = new Gallery();
	public Gallery getList2(int id) {
		ConnectionRequest con = new ConnectionRequest();
		con.setUrl("http://127.0.0.1:8000/gallery/photo/likee/mobile/"+id);

		con.addResponseListener(new ActionListener<NetworkEvent>() {
			@Override
			public void actionPerformed(NetworkEvent evt) {
				ServiceLikes ser = new ServiceLikes();
				listGallery1 = ser.getListGallery( new String(con.getResponseData()));
			}
		});
		NetworkManager.getInstance().addToQueueAndWait(con);
		return listGallery1;
	}

	
	
}
