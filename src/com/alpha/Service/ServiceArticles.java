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
                // User u = new User(auteurMap.get("username").toString(), auteurMap.get("email").toString(), true, (auteurMap.get("salt") == null) ? "null" : "je sais pas", auteurMap.get("password").toString(), TimeZone.getTimeZone("Europe/Berlin"), auteurMap.get("roles").toString(), auteurMap.get("firstname").toString(), auteurMap.get("lastname").toString());
                p.setArticleImage(obj.get("articleimage").toString());
                p.setTitre(obj.get("titre").toString());
                float numComments = Float.parseFloat(obj.get("numComments").toString());
                p.setId((int) id);
                p.setNum_comments((int) numComments);
                p.setContenu(obj.get("contenu").toString());
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

    public Article getArticle(int id) {
        // /articles/mobile/get/{id}
        Article article = new Article();
        ConnectionRequest con = new ConnectionRequest();
        String url = "http://localhost/russie2k18/web/app_dev.php/articles/mobile/get/" + id;
        con.setUrl(url);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser j = new JSONParser();
                String json = new String(con.getResponseData());
                try {
                    Map<String, Object> art = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    article.setArticleImage(art.get("articleimage").toString());
                    article.setTitre(art.get("titre").toString());
                    float numComments = Float.parseFloat(art.get("numComments").toString());
                    article.setId((int) id);
                    article.setNum_comments((int) numComments);
                    article.setContenu(art.get("contenu").toString());
                } catch (IOException ex) {
                    System.out.println("error sql");
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return article;

    }
}
