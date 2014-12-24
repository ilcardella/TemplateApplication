package it.polimi.template.view;

import java.awt.BorderLayout;

import it.polimi.template.controller.MissionsPageOkButtonListener;
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
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class DronesPage extends JFrame {

	private ArrayList<Trip> trips = new ArrayList<Trip>();
	private ArrayList<Mission> missions = new ArrayList<Mission>();
	private ArrayList<Drone> drones = new ArrayList<Drone>();

	public DronesPage(ArrayList<Trip> trips, ArrayList<Mission> missions,
			ArrayList<Drone> drones) {
		this.trips = trips;
		this.missions = missions;
		this.drones = drones;
		initUI();
	}

	public final void initUI() {

		setLayout(new BorderLayout());

		DefaultTableModel model= new DefaultTableModel();
		final JTable table = new JTable(model);
		model.addColumn("ID");
		model.addColumn("Trip");
		model.addColumn("Status");
		final MissionsPageOkButtonListener mpob = new MissionsPageOkButtonListener();

		JScrollPane tablePane = new JScrollPane(table);
		table.setFillsViewportHeight(true);

		JTextArea text = new JTextArea();
		JScrollPane textPane = new JScrollPane(text);
		text.setBackground(Color.LIGHT_GRAY);
		text.append("console\n" + "Here there is the log of the execution\n"
				+ "Pluto");
		text.setForeground(Color.RED);

		JPanel buttonsPane = new JPanel();
		JButton start = new JButton("Start");
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				for (Mission m: missions) {
					mpob.startDroneAllocator(m, drones);

					for (Trip t : trips) {
						DefaultTableModel model = (DefaultTableModel) table
								.getModel();
						model.addRow(new Object[] { t.getDrone().getId(),
								t.getName(), "" });
					}
				}

			}
		});
		JButton stop = new JButton("Stop");
		buttonsPane.add(start);
		buttonsPane.add(stop);

		getContentPane().add(tablePane, BorderLayout.NORTH);
		getContentPane().add(buttonsPane, BorderLayout.EAST);
		getContentPane().add(textPane);

		setTitle("Pluto-Drones Page");
		setSize(1000, 800);
		setLocationRelativeTo(null);
	}

	/* class MyTableModel extends AbstractTableModel {
		
		private static final long serialVersionUID = 1L;

		private List<String> columnNames = new ArrayList();

		{
			columnNames.add("ID");
			columnNames.add("Trip");
			columnNames.add("Status");

		}

		private List<List> data = new ArrayList();

		public int getColumnCount() {
			return columnNames.size();
		}

		public int getRowCount() {
			return data.size();
		}

		public String getColumnName(int col) {
			return columnNames.get(col);
		}

		public Object getValueAt(int row, int col) {
			return data.get(row).get(col);
		}

		public boolean isCellEditable(int row, int col) {
			return false;
		}

		public Class getColumnClass(int c) {
			return getValueAt(0, c).getClass();
		}

		public void addRow(List rowData) {

			data.add(rowData);
			fireTableRowsInserted(data.size() - 1, data.size() - 1);

		}

	};*/

}
