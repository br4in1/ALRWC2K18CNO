/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.gui;

import com.alpha.Entite.Bet;
import com.alpha.Entite.Game;
import com.alpha.Entite.SimpleUser;
import com.alpha.Service.ServiceBet;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
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
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.util.HashMap;
import java.util.Map;
import java.lang.NullPointerException;

/**
 *
 * @author Moez
 */
public class BetAddForm extends BaseForm {

	public static Bet team = new Bet();
	Form f;
	SpanLabel lb;

	public BetAddForm(Game game,Resources res) {
		super("Add Bet", BoxLayout.y());

		// ServiceArticles sa = new ServiceArticles();
		//ArrayList<Article> articles = sa.getList2();
		Toolbar tb = new Toolbar(true);
		setToolbar(tb);
		getTitleArea().setUIID("Container");
		setTitle("BETS");
		getContentPane().setScrollVisible(false);

		super.addSideMenu(res);
		tb.addSearchCommand(e -> {
		});

		Tabs swipe = new Tabs();

		Label spacer1 = new Label();
		Label spacer2 = new Label();
		addTab(swipe, res.getImage("news-item.jpg"), spacer1, "Add bet");

		FlowLayout flow = new FlowLayout(CENTER);
		flow.setValign(BOTTOM);
		Container radioContainer = new Container(flow);

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

		addBetButton.setSelected(true);
		arrow.setVisible(false);
		addShowListener(e -> {
			arrow.setVisible(true);
			updateArrowPosition(addBetButton, arrow);
		});
		bindButtonSelection(addBetButton, arrow);
		//	bindButtonSelection(addBetButton, arrow);

		addOrientationListener(e -> {
			updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
		});
		TextField resultField = new TextField("", "your bet", 20, TextField.ANY);
		resultField.setUIID("TextFieldBlack");

		
		
		Button Ajouter = new Button("Add bet");
		
		TextArea homeField = new TextField("", "1 for win of home team ", 20, TextField.ANY);
		homeField.setUIID("TextFieldBlack");
		TextArea drawField = new TextField("", "x for draw", 20, TextField.ANY);
		drawField.setUIID("TextFieldBlack");
		TextArea awayField = new TextField("", "2 for win of away team ", 20, TextField.ANY);
		awayField.setUIID("TextFieldBlack");
		

		Ajouter.setUIID("Button");
		
		super.add(resultField);
		super.add(Ajouter);
		super.add(homeField);
		super.add(drawField);
		super.add(awayField);
		Ajouter.addActionListener((e) -> {
			if (resultField.getText().equals("")) {
				Dialog.show("Error", "Please set value in your bet ", "OK", null);
			} else {
				//if (Integer.parseInt(idGameField.getText()) > 0) {
				//	System.out.println(resultField.getText() + "  ! ssssss ");
					if (resultField.getText().equals("x") || resultField.getText().equals("1") || resultField.getText().equals("2")) {
						ServiceBet fs = new ServiceBet();

						/*if (fs.getList3(Integer.parseInt(idGameField.getText())).get(0).getId() == 0) {
						Dialog.show("Error", "Game does not exist ! ", "OK", null);
						} else {*/
							Map<Integer, Object> mapIdGame = new HashMap<Integer, Object>();
							mapIdGame.put(game.getId(), "");
							//System.out.println(mapIdGame);
							Bet f = new Bet(mapIdGame, resultField.getText(), SimpleUser.current_user.getId());
							fs.AjouterForum(f);
							Dialog.show("Success", "added ! ", "OK", null);
						//}
					} else {
						Dialog.show("Error", "Result must be x or 1 or 2 ! ", "OK", null);
					}
				//} else {
					//Dialog.show("Error", "ID game can't be negative ! ", "OK", null);
				//}
			}

		});

		all.addActionListener(e -> {
			
			BetForm betF = new BetForm(res);
			betF.show();
		});
		addBetButton.addActionListener(e -> {
			// updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);

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



}
