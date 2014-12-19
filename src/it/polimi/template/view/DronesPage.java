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

public class DronesPage extends JFrame {
	
	private ArrayList<Trip> trips=new ArrayList<Trip>();
	private ArrayList<Mission> missions=new ArrayList<Mission>();
	private ArrayList<Drone> drones=new ArrayList<Drone>();


	public DronesPage(ArrayList<Trip> trips, ArrayList<Mission> missions,ArrayList<Drone> drones) {
		this.trips=trips;
		this.missions=missions;
		this.drones=drones;
		initUI();
	}

	public final void initUI() {

		setLayout(new BorderLayout());

		JTable table = new JTable(new MyTableModel());
		final MissionsPageOkButtonListener mpob= new MissionsPageOkButtonListener();

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
				
				for (Mission m: missions){
					mpob.startDroneAllocator(m, drones);
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

	class MyTableModel extends AbstractTableModel {
		private String[] columnNames = { "ID", "Trip", "Status"};

		private Object[][] data = { { "", trips.get(0).getName(), ""}
		

		};
		

		public int getColumnCount() {
			return columnNames.length;
		}

		public int getRowCount() {
			return data.length;
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}

		public Object getValueAt(int row, int col) {
			return data[row][col];
		}

		public Class getColumnClass(int c) {
			return getValueAt(0, c).getClass();
		}

		public void setValueAt(Object value, int row, int col) {
			data[row][col] = value;
			fireTableCellUpdated(row, col);
		}
		

	}

}
