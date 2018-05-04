/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.Service;

import com.alpha.Entite.Commentaire;
import com.alpha.Entite.Gallery;
import com.alpha.Entite.Likes;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author dell
 */
public class ServiceCommentaire {
	
	public void CommentPhoto(Commentaire C) {
		ConnectionRequest con = new ConnectionRequest();
		String Url = "http://127.0.0.1:8000/gallery/photo/comment/mobile?iduser="+C.getIdUser()+"&idphoto="+C.getIdPhoto()+"&text="+C.getText(); 
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
				listGallery.setComments((int) id);
			}

		} catch (IOException ex) {
		}
		return listGallery;
	}
	
	
	
	public Gallery listGallery1 = new Gallery();
	public Gallery getList3(int id) {
		ConnectionRequest con = new ConnectionRequest();
		con.setUrl("http://127.0.0.1:8000/gallery/photo/comments/mobile/"+id);

		con.addResponseListener(new ActionListener<NetworkEvent>() {
			@Override
			public void actionPerformed(NetworkEvent evt) {
				ServiceCommentaire ser = new ServiceCommentaire();
				listGallery1 = ser.getListGallery( new String(con.getResponseData()));
			}
		});
		NetworkManager.getInstance().addToQueueAndWait(con);
		return listGallery1;
	}

	
}
