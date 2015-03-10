package it.polimi.template.view;

import it.polimi.template.model.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polimi.template.controller.TripsPageController.DragAndDropListener;

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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.TransferHandler;

public class TripsPage extends JFrame implements DragSourceListener,
		DragGestureListener {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private DefaultListModel<String> actionListModel;
	private DefaultListModel<String> tripListModel;

	private JList<String> list;
	private JList<String> tripList;

	private DragAndDropListener dndListener;

	private JLabel label;
	private JButton ok;
	private JButton delete;
	private JButton deleteOne;
	private JTextField text;

	Map<String, String> tripsMap;
	private String nameMission;
	private String selectedAction;
	private ImageIcon icon;

	private String currentCoordinates;

	DragSource ds;
	StringSelection transferable;

	public TripsPage(String name, Map<String, String> map) {
		this.nameMission = name;
		this.tripsMap = map;

		try {
			initUI();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void createActionList() {
		actionListModel = new DefaultListModel<String>();
		list = new JList<String>(actionListModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setDragEnabled(true);
		list.setTransferHandler(new TransferHandler("text"));
		ds = new DragSource();
		ds.createDefaultDragGestureRecognizer(list, DnDConstants.ACTION_COPY,
				this);

		// add the action names
		for (Action a : Action.values())
			actionListModel.addElement(a.toString());
	}

	private void createTripList() {
		tripListModel = new DefaultListModel<String>();
		tripList = new JList<String>(tripListModel);
		tripList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tripList.setFixedCellWidth(200);
	}

	private void createTripsIconsOnMap() {
		for (String s : tripsMap.keySet()) {
			addTripToView(s);
		}
	}

	public final void initUI() throws IOException {

		setLayout(new BorderLayout());

		icon = new ImageIcon("Map/casa.gif");
		label = new JLabel(icon);

		createActionList();
		createTripList();
		createTripsIconsOnMap();

		JScrollPane actionsPane = new JScrollPane(list);
		JScrollPane tripsPane = new JScrollPane(tripList);
		JPanel imagePane = new JPanel();
		JPanel buttonsPane = new JPanel();
		JTextField instructions = new JTextField("Drag and Drop the desired Action on the Map");
		instructions.setForeground(Color.RED);
		instructions.setBackground(Color.LIGHT_GRAY);
		instructions.setEditable(false);
		
		JPanel p = new JPanel(new BorderLayout());

		ok = new JButton("Ok");
		delete = new JButton("Delete all");
		deleteOne = new JButton("Delete trip");

		imagePane.add(label);
		imagePane.setTransferHandler(new TransferHandler("text"));
		new MyDropTargetListener(imagePane);

		MouseListener listener = new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				JComponent c = (JComponent) e.getSource();
				TransferHandler th = c.getTransferHandler();
				th.exportAsDrag(c, e, TransferHandler.COPY);
			}
		};
		imagePane.addMouseListener(listener);

		buttonsPane.add(ok);
		buttonsPane.add(delete);
		buttonsPane.add(deleteOne);
		
		p.add(buttonsPane, BorderLayout.NORTH);
		p.add(instructions, BorderLayout.SOUTH);


		getContentPane().add(imagePane, BorderLayout.WEST);
		getContentPane().add(actionsPane, BorderLayout.NORTH);
		getContentPane().add(p, BorderLayout.SOUTH);
		getContentPane().add(tripsPane, BorderLayout.EAST);

		setTitle("Pluto-Trips Page (" + nameMission + ")");
		setSize(700, 600);
		setVisible(true);

	}

	@Override
	public void dragGestureRecognized(DragGestureEvent dge) {
		System.out.println("Drag Gesture Recognized!");
		transferable = new StringSelection(list.getSelectedValue());
		ds.startDrag(dge, DragSource.DefaultCopyDrop, transferable, this);
	}

	private class MyDropTargetListener extends DropTargetAdapter {

		public MyDropTargetListener(JPanel panel) {
			new DropTarget(panel, DnDConstants.ACTION_COPY, this);
		}

		@Override
		public void drop(DropTargetDropEvent event) {

			try {
				Transferable tr = event.getTransferable();
				selectedAction = (String) tr
						.getTransferData(DataFlavor.stringFlavor);

				if (event.isDataFlavorSupported(DataFlavor.stringFlavor)) {

					event.acceptDrop(DnDConstants.ACTION_COPY);

					// update the location of dnd
					int locX = (int) event.getLocation().getX();
					int locY = (int) event.getLocation().getY();
					currentCoordinates = locX + "/" + locY;

					// update the hashmap
					// tripsMap.put(getSelectedTrip(), locX+"/"+locY);

					// notify the controller of the drag and drop
					dndListener.actionPerformed();

					event.dropComplete(true);

				}
			} catch (Exception e) {
				e.printStackTrace();
				event.rejectDrop();
			}

		}

	}

	@Override
	public void dragEnter(DragSourceDragEvent dsde) {
	}

	@Override
	public void dragOver(DragSourceDragEvent dsde) {
	}

	@Override
	public void dropActionChanged(DragSourceDragEvent dsde) {
	}

	@Override
	public void dragExit(DragSourceEvent dse) {
	}

	@Override
	public void dragDropEnd(DragSourceDropEvent dsde) {
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
				.showInputDialog("Indicate the delay for the trip (seconds)");

		String delay = text3.trim();

		if (delay.isEmpty())
			return 0;

		else
			return Integer.parseInt(delay);

	}

	public void addTripToHashMap(String name, String coords) {
		// update the hashmap
		if (!tripsMap.containsKey(name)) {
			tripsMap.put(name, coords);
		} else {
			tripsMap.remove(name);
			tripsMap.put(name, coords);
		}
	}

	public void removeTripFromHashMap(String name) {
		if (tripsMap.containsKey(name))
			tripsMap.remove(name);
	}

	public void addTripToView(String name) {

		// getting coordinates from hashmap
		String[] coords = tripsMap.get(name).split("/");
		int locX = Integer.parseInt(coords[0]);
		int locY = Integer.parseInt(coords[1]);

		// update the map
		text = new JTextField(name.split("-")[1]);
		text.setBounds(locX, locY, 15, 20);
		text.setEditable(false);
		text.setBackground(Color.BLUE);
		text.setForeground(Color.CYAN);
		label.add(text);

		// add the trip to the tripList
		tripListModel.addElement(name);
	}

	public void setDragAndDropListener(DragAndDropListener listener) {
		dndListener = listener;

	}

	public String getAction() {
		return selectedAction;
	}

	// ok button

	public void setOkButtonListener(ActionListener listener) {
		ok.addActionListener(listener);

	}

	public void killWindow() {
		setVisible(false);
	}

	// delete all button

	public void setDeleteAllButtonListener(ActionListener listener) {
		delete.addActionListener(listener);

	}

	public void deleteAllTripsFromView() {
		// clear the hashmap
		tripsMap.clear();
		// clear the trip list
		tripListModel.removeAllElements();
		// clear the map
		label.removeAll();
		label.revalidate();
		label.repaint();
	}

	// delete one button

	public void setDeleteOneButtonListener(ActionListener listener) {
		deleteOne.addActionListener(listener);

	}

	public String getSelectedTrip() {
		ListSelectionModel selmodel = tripList.getSelectionModel();
		int index = selmodel.getMinSelectionIndex();
		if (index >= 0) {

			String trip = tripListModel.getElementAt(index).toString();
			return trip;
		}
		return "";
	}

	public void deleteSelectedTripFromList() {
		ListSelectionModel selmodel = tripList.getSelectionModel();
		int index = selmodel.getMinSelectionIndex();
		tripListModel.remove(index);
	}

	public void deleteTripFromMap(String name) {
		String[] xy = tripsMap.get(name).split("/");
		int locX = Integer.parseInt(xy[0]);
		int locY = Integer.parseInt(xy[1]);
		label.remove(label.getComponentAt(locX, locY));
		label.revalidate();
		label.repaint();

	}

	public String getCoordinatesOfDroppedTrip() {
		// take the coords of the dropped trip and return them
		return currentCoordinates;
	}

}
