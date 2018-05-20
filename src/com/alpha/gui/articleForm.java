/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.gui;

import com.alpha.Entite.Article;
import com.alpha.Entite.ArticleCommentaire;
import com.alpha.Entite.SimpleUser;
import com.alpha.Service.ServiceArticles;
import com.alpha.utils.PDFHandler;
import com.codename1.components.FloatingHint;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.WebBrowser;
import com.codename1.messaging.Message;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
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

/**
 *
 * @author raiiz
 */
public class articleForm extends BaseForm {

    Resources res;
    Article article;

    public articleForm(Resources res, int id) {
        super("Newsfeed", BoxLayout.y());
        this.res = res;
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Article");
        getContentPane().setScrollVisible(false);

        ServiceArticles sa = new ServiceArticles();
        article = sa.getArticle(id);

        ArrayList<ArticleCommentaire> commentaires = sa.getCommentaires(id);

        super.addSideMenu(res);
        tb.addSearchCommand(e -> {
        });

        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, article.getArticleImage(), spacer1, article.getNum_comments() + " Comments ", article.getTitre() + ". ");

        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

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
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        BrowserComponent browser = new BrowserComponent();
        browser.setPage(article.getContenu(), null);
        browser.getStyle().setBgColor(0x99CCCC);
        browser.getStyle().setBgTransparency(255);
        Button download = new Button("Download");
        download.setText("Download");
        download.addActionListener(e -> {
            PDFHandler pdfh = new PDFHandler();
            pdfh.getFile(article.getContenu(), "article" + article.getId() + ".pdf");
        });

        Button sendMail = new Button("Share");
        sendMail.addActionListener(e -> {

            Message m = new Message(article.getContenu());
            m.getAttachments().put("", "text/plain");
            m.getAttachments().put("", "image/png");
            Display.getInstance().sendMessage(new String[]{SimpleUser.current_user.getEmail()}, article.getTitre(), m);
        });
        add(browser);

        Image imv = res.getImage("avatar_author.png").fill(55, 55);
        ImageViewer imvv = new ImageViewer(imv);

        Label created_at = new Label();
        created_at.setText("Written In " + article.getDatePublication());
        Label autho = new Label();
        autho.setText("Article By : " + article.getAuteur().getUsername());
        Container c2 = BorderLayout.east(BoxLayout.encloseX(
                download, sendMail
        ));
        Container c1 = BorderLayout.west(imvv);
        c1.add(BorderLayout.CENTER,
                BoxLayout.encloseY(
                        created_at,
                        BoxLayout.encloseX(autho)
                ));

        add(c2);
        add(c1);

        Label leave = new Label("Leave a comment :");
        TextArea ta = new TextArea();
        ta.setHint("Your Comment");
        Button comm = new Button("Comment");
        comm.setText("Comment");
        Button cancel = new Button("Cancel");
        cancel.setText("Cancel");

        cancel.addActionListener(e -> {
            ta.setText("");
        });
        comm.addActionListener(e -> {
            if (ta.getText().equals("") || ta.getText() == "") {
                Dialog.show("Error", "The comment must not be empty", "OK", "Cancel");
            } else {
                Dialog.show("Success", "Your comment has been added", "OK", "Close");
            }
        });
        ta.getStyle().setBgColor(0xb71f1f);
        ta.getStyle().setBgTransparency(255);
        Container commentarea = BorderLayout.west(leave);
        commentarea.add(BorderLayout.CENTER,
                BoxLayout.encloseX(
                        ta));
        add(commentarea);
        Container buttonsa = BorderLayout.east(BoxLayout.encloseX(comm, cancel));
        add(buttonsa);
        for (int i = 0; i < commentaires.size(); i++) {
            Image im = res.getImage("avatar_comm.jpg").fill(55, 55);
            ImageViewer iv = new ImageViewer(im);
            Container cnt = BorderLayout.west(iv);
            cnt.getStyle().setBgColor(0xb71f1f);
            browser.getStyle().setBgTransparency(255);
            Label author = new Label(commentaires.get(i).getAuthor().getUsername() + " :");
            Label commentaire = new Label(commentaires.get(i).getBody());
            author.setUIID("ktiba_kbira");
            commentaire.setUIID("commentaires");
            cnt.add(BorderLayout.CENTER,
                    BoxLayout.encloseY(
                            author,
                            BoxLayout.encloseX(commentaire)
                    ));
            add(cnt);
        }

    }

    private void addTab(Tabs swipe, String imgUrl, Label spacer, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());

        ImageViewer im = new ImageViewer();
        Image placeholder = Image.createImage(45, 45, 0xbfc9d2);
        if (im.getHeight() < size) {
            placeholder = placeholder.scaledHeight(size);
        }

        Label comments = new Label(commentsStr);
        FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);

        if (im.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            placeholder = placeholder.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        Label overlay = new Label(" ", "ImageOverlay");
        EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);

        im.setImage(URLImage.createToStorage(encImage, "Medium" + imgUrl, imgUrl, URLImage.RESIZE_SCALE));

        Container page1
                = LayeredLayout.encloseIn(
                        im,
                        overlay,
                        BorderLayout.south(
                                BoxLayout.encloseY(
                                        new SpanLabel(text, "LargeWhiteText"),
                                        FlowLayout.encloseIn(comments),
                                        spacer
                                )
                        )
                );

        swipe.addTab("", page1);
    }

}
