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
		super("Bet", BoxLayout.y());

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
		addTab(swipe, res.getImage("news-item.jpg"), spacer1, "Bets display");

		FlowLayout flow = new FlowLayout(CENTER);
		flow.setValign(BOTTOM);
		Container radioContainer = new Container(flow);

		Component.setSameSize(radioContainer, spacer1, spacer2);
		add(LayeredLayout.encloseIn(swipe, radioContainer));

		ButtonGroup barGroup = new ButtonGroup();
		RadioButton all = RadioButton.createToggle("My Bets", barGroup);
		all.setUIID("SelectBar");
		RadioButton addBetButton = RadioButton.createToggle("", barGroup);
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
		//	bindButtonSelection(addBetButton, arrow);

		addOrientationListener(e -> {
			updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
		});

		f = new Form();
		lb = new SpanLabel("");
		f.add(lb);

		TextArea ta = new TextArea("Result");
		ta.setUIID("TextField");
		ta.setEditable(false);

		TextArea ta2 = new TextArea("Bets");
		ta2.setUIID("TextField");
		ta2.setEditable(false);

		TextArea ta3 = new TextArea("W/L");
		ta3.setUIID("TextField");
		ta3.setEditable(false);

		f.add(ta);
		f.add(ta2);
		f.add(ta3);
		super.add(f);

		ServiceBet serviceBet = new ServiceBet();

		
		for (Bet e : serviceBet.getList2()) {
			System.out.println(e.getIdUser());
			if(e.getIdUser()==SimpleUser.current_user.getId())
			{
				addButton(e, res);
			}
		}

		

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

	private void addButton(Bet t, Resources res) {
		
		TextArea ta = new TextArea("   " + t.getIdGame().get("result").toString());
		ta.setUIID("TextField");
		ta.setEditable(false);

		TextArea ta2 = new TextArea("   " + t.getResult());
		ta2.setUIID("TextField");
		ta2.setEditable(false);

		String ch = "";
		String result = t.getIdGame().get("result").toString();
	

		if (t.getResult().equals("1") && result.charAt(0) > result.charAt(2)
				|| t.getResult().equals("2") && result.charAt(0) < result.charAt(2)
				|| t.getResult().equals("x") && result.charAt(0) == result.charAt(2)) {
			ch = "win";
		} else {
			ch = "loose";
		}

		TextArea ta3 = new TextArea(" " + ch);
		ta3.setUIID("TextField");
		ta3.setEditable(false);

		
		Container cnt = new Container();

		f = new Form();

		cnt.add(ta);
		cnt.add(ta2);
		cnt.add(ta3);

		//cnt.add(f);
		add(cnt);

	}


}
