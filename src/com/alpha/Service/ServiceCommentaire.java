/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.Service;

import com.alpha.Entite.Commentaire;
import com.alpha.Entite.Likes;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;

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
	
}
