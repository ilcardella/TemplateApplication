package it.polimi.template.view;


import it.polimi.template.controller.AddTripOnMapListener;
import it.polimi.template.controller.StartMissionCreator;
import it.polimi.template.model.*;

import java.awt.BorderLayout;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

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
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;

public class TripsPage extends JFrame implements DragSourceListener,
		DragGestureListener {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private DefaultListModel model;
	private JList list;
	private String nameMission;
	private ArrayList<Mission> missions;
	private ArrayList<Trip> trips;

	
	DragSource ds;
	StringSelection transferable;
	


	public TripsPage(String name, ArrayList<Mission> m) {
		this.nameMission=name;
		this.missions=m;
		initUI();
	}

	private void createList() {
		

		model = new DefaultListModel();
		list = new JList(model);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		model.addElement("Pick item");
		model.addElement("Release item");
		model.addElement("Take a measure");
		model.addElement("Take a picture");

		list.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				if (e.getClickCount() == 2) {
					int index = list.locationToIndex(e.getPoint());
					Object item = model.getElementAt(index);
					String text = JOptionPane.showInputDialog(
							"Set the priority for this trip", item);
					String newitem = null;
					if (text != null) {
						newitem = text.trim();
					} else {
						return;
					}

				}
			}
		});
	}

	public final void initUI() {
		
		setLayout(new BorderLayout());

		JLabel label = new JLabel();
		label.setIcon(new ImageIcon("/home/bitcraze/Desktop/casa.gif"));
		label.setTransferHandler(new TransferHandler("text"));

		createList();
		JScrollPane actionsPane = new JScrollPane(list);
		ds = new DragSource();
		ds.createDefaultDragGestureRecognizer(list, DnDConstants.ACTION_COPY,
				this);
		setVisible(true);

		JPanel buttonsPane = new JPanel();
		JButton ok = new JButton("Ok");
	
		ok.addActionListener(new ActionListener() {
			StartMissionCreator sm = new StartMissionCreator();

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				sm.run(nameMission,missions,trips);
				
			}
		});
		JButton delete = new JButton("Delete");
		buttonsPane.add(ok);
		buttonsPane.add(delete);
		
		

		new MyDropTargetListener(label);
		MouseListener listener = new DragMouseAdapter();
		label.addMouseListener(listener);

		getContentPane().add(label, BorderLayout.SOUTH);
		getContentPane().add(actionsPane, BorderLayout.NORTH);
		getContentPane().add(buttonsPane, BorderLayout.WEST);

		setTitle("Pluto-Trips Page");
		setSize(700, 600);
		setLocationRelativeTo(null);
	}



	private class DragMouseAdapter extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			JComponent c = (JComponent) e.getSource();
			TransferHandler handler = c.getTransferHandler();
			handler.exportAsDrag(c, e, TransferHandler.COPY);
		}
	}

	@Override
	public void dragGestureRecognized(DragGestureEvent dge) {
	     

	     
		System.out.println("Drag Gesture Recognized!");
		transferable = new StringSelection(list.getSelectedValue().toString());
		ds.startDrag(dge, DragSource.DefaultCopyDrop, transferable, this);

	}
	
	private class MyDropTargetListener extends DropTargetAdapter {

		private DropTarget dropTarget;
		private JLabel label;

		public MyDropTargetListener(JLabel label) {
			this.label = label;

			dropTarget = new DropTarget(label, DnDConstants.ACTION_COPY, this,
					true, null);
		}

		@Override
		public void drop(DropTargetDropEvent event) {
			
			

			try {
				AddTripOnMapListener tp = new AddTripOnMapListener();
		          Transferable tr = event.getTransferable();
		          String action = (String) tr.getTransferData(DataFlavor.stringFlavor);

		            if (event.isDataFlavorSupported(DataFlavor.stringFlavor)) {

		              event.acceptDrop(DnDConstants.ACTION_COPY);
		              event.dropComplete(true);
		              
		              
		              String text = JOptionPane.showInputDialog("Add a name for the trip");
						String item = null;

						if (text != null) {
							item = text.trim();
							trips=tp.createTripWithName(item, nameMission,missions);
							
						}
		              
		              
		              
		              return;
		            }
		          event.rejectDrop();
		        } catch (Exception e) {
		          e.printStackTrace();
		          event.rejectDrop();
		        }
		      }
		}
	

	@Override
	public void dragEnter(DragSourceDragEvent dsde) {
		System.out.println("Drag Enter");

	}

	@Override
	public void dragOver(DragSourceDragEvent dsde) {
		System.out.println("Drag Over");

	}

	@Override
	public void dropActionChanged(DragSourceDragEvent dsde) {
		System.out.println("Drop Action Changed");

	}

	@Override
	public void dragExit(DragSourceEvent dse) {
		System.out.println("Drag Exit");

	}

	@Override
	public void dragDropEnd(DragSourceDropEvent dsde) {
		System.out.print("Drag Drop End: ");
		if (dsde.getDropSuccess()) {
			System.out.println("Succeeded");
		} else {
			System.out.println("Failed");
		}
	}
	 public String getNameMission() {
		return nameMission;
	}

	public void setNameMission(String nameMission) {
		this.nameMission = nameMission;
	}

	class TransferableString implements Transferable {
		 
		 
	    protected  DataFlavor stringFlavor =
	        new DataFlavor(String.class, "A String Object");
	    
	    public TransferableString(String color) { this.action = color; }

	    protected  DataFlavor[] supportedFlavors = {
	        DataFlavor.stringFlavor,
	    };
		 String action;

		@Override
		public DataFlavor[] getTransferDataFlavors() {
			return supportedFlavors;
		}

		@Override
		public boolean isDataFlavorSupported(DataFlavor flavor) {
			if (flavor.equals(DataFlavor.stringFlavor)) 
				return true;
			
			    return false;			
		}

		@Override
		public Object getTransferData(DataFlavor flavor)
				throws UnsupportedFlavorException, IOException {
			if (flavor.equals(DataFlavor.stringFlavor))
		         return action;
		    
		     else 
		         throw new UnsupportedFlavorException(flavor);			
		}
	}

}
