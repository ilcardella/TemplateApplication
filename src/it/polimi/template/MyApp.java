package it.polimi.template;


import it.polimi.template.model.*;
import it.polimi.template.view.*;

import java.awt.EventQueue;
import java.util.ArrayList;


public class MyApp {
	
		static ArrayList<Mission> missions=new ArrayList<Mission>();
		static ArrayList<Trip> trips=new ArrayList<Trip>();

	
	
	public static void main(String[] args) {
		
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				MissionsPage mp = new MissionsPage(missions,trips);
				mp.setVisible(true);
			}
		});
	

		


	}

}
