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
import com.alpha.Service.ServiceUser;
import com.codename1.components.FloatingHint;
import com.codename1.components.ImageViewer;
import com.codename1.db.Database;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;

/**
 * Sign in UI
 *
 * @author Shai Almog
 */
public class SignInForm extends BaseForm {

	public SignInForm(Resources res) {
		super(new BorderLayout());

		if (!Display.getInstance().isTablet()) {
			BorderLayout bl = (BorderLayout) getLayout();
			bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
			bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
		}
		getTitleArea().setUIID("Container");
		setUIID("SignIn");

		add(BorderLayout.NORTH, new Label(res.getImage("Logo.png"), "LogoLabel"));

		TextField username = new TextField("", "Username", 20, TextField.ANY);
		TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
		username.setSingleLineTextArea(true);
		password.setSingleLineTextArea(true);
		Button signIn = new Button("Sign In");
		Button signUp = new Button("Sign Up");
		signUp.addActionListener(e -> new SignUpForm(res).show());
		signUp.setUIID("Link");
		Label doneHaveAnAccount = new Label("Don't have an account?");

		Container content = BoxLayout.encloseY(
				new FloatingHint(username),
				createLineSeparator(),
				new FloatingHint(password),
				createLineSeparator(),
				signIn,
				FlowLayout.encloseCenter(doneHaveAnAccount, signUp)
		);
		content.setScrollableY(true);
		Image cwfim = res.getImage("facebook-login.png");
		ImageViewer cwf = new ImageViewer(cwfim.scaled(cwfim.getWidth()*3, cwfim.getHeight()*3));
		cwf.addPointerReleasedListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				new FacebookLogin(res);
			}
		});
		content.add(cwf);
		//content.getAllStyles().setMarginTop(500);
		add(BorderLayout.CENTER, content);
		signIn.requestFocus();
		//signIn.addActionListener(e -> new NewsfeedForm(res).show());
		signIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				if (username.getText() != "" || password.getText() != "") {
					ServiceUser ser = new ServiceUser();
					SimpleUser u = ser.CheckLoginData(username.getText(), password.getText());
					if (u == null) {
						Dialog.show("Wrong Credentials", "Please verify your username and password.", "Ok", "");
						username.setText("");
						password.setText("");
					} else {
						SimpleUser.current_user = u;
						Database db;
						try {
							db = Database.openOrCreate("Russia2018.db");
							db.execute("delete from appstates");
							db.execute("insert into appstates(loggedin,loggeduserid) values (1," + u.getId() + ");");
							NewsfeedForm h = new NewsfeedForm(res);
							h.show();
						} catch (IOException ex) {
						}
					}
				}
			}
		});
	}

}
