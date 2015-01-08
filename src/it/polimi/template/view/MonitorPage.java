package it.polimi.template.view;

import java.awt.BorderLayout;

import it.polimi.template.model.*;

import java.awt.Color;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class MonitorPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private ArrayList<Mission> missions = new ArrayList<Mission>();
	private ArrayList<Drone> drones = new ArrayList<Drone>();

	private JButton start;
	private JButton stop;
	private JTable table;

	public MonitorPage() {

		initUI();
	}

	public final void initUI() {

		final ArrayList<Trip> trips = new ArrayList<Trip>();

		setLayout(new BorderLayout());

		DefaultTableModel model = new DefaultTableModel();
		table = new JTable(model);
		model.addColumn("Mission");
		model.addColumn("Mission status");
		model.addColumn("Drone");
		model.addColumn("Drone status");
		model.addColumn("Trip");
		model.addColumn("Trip Status");

		JScrollPane tablePane = new JScrollPane(table);
		table.setFillsViewportHeight(true);

		JPanel buttonsPane = new JPanel();
		start = new JButton("Start");

		stop = new JButton("Stop");

		buttonsPane.add(start);
		buttonsPane.add(stop);

		getContentPane().add(tablePane, BorderLayout.NORTH);
		getContentPane().add(buttonsPane, BorderLayout.CENTER);

		setTitle("Pluto - Monitor Page");
		setSize(1000, 800);
		setLocationRelativeTo(null);
	}

	// start button

	public void setStartButtonListener(ActionListener listener) {
		start.addActionListener(listener);

	}

	// stop button

	public void setStopButtonListener(ActionListener listener) {
		stop.addActionListener(listener);

	}

	public void clearTable() {
		DefaultTableModel dm = (DefaultTableModel) table.getModel();
		int rowCount = dm.getRowCount();
		// Remove rows one by one from the end of the table
		for (int i = rowCount - 1; i >= 0; i--) {
			dm.removeRow(i);
		}
	}

	public void updateTableRow(String missionName, int missionStatus,
			int droneID, int droneStatus, String tripName, int tripStatus) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();

		int rowCount = model.getRowCount();

		if (rowCount == 0)
			model.addRow(new Object[] { missionName, missionStatus, droneID,
					droneStatus, tripName, tripStatus });

		for (int i = rowCount - 1; i >= 0; i--) {
			// if there is a row with the same missionName
			if (model.getValueAt(i, 0).equals(missionName))
				// if there is a row with the same trip
				if (model.getValueAt(i, 4).equals(tripName))
					// if the mission is not completed yet
					if (!model.getValueAt(i, 1).equals(Mission.COMPLETED))
						// if the trip is not completed yet
						if (!model.getValueAt(i, 5).equals(Trip.COMPLETED)) {
							// add the new row and delete the old one
							model.removeRow(i);
							model.addRow(new Object[] { missionName,
									missionStatus, droneID, droneStatus,
									tripName, tripStatus });
						}

		}
	}
}
