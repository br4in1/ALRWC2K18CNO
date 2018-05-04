/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.gui;

import com.alpha.Entite.Gallery;
import com.alpha.Entite.Hotel;
import com.alpha.Entite.Stadium;
import com.alpha.Service.ServiceCommentaire;
import com.alpha.Service.ServiceGallery;
import com.alpha.Service.ServiceGuide;
import com.alpha.Service.ServiceLikes;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

/**
 *
 * @author dell
 */
public class DisplayGallery extends BaseForm {

	Resources res;

	Container cnt2 = new Container();

	public DisplayGallery(Resources res) {
		super("", BoxLayout.y());
		this.res = res;
		Toolbar tb = new Toolbar(true);
		setToolbar(tb);
		getTitleArea().setUIID("Container");
		getContentPane().setScrollVisible(false);

		super.addSideMenu(res);

		Tabs swipe = new Tabs();

		Label spacer1 = new Label();
		Label spacer2 = new Label();
		addTab(swipe, res.getImage("news-item.jpg"), spacer1, "15 Likes  ", "85 Comments", "Integer ut placerat purued non dignissim neque. ");
		addTab(swipe, res.getImage("dog.jpg"), spacer2, "100 Likes  ", "66 Comments", "Dogs are cute: story at 11");

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
		for (int iter = 0; iter < rbs.length; iter++) {
			rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
			rbs[iter].setPressedIcon(selectedWalkthru);
			rbs[iter].setUIID("Label");
			radioContainer.add(rbs[iter]);
		}

		rbs[0].setSelected(true);

		ButtonGroup barGroup = new ButtonGroup();
		RadioButton all = RadioButton.createToggle("Gallery photo ", barGroup);
		all.setUIID("SelectBar");

		Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");

		add(LayeredLayout.encloseIn(
				GridLayout.encloseIn(1, all),
				FlowLayout.encloseBottom(arrow)
		));

		all.setSelected(true);
		arrow.setVisible(false);
		addShowListener(e -> {
			arrow.setVisible(false);
			updateArrowPosition(all, arrow);
		});
		bindButtonSelection(all, arrow);

		// special case for rotation
		addOrientationListener(e -> {
			updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
		});
		Button Btn = new Button(" + ");

		Btn.addActionListener((evt) -> {
			try {
				new AddGallery(res).show();
			} catch (IOException ex) {
			}

		});
		cnt2.add(Btn);
		add(cnt2);
		ServiceGallery ser = new ServiceGallery();
		ServiceLikes ser1 = new ServiceLikes();
		ServiceCommentaire ser2 = new ServiceCommentaire();
		Gallery ga = new Gallery();
		ArrayList<Gallery> Tab = ser.getList2();
		for (int i = 0; i < Tab.size(); i++) {
			System.out.println(ser1.getList2(Tab.get(i).getId()).getLikes());
			System.out.println(ser2.getList3(Tab.get(i).getId()).getComments());

			addButton1(Tab.get(i).getImage(), Tab.get(i).getVille() + "  , " + Tab.get(i).getLieu(), false, ser1.getList2(Tab.get(i).getId()).getLikes(), ser2.getList3(Tab.get(i).getId()).getComments(), Tab.get(i));
		}

	}

	private void updateArrowPosition(Button b, Label arrow) {
		arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
		arrow.getParent().repaint();

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
		Label overlay = new Label("", "ImageOverlay");

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

	private void addButton1(String imageUrl, String title, boolean liked, int likeCount, int commentCount, Gallery g1) {

		ImageViewer im = new ImageViewer();

		Image placeholder = Image.createImage(45, 45, 0xbfc9d2);
		EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);

		im.setImage(URLImage.createToStorage(encImage, "Medium" + imageUrl, imageUrl, URLImage.RESIZE_SCALE));

		int height = Display.getInstance().convertToPixels(11.5f);
		int width = Display.getInstance().convertToPixels(14f);
		Button image = new Button(im.getImage().fill(width, height));
		image.setUIID("Label");
		Container cnt = BorderLayout.west(image);
		cnt.setLeadComponent(image);

		TextArea ta = new TextArea(title);
		ta.setUIID("NewsTopLine");
		ta.setEditable(false);

		Label likes = new Label(likeCount + " Likes  ", "NewsBottomLine");
		likes.setTextPosition(RIGHT);
		if (!liked) {
			FontImage.setMaterialIcon(likes, FontImage.MATERIAL_FAVORITE);
		} else {
			Style s = new Style(likes.getUnselectedStyle());
			s.setFgColor(0xff2d55);
			FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s);
			likes.setIcon(heartImage);
		}
		Label comments = new Label(commentCount + " Comments", "NewsBottomLine");
		FontImage.setMaterialIcon(likes, FontImage.MATERIAL_CHAT);

		cnt.add(BorderLayout.CENTER,
				BoxLayout.encloseY(
						ta,
						BoxLayout.encloseX(likes, comments)
				));
		add(cnt);

		image.addActionListener((e) -> {
			new DisplayOne(res, (Gallery) g1).show();
			System.out.println(g1);
		});
	}

	private void bindButtonSelection(Button b, Label arrow) {
		b.addActionListener(e -> {
			if (b.isSelected()) {
				updateArrowPosition(b, arrow);
				/*if (b.getName() == "All") {
					cnt2.removeAll();
					ServiceGallery ser = new ServiceGallery();
					ArrayList<Gallery> Tab = ser.getList2();
					for (int i = 0; i < Tab.size(); i++) {
						addButton(Tab.get(i).getImage(), Tab.get(i).getVille() + "  , " + Tab.get(i).getLieu(), false, 11, 9, 2);
					} 

				} 
				/*else if (b.getName() == "New") {
					cnt2.removeAll();
					TextField Ville = new TextField("", "Ville", 20, TextField.ANY);
					TextField Lieu = new TextField("", "Lieu", 20, TextField.ANY);
					TextField Description = new TextField("", "Description", 20, TextField.ANY);
					FileChooser file = new FileChooser();
					Ville.setSingleLineTextArea(false);
					Lieu.setSingleLineTextArea(false);
					Description.setSingleLineTextArea(false);
					Button Valider = new Button("Add your photo");
					cnt2.add(Ville);
					cnt2.add(Lieu);
					cnt2.add(Description);
					cnt2.add(Valider);
					
				} */
			}
		});
	}

}
