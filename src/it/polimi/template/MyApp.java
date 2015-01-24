package it.polimi.template;
/**
 * Politecnico di Milano 2014/2015
 * Master Thesis Project developed by
 * Alberto Cardellini and Manuel Belgioioso
 * 
 * 
 * papà
 */
import it.polimi.template.controller.MissionPageController;
import it.polimi.template.model.*;
import it.polimi.template.utils.DronesManager;
import it.polimi.template.utils.ItemsManager;
import it.polimi.template.view.*;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

public class MyApp {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				
				MissionsPage mp = new MissionsPage();

				MissionPageController main = new MissionPageController(mp);

				mp.setVisible(true);
			}
		});

	}
}
