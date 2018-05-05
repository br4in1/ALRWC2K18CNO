/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.gui;

import com.alpha.Entite.Covoiturage;
import com.alpha.Entite.Passager;
import com.alpha.Entite.SimpleUser;
import com.alpha.Service.ServiceGuide;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Sof
 */
public class BuyAPLaceCovoiturage extends BaseForm {
    String lastValidInput = "0"  ;  String lastValidInput2 = "0"  ; 
    public BuyAPLaceCovoiturage(Resources res, Covoiturage cov , String imageUrl )
             { super("", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
      
        Container cncn = new Container();
      
       
        Label InfoStade = new Label("Details : ");
        Label nom = new Label("Driver's name : "+cov.getNameUser());
        
        Label From = new Label("Departure : "+cov.getDepart());
        Label To = new Label("Destination : "+cov.getDestination());
    
        Label NumCompte = new Label("Account Number : ");
        Label Password = new Label("Password : ");
     TextField AccountNumber = new TextField();
                 TextField PasswordCompte = new TextField();
         Label SeatsRest = new Label("Seats Available : "+cov.getNbPlaceRestantes());
        Label Seats_booked = new Label("Seats booked : ");
        Label Price = new Label("Price : ");
                 TextField Prix = new TextField();
                 TextField myTextField = new TextField();
        InfoStade.setUIID("SideCommand");
        Button Voir = new Button("Buy");
        Voir.setUIID("Button");
        Voir.addActionListener(e->{
            ServiceGuide ser = new ServiceGuide();
            System.out.println(SimpleUser.current_user.getLastname()+SimpleUser.current_user.getFirstname());
            Passager g = new Passager(2, SimpleUser.current_user.getId(), SimpleUser.current_user.getLastname(),cov.getId() , Integer.parseInt(myTextField.getText()));
            ser.ajoutPassager(g);
            
            });
       myTextField.addDataChangedListener((i, ii) -> {
    if(isValidInput(myTextField.getText())){
        lastValidInput = myTextField.getText();
        if(!lastValidInput.equals(""))
        {if(Integer.parseInt(lastValidInput)>cov.getNbPlaceRestantes())
        {
            lastValidInput="0";
            myTextField.setText(lastValidInput);
        }
        else {
            Prix.setText("0");
             Prix.setText(""+Integer.parseInt(lastValidInput)*cov.getNbPlaceRestantes());
        }}
    } else {
       myTextField.stopEditing();
       myTextField.setText(lastValidInput);
       myTextField.startEditingAsync();
    }
});
       AccountNumber.addDataChangedListener((i, ii) -> {
    if(lastValidInput2.length()<4)
    {  if(isValidInput(AccountNumber.getText())){
        
        lastValidInput2 = AccountNumber.getText();
        
    }
    else {
       AccountNumber.stopEditing();
       AccountNumber.setText(lastValidInput2);
       AccountNumber.startEditingAsync();
    }
    }
    else{
    Dialog.show("Warnig", "Account Number is too long", "ok","ok");
    lastValidInput2="0";
       AccountNumber.setText(lastValidInput2);
    }
    
});
        cncn.add(BoxLayout.encloseY(
                 BorderLayout.center(InfoStade),nom,
                From,To,SeatsRest,
                BoxLayout.encloseX(Seats_booked,myTextField),
                BoxLayout.encloseX(Price,Prix),
                  BoxLayout.encloseX(NumCompte,AccountNumber),
                  BoxLayout.encloseX(Password,PasswordCompte),
                
                BorderLayout.center(Voir)
         ));
       cncn.setUIID("InputContainerForeground");
       Container actualContent = LayeredLayout.encloseIn(cncn);
       Container input;
       input = BorderLayout.center(actualContent);
        
        input.setUIID("InputContainerBackground");
       
        
        add(input);}

    private boolean isValidInput(String text) {
     StringBuilder sb = new StringBuilder();
    boolean found = true;
    for(char c : text.toCharArray()){
        if(!Character.isDigit(c)){
            sb.append(c);
            found = false;
            break ; 
        } else if(!found){
            // If we already found a digit before and this char is not a digit, stop looping
            break;                
        }
    } 
    return found ; 
    }
}
