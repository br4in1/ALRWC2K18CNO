/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
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
public class TeamForm extends BaseForm {

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
		addTab(swipe, res.getImage("signup-background.jpg"), spacer1, "Teams & Players");

		swipe.setUIID("Container");
		swipe.getContentPane().setUIID("Container");
		swipe.hideTabs();

		add(LayeredLayout.encloseIn(swipe));

		ButtonGroup barGroup = new ButtonGroup();
		RadioButton teamsButton = RadioButton.createToggle("Teams", barGroup);
		teamsButton.setUIID("SelectBar");
		RadioButton playersButton = RadioButton.createToggle("Players", barGroup);
		playersButton.setUIID("SelectBar");

		RadioButton voidBut01 = RadioButton.createToggle("", barGroup);
		voidBut01.setUIID("SelectBar");
		RadioButton voidBut02 = RadioButton.createToggle("", barGroup);
		voidBut02.setUIID("SelectBar");

		Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");

		add(LayeredLayout.encloseIn(
				GridLayout.encloseIn(4, teamsButton, playersButton, voidBut01, voidBut02),
				FlowLayout.encloseBottom(arrow)
		));

		teamsButton.setSelected(true);
		arrow.setVisible(false);
		addShowListener(e -> {
			arrow.setVisible(true);
			updateArrowPosition(teamsButton, arrow);
		});
		bindButtonSelection(teamsButton, arrow);
		bindButtonSelection(playersButton, arrow);

		// special case for rotation
		addOrientationListener(e -> {
			updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
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
