/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.Service;

import com.alpha.Entite.ArticleCommentaire;
import com.alpha.Entite.Commentaire;
import com.alpha.Entite.Gallery;
import com.alpha.Entite.Likes;
import com.alpha.Entite.SimpleUser;
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
	
	
	
	
	
	
public ArrayList<Commentaire> getListCommentaire(String json) {

		ArrayList<Commentaire> listCom = new ArrayList<Commentaire>();

		try {
			JSONParser j = new JSONParser();

			Map<String, Object> G1 = j.parseJSON(new CharArrayReader(json.toCharArray()));

			List<Map<String, Object>> list = (List<Map<String, Object>>) G1.get("root");

			for (Map<String, Object> obj : list) {
				Commentaire p = new Commentaire();
				float id = Float.parseFloat(obj.get("id").toString());
				p.setId((int) id);
				p.setText((String) obj.get("text"));
				listCom.add(p);
			}

		} catch (IOException ex) {
		}
		return listCom;
	}

	public ArrayList<Commentaire> listComm = new ArrayList<Commentaire>();

	public ArrayList<Commentaire> getList4(int id ) {
		ConnectionRequest con = new ConnectionRequest();
		con.setUrl("http://127.0.0.1:8000/gallery/photo/comment/"+id);

		con.addResponseListener(new ActionListener<NetworkEvent>() {
			@Override
			public void actionPerformed(NetworkEvent evt) {
				ServiceCommentaire ser = new ServiceCommentaire();
				listComm = ser.getListCommentaire(new String(con.getResponseData()));
			}
		});
		NetworkManager.getInstance().addToQueueAndWait(con);
		return listComm;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	  public ArrayList<Commentaire> getCommentaires(int id) {
        ArrayList<Commentaire> commentaires = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/gallery/photo/comment/" + id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser j = new JSONParser();
                String json = new String(con.getResponseData());
                try {
                    Map<String, Object> all = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    List<Map<String, Object>> coms = (List<Map<String, Object>>) all.get("root");
                    for (Map<String, Object> obj : coms) {
                        Commentaire ac = new Commentaire();
                        ac.setId((int) Float.parseFloat(obj.get("id").toString()));
                        ac.setText(obj.get("com").toString());
						ServiceUser su = new ServiceUser();
                        Map< String, Object> user = (Map< String, Object>) obj.get("iduser");
                        SimpleUser author = su.getUserData((int) Float.parseFloat(user.get("id").toString()));
                        ac.setAuthor(author);
                        commentaires.add(ac);
                    }
                } catch (IOException ex) {
                    System.out.println("error sql");
                }                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return commentaires;
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
}
