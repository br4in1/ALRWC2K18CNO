/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.gui;

import com.alpha.Service.ServiceTeam;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Form;

/**
 *
 * @author Moez
 */
public class DisplayTeam {
	
	
    Form f;
    SpanLabel lb;
  
    public DisplayTeam() {
        
        f = new Form();
        lb = new SpanLabel("");
        f.add(lb);
		
		ServiceTeam teamtask = new ServiceTeam() ; 
		lb.setText(teamtask.getList2().toString());

          f.getToolbar().addCommandToRightBar("back", null, (ev)->{HomeForm h=new HomeForm();
          h.getF().show();
          });
    }
	
	public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
	
}
