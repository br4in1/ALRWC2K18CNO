/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.gui;

import com.alpha.Entite.Team;
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
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
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
 * @author Moez
 */
public class TeamForm extends BaseForm {

	public static Team team = new Team();
	Form f;
	SpanLabel lb;

	public TeamForm(Resources res) {
		super("Teams", BoxLayout.y());
		Toolbar tb = new Toolbar(true);
		setToolbar(tb);
		getTitleArea().setUIID("Container");
		setTitle("Teams");
		getContentPane().setScrollVisible(false);

		super.addSideMenu(res);
		tb.addSearchCommand(e -> {
		});

		Tabs swipe = new Tabs();

		Label spacer1 = new Label();
		Label spacer2 = new Label();
		addTab(swipe, res.getImage("signup-background.jpg"), spacer1, "Teams");

		swipe.setUIID("Container");
		swipe.getContentPane().setUIID("Container");
		swipe.hideTabs();

		add(LayeredLayout.encloseIn(swipe));

		ButtonGroup barGroup = new ButtonGroup();
		RadioButton teamsButton = RadioButton.createToggle("Teams", barGroup);
		teamsButton.setUIID("SelectBar");

		RadioButton voidBut00 = RadioButton.createToggle("", barGroup);
		voidBut00.setUIID("SelectBar");
		RadioButton voidBut01 = RadioButton.createToggle("", barGroup);
		voidBut01.setUIID("SelectBar");
		RadioButton voidBut02 = RadioButton.createToggle("", barGroup);
		voidBut02.setUIID("SelectBar");

		Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");

		add(LayeredLayout.encloseIn(
				GridLayout.encloseIn(4, teamsButton, voidBut00, voidBut01, voidBut02),
				FlowLayout.encloseBottom(arrow)
		));

		teamsButton.setSelected(true);
		arrow.setVisible(false);
		addShowListener(e -> {
			arrow.setVisible(true);
			updateArrowPosition(teamsButton, arrow);
		});
		bindButtonSelection(teamsButton, arrow);

		addOrientationListener(e -> {
			updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);

		});

		f = new Form();
		lb = new SpanLabel("");
		f.add(lb);

		ServiceTeam serviceTeam = new ServiceTeam();

		for (Team e : serviceTeam.getList2()) {
			
			addButton(e,res);
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

	//private void addButton(String imageUrl, String title, boolean liked, int fifarank, int points, int id) 
	private void addButton(Team t,Resources res) 		
	{

		ImageViewer im = new ImageViewer();

		Image placeholder = Image.createImage(45, 45, 0xbfc9d2);
		EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);

		im.setImage(URLImage.createToStorage(encImage, "Medium" + t.getFlagPhoto(), t.getFlagPhoto(), URLImage.RESIZE_SCALE));

		int height = Display.getInstance().convertToPixels(11.5f);
		int width = Display.getInstance().convertToPixels(14f);
		Button image = new Button(im.getImage().fill(width, height));
		image.setUIID("Label");
		Container cnt = BorderLayout.west(image);
		cnt.setLeadComponent(image);

		TextArea ta = new TextArea(t.getName());
		ta.setUIID("NewsTopLine");
		ta.setEditable(false);

		Label rankLabel = new Label(" rank : " + t.getFifaRank());
		rankLabel.setTextPosition(RIGHT);
		
			Style s = new Style(rankLabel.getUnselectedStyle());
			s.setFgColor(0xff2d55);
			FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s);
			rankLabel.setIcon(heartImage);
		
		Label pointsLabel = new Label(" Points : " + t.getPoints());
		FontImage.setMaterialIcon(rankLabel, FontImage.MATERIAL_CHAT);

		cnt.add(BorderLayout.CENTER,
				BoxLayout.encloseY(
						ta,
						BoxLayout.encloseX(rankLabel, pointsLabel)
				));
		add(cnt);
		image.addActionListener(
				e -> {
					team= t ;
					TeamStatForm h = new TeamStatForm(res);
					h.show();
					ToastBar.showMessage(team.getName(), FontImage.MATERIAL_INFO);
				}
		);

	}

}
