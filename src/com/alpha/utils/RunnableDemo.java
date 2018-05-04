/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.utils;

import com.alpha.Service.ServiceGame;
import com.alpha.gui.GameDetails;
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
				ServiceGame ser = new ServiceGame();
				GameDetails.thisClass.cards = ser.getCardsFromDb(this.game_id);
				GameDetails.thisClass.goals = ser.getGoalsFromDb(this.game_id);
				GameDetails.thisClass.reloadGameData();
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
