/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.gui;

import com.alpha.Entite.SimpleUser;
import com.alpha.Service.ServiceUser;
import com.codename1.charts.views.DialChart;
import com.codename1.components.ScaleImageLabel;
import com.codename1.db.Database;
import com.codename1.facebook.FaceBookAccess;
import com.codename1.facebook.User;
import com.codename1.io.Storage;
import com.codename1.social.FacebookConnect;
import com.codename1.social.Login;
import com.codename1.social.LoginCallback;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;

/**
 * GUI builder created Form
 *
 * @author Tiburcio
 */
public class FacebookLogin extends BaseForm {

	public FacebookLogin(Resources res) {
		super(new BorderLayout());
		facebookLogin(res);
	}

	private void facebookLogin(Resources res) {
		//use your own facebook app identifiers here   
		//These are used for the Oauth2 web login process on the Simulator.
		String clientId = "2100597990159243";
		String redirectURI = "https://www.codenameone.com/"; //Una URI cualquiera. Si la pones en tu equipo debes crear un Servidor Web. Yo usé XAMPP
		String clientSecret = "819dc6dd91612fad4c43981e167ba986";
		Login fb = FacebookConnect.getInstance();
		fb.setClientId(clientId);
		fb.setRedirectURI(redirectURI);
		fb.setClientSecret(clientSecret);
		//Sets a LoginCallback listener
		fb.setCallback(new LoginCallback() {
			@Override
			public void loginFailed(String errorMessage) {
				Storage.getInstance().writeObject("token", "");
				if (Dialog.show("Login Failed", errorMessage, "OK", "")) {
					new SignInForm(res).show();
				}
			}

			@Override
			public void loginSuccessful() {
				try {
					Database db = Database.openOrCreate("Russia2018.db");
					db.execute("delete from appstates");
					ServiceUser ser = new ServiceUser();
					String token = fb.getAccessToken().getToken();
					Storage.getInstance().writeObject("token", token);
					FaceBookAccess.setToken(token);
					SimpleUser u = new SimpleUser();
					User me = new User();
					FaceBookAccess.getInstance().getUser("me", me, new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent evt) {
							u.setUsername(me.getId());
							u.setEmail(me.getId());
							u.setFirstname(me.getName());
							u.setLastname(me.getName());
							ser.LoginUserWithFacebook(u);
							SimpleUser.current_user = u;
							if (SimpleUser.current_user == null) {
								new SignInForm(res).show();
							} else {
								new NewsfeedForm(res).show();
							}
						}
					});
				} catch (IOException ex) {
				}
			}
		});
		if (!fb.isUserLoggedIn()) {
			fb.doLogin();
		} else {
			//get the token and now you can query the facebook API
			String token = fb.getAccessToken().getToken();
			Storage.getInstance().writeObject("token", token);
		}
	}
}
