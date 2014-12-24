package it.polimi.template.view;

import it.polimi.template.controller.AddMissionButtonListener;
import it.polimi.template.controller.MissionsPageOkButtonListener;

import it.polimi.template.model.*;

import javax.swing.JFrame;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import static javax.swing.GroupLayout.Alignment.LEADING;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class MissionsPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private ArrayList<Mission> missions=new ArrayList<Mission>();
	private ArrayList<Drone> drones=new ArrayList<Drone>();
	private ArrayList<Item> items=new ArrayList<Item>();


	private DefaultListModel model;
	private JList list;
	private JButton remallbtn;
	private JButton addbtn;
	private JButton renbtn;
	private JButton delbtn;
	private JButton tpsbtn;
	private JButton okbtn;

	public MissionsPage(ArrayList<Mission> missions, ArrayList<Drone> drones, ArrayList<Item> items) {
		this.missions=missions;
		this.drones=drones;
		this.items=items;

		initUI();
	}

	private void createList() {

		model = new DefaultListModel();
		list = new JList(model);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		list.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				if (e.getClickCount() == 2) {
					int index = list.locationToIndex(e.getPoint());
					Object item = model.getElementAt(index);
					String text = JOptionPane.showInputDialog("Rename item",
							item);
					String newitem = null;
					if (text != null) {
						newitem = text.trim();
					} else {
						return;
					}

					if (!newitem.isEmpty()) {
						model.remove(index);
						model.add(index, newitem);
						ListSelectionModel selmodel = list.getSelectionModel();
						selmodel.setLeadSelectionIndex(index);
					}
				}
			}
		});
	}

	private void createButtons() {

		remallbtn = new JButton("Remove All");
		addbtn = new JButton("Add Mission");
		renbtn = new JButton("Rename");
		delbtn = new JButton("Delete");
		tpsbtn = new JButton("Set Trips");
		okbtn = new JButton("Ok");
		final AddMissionButtonListener ambl = new AddMissionButtonListener();

		addbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String text = JOptionPane.showInputDialog("Add a new mission");
				String missionName = null;

				if (text != null) {
					missionName = text.trim();
					ambl.createMissionWithName(missionName, missions);

				} else {
					return;
				}

				if (!missionName.isEmpty()) {
					model.addElement(missionName);
				}
			}
		});
		delbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {

				ListSelectionModel selmodel = list.getSelectionModel();
				int index = selmodel.getMinSelectionIndex();
				if (index >= 0) {
					model.remove(index);
				}
			}

		});

		renbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				ListSelectionModel selmodel = list.getSelectionModel();
				int index = selmodel.getMinSelectionIndex();
				if (index == -1) {
					return;
				}

				Object item = model.getElementAt(index);
				String text = JOptionPane.showInputDialog("Rename item", item);
				String newitem = null;

				if (text != null) {
					newitem = text.trim();
				} else {
					return;
				}

				if (!newitem.isEmpty()) {
					model.remove(index);
					model.add(index, newitem);
				}
			}
		});

		remallbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.clear();
			}
		});

		tpsbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				ListSelectionModel selmodel = list.getSelectionModel();
				int index = selmodel.getMinSelectionIndex();
				if (index == -1) {
					return;
				}

				Object item = model.getElementAt(index);
				String mission = item.toString();

				TripsPage tp = new TripsPage(mission, missions,drones,items);
				tp.setVisible(true);
			}
		});
		okbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				DronesPage dp = new DronesPage(missions,drones);
				dp.setVisible(true);
				
			}
		});

	}

	private void initUI() {

		createList();
		createButtons();
		JScrollPane scrollpane = new JScrollPane(list);
		Container pane = getContentPane();
		GroupLayout gl = new GroupLayout(pane);
		pane.setLayout(gl);

		gl.setAutoCreateContainerGaps(true);
		gl.setAutoCreateGaps(true);

		gl.setHorizontalGroup(gl
				.createSequentialGroup()
				.addComponent(scrollpane)
				.addGroup(
						gl.createParallelGroup().addComponent(addbtn)
								.addComponent(renbtn).addComponent(delbtn)
								.addComponent(remallbtn).addComponent(tpsbtn)
								.addComponent(okbtn)

				));

		gl.setVerticalGroup(gl
				.createParallelGroup(LEADING)
				.addComponent(scrollpane)
				.addGroup(
						gl.createSequentialGroup().addComponent(addbtn)
								.addComponent(renbtn).addComponent(delbtn)
								.addComponent(tpsbtn).addComponent(okbtn)
								.addComponent(remallbtn)

				)

		);

		gl.linkSize(addbtn, renbtn, delbtn, remallbtn, tpsbtn, okbtn);
		pack();


		setTitle("Pluto-Missions Page");
		setSize(1000, 800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
