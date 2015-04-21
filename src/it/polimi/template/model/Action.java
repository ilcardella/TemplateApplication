package it.polimi.template.model;

import it.polimi.template.model.interfaces.IAction;

public enum Action implements IAction {

	TAKE_PHOTO {
		@Override
		public Object doAction() {
			//<act>
			try {
				Thread.sleep(ACTION_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return true;
		}

		@Override
		public String toString() {
			return "Take photo";
		}

		@Override
		public boolean isItemRequired() {
			return false;
		}

	},
	MEASURE {

		@Override
		public Object doAction() {
			//<act>
			try {
				Thread.sleep(ACTION_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return true;
		}

		@Override
		public String toString() {
			return "Measure";
		}

		@Override
		public boolean isItemRequired() {
			return false;
		}

	},
	PICK_ITEM {

		@Override
		public Object doAction() {
			//<act>
			try {
				Thread.sleep(ACTION_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return true;
		}

		@Override
		public String toString() {
			return "Pick item";
		}

		@Override
		public boolean isItemRequired() {
			return true;
		}

	},
	RELEASE_ITEM {

		@Override
		public Object doAction() {
			//<act>
			try {
				Thread.sleep(ACTION_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return true;
		}

		@Override
		public String toString() {
			return "Release item";
		}

		@Override
		public boolean isItemRequired() {
			return true;
		}

	},
	
	CUSTOM {

		@Override
		public Object doAction() {
			<act>
			try {
				Thread.sleep(ACTION_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return true;
		}

		@Override
		public String toString() {
			return "Custom action";
		}

		@Override
		public boolean isItemRequired() {
			return false;
		}

	};

}
