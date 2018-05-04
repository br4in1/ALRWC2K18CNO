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
import com.alpha.Service.CountriesList;
import com.alpha.Service.ServiceUser;
import com.codename1.charts.views.DialChart;
import com.codename1.components.FloatingHint;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import com.codename1.l10n.L10NManager;
import com.codename1.ui.Dialog;
import java.util.Date;

/**
 * Signup UI
 *
 * @author Shai Almog
 */
public class SignUpForm extends BaseForm {

	public SignUpForm(Resources res) {
		super(new BorderLayout());
		Toolbar tb = new Toolbar(false);
		setToolbar(tb);
		tb.setUIID("Container");
		getTitleArea().setUIID("Container");
		Form previous = Display.getInstance().getCurrent();
		tb.setBackCommand("", e -> previous.showBack());
		setUIID("SignIn");
		//getAllStyles().setBgImage(res.getImage("signup-background.jpg"));

		TextField username = new TextField("", "Username", 20, TextField.ANY);
		TextField email = new TextField("", "E-Mail", 20, TextField.EMAILADDR);
		TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
		TextField confirmPassword = new TextField("", "Confirm Password", 20, TextField.PASSWORD);
		Picker datePicker = new Picker();
		datePicker.setText("Birth Date");
		username.setSingleLineTextArea(true);
		email.setSingleLineTextArea(true);
		password.setSingleLineTextArea(true);
		confirmPassword.setSingleLineTextArea(true);
		Button next = new Button("Next");
		Button signIn = new Button("Sign In");
		signIn.addActionListener(e -> previous.showBack());
		signIn.setUIID("Link");
		Label alreadHaveAnAccount = new Label("Already have an account?");
		Picker country = new Picker();
		country.setStrings(CountriesList.countries);
		country.setText("Country");
		Container content = BoxLayout.encloseY(
				new Label("Sign Up", "LogoLabel"),
				new FloatingHint(username),
				createLineSeparator(),
				new FloatingHint(email),
				createLineSeparator(),
				new FloatingHint(password),
				createLineSeparator(),
				new FloatingHint(confirmPassword),
				createLineSeparator(),
				datePicker,
				createLineSeparator(),
				country
		);
		content.setScrollableY(true);
		add(BorderLayout.CENTER, content);
		add(BorderLayout.SOUTH, BoxLayout.encloseY(
				next,
				FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
		));
		next.requestFocus();
		next.addActionListener(e -> {
			if(username.getText().length() < 5){
				Dialog.show("Error","Username is required and must contains at least 5 characters.","OK","");
			}
			else if(password.getText().length() < 5){
				Dialog.show("Error","Password is required and must contains at least 8 characters.","OK","");
			}
			else if(email.getText().length() == 0 || !email.getText().contains("@") || !email.getText().contains(".")){
				Dialog.show("Error","Please enter a valid email address.","OK","");
			}
			else if(!confirmPassword.getText().equals(password.getText())){
				Dialog.show("Error","Please check your password confirmation.","OK","");
			}
			else if(datePicker.getDate().getTime() > new Date().getTime()){
				Dialog.show("Error","The date of birth must not bypass today's date.","OK","");
			}
			else if(country.getSelectedString() == null || country.getSelectedString().equals("Country")){
				Dialog.show("Error","Please pick a country from the list.","OK","");
			}
			else{
				SimpleUser u = new SimpleUser();
				u.setEmail(email.getText());
				u.setPassword(password.getText());
				u.setUsername(username.getText());
				u.setBirthdate(datePicker.getDate());
				u.setNationality(country.getSelectedString());
				ServiceUser ser = new ServiceUser();
				String ans = ser.SignUpUser(u);
				System.out.println("ans == "+ans);
				if(ans.equals("OK")) new SignInForm(res).show();
				else if(ans.equals("username")){
					Dialog.show("Error", "This username is already in use.", "OK","");
				}
				else{
					Dialog.show("Error", "This email address is already in use.", "OK","");
				}
			}
		});
	}

}
