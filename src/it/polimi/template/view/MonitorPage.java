package it.polimi.template.view;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class MonitorPage extends JFrame {

	private static final long serialVersionUID = 1L;

	private JButton start;
	private JButton stop;
	private JTable table;
	private JTextArea text;

	public MonitorPage() {

		initUI();
	}

	public final void initUI() {

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

		text = new JTextArea();
		text.setBackground(Color.LIGHT_GRAY);
		text.setForeground(Color.RED);
		JScrollPane textPane = new JScrollPane(text);

		JPanel buttonsPane = new JPanel();
		start = new JButton("Start");
		stop = new JButton("Stop");

		buttonsPane.add(start);
		buttonsPane.add(stop);

		getContentPane().add(tablePane, BorderLayout.NORTH);

		getContentPane().add(buttonsPane, BorderLayout.EAST);
		getContentPane().add(textPane);

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

	public void clearConsole() {
		text.setText(null);
		text.revalidate();
		text.repaint();
	}

	public void fillConsole(final String log) {
		text.append(log);
	}

	public void showStopOptions() {

		String[] simpleArray = new String[2];
		simpleArray[0] = "RTL";
		simpleArray[1] = "Land";

		JOptionPane.showInputDialog(null, "Choose the action to perform ",
				"Choose the stop behaviour", JOptionPane.QUESTION_MESSAGE, null,
				simpleArray, simpleArray[0]);

	}

	public void updateTableRow(String missionName, String missionStatus,
			int droneID, String droneStatus, String tripName, String tripStatus) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();

		int rowCount = model.getRowCount();

		boolean exist = false;

		if (rowCount == 0) {
			model.addRow(new Object[] { missionName, missionStatus, droneID,
					droneStatus, tripName, tripStatus });
		}

		if (rowCount > 0) {

			for (int i = 0; i < rowCount; i++) {
				// if there is a row with the same missionName
				if (model.getValueAt(i, 0).equals(missionName)) {

					exist = true;
					model.setValueAt(missionName, i, 0);
					model.fireTableCellUpdated(i, 0);

					model.setValueAt(missionStatus, i, 1);
					model.fireTableCellUpdated(i, 1);

					model.setValueAt(droneID, i, 2);
					model.fireTableCellUpdated(i, 2);

					model.setValueAt(droneStatus, i, 3);
					model.fireTableCellUpdated(i, 3);

					model.setValueAt(tripName, i, 4);
					model.fireTableCellUpdated(i, 4);

					model.setValueAt(tripStatus, i, 5);
					model.fireTableCellUpdated(i, 5);
				}

			}
			if (exist == false)
				model.addRow(new Object[] { missionName, missionStatus,
						droneID, droneStatus, tripName, tripStatus });
		}

	}
}
