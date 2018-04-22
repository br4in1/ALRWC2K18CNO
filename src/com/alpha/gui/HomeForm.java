/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.alpha.Service.ServiceTask;
import com.alpha.Entite.Task;
import com.alpha.Service.ServiceTeam;

/**
 *
 * @author sana
 */
public class HomeForm {

    Form f;
    TextField tnom;
    TextField tetat;
    Button btnajout,btnaff , btnDisplayTeam, btnDisplayPlayer;

    public HomeForm() {
        f = new Form("home");
        tnom = new TextField();
        tetat = new TextField();
        btnajout = new Button("ajouter");
        
		btnaff=new Button("Affichage");
		btnDisplayTeam = new Button("Display Teams");
		btnDisplayPlayer = new Button ("Display Players");
		
        f.add(tnom);
        f.add(tetat);
        f.add(btnajout);
        f.add(btnaff);
		f.add(btnDisplayPlayer);
		f.add(btnDisplayTeam);
		
        btnajout.addActionListener((e) -> {
            ServiceTask ser = new ServiceTask();
            Task t = new Task(0, tnom.getText(), tetat.getText());
            //ser.ajoutTask(t);
			
			//TeamTask ser = new ServiceTeam() ;
			//TeamTask t = new Team (0,tnom.getText(),tetat.get) ;
            

        });
        btnaff.addActionListener((e)->{
        Affichage a=new Affichage();
		
        a.getF().show();
        });
		 btnDisplayTeam.addActionListener((e)->{
		DisplayTeam a = new DisplayTeam();
		
        a.getF().show();
        });
		 
		btnDisplayPlayer.addActionListener((e)->{
      
		DisplayPlayer a = new DisplayPlayer();
		
        a.getF().show();
        });
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public TextField getTnom() {
        return tnom;
    }

    public void setTnom(TextField tnom) {
        this.tnom = tnom;
    }

}
