/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Form;
import com.alpha.Service.ServiceTask;
import com.alpha.Service.ServiceTeam;

/**
 *
 * @author sana
 */
public class Affichage {

    Form f;
    SpanLabel lb;
  
    public Affichage() {
        
        f = new Form();
        lb = new SpanLabel("");
        f.add(lb);
        ServiceTask serviceTask=new ServiceTask();
		
		//TeamTask teamtask = new ServiceTeam() ; 
		//lb.setText(ServiceTeam.getList2().toString());

		
        lb.setText(serviceTask.getList2().toString());
        /*/
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://41.226.11.243:10004/tasks/");
        NetworkManager.getInstance().addToQueue(con);
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceTask ser = new ServiceTask();
                List<Task> list = ser.getListTask(new String(con.getResponseData()));
                System.out.println("sana");
                System.out.println(list);
                lb.setText(list.toString());
               
                System.out.println(lb.getText());
                f.refreshTheme();
            }
        });
        //*/
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
