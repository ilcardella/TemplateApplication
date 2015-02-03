package it.polimi.template.view;

import static javax.swing.GroupLayout.Alignment.LEADING;

import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class MissionsPage extends JFrame {

	private static final long serialVersionUID = 1L;

	private JList<String> list;
	private JButton remallbtn;
	private JButton addbtn;
	private JButton renbtn;
	private JButton delbtn;
	private JButton tpsbtn;
	private JButton okbtn;

	public MissionsPage() {
		initUI();
	}

	private void createList() {

		DefaultListModel<String> model = new DefaultListModel<String>();
		list = new JList<String>(model);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	private void createButtons() {

		remallbtn = new JButton("Remove All");
		addbtn = new JButton("Add Mission");
		renbtn = new JButton("Rename");
		delbtn = new JButton("Delete");
		tpsbtn = new JButton("Set Trips");
		okbtn = new JButton("Monitor Page");

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
								.addComponent(remallbtn).addComponent(tpsbtn)
								.addComponent(okbtn)

				)

		);

		gl.linkSize(addbtn, renbtn, delbtn, remallbtn, tpsbtn, okbtn);
		pack();

		setTitle("Pluto-Missions Page");
		setSize(1000, 800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	// add button

	public String showNewNamePanel(String name) {
		String text = JOptionPane.showInputDialog(name);
		String missionName = null;

		if (text != null) {
			missionName = text.trim();

		} else {
			return "null";
		}

		return missionName;
	}

	public boolean showRepeatPanel() {

		String[] simpleArray = new String[2];
		simpleArray[0] = "Yes";
		simpleArray[1] = "No";

		String selection = (String) JOptionPane
				.showInputDialog(null, "Do you want to repeat the mission?",
						"Mission Repeat", JOptionPane.QUESTION_MESSAGE, null,
						simpleArray, simpleArray[0]);
		if (selection.equals("Yes"))
			return true;

		return false;

	}

	public void setAddMissionButtonListener(ActionListener listener) {
		addbtn.addActionListener(listener);
	}

	public void addMissionToList(String name) {

		if (!name.isEmpty())
			((DefaultListModel<String>) list.getModel()).addElement(name);
	}

	// delete button

	public String getSelectedMission() {
		ListSelectionModel selmodel = list.getSelectionModel();
		int index = selmodel.getMinSelectionIndex();
		if (index >= 0) {

			String mission = ((DefaultListModel<String>) list.getModel())
					.getElementAt(index).toString();
			return mission;
		}
		return "";
	}

	public void removeSelectedMissionFromList() {

		ListSelectionModel selmodel = list.getSelectionModel();
		int index = selmodel.getMinSelectionIndex();

		((DefaultListModel<String>) list.getModel()).remove(index);
	}

	public void setDeleteMissionButtonListener(ActionListener listener) {
		delbtn.addActionListener(listener);
	}

	// remove all button

	public void clearMissionList() {

		((DefaultListModel<String>) list.getModel()).clear();
	}

	public void setRemoveAllMissionButtonListener(ActionListener listener) {
		remallbtn.addActionListener(listener);
	}

	// rename button

	public void setRenameButtonListener(ActionListener listener) {
		renbtn.addActionListener(listener);
	}

	public void renameSelectedMission(String name) {
		ListSelectionModel selmodel = list.getSelectionModel();
		int index = selmodel.getMinSelectionIndex();
		if (index >= 0) {
			((DefaultListModel<String>) list.getModel()).remove(index);
			((DefaultListModel<String>) list.getModel()).add(index, name);
		}

	}

	// set trips button

	public void setTripsButtonListener(ActionListener listener) {
		tpsbtn.addActionListener(listener);
	}

	// ok button

	public void setMissionsPageOkButtonListener(ActionListener listener) {
		okbtn.addActionListener(listener);

	}

	public void setListMouseListener(MouseListener listener) {
		list.addMouseListener(listener);
	}

	// monitor page start button

	public void removeMissionFromList(String name) {
		for (int i = 0; i < list.getModel().getSize(); i++) {
			if (list.getModel().getElementAt(i).equals(name)) {
				((DefaultListModel<String>) list.getModel()).remove(i);
				break;
			}
		}

	}

}
