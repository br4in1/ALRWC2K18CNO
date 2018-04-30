/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.gui;

import com.alpha.Entite.Product;
import com.codename1.braintree.Purchase;
import com.alpha.Entite.Team;
import com.alpha.Service.ServiceProduct;
import com.alpha.Service.ServiceTeam;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.io.File;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author br4in
 */
public class Cart extends BaseForm {

	Form f;
	SpanLabel lb;
	Database db;
	public static ArrayList<Product> staticList = new ArrayList<Product>();
	ArrayList<Product> MyCart;
	public static Float TotalAmount = 0f;

	public Cart(Resources res) throws IOException {
		super("Teams", BoxLayout.y());
		MyCart = staticList;
		reloadData(res);
	}
	
	public void reloadData(Resources res) throws IOException{
		this.removeAll();
		Toolbar tb = new Toolbar(true);
		setToolbar(tb);
		getTitleArea().setUIID("Container");
		setTitle("Cart");
		getContentPane().setScrollVisible(false);

		ButtonGroup barGroup = new ButtonGroup();
		RadioButton teamsButton = RadioButton.createToggle("", barGroup);
		teamsButton.setUIID("SelectBar");

		add(LayeredLayout.encloseIn(
				GridLayout.encloseIn(1, teamsButton)
		));

		Tabs swipe = new Tabs();
		super.addSideMenu(res);
		tb.addSearchCommand(e -> {
		});

		Label spacer1 = new Label();
		Label spacer2 = new Label();

		swipe.setUIID("Container");
		swipe.getContentPane().setUIID("Container");
		swipe.hideTabs();

		add(LayeredLayout.encloseIn(swipe));
		f = new Form();
		lb = new SpanLabel("");
		f.add(lb);
		for (int i = 0; i < MyCart.size(); i++) {
			addButton(MyCart.get(i),res.getImage("1.jpg"),res);
		}
		
		Container total = new Container(new BorderLayout());
		Button pay = new Button("Check Out");
		pay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				//handle payment
			}
		});
		total.add(BorderLayout.WEST,new Label(TotalAmount.toString()+ " €"));
		total.add(BorderLayout.EAST,pay);
		total.getAllStyles().setPadding(20, 20, 20, 20);
		add(total);
	}

	public Form getF() {
		return f;
	}

	public void setF(Form f) {
		this.f = f;
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

	private void addButton(Product p,Image image,Resources res) throws IOException {
		ImageViewer im = new ImageViewer();
		Image placeholder = Image.createImage(45, 45, 0xbfc9d2);
        EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
        im.setImage(URLImage.createToStorage(encImage, "Product" + p.getPic1(), p.getPic1(), URLImage.RESIZE_SCALE));
		int height = Display.getInstance().convertToPixels(20f);
		int width = Display.getInstance().convertToPixels(20f);
		Button image1 = new Button(im.getImage().fill(width, height));
		image1.setUIID("Label");
		Container cnt = BorderLayout.west(image1);
		Container details = new Container(BoxLayout.y());
		Label _label = new Label(p.getLabel());
		Font existingFont = _label.getAllStyles().getFont();
		_label.getAllStyles().setFont(Font.createSystemFont(existingFont.getFace(), Font.STYLE_BOLD, existingFont.getSize()));
		Label _price = new Label(String.valueOf(p.getPrice()) + " €");
		existingFont = _price.getAllStyles().getFont();
		_price.getAllStyles().setFont(Font.createSystemFont(existingFont.getFace(), Font.STYLE_BOLD, existingFont.getSize()));
		Button delete = new Button(" X ");
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				System.out.println("reloading data");
				staticList.remove(p);
				MyCart = staticList;
				try {
					reloadData(res);
				} catch (IOException ex) {
					
				}
			}
		});
		details.add(_label);
		details.add(_price);
		details.add(delete);
		cnt.add(BorderLayout.CENTER,
				BoxLayout.encloseY(
						details
				));
		cnt.getAllStyles().setPaddingTop(30);
		add(cnt);
	}

}
