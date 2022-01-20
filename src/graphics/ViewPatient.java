package graphics;

import java.awt.Dimension;

import javax.swing.JPanel;

import anadocteur.ClientTCP;

import config.Configuration;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class ViewPatient extends JPanel {
	private DefaultTableModel model;
	private final static Dimension SizeDashboard = new Dimension(Configuration.WINDOW_WIDTH,Configuration.WINDOW_HEIGHT);
	private JTable table;
	private ClientTCP clientTCP;
	private int i=0;
	/**
	 * Create the panel.
	 * @param clientTCP 
	 */
	public ViewPatient(ClientTCP clientTCP) {
		setBackground(new Color(0, 206, 209));

		this.clientTCP=clientTCP;
		setPreferredSize(SizeDashboard);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Afficher la liste des patients");
		lblNewLabel.setFont(new Font("Sitka Text", Font.BOLD, 19));
		lblNewLabel.setBounds(311, 20, 436, 50);
		add(lblNewLabel);
		

		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 109, 880, 481);
		add(scrollPane);
		
		table = new JTable();
		table.setBackground(new Color(176, 224, 230));
		model =new DefaultTableModel();
		Object[] column = {"ID","Prénom","Nom","Date de naissance","numero","e-mail","Mot de passe"};
		Object[] row = new Object[7];
		
		
		model.setColumnIdentifiers(column);
		table.setModel(model);
		
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("AFFICHER");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = model.getRowCount()-1; i >= 0; i--) {
					model.removeRow(i);
				}
				//clientTCP.setTcp("select;;*;;patient;;++;;++");
				clientTCP.exeTcp("select;;*;;patient");
				String chaine = clientTCP.getTcp();
		   	 	String ligne[] = chaine.split(";;");

		   	 	for (String str:ligne) {
			   	 	String element[] = str.split("#");
					for(String elt:element) {
				   	 	row[i]=elt;
				   	 	i++;
					}
					i=0;
					model.addRow(row);
		   			//System.out.println ("Patient " + str) ;
		   	 	}
			}
		});

		btnNewButton.setBounds(378, 60, 119, 21);
		add(btnNewButton);
		
		setVisible(false);
	}

}
