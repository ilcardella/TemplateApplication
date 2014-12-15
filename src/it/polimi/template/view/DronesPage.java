package it.polimi.template.view;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class DronesPage extends JFrame {

	public DronesPage() {

		initUI();
	}

	public final void initUI() {
		
		setLayout(new BorderLayout());

		String[] columnNames = { "ID", "Status", "Trip" };
		Object[][] data = { { "1", "BUSY", "A-B" },
				{ "2", "FREE", "___" }};
		
		JTable table = new JTable(data, columnNames);
		JScrollPane tablePane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		

		JTextArea text = new JTextArea();
		JScrollPane textPane = new JScrollPane(text);
		text.setBackground(Color.LIGHT_GRAY);
		text.append("console\n" +
				"Here there is the log of the execution\n" +
				"Pluto");
		text.setForeground(Color.RED);
		
		JPanel buttonsPane = new JPanel();
		JButton start = new JButton("Start");
		JButton stop = new JButton("Stop");
		buttonsPane.add(start);
		buttonsPane.add(stop);
		
		getContentPane().add(tablePane, BorderLayout.NORTH);
		getContentPane().add(buttonsPane,BorderLayout.EAST );
		getContentPane().add(textPane);
		


		setTitle("Pluto-Drones Page");
		setSize(1000, 800);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				DronesPage dp = new DronesPage();
				dp.setVisible(true);
			}
		});
	}
}

/*
 * public void printMenu() { System.out.println("Choose an option:\n" + "\n" +
 * "1 start missions\n" + "2 stop missions\n" + "3 return to launch\n");
 * 
 * }
 * 
 * public void rtl() { System.out.println("Choose the drone to recall\n");
 * 
 * }
 */

