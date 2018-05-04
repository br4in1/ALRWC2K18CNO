/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.Service;

import com.alpha.Entite.Likes;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;

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
	
	
}
