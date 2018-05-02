/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.gui;

import com.alpha.Entite.Gallery;
import com.alpha.Entite.SimpleUser;
import com.alpha.Service.ServiceGallery;
import com.codename1.components.ImageViewer;
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
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.codename1.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javafx.stage.FileChooser;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.codename1.io.File;
import com.cloudinary.*;
import java.util.Map;

/**
 *
 * @author dell
 */
public class AddGallery extends BaseForm {

	Cloudinary cloudinaryy = new Cloudinary(ObjectUtils.asMap(
			"cloud_name", "russie2k18",
			"api_key", "451245641369774",
			"api_secret", "fTOR3y7gDwymp7mztVOArxrP_Rw"));

	File file;
	File image1;
	TextField Ville;
	TextField Lieu;
	TextField Description;
	Button photo;
	Button ajouter;

	Container cnt3 = new Container();

	public AddGallery(Resources res) throws IOException {
		super("", BoxLayout.y());

		getAllStyles().setBgColor(0xE8E8E8);
		//cloudinaryy = new Cloudinary("cloudinary://212894137142756:7Coi2BsCet7rXqPmDAuBi08ONfQ@dbs7hg9cy");

		Toolbar tb = new Toolbar(true);
		setToolbar(tb);
		getTitleArea().setUIID("Container");
		getContentPane().setScrollVisible(false);

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
		/* RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
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
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        }); */

 /* Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));*/
		ButtonGroup barGroup = new ButtonGroup();
		RadioButton all = RadioButton.createToggle("Add your photo", barGroup);
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

		Ville = new TextField("","Ville", 20, TextField.ANY);
		Lieu = new TextField("","Lieu", 20, TextField.ANY);
		Description = new TextField("", "Description", 20, TextField.ANY);
		Ville.setUIID("TextFieldBlack");
		Lieu.setUIID("TextFieldBlack");
		Description.setUIID("TextFieldBlack");

		photo = new Button("Browse");
		ajouter = new Button("Click here to add your photo ");

		photo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				Display.getInstance().openGallery(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent ev) {
						if (ev != null && ev.getSource() != null) {
							String filePath = (String) ev.getSource();
							int fileNameIndex = filePath.lastIndexOf("/") + 1;
							String fileName = filePath.substring(fileNameIndex);
							Image imgg;
							try {
								imgg = Image.createImage(filePath);
								if (imgg != null) {

									file = new File(filePath);
									System.err.println(file);

								}
							} catch (IOException ex) {
							}
						}
					}
				},
						Display.GALLERY_IMAGE);

			}
		});

		ajouter.addActionListener((evt) -> {
			//System.out.println("aaaa");
			//Map uploadResult = cloudinaryy.uploader().upload(file , ObjectUtils.emptyMap());
			ServiceGallery ser = new ServiceGallery();
			String description = Description.getText();
			String ville = Ville.getText();
			String lieu = Lieu.getText();
			int user = SimpleUser.current_user.getId();
			Gallery g1 = new Gallery(user,ville,lieu,description,"ddf","0");
			ser.ajoutPhoto(g1);

		});
		cnt3.add(Ville);
		cnt3.add(Lieu);
		cnt3.add(Description);
		cnt3.add(photo);
		cnt3.add(ajouter);

		add(cnt3);

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
		Label overlay = new Label(" ", "ImageOverlay");

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

	private void addButton(String imageUrl, String title, boolean liked, int likeCount, int commentCount, int id) {

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

		image.addActionListener(e -> {

		});
	}

	private void bindButtonSelection(Button b, Label arrow) {
		b.addActionListener(e -> {
			if (b.isSelected()) {
				updateArrowPosition(b, arrow);
			}
		});
	}

}
