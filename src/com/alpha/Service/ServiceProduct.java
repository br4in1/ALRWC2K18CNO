/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.Service;

import com.alpha.Entite.Category;
import com.alpha.Entite.Player;
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

/**
 *
 * @author Moez
 */
public class ServiceProduct {

	public ArrayList<Product> getListProducts(String json) {

		ArrayList<Product> listProducts = new ArrayList<Product>();

		try {
			JSONParser j = new JSONParser();

			Map<String, Object> products = j.parseJSON(new CharArrayReader(json.toCharArray()));

			List<Map<String, Object>> list = (List<Map<String, Object>>) products.get("root");

			for (Map<String, Object> obj : list) {
				Product p = new Product();
				float id = Float.parseFloat(obj.get("id").toString());
				p.setId((int)id);
				p.setLabel(obj.get("label").toString());
				p.setPrice(Float.parseFloat(obj.get("price").toString()));
				p.setRemaining((int)Float.parseFloat(obj.get("remaining").toString()));
				p.setPic1(""+obj.get("pic1"));
				listProducts.add(p);
			}

		} catch (IOException ex) {
		}
		return listProducts;

	}
	public ArrayList<Product> listProducts2 = new ArrayList<Product>();

	public ArrayList<Product> getList2() {
		ConnectionRequest con = new ConnectionRequest();
		con.setUrl("http://127.0.0.1:8000/api/products/all");

		con.addResponseListener(new ActionListener<NetworkEvent>() {
			@Override
			public void actionPerformed(NetworkEvent evt) {
				ServiceProduct ser = new ServiceProduct();
				listProducts2 = ser.getListProducts(new String(con.getResponseData()));
			}
		});
		NetworkManager.getInstance().addToQueueAndWait(con);
		return listProducts2;
	}

}
