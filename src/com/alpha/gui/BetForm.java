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
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
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
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Moez
 */
public class BetForm extends BaseForm {

	public static Bet team = new Bet();
	Form f;
	SpanLabel lb;

	public BetForm(Resources res) {
		super("Newsfeed", BoxLayout.y());
        
       // ServiceArticles sa = new ServiceArticles();
        //ArrayList<Article> articles = sa.getList2();

        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Newsfeed");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
        tb.addSearchCommand(e -> {
        });

        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("news-item.jpg"), spacer1, "15 Likes  ", "85 Comments", "Integer ut placerat purued non dignissim neque. ");
        addTab(swipe, res.getImage("dog.jpg"), spacer2, "100 Likes  ", "66 Comments", "Dogs are cute: story at 11");

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
		/*
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });*/

        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton all = RadioButton.createToggle("My Bets", barGroup);
        all.setUIID("SelectBar");
        RadioButton addBetButton = RadioButton.createToggle("Add bet", barGroup);
        addBetButton.setUIID("SelectBar");
        RadioButton popular = RadioButton.createToggle("", barGroup);
        popular.setUIID("SelectBar");
        RadioButton myFavorite = RadioButton.createToggle("", barGroup);
        myFavorite.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");

		
		
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(4, all, addBetButton, popular, myFavorite),
                FlowLayout.encloseBottom(arrow)
        ));

        all.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(all, arrow);
        });
        bindButtonSelection(all, arrow);
        bindButtonSelection(addBetButton, arrow);
        //bindButtonSelection(popular, arrow);
        //bindButtonSelection(myFavorite, arrow);

        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        /*for (int i = 0; i < articles.size(); i++) {
            addButton(articles.get(i).getArticleImage(), articles.get(i).getTitre(), false, 0, articles.get(i).getNum_comments(), articles.get(i).getId());

        }*/
		
		f = new Form();
		lb = new SpanLabel("");
		f.add(lb);

		ServiceBet serviceBet = new ServiceBet();

		for (Bet e : serviceBet.getList2()) {
			//System.out.println(e.getFlagPhoto());

			//addButton(e.getFlagPhoto(), e.getName(), false, e.getFifaRank(), e.getPoints(), 0);
			addButton(e,res);
		}
		
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
			if (idGameField.getText().equals("") || resultField.getText().equals("")) {
				Dialog.show("Error", "Please set all fields ", "OK", null);
			} else {
				if (Integer.parseInt(idGameField.getText()) > 0) {
					ServiceBet fs = new ServiceBet();
					Map<Integer,Object> mapIdGame = new HashMap<Integer,Object>() ;
					
					mapIdGame.put(Integer.parseInt(idGameField.getText()),"");
					//System.out.println(mapIdGame);
					
					Bet f = new Bet(mapIdGame, resultField.getText(), SimpleUser.current_user.getId());
					fs.AjouterForum(f);
					Dialog.show("Success", "added ! ", "OK", null);

				} else {
					Dialog.show("Error", "ID game can't be negative ! ", "OK", null);

				}

			}

		});
		
		all.addActionListener(e -> {
           // updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
		   System.out.println("all");
        });
		addBetButton.addActionListener(e -> {
           // updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
		   System.out.println("add");
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
	private void addButton(Bet t, Resources res) {

		//	TextArea ta = new TextArea(t.getResult());
		TextArea ta = new TextArea(t.getIdGame().get("result").toString());
		ta.setUIID("NewsTopLine");
		ta.setEditable(false);
		
		TextArea ta2 = new TextArea(t.getResult());
		ta2.setUIID("NewsTopLine");
		ta2.setEditable(false);
		
		String ch= "" ;
		String result = t.getIdGame().get("result").toString();
		System.out.println(result);
		System.out.println(result.charAt(0));
		System.out.println(result.charAt(2));
		
		if(t.getResult().equals("x") && result.charAt(0)>result.charAt(2)
				||
			t.getResult().equals("y") && result.charAt(0)<result.charAt(2)	
				||
				t.getResult().equals("z") && result.charAt(0)==result.charAt(2)
				) {ch = "win";}
		else ch="loose";
		
		/*
		||
				t.getResult().equals("y") && result.charAt(0)<result.charAt(3)
				||
				t.getResult().equals("z") && result.charAt(0)==result.charAt(3)
		*/
		TextArea ta3 = new TextArea(ch);
		ta3.setUIID("NewsTopLine");
		ta3.setEditable(false);

		Label likes = new Label(" rank : ");
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
		cnt.add(ta2);
		cnt.add(ta3);
		add(cnt);

	}
    private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if (img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
        Label likes = new Label(likesStr);
        Style heartStyle = new Style(likes.getUnselectedStyle());
        heartStyle.setFgColor(0xff2d55);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);
        likes.setIcon(heartImage);
        likes.setTextPosition(RIGHT);

        Label comments = new Label(commentsStr);
        FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
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
                                        FlowLayout.encloseIn(likes, comments),
                                        spacer
                                )
                        )
                );
        swipe.addTab("", page1);
    }
}
