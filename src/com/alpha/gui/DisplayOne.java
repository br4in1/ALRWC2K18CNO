/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.gui;

import com.alpha.Entite.Commentaire;
import com.alpha.Entite.Gallery;
import com.alpha.Entite.Likes;
import com.alpha.Entite.SimpleUser;
import com.alpha.Service.ServiceArticles;
import com.alpha.Service.ServiceCommentaire;
import com.alpha.Service.ServiceGallery;
import com.alpha.Service.ServiceLikes;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
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
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.image.ImageView;

/**
 *
 * @author dell
 */
public class DisplayOne extends BaseForm {

	Gallery gallery;
	Resources res;
	TextField Commentaire;
	Label Comments ; 

	@Override
	public boolean visibleBoundsContains(int x, int y) {
		return super.visibleBoundsContains(x, y); //To change body of generated methods, choose Tools | Templates.
	}

	Container cnt2 = new Container();
	private int x = 0;
	private boolean x1;

	public DisplayOne(Resources res, Gallery g1) {
		super("", BoxLayout.y());
		this.res = res;
		Toolbar tb = new Toolbar(true);
		setToolbar(tb);
		getTitleArea().setUIID("Container");
		getContentPane().setScrollVisible(false);

		//super.addSideMenu(res);
		Tabs swipe = new Tabs();

		Label spacer1 = new Label();
		Label spacer2 = new Label();
		addTab(swipe, res.getImage("news-item.jpg"), spacer1, "15 Likes  ", "85 Comments", "Integer ut placerat purued non dignissim neque. ");
		addTab(swipe, res.getImage("dog.jpg"), spacer2, "100 Likes  ", "66 Comments", "Dogs are cute: story at 11");
		Button back = new Button("Back");
		back.addActionListener((evt) -> {
			new DisplayGallery(res).show();

		});
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

		RadioButton all = RadioButton.createToggle("Prise à " + g1.getVille(), barGroup);
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
		ServiceLikes ser4 = new ServiceLikes();
		ServiceCommentaire ser5 = new ServiceCommentaire();

		Label likes = new Label(ser4.getList2(g1.getId()).getLikes() + " likes");
		Label Comments = new Label(ser5.getList3(g1.getId()).getComments() + " Comments");
Comments.addPointerPressedListener((evt) -> {
			new AfficherComm(res ,g1.getId()).show();
			System.out.println(g1.getId());
			
		});
		Style heartStyle = new Style(likes.getUnselectedStyle());
		heartStyle.setFgColor(0xff2d55);
		FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE_BORDER, heartStyle);
		likes.setIcon(heartImage);
		likes.setTextPosition(RIGHT);

		Label dislike = new Label("");
		Style heartStylee = new Style(dislike.getUnselectedStyle());
		heartStylee.setFgColor(0xff2d55);
		FontImage heartImagee = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStylee);
		dislike.setIcon(heartImagee);
		dislike.setTextPosition(RIGHT);
		dislike.setVisible(false);

		likes.addPointerPressedListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				if (x == 0) {
					ServiceLikes ser = new ServiceLikes();

					Gallery j = new Gallery();
					j = ser.existe(SimpleUser.current_user.getId(), g1.getId());

					likes.setIcon(FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle));
					Likes l1 = new Likes(SimpleUser.current_user.getId(), g1.getId());
					ser.LikedPhoto(l1);
					likes.setText(String.valueOf(ser4.getList2(g1.getId()).getLikes() + " likes"));

					x = 1;

				} else {
					ServiceLikes ser = new ServiceLikes();

					Likes l1 = new Likes(SimpleUser.current_user.getId(), g1.getId());
					ser.DislikedPhoto(l1);
					likes.setText(String.valueOf(ser4.getList2(g1.getId()).getLikes() + "likes"));
					likes.setIcon(FontImage.createMaterial(FontImage.MATERIAL_FAVORITE_BORDER, heartStyle));

					x = 0;
				}
			}
		});

		ServiceGallery ser = new ServiceGallery();
		ServiceLikes ser2 = new ServiceLikes();
		ServiceCommentaire ser3 = new ServiceCommentaire();

		addButton(g1.getImage(), g1.getVille() + "  , " + g1.getLieu(), false, ser2.getList2(g1.getId()).getLikes(), ser3.getList3(g1.getId()).getComments(), g1.getId());

		Commentaire = new TextField("", "Commenaitre", 20, TextField.ANY);
		Button comment = new Button("Comment");
		comment.addActionListener((evt) -> {

			ServiceCommentaire ser1 = new ServiceCommentaire();
			Commentaire com = new Commentaire(SimpleUser.current_user.getId(), g1.getId(), Commentaire.getText());
			ServiceCommentaire ser7 = new ServiceCommentaire();
			ser1.CommentPhoto(com);
			Comments.setText(String.valueOf(ser7.getList3(g1.getId()).getComments() + " Comments"));

		});
	
		Container cont = BorderLayout.west(likes);
		Container cont1 = BorderLayout.west(Comments);

		
	

		cnt2.add(Commentaire);
		cnt2.add(comment);
		cnt2.add(back);
		add(cont1);

		add(cont);
		add(cnt2);
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
								BoxLayout.encloseX(
										new SpanLabel(text, "LargeWhiteText"),
										FlowLayout.encloseIn(likes, comments),
										spacer
								)
						)
				);

		swipe.addTab("", page1);
	}

	private void addButton(String imageUrl, String title, boolean liked, int likeCount, int commentCount, int id) {

		ImageViewer im = new ImageViewer();

		Image placeholder = Image.createImage(45, 45, 0xbfc9d2);
		EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);

		im.setImage(URLImage.createToStorage(encImage, "Medium" + imageUrl, imageUrl, URLImage.RESIZE_SCALE));

		int height = Display.getInstance().convertToPixels(25f);
		int width = Display.getInstance().convertToPixels(25f);
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
		/*comments.addPointerPressedListener((evt) -> {
			new ActivateForm(res).show();
		});*/
		FontImage.setMaterialIcon(likes, FontImage.MATERIAL_CHAT);

		cnt.add(BorderLayout.CENTER,
				BoxLayout.encloseY(
						ta,
						BoxLayout.encloseX(likes, comments)
				));
			/*Comments.addPointerPressedListener((evt) -> {
			new AfficherComm(res ,id).show();
		})*/;
			
		add(cnt);
		
		

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
