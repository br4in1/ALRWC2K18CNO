/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.gui;

import com.alpha.Entite.Bet;
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

	public BetAddForm(Resources res) {
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
		TextField resultField = new TextField("", "result", 20, TextField.ANY);
		resultField.setUIID("TextFieldBlack");

		TextField idGameField = new TextField("", "id Game", 20, TextField.NUMERIC);
		
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
					System.out.println(resultField.getText() + "  ! ssssss ");
					if (resultField.getText().equals("x") || resultField.getText().equals("y") || resultField.getText().equals("z")) {
						ServiceBet fs = new ServiceBet();

						if (fs.getList3(Integer.parseInt(idGameField.getText())).get(0).getId() == 0) {
							Dialog.show("Error", "Game does not exist ! ", "OK", null);
						} else {
							Map<Integer, Object> mapIdGame = new HashMap<Integer, Object>();
							mapIdGame.put(Integer.parseInt(idGameField.getText()), "");
							//System.out.println(mapIdGame);
							Bet f = new Bet(mapIdGame, resultField.getText(), SimpleUser.current_user.getId());
							fs.AjouterForum(f);
							Dialog.show("Success", "added ! ", "OK", null);
						}
					} else {
						Dialog.show("Error", "Result must be x or y or z ! ", "OK", null);
					}
				} else {
					Dialog.show("Error", "ID game can't be negative ! ", "OK", null);
				}
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
