package it.polimi.template.utils;

import it.polimi.template.model.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemsManager {

	private static List<Item> items;
	private final static int NUM_OF_ITEMS = 4;

	public synchronized static List<Item> getItems() {
		if(items == null){
			items = new ArrayList<Item>();
			
			for(int j=0; j<NUM_OF_ITEMS;j++){
				Item i = new Item();
				i.setShapeCategory(1);
				i.setName("Item"+i+1);
				items.add(i);
			}
		}
		
		return items;
	}
}
