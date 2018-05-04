/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.Entite;

import java.util.Date;

/**
 *
 * @author raiiz
 */
public class ArticleCommentaire {
    private int id;
    private int thread_id;
    private String body;
    private String ancestors;
    private int depth;
    private Date created_at;
    private int state;
    private SimpleUser author;
    
    public ArticleCommentaire(){
        
    }

    public ArticleCommentaire(int id, int thread_id, String body, String ancestors, int depth, Date created_at, int state, SimpleUser author) {
        this.id = id;
        this.thread_id = thread_id;
        this.body = body;
        this.ancestors = ancestors;
        this.depth = depth;
        this.created_at = created_at;
        this.state = state;
        this.author = author;
    }
  
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getThread_id() {
        return thread_id;
    }

    public void setThread_id(int thread_id) {
        this.thread_id = thread_id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAncestors() {
        return ancestors;
    }

    public void setAncestors(String ancestors) {
        this.ancestors = ancestors;
    }

    public int getDepth() {
        return depth;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(SimpleUser author) {
        this.author = author;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "ArticleCommentaire{" + "id=" + id + ", thread_id=" + thread_id + ", body=" + body + ", ancestors=" + ancestors + ", depth=" + depth + ", created_at=" + created_at + ", state=" + state + ", author=" + author + '}';
    }
   
}
