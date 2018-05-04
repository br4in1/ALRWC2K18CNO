/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package com.alpha.gui;

import com.alpha.Entite.SimpleUser;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.io.IOException;

/**
 * Base class for the forms with common functionality
 *
 * @author Shai Almog
 */
public class BaseForm extends Form {

	public BaseForm() {
	}

	public BaseForm(Layout contentPaneLayout) {
		super(contentPaneLayout);
	}

	public BaseForm(String title, Layout contentPaneLayout) {
		super(title, contentPaneLayout);
	}

	public Component createLineSeparator() {
		Label separator = new Label("", "WhiteSeparator");
		separator.setShowEvenIfBlank(true);
		return separator;
	}

	public Component createLineSeparator(int color) {
		Label separator = new Label("", "WhiteSeparator");
		separator.getUnselectedStyle().setBgColor(color);
		separator.getUnselectedStyle().setBgTransparency(255);
		separator.setShowEvenIfBlank(true);
		return separator;
	}

	protected void addSideMenu(Resources res) {
		Toolbar tb = getToolbar();
		Image img = res.getImage("profile-background.jpg");
		if (img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
			img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
		}
		Image placeholder = Image.createImage(45, 45, 0xbfc9d2);
		EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
		ScaleImageLabel sl = new ScaleImageLabel(img);
		sl.setUIID("BottomPad");
		sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
		Image userImage;
		System.out.println("user profile picture is "+ SimpleUser.current_user.getProfilepicture());
		if (SimpleUser.current_user.getProfilepicture().equals("null")) {
			userImage = res.getImage("default_profile_picture.png");
			tb.addComponentToSideMenu(LayeredLayout.encloseIn(
					sl,
					FlowLayout.encloseCenterBottom(
							new Label(userImage.scaled(200, 200)))
			));
		} else {
			tb.addComponentToSideMenu(LayeredLayout.encloseIn(
					sl,
					FlowLayout.encloseCenterBottom(
							new Label(URLImage.createToStorage(encImage, "User" + SimpleUser.current_user.getProfilepicture(), SimpleUser.current_user.getProfilepicture(), URLImage.RESIZE_SCALE_TO_FILL).scaled(200, 200), "PictureWhiteBackgrond"))
			));
		}

		tb.addMaterialCommandToSideMenu("Newsfeed", FontImage.MATERIAL_UPDATE, e -> new NewsfeedForm(res).show());
		tb.addMaterialCommandToSideMenu("Profile", FontImage.MATERIAL_SETTINGS, e -> new ProfileForm(res).show());
		tb.addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> new WalkthruForm(res).show());
		tb.addMaterialCommandToSideMenu("Teams", FontImage.MATERIAL_FLAG, e -> new TeamForm(res).show());
		tb.addMaterialCommandToSideMenu("Bets", FontImage.MATERIAL_UPDATE,  e -> new BetForm(res).show());
		tb.addMaterialCommandToSideMenu("Guide", FontImage.MATERIAL_HOME, e -> new GuideAffichage(res).show());		
		tb.addMaterialCommandToSideMenu("Gallery", FontImage.MATERIAL_IMAGE, e -> new DisplayGallery(res).show());
		tb.addMaterialCommandToSideMenu("Shop", FontImage.MATERIAL_SHOP, e -> {
			try {
				new Shop(res).show();
			} catch (IOException ex) {
			}
		});
		tb.addMaterialCommandToSideMenu("My Cart", FontImage.MATERIAL_CREDIT_CARD, e -> {
			try {
				new Cart(res).show();
			} catch (IOException ex) {
			}
		});
	}
}
