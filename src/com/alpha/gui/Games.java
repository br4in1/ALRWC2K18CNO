/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.gui;

import com.alpha.Entite.Game;
import com.alpha.Service.ServiceGame;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.io.rest.Response;
import com.codename1.io.rest.Rest;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.CheckBox;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import static com.codename1.ui.events.ActionEvent.Type.Response;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.util.Base64;
import com.codename1.util.EasyThread;
import java.util.ArrayList;
import java.util.Map;


/**
 *
 * @author simo
 */
public class Games extends BaseForm {

	public Games(Resources res) {
		super(BoxLayout.y());
		Toolbar tb = new Toolbar(true);
		setToolbar(tb);
		getTitleArea().setUIID("Container");
		setTitle("Games");
		getContentPane().setScrollVisible(false);

		super.addSideMenu(res);
		tb.addSearchCommand(e -> {
		});

		Tabs swipe = new Tabs();

		Label spacer1 = new Label();
		Label spacer2 = new Label();
		addTab(swipe, res.getImage("signin-background.jpg"), spacer1, "Games");

		swipe.setUIID("Container");
		swipe.getContentPane().setUIID("Container");
		swipe.hideTabs();

		add(LayeredLayout.encloseIn(swipe));

          ServiceGame serviceGame =new ServiceGame(); 
		ArrayList<Game> games = new ArrayList<Game>(serviceGame.getListGames());
			for (Game game : games) {
				addButton(res,game,game.getHomeTeam().get("flagphoto").toString(),game.getAwayTeam().get("flagphoto").toString(),game.getHomeTeam().get("name").toString(),game.getAwayTeam().get("name").toString(),game.getDate().toString(),game.getResult());
		
			}

		
	
	}

	private void updateArrowPosition(Button b, Label arrow) {
		arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
		arrow.getParent().repaint();

	}
private void addButton(Resources res,Game game,String imgHome,String imgAway, String home, String away, String gDate, String score) {

        ImageViewer im = new ImageViewer();
		ImageViewer im2 = new ImageViewer();

        Image placeholder = Image.createImage(45, 45);
		Image placeholder2 = Image.createImage(45, 45);
        EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
		EncodedImage encImage2 = EncodedImage.createFromImage(placeholder, false);

        im.setImage(URLImage.createToStorage(encImage, "Medium" + imgHome, imgHome, URLImage.RESIZE_SCALE));
		im2.setImage(URLImage.createToStorage(encImage2, "Medium" + imgAway, imgAway, URLImage.RESIZE_SCALE));

        int height = Display.getInstance().convertToPixels(6f);
        int width = Display.getInstance().convertToPixels(10f);
        Button imageHome = new Button(im.getImage().fill(width, height));
		Button imageAway = new Button(im2.getImage().fill(width, height));
        imageHome.setUIID("home");
		imageAway.setUIID("away");
		Container mainCnt = BoxLayout.encloseX();
		
        TextArea homeName = new TextArea(home);
		TextArea awayName = new TextArea(away);
		
		TextArea homeScore = new TextArea(score.substring(0,1));
		TextArea AwayScore = new TextArea(score.substring(2,3));
		
		TextArea date = new TextArea(gDate);
		date.getAllStyles().setFgColor(0xFFF);
		Container dateCnt = BoxLayout.encloseX(date);
		dateCnt.getAllStyles().setBgColor(0x000);
        homeName.setUIID("homeN");
        homeName.setEditable(false);
		awayName.setUIID("awayN");
        awayName.setEditable(false);
		
		homeScore.setUIID("homeS");
        homeScore.setEditable(false);
		AwayScore.setUIID("awayS");
        AwayScore.setEditable(false);
		
		date.setUIID("date");
        date.setEditable(false);
		
		Container homeCnt = BoxLayout.encloseX(imageHome,homeName);
		Container awayCnt = BoxLayout.encloseX(imageAway,awayName);
		Container scoreCnt  = BoxLayout.encloseY(homeScore,AwayScore);
		Button more= new Button("...");
		
		more.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				new GameDetails(game,res).show();
			}
		});
		
	
				
		//scoreCnt.getAllStyles().setPaddingLeft(10);
		Container d = new Container(new BorderLayout());
		d.add(BorderLayout.WEST, more);
		Container scoreCntContainer = new Container(new BorderLayout());
		scoreCntContainer.add(BorderLayout.EAST,scoreCnt);
		d.setWidth(Display.getInstance().getDisplayWidth());
		Container teams = new Container();
		//scoreCntContainer.getAllStyles().setMarginLeft(90);
		d.add(BorderLayout.CENTER,BoxLayout.encloseY(homeCnt,awayCnt));
		d.add(BorderLayout.EAST,scoreCntContainer);
		Container y = BoxLayout.encloseY(dateCnt,d);
		mainCnt.add(y);
		add(mainCnt);
		AwayScore.getAllStyles().setMarginTop(40);
		homeScore.getAllStyles().setMarginTop(25);
		homeName.getAllStyles().setMarginTop(20);
		awayName.getAllStyles().setMarginTop(20);

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
}
