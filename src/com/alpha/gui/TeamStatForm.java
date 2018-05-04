/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.gui;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Moez
 */
public class TeamStatForm extends BaseForm {

	Form f;
	SpanLabel lb;

	public TeamStatForm(Resources res) {
		super("TeamsStat", BoxLayout.y());
		Toolbar tb = new Toolbar(true);
		setToolbar(tb);
		getTitleArea().setUIID("Container");
		setTitle("Team's static");
		getContentPane().setScrollVisible(false);

		super.addSideMenu(res);
		tb.addSearchCommand(e -> {
		});

		Tabs swipe = new Tabs();

		Label spacer1 = new Label();
		Label spacer2 = new Label();
		addTab(swipe, res.getImage("news-item.jpg"), spacer1, "Stats");

		swipe.setUIID("Container");
		swipe.getContentPane().setUIID("Container");
		swipe.hideTabs();

		add(LayeredLayout.encloseIn(swipe));

		super.add(createPieChartForm());

	}

	/**
	 * Creates a renderer for the specified colors.
	 */
	private DefaultRenderer buildCategoryRenderer(int[] colors) {
		DefaultRenderer renderer = new DefaultRenderer();
		renderer.setLabelsTextSize(15);
		renderer.setLegendTextSize(15);
		renderer.setMargins(new int[]{20, 30, 15, 0});
		for (int color : colors) {
			SimpleSeriesRenderer r = new SimpleSeriesRenderer();
			r.setColor(color);
			renderer.addSeriesRenderer(r);
		}
		return renderer;
	}

	/**
	 * Builds a category series using the provided values.
	 *
	 * @param titles the series titles
	 * @param values the values
	 * @return the category series
	 */
	protected CategorySeries buildCategoryDataset(String title, double[] values) {

		CategorySeries series = new CategorySeries(title);
		int k = 0;

		//  for (double value : values) {
		//series.add("tt " + ++k, value);
		series.add("win", TeamForm.team.getWin());
		series.add("loose", TeamForm.team.getLoose());
		series.add("draw", TeamForm.team.getDraw());

		//}
		return series;
	}

	public Form createPieChartForm() {
		// Generate the values
		double[] values = new double[]{TeamForm.team.getWin(), TeamForm.team.getLoose(), TeamForm.team.getDraw()};

		// Set up the renderer
		int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.YELLOW, ColorUtil.CYAN};
		DefaultRenderer renderer = buildCategoryRenderer(colors);
		//renderer.setZoomButtonsVisible(true);
		//renderer.setZoomEnabled(true);
		renderer.setChartTitleTextSize(2000);
		renderer.setLabelsTextSize(50);
		renderer.setLabelsColor(ColorUtil.BLACK);
		renderer.setDisplayValues(true);
		renderer.setShowLabels(true);
		SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
		r.setGradientEnabled(true);
		r.setGradientStart(0, ColorUtil.BLUE);
		r.setGradientStop(0, ColorUtil.GREEN);
		//r.setHighlighted(true);

		// Create the chart ... pass the values and renderer to the chart object.
		PieChart chart = new PieChart(buildCategoryDataset("Project budget", values), renderer);

		// Wrap the chart in a Component so we can add it to a form
		ChartComponent c = new ChartComponent(chart);

		// Create a form and show it.
		Form f = new Form("Budget", new BorderLayout());
		f.add(BorderLayout.CENTER, c);
		return f;

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

	public Form getF() {
		return f;
	}

	public void setF(Form f) {
		this.f = f;
	}

}
