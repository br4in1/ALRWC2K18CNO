/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.gui;

import com.alpha.Entite.Bet;
import com.alpha.Entite.SimpleUser;
import com.alpha.Entite.Team;
import com.alpha.Service.ServiceBet;
import com.alpha.Service.ServiceTeam;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Moez
 */
public class BetForm extends BaseForm{
	
	public static Bet team = new Bet();
	Form f;
	SpanLabel lb;

	public BetForm(Resources res) {
super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajouter Forum");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
        tb.addSearchCommand(e -> {});
        
          
        Image img = res.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

      
        
        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                    GridLayout.encloseIn(3, 
                         
                            FlowLayout.encloseCenter(
                           
                         
                    )
                )
        )));


        

        TextField resultField = new TextField("", "result", 20, TextField.ANY);
        resultField.setUIID("TextFieldBlack");
        
        TextField idGameField = new TextField("", "id Game", 20, TextField.NUMERIC);
      //  idGameField.setUIID("TextFieldBlack");
     //   addStringValue("Tag", tags);
	 super.add(resultField);
	 super.add(idGameField);

      Button Ajouter = new Button("Add bet");
	  
      Ajouter.setUIID("Button");
      super.add(Ajouter);
            Ajouter.addActionListener((e) -> {
				if(idGameField.getText().equals("") || resultField.getText().equals("") ){
					Dialog.show("Error", "Please set all fields ", "OK", null);
				}
				else {
					///if(idGameField.getText().equals("a"))
					//if(idGameField.getText().matches(regex))
					if(Integer.parseInt(idGameField.getText()) >0 )
					{
						ServiceBet fs = new ServiceBet();
						Bet f = new Bet(Integer.parseInt(idGameField.getText()),resultField.getText(),SimpleUser.current_user.getId());
						fs.AjouterForum(f);
						Dialog.show("Success", "added ! ", "OK", null);

					}
					else {
						Dialog.show("Error", "ID game can't be negative ! ", "OK", null);

				}
					
				}
			
                      
			
      
    });
            
	}



	private void updateArrowPosition(Button b, Label arrow) {
		arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
		arrow.getParent().repaint();

	}

	private void addTab(Tabs swipe, Image img, Label spacer, String text) {
		int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
		if (img.getHeight() < size) {
			img = img.scaledHeight(size);
		}

		ScaleImageLabel image = new ScaleImageLabel(img);
		image.setUIID("Container");
		image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
		Label overlay = new Label(" ", "ImageOverlay");

		Container page1
				= LayeredLayout.encloseIn(
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

	private void bindButtonSelection(Button b, Label arrow) {
		b.addActionListener(e -> {
			if (b.isSelected()) {
				updateArrowPosition(b, arrow);
			}
		});
	}

	//private void addButton(String imageUrl, String title, boolean liked, int fifarank, int points, int id) 
	private void addButton(Bet t,Resources res) 		
	{

	//	TextArea ta = new TextArea(t.getResult());
		
		TextArea ta = new TextArea(t.getResult());
		ta.setUIID("NewsTopLine");
		ta.setEditable(false);

		Label likes = new Label(" rank : " );
		likes.setTextPosition(RIGHT);
		//if (!liked) {
			//FontImage.setMaterialIcon(likes, FontImage.MATERIAL_FAVORITE);
		//} else {
			Style s = new Style(likes.getUnselectedStyle());
			s.setFgColor(0xff2d55);
			FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s);
			likes.setIcon(heartImage);
		//}
		Label comments = new Label(" Points : " + t.getPoints());
		FontImage.setMaterialIcon(likes, FontImage.MATERIAL_CHAT);
		Container cnt = new Container();
		cnt.add(ta);
		add(cnt);

	}
	
}
