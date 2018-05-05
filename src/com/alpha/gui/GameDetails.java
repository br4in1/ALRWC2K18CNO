/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.gui;

import com.alpha.Entite.Card;
import com.alpha.Entite.Game;
import com.alpha.Entite.Goal;
import com.alpha.Entite.SimpleUser;
import com.alpha.Service.ServiceGame;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import com.alpha.utils.RunnableDemo;
import com.codename1.components.ToastBar;
import com.codename1.io.rest.Response;
import com.codename1.io.rest.Rest;

import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.FontImage;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.messaging.Message;
import com.codename1.ui.Dialog;
import static com.codename1.ui.events.ActionEvent.Type.Response;
import com.codename1.ui.geom.Dimension;
import com.codename1.util.Base64;
import com.wefeel.QRMaker.QRMaker;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author simo
 */
public class GameDetails extends BaseForm {

	public static GameDetails thisClass;
	public ArrayList<Card> cards;
	public ArrayList<Goal> goals;
	public int ffcost=0;
	public int x= 0;
	public GameDetails(Game game, Resources res) {
		super(BoxLayout.y());
		thisClass = this;
		RunnableDemo runnableDemo = new RunnableDemo(game.getId());
		runnableDemo.start();
		

		
		Toolbar tb = new Toolbar(true);
		setToolbar(tb);
		getTitleArea().setUIID("Container");
		setTitle("Games");
		getContentPane().setScrollVisible(false);

		super.addSideMenu(res);
		tb.addSearchCommand(e -> {
		});
				ButtonGroup barGroup = new ButtonGroup();
		RadioButton teamsButton = RadioButton.createToggle("  ", barGroup);
		teamsButton.setUIID("SelectBar");
		add(LayeredLayout.encloseIn(
				GridLayout.encloseIn(1, teamsButton)
		));


		Tabs swipe = new Tabs();

		Label spacer1 = new Label();
		Label spacer2 = new Label();
		addTab(swipe, res.getImage("signin-background.jpg"), spacer1, "Games");

		swipe.setUIID("Container");
		swipe.getContentPane().setUIID("Container");
		swipe.hideTabs();

		add(LayeredLayout.encloseIn(swipe));


		
		addButton(res,game,game.getCost(),game.getHomeTeam().get("flagphoto").toString(),game.getAwayTeam().get("flagphoto").toString(),game.getHomeTeam().get("name").toString(),game.getAwayTeam().get("name").toString(),game.getDate().toString(),game.getResult());
	}
	
	public void reloadGameData(){
		//TODO : update horizontal line
	}
	
	private void addButton(Resources res,Game game,int cost,String imgHome,String imgAway, String home, String away, String gDate, String score) {
		
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
		TextArea scoreG = new TextArea(score);
		
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
		
		scoreG.setUIID("ticket");
        scoreG.setEditable(false);
		
		date.setUIID("date");
        date.setEditable(false);
		
		TextField nbTickets = new TextField();
		nbTickets.setWidth(5);
		
		Label costF = new Label(String.valueOf(cost)+" FP");
		costF.getAllStyles().setFgColor(0x000);
		Button tickets= new Button("Book a Ticket");
		Button bet= new Button("Bet");
		Container gameContainer = new Container(new BorderLayout());
		gameContainer.add(BorderLayout.NORTH,dateCnt);
		gameContainer.add(BorderLayout.EAST,BoxLayout.encloseY(awayName,imageAway));
		gameContainer.add(BorderLayout.WEST,BoxLayout.encloseY(homeName,imageHome));
		gameContainer.add(BorderLayout.CENTER,scoreG);
		nbTickets.setHint("Number Of Tickets");
		nbTickets.addDataChangedListener(new DataChangedListener() {
			@Override
			public void dataChanged(int type, int index) {
				
				
					if(test(nbTickets.getText()))
					{	
				ffcost=Integer.valueOf(nbTickets.getText())*cost;
				costF.setText(String.valueOf(ffcost)+ " FP");
				}
				
				else
				if (!nbTickets.getText().equals(""))
				
				ToastBar.showMessage("Number of tickets must an integer", FontImage.MATERIAL_INFO);
				
				
				}
			
		});
		
		
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date today = new Date();
	
		bet.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
								if(dateFormat.format(today).compareTo(dateFormat.format(game.getDate())) < 0 ){
				//show the bet form 
					System.out.println("not played yet !");
				}
				else{
					Dialog.show("Error", "Game already played ! ", "OK", null);
				}
			}
		});
		tickets.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				if (!test(nbTickets.getText()))
					Dialog.show("Error", "Invalid number of tickets ! ", "OK", null);
				else if(dateFormat.format(today).compareTo(dateFormat.format(game.getDate())) < 0 ){
									if (SimpleUser.current_user.getFidaelitypoints()>=ffcost)
									{	
									for (int i = 0; i < (int)ffcost/cost; i++) {
								ServiceGame serviceGame =new ServiceGame();
								serviceGame.bookTicket(game.getId(), SimpleUser.current_user.getId());
									}
									 String accountSID = "AC0d979902ef3a68ca297b9628844d22f6";
String authToken = "52a239517f4998fa7ea9da4bebc55cc0";
String fromPhone = "+18606136490";
		Response<Map> result = Rest.post("https://api.twilio.com/2010-04-01/Accounts/" + accountSID + "/Messages.json").
        queryParam("To", "+21628355355").
        queryParam("From", fromPhone).
        queryParam("Body", "You have succesfully booked "+ffcost/cost+"Ticket(s)").
        header("Authorization", "Basic " + Base64.encodeNoNewline((accountSID + ":" + authToken).getBytes())).
        getAsJsonMap();
		

Image img = QRMaker.QRCode(String.valueOf(game.getId()+SimpleUser.current_user.getId()*2341));
ImageViewer image= new ImageViewer(img);

Dialog.show("Success", "Successfuly booked "+ffcost/cost+" tickets ! ", "OK", null);
Dialog d = new Dialog("QR Code");
 Image placeholder = Image.createImage(45, 45);
		

        int height = Display.getInstance().convertToPixels(25f);
        int width = Display.getInstance().convertToPixels(25f);
        Button imgg = new Button(image.getImage().fill(width, height));
d.add(GridLayout.encloseIn(1, imgg));
d.show();
imgg.addActionListener(new ActionListener() {
										@Override
										public void actionPerformed(ActionEvent evt) {
										d.setVisible(false);
										}
									});
		
									}
									else
								Dialog.show("Error", "Fidelity points insufficient  ! ", "OK", null);
				}
				else{
					Dialog.show("Error", "Game already played ! ", "OK", null);
				}
			}
		});
		add(gameContainer);
		Container cnt = GridLayout.encloseIn(2,BoxLayout.encloseY(tickets,nbTickets,costF),BoxLayout.encloseY(bet,new Label(""),new Label("")));
		cnt.getAllStyles().setMarginLeft(20);
		cnt.getAllStyles().setMarginRight(20);
		add(cnt);
		
		
		
	
				
		//scoreCnt.getAllStyles().setPaddingLeft(10);
		

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
	
	private void updateArrowPosition(Button b, Label arrow) {
		arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
		arrow.getParent().repaint();

	}
	
	private boolean test(String str)
	{
		int x=0;
		if (str.equals(""))
			return false;
		for (Character c : str.toCharArray()) {
			if (c=='0' || c=='1' || c=='2' || c=='3' || c=='4'|| c=='5'|| c=='6' || c=='7' || c=='8'|| c=='9')
				x++;
		}
		if (x==str.length())
			return true;
			return false;
	}
}
