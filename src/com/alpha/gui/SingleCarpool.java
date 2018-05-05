/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.gui;

import com.alpha.Entite.Covoiturage;
import com.alpha.Entite.SimpleUser;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
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
public class SingleCarpool extends BaseForm {
    public SingleCarpool(Resources res,Covoiturage cov, String imageUrl)
    { super("", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
       ImageViewer im = new ImageViewer();

        Image placeholder = Image.createImage(300, 300, 0xbfc9d2);
        EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);

        im.setImage(URLImage.createToStorage(encImage, "High" + imageUrl, imageUrl, URLImage.RESIZE_SCALE));

        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        Button image = new Button(im.getImage().fill(width, height));
        image.setUIID("Label");
        Container cncn = BorderLayout.east(image);
        cncn.setLeadComponent(image);
       
        Label InfoStade = new Label("Details : ");
        Label nom = new Label("Driver's name : "+cov.getNameUser());
        Label capacity = new Label("Language : "+cov.getLangue());
        Label city = new Label("Car : "+cov.getVoiture());
        Label From = new Label("Departure : "+cov.getDepart());
        Label To = new Label("Destination : "+cov.getDestination());
        Label Duration = new Label("Duration : "+cov.getDuree());
        Label Kilo = new Label("Distance : "+cov.getKilometrage());
        Label SeatsToken = new Label("Total Seats : "+cov.getNbPlaceTot());
        Label SeatsRest = new Label("Seats Available : "+cov.getNbPlaceRestantes());
        Label Bagage = new Label("Bagage : "+cov.getBagage());
        Label Smokin = new Label("Smoking : "+cov.getSmoking());
        InfoStade.setUIID("SideCommand");
        Button Voir = new Button("Show Location");
        Voir.setUIID("Button");
        Voir.addActionListener(e->{
        GoogleMapsTestApp x = new GoogleMapsTestApp();
       x.start(cov.getDepart(),cov.getDestination(),res,cov, imageUrl );
            System.out.println("aaaa");
        });
        cncn.add(BorderLayout.CENTER,BoxLayout.encloseY(
                 BorderLayout.center(InfoStade),nom,capacity,city,
                From,To,Duration,Kilo,SeatsRest,SeatsToken,Bagage,Smokin,
                
                BorderLayout.center(Voir)
         ));
       cncn.setUIID("InputContainerForeground");
       Container actualContent = LayeredLayout.encloseIn(cncn);
       Container input;
       input = BorderLayout.center(actualContent);
        
        input.setUIID("InputContainerBackground");
        input.addPointerPressedListener(e->{
        GoogleMapsTestApp x = new GoogleMapsTestApp();
       x.start(cov.getDepart(),cov.getDestination(),res,cov, imageUrl );
            
        });
        image.addActionListener(e->{
            if(cov.getIdUser()!=SimpleUser.current_user.getId())
            { boolean test =  Dialog.show("Do You Want to Participate ? ", "It's 100% secure!", "I am In","Show me The Route");
            if(test)
            {
                new BuyAPLaceCovoiturage(res,cov,imageUrl).show();
            }
            else{
            GoogleMapsTestApp x = new GoogleMapsTestApp();
       x.start(cov.getDepart(),cov.getDestination(),res,cov, imageUrl );
            }
        
            
        }
        }
        
        );
        add(input);}
}
