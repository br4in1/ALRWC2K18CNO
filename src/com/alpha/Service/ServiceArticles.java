/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.Service;

import com.alpha.Entite.Article;
import com.alpha.Entite.User;
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
import java.util.TimeZone;

/**
 *
 * @author raiiz
 */
public class ServiceArticles {

    public ArrayList<Article> getListProducts(String json) {

        ArrayList<Article> listArticles = new ArrayList<Article>();

        try {
            JSONParser j = new JSONParser();

            Map<String, Object> articles = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) articles.get("root");

            for (Map<String, Object> obj : list) {
                Article p = new Article();
                float id = Float.parseFloat(obj.get("id").toString());
                p.setId((int) id);
                Map< String, Object> auteurMap = (Map< String, Object>) obj.get("auteur");
                User u = new User(auteurMap.get("username").toString(), auteurMap.get("email").toString(), true, (auteurMap.get("salt") == null) ? "null" : "je sai pas", auteurMap.get("password").toString(), TimeZone.getTimeZone("Europe/Berlin"), auteurMap.get("roles").toString(), auteurMap.get("firstname").toString(), auteurMap.get("lastname").toString());
                p.setAuteur(u);

                p.setArticleImage(obj.get("articleimage").toString());
                p.setTitre(obj.get("titre").toString());
                p.setId(1);
                //  p.setId(Integer.parseInt(obj.get("id").toString().trim()));
                //p.setNum_comments((Integer.parseInt(obj.get("numComments").toString()) == 0) ? 0 : Integer.parseInt(obj.get("numComments").toString()));
                p.setNum_comments(2);

                //trim()
                listArticles.add(p);
            }

        } catch (IOException ex) {
        }
        return listArticles;
    }
    public ArrayList<Article> listArticles2 = new ArrayList<Article>();

    public ArrayList<Article> getList2() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/russie2k18/web/app_dev.php/articles/mobile/all");

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceArticles ser = new ServiceArticles();
                listArticles2 = ser.getListProducts(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listArticles2;
    }
}
