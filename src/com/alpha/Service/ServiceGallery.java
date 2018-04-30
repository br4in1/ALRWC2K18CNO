/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.Service;

import com.alpha.Entite.Gallery;
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

/**
 *
 * @author dell
 */
public class ServiceGallery {
	public void ajoutPhoto(Gallery g) {
        ConnectionRequest con = new ConnectionRequest();
      //  String Url = "http://localhost/alrwc2k18/web/app_dev.php/gallery/photo/ajouter/mobile/" + g.getTitre() + "/" + ev.getPrix() + "/" + ev.getDescription() + "/" + ev.getNbPlace() + "/" + ev.getType() + "/" + ev.getLieu() + "/" + ev.getDate() + "/" + ev.getImage();
     //   con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
	
	public ArrayList<Gallery> getListGallery(String json) {

		ArrayList<Gallery> listGallery = new ArrayList<Gallery>();

		try {
			JSONParser j = new JSONParser();

			Map<String, Object> G1 = j.parseJSON(new CharArrayReader(json.toCharArray()));

			List<Map<String, Object>> list = (List<Map<String, Object>>) G1.get("root");

			for (Map<String, Object> obj : list) {
				Gallery p = new Gallery();
				float id = Float.parseFloat(obj.get("id").toString());
				p.setId((int)id);
				p.setVille((String) obj.get("ville"));
				p.setLieu((String) obj.get("lieu"));
				p.setDescription("description");
				p.setImage(obj.get("image").toString());
				System.out.println(p.getImage());
				listGallery.add(p);
			}

		} catch (IOException ex) {
		}
	     	return listGallery ;
				}
				

		public ArrayList<Gallery> listGallery1 = new ArrayList<Gallery>();

	public ArrayList<Gallery> getList2() {
		ConnectionRequest con = new ConnectionRequest();
		con.setUrl("http://localhost/alrwc2k18/web/app_dev.php/gallery/photo/mobile");

		con.addResponseListener(new ActionListener<NetworkEvent>() {
			@Override
			public void actionPerformed(NetworkEvent evt) {
				ServiceGallery ser = new ServiceGallery();
								System.out.println(new String(con.getResponseData()));
				listGallery1 = ser.getListGallery(new String(con.getResponseData()));
			}
		});
		NetworkManager.getInstance().addToQueueAndWait(con);
		return listGallery1;
	}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
