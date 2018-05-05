/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.gui;

import com.alpha.Entite.Hotel;
import com.alpha.Entite.Stadium;
import com.alpha.Service.ServiceGuide;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;

/**
 *
 * @author Sof
 */
public class GuideAffichage extends BaseForm {
	Resources res;
	Container CntStades = new Container(); 
	 public GuideAffichage(Resources res) {
		 super("", BoxLayout.y());
		 this.res = res ; 
        
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        
        addTab(swipe, res.getImage("news-item.jpg"), spacer1, "15 Likes  ", "85 Comments", " ");
                
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        
        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });
        
        Component.setSameSize(radioContainer, spacer1);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        
        ButtonGroup barGroup = new ButtonGroup();
        RadioButton all = RadioButton.createToggle("All", barGroup);
		all.setName("all");
        all.setUIID("SelectBar");
        RadioButton featured = RadioButton.createToggle("Stadiums", barGroup);
		featured.setName("Stadiums");
        featured.setUIID("SelectBar");
        RadioButton popular = RadioButton.createToggle("Hotels", barGroup);
		popular.setName("Hotels");
        popular.setUIID("SelectBar");
        RadioButton myFavorite = RadioButton.createToggle("Restos", barGroup);
		myFavorite.setName("Restos");
        myFavorite.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(4, all, featured, popular, myFavorite),
                FlowLayout.encloseBottom(arrow)
        ));
        
        all.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(all, arrow);
        });
        bindButtonSelection(all, arrow);
        bindButtonSelection(featured, arrow);
        bindButtonSelection(popular, arrow);
        bindButtonSelection(myFavorite, arrow);
        
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
		
		
        
          ServiceGuide ser= new  ServiceGuide();
		 ArrayList<Stadium> Tab =  ser.getListStade();
	 
		 for(int i = 0 ; i < Tab.size() ; i++)
			  {
		 addButtonStade(Tab.get(i).getPhoto(), Tab.get(i).getName(), false, 11, 9,Tab.get(i).getId(),Tab.get(i));
		
		}
		 add(CntStades);
	 
	 }
    
    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
        
        
    }
    
    private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
        
         if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");
        
        Container page1 = 
            LayeredLayout.encloseIn(
                image,
                overlay,
                BorderLayout.south(
                    BoxLayout.encloseY(
                            new SpanLabel(text, "LargeWhiteText"),
                            spacer
                        )
                )
            );

        swipe.addTab("", page1);
    }
 private void addButtonStade(String imageUrl, String title, boolean liked, int likeCount, int commentCount, int id , Stadium stade) {

        ImageViewer im = new ImageViewer();

        Image placeholder = Image.createImage(300, 300, 0xbfc9d2);
        EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);

        im.setImage(URLImage.createToStorage(encImage, "High" + imageUrl, imageUrl, URLImage.RESIZE_SCALE));

        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        Button image = new Button(im.getImage().fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
        cnt.setLeadComponent(image);

        TextArea ta = new TextArea(title);
        ta.setUIID("NewsTopLine");
        ta.setEditable(false);

        Label likes = new Label("City : "+stade.getCity(), "NewsBottomLine");
        likes.setTextPosition(RIGHT);
      
            FontImage.setMaterialIcon(likes, FontImage.MATERIAL_MAP);
     Label capac = new Label("Capacity :"+stade.getCapacity(), "NewsBottomLine");
        capac.setTextPosition(RIGHT);
        
            FontImage.setMaterialIcon(capac, FontImage.MATERIAL_AIRLINE_SEAT_FLAT);
        
        cnt.add(BorderLayout.CENTER,
                BoxLayout.encloseY(
                        ta,
                        BoxLayout.encloseX(likes),
                        BoxLayout.encloseX(capac)
                ));
       CntStades.add(cnt);

         image.addActionListener(e -> new AfficherSingleStade(this.res,stade).show());
    }    
 private void addButtonHotel(String imageUrl, String title, boolean liked, int likeCount, int commentCount, int id , Hotel h) {

        ImageViewer im = new ImageViewer();

        Image placeholder = Image.createImage(45, 45, 0xbfc9d2);
        EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);

        im.setImage(URLImage.createToStorage(encImage, "High" + imageUrl, imageUrl, URLImage.RESIZE_SCALE));

        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        Button image = new Button(im.getImage().fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
        cnt.setLeadComponent(image);

        TextArea ta = new TextArea(title);
        ta.setUIID("NewsTopLine");
        ta.setEditable(false);

        Label likes = new Label("City :"+h.getCity() , "NewsBottomLine");
        likes.setTextPosition(RIGHT);
        
            FontImage.setMaterialIcon(likes, FontImage.MATERIAL_MAP);
         Label capac = new Label("Stars :"+h.getNbEtoiles(), "NewsBottomLine");
        capac.setTextPosition(RIGHT);
        
            FontImage.setMaterialIcon(capac, FontImage.MATERIAL_STAR);
     
      
        cnt.add(BorderLayout.CENTER,
                BoxLayout.encloseY(
                        ta,
                        BoxLayout.encloseX(likes),
                        BoxLayout.encloseX(capac)
                ));
       CntStades.add(cnt);

         image.addActionListener(e -> new AfficherSingleHotel(this.res,h).show());
    }    
   
      
   
    
    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if(b.isSelected()) {
                updateArrowPosition(b, arrow);
				if(b.getName()=="all")
				{
					CntStades.removeAll();
					
					   ServiceGuide ser= new  ServiceGuide();
					ArrayList<Stadium> Tab =  ser.getListStade();
	 
		 for(int i = 0 ; i < Tab.size() ; i++)
			  {
				  addButtonStade(Tab.get(i).getPhoto(), Tab.get(i).getName(), false, 11, 9,Tab.get(i).getId(),Tab.get(i));
			}
				 ArrayList<Hotel> Tab2 =  ser.getListHotel();
	 
		 for(int i = 0 ; i < Tab2.size() ; i++)
			  {
				   addButtonHotel(Tab2.get(i).getImage(), Tab2.get(i).getNom(), false, 11, 9,Tab2.get(i).getId(),Tab2.get(i));
		
			 }
				
				
				
				
				
				}
				else if(b.getName()=="Stadiums")
				{CntStades.removeAll();
					   ServiceGuide ser= new  ServiceGuide();
		 ArrayList<Stadium> Tab =  ser.getListStade();
	 
				  CntStades.removeAll();
		 for(int i = 0 ; i < Tab.size() ; i++)
			  {
				 addButtonStade(Tab.get(i).getPhoto(), Tab.get(i).getName(), false, 11, 9,Tab.get(i).getId(),Tab.get(i));
			}
		 
				}
				else if(b.getName()=="Hotels")
				{
					CntStades.removeAll();
					 ServiceGuide ser= new  ServiceGuide();
		 ArrayList<Hotel> Tab =  ser.getListHotel();
	 
				  CntStades.removeAll();
		 for(int i = 0 ; i < Tab.size() ; i++)
			  {
				   addButtonHotel(Tab.get(i).getImage(), Tab.get(i).getNom(), false, 11, 9,Tab.get(i).getId(),Tab.get(i));
		
			 }
				}
				else {
					CntStades.removeAll();
				
				}
            }
        });
    }
	
}
