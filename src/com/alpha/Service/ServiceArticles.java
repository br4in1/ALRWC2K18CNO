/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.Service;

import com.alpha.Entite.Article;
import com.alpha.Entite.ArticleCommentaire;
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
        con.setUrl("http://127.0.0.1:8000/articles/mobile/all");
        
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
        Article article = new Article();
        ConnectionRequest con = new ConnectionRequest();
        String url = "http://127.0.0.1:8000/articles/mobile/get/" + id;
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
                    Map< String, Object> auteur = (Map< String, Object>) art.get("auteur");
                    ServiceUser su = new ServiceUser();
                    float id_user = Float.parseFloat(auteur.get("id").toString());
                    SimpleUser simpleU = su.getUserData((int) id_user);
                    article.setAuteur(simpleU);
                    Map< String, Object> dateCreated = (Map< String, Object>) art.get("datepublication");
                    String dat = dateCreated.get("timestamp").toString();
                    float ts = Float.parseFloat(dat);
                    Date d = new Date((long) ts * 1000);
                    article.setDatePublication(d);
                } catch (IOException ex) {
                    System.out.println("error sql");
                }
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return article;
    }
    
    public ArrayList<ArticleCommentaire> getCommentaires(int id) {
        ArrayList<ArticleCommentaire> commentaires = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/commentaires/mobile/get/" + id);
        
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser j = new JSONParser();
                String json = new String(con.getResponseData());
                try {
                    Map<String, Object> all = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    List<Map<String, Object>> coms = (List<Map<String, Object>>) all.get("root");
                    for (Map<String, Object> obj : coms) {
                        ArticleCommentaire ac = new ArticleCommentaire();
                        ac.setId((int) Float.parseFloat(obj.get("id").toString()));
                        ac.setBody(obj.get("body").toString());
                        ac.setThread_id(id);
                        ServiceUser su = new ServiceUser();
                        Map< String, Object> user = (Map< String, Object>) obj.get("author");
                        SimpleUser author = su.getUserData((int) Float.parseFloat(user.get("id").toString()));
                        ac.setAuthor(author);
                        Map< String, Object> creat = (Map< String, Object>) obj.get("createdAt");
                        String dat = creat.get("timestamp").toString();
                        float ts = Float.parseFloat(dat);
                        Date d = new Date((long) ts * 1000);
                        ac.setCreated_at(d);
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
    
    public void addComment(int idarticle,ArticleCommentaire a){
        ConnectionRequest con = new ConnectionRequest();
        //con.setUrl("http://127.0.0.1:8000/commentaires/mobile/get/" + id);
    }
    
}
