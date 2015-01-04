package it.polimi.template.view;

import java.awt.BorderLayout;

import it.polimi.template.controller.DronesPageStartButtonListener;
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
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class DronesPage extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private ArrayList<Mission> missions = new ArrayList<Mission>();
	private ArrayList<Drone> drones = new ArrayList<Drone>();

	public DronesPage(ArrayList<Mission> missions, ArrayList<Drone> drones) {
		this.missions = missions;
		this.drones = drones;
		initUI();
	}

	public final void initUI() {

		setLayout(new BorderLayout());

		DefaultTableModel model = new DefaultTableModel();
		final JTable table = new JTable(model);
		model.addColumn("ID");
		model.addColumn("Trip");
		model.addColumn("Status");
		final MissionsPageOkButtonListener mpob = new MissionsPageOkButtonListener();
		final DronesPageStartButtonListener dpsbl = new DronesPageStartButtonListener();

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

				for (final Mission m : missions) {
					mpob.startDroneAllocator(m, drones);
					dpsbl.StartTripLauncher(m);
					

					for (Trip t : m.getTrips()) {
						
						if(m.getUsed()==false){

						DefaultTableModel model = (DefaultTableModel) table
								.getModel();

						if (t.getDrone() == null)
							model.addRow(new Object[] { "", t.getName(),
									t.getStatus() });
						else
							model.addRow(new Object[] { t.getDrone().getId(),
									t.getName(), t.getStatus() });
						
						}

					}

					m.setUsed(true);

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

}
