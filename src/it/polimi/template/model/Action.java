package it.polimi.template.model;

import it.polimi.template.model.interfaces.IAction;

public enum Action implements IAction{

	TAKE_PHOTO{
		 @Override
         public void doAction() {
			// TODO Auto-generated method stub 
         }

	}, MEASURE{

		@Override
		public void doAction() {
			// TODO Auto-generated method stub
			
		}
		
	}, PICK_ITEM{

		@Override
		public void doAction() {
			// TODO Auto-generated method stub
			
		}
		
	}, RELEASE_ITEM{

		@Override
		public void doAction() {
			// TODO Auto-generated method stub
			
		}
		
	};
	
}
