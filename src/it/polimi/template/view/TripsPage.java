package it.polimi.template.view;

import it.polimi.template.model.*;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSource;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.polimi.template.controller.TripsPageController.ExportDoneListener;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.TransferHandler;

public class TripsPage extends JFrame {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private DefaultListModel model;
	private DefaultListModel model1;

	private JList list;
	private JList tripList;

	private Object lalla;

	private ExportDoneListener edp;

	private JLabel label;
	private JButton ok;
	private JButton delete;

	private String nameMission;
	private ImageIcon icon;

	DragSource ds;
	StringSelection transferable;

	public TripsPage(String name) {
		this.nameMission = name;

		try {
			initUI();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void createActionList() {

		model = new DefaultListModel();
		list = new JList(model);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setDragEnabled(true);
		list.setTransferHandler(new ListTransferHandler());

		for (Action a : Action.values())
			model.addElement(a.toString());

	}

	private void createTripList() {

		model1 = new DefaultListModel();
		tripList = new JList(model1);
		tripList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	}

	public final void initUI() throws IOException {

		setLayout(new BorderLayout());

		icon = new ImageIcon("Map/casa.gif");

		label = new JLabel(icon);
		label.setTransferHandler(new ListTransferHandler());

		createActionList();
		createTripList();
		JScrollPane actionsPane = new JScrollPane(list);
		JScrollPane tripsPane = new JScrollPane(tripList);

		setVisible(true);

		JPanel buttonsPane = new JPanel();
		ok = new JButton("Ok");

		delete = new JButton("Delete all trips");

		buttonsPane.add(ok);
		buttonsPane.add(delete);

		getContentPane().add(label, BorderLayout.WEST);
		getContentPane().add(actionsPane, BorderLayout.NORTH);
		getContentPane().add(buttonsPane, BorderLayout.SOUTH);
		getContentPane().add(tripsPane, BorderLayout.EAST);

		setTitle("Pluto-Trips Page (" + nameMission + ")");
		setSize(700, 600);
		setLocationRelativeTo(null);
	}

	public class ListTransferHandler extends TransferHandler {

		@Override
		public boolean canImport(TransferSupport support) {
			return (support.getComponent() instanceof JLabel)
					&& support
							.isDataFlavorSupported(ListItemTransferable.LIST_ITEM_DATA_FLAVOR);
		}

		@Override
		public boolean importData(TransferSupport support) {
			boolean accept = false;
			if (canImport(support)) {
				try {
					Transferable t = support.getTransferable();
					Object value = t
							.getTransferData(ListItemTransferable.LIST_ITEM_DATA_FLAVOR);
					if (value instanceof ListItem) {
						Component component = support.getComponent();
						if (component instanceof JLabel) {
							((JLabel) component).setText(((ListItem) value)
									.getText());
							accept = true;
						}
					}
				} catch (Exception exp) {
					exp.printStackTrace();
				}
			}
			return accept;
		}

		@Override
		public int getSourceActions(JComponent c) {
			return DnDConstants.ACTION_COPY_OR_MOVE;
		}

		@Override
		protected Transferable createTransferable(JComponent c) {
			Transferable t = null;
			if (c instanceof JList) {
				JList list = (JList) c;
				lalla = list.getSelectedValue();

				if (lalla instanceof ListItem) {
					ListItem li = (ListItem) lalla;
					t = new ListItemTransferable(li);
				}
			}
			return t;
		}

		@Override
		protected void exportDone(JComponent source, Transferable data,
				int action) {
			System.out.println("ExportDone");
			edp.actionPerformed();

		}
	}

	public static class ListItemTransferable implements Transferable {

		public static final DataFlavor LIST_ITEM_DATA_FLAVOR = new DataFlavor(
				ListItem.class, "java/ListItem");
		private ListItem listItem;

		public ListItemTransferable(ListItem listItem) {
			this.listItem = listItem;
		}

		@Override
		public DataFlavor[] getTransferDataFlavors() {
			return new DataFlavor[] { LIST_ITEM_DATA_FLAVOR };
		}

		@Override
		public boolean isDataFlavorSupported(DataFlavor flavor) {
			return flavor.equals(LIST_ITEM_DATA_FLAVOR);
		}

		@Override
		public Object getTransferData(DataFlavor flavor)
				throws UnsupportedFlavorException, IOException {

			return listItem;

		}
	}

	public static class ListItem {

		private String text;

		public ListItem(String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}

		@Override
		public String toString() {
			return getText();
		}
	}

	// drag and drop listeners

	public String showItemsPanel(List<String> items) {
		ArrayList<String> choices = new ArrayList<String>();
		for (String i : items)
			choices.add(i);

		String[] simpleArray = new String[choices.size()];
		choices.toArray(simpleArray);

		String input = (String) JOptionPane
				.showInputDialog(null, "Set the item", "Choose the item",
						JOptionPane.QUESTION_MESSAGE, null, simpleArray,
						simpleArray[0]);
		System.out.println(input);

		return input;
	}

	public int showPriorityPanel() {

		ArrayList<String> choosePriority = new ArrayList<String>();

		choosePriority.add("NORMAL");
		choosePriority.add("LOW");
		choosePriority.add("HIGH");
		choosePriority.add("VERY HIGH");
		choosePriority.add("VERY LOW");

		String[] simpleArray = new String[choosePriority.size()];
		choosePriority.toArray(simpleArray);

		String input1 = (String) JOptionPane
				.showInputDialog(null, "Set the priority for the trip ",
						"Choose the priority", JOptionPane.QUESTION_MESSAGE,
						null, simpleArray, simpleArray[0]);

		if (input1.toString() == "NORMAL")
			return 100;
		if (input1.toString() == "LOW")
			return 50;
		if (input1.toString() == "HIGH")
			return 150;
		if (input1.toString() == "VERY HIGH")
			return 200;
		if (input1.toString() == "VERY LOW")
			return 1;

		return 100;
	}

	public int showDelayPanel() {

		String text3 = JOptionPane
				.showInputDialog("Indicate the delay for the trip");
		
		String delay=text3.trim();

	
		
			
		return Integer.parseInt(delay);

	}

	public void fillTripList(String name) {
		model1.addElement(name);
	}

	// ok button

	public void setOkButtonListener(ActionListener listener) {
		ok.addActionListener(listener);

	}

	public void killWindow() {
		setVisible(false);
	}

	// delete button

	public void setDeleteAllButtonListener(ActionListener listener) {
		delete.addActionListener(listener);

	}

	public void deleteAllTrips() {
		// TODO
	}

	public void setExportDoneActionListener(ExportDoneListener listener) {
		edp = listener;

	}

	public String getAction() {
		return lalla.toString();
	}

}
