/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.gui;

import com.alpha.Service.ServiceProduct;
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
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
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
 * @author dell
 */
public class Shop extends BaseForm {

	Container cnt2 = new Container();

	public Shop(Resources res) {
		super("", BoxLayout.y());

		Toolbar tb = new Toolbar(true);
		setToolbar(tb);
		getTitleArea().setUIID("Container");
		getContentPane().setScrollVisible(false);

		super.addSideMenu(res);

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

		ServiceProduct ser = new ServiceProduct();
		System.out.println(ser.getList2());
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
								BoxLayout.encloseY(
										new SpanLabel(text, "LargeWhiteText"),
										FlowLayout.encloseIn(likes, comments),
										spacer
								)
						)
				);

		swipe.addTab("", page1);
	}

	private void addButton(Image img, String title, boolean liked, int likeCount, int commentCount) {
		int height = Display.getInstance().convertToPixels(22f);
		int width = Display.getInstance().convertToPixels(22f);
		Button image = new Button(img.fill(width, height));
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
		//add(cnt);
		cnt2.add(cnt);
		image.addActionListener(e -> ToastBar.showMessage(title, FontImage.MATERIAL_INFO));
	}

	private void bindButtonSelection(Button b, Label arrow) {
		b.addActionListener(e -> {
			if (b.isSelected()) {
				cnt2.removeAll();
				updateArrowPosition(b, arrow);
			}
		});
	}

}
