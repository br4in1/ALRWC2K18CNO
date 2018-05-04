/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.utils;

import javafx.application.Platform;

/**
 *
 * @author br4in
 */
public class RunnableDemo implements Runnable {

	private Thread t;
	public static int indicator;
	public static int game_id;

	public RunnableDemo(int game_id) {
		this.indicator = 1;
	}

	public void run() {
		try {
			while (indicator == 1) {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						
					}
				});
				Thread.sleep(5000);
			}
		} catch (InterruptedException e) {
			//System.out.println("Thread " + threadName + " interrupted.");
		}
	}

	public void start() {
		if (t == null) {
			t = new Thread(this, "game");
			t.start();
		}
	}
}
