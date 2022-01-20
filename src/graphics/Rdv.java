package graphics;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import anadocteur.ClientTCP;
import config.Configuration;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Rdv extends JPanel {
	private final static Dimension SizeDashboard = new Dimension(Configuration.WINDOW_WIDTH,Configuration.WINDOW_HEIGHT);
	private JTextField id;
	private JTable table;
	private JScrollPane scrollPane;
	private DefaultTableModel model;
	private JLabel lblRechercherLePatientpar;
	private JButton btnNewButton_1;
	private ClientTCP clientTCP;
	private int i;

	/**
	 * Create the panel.
	 * @param clientTCP 
	 * @throws IOException 
	 */
	public Rdv( ClientTCP clientTCP) throws IOException {
		setBackground(new Color(0, 206, 209));
		setPreferredSize(SizeDashboard);
		this.clientTCP = clientTCP;
		setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 233, 880, 346);
		add(scrollPane);
		
		table = new JTable();
		table.setBackground(new Color(176, 224, 230));
		model =new DefaultTableModel();
		Object[] column = {"Date","Heure"};
		Object[] row = new Object[2];
		


		
		
		
		
		
		JLabel lblNewLabel = new JLabel("affichez tous les cr\u00E9neaux libre \u00E0 partir de la date courante");
		lblNewLabel.setFont(new Font("SimSun", Font.BOLD, 20));
		lblNewLabel.setBounds(129, -15, 723, 93);
		add(lblNewLabel);
		

   	 	


		
		
		
		JButton libre = new JButton("Recherche");
		libre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setColumnIdentifiers(column);
				table.setModel(model);
				
				scrollPane.setViewportView(table);
				for (int i = model.getRowCount()-1; i >= 0; i--) {
					model.removeRow(i);
				}
				clientTCP.exeTcp("select;;*;;rdv");
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
		
		libre.setFont(new Font("Tahoma", Font.BOLD, 15));
		libre.setBounds(365, 52, 217, 41);
		add(libre);
		
		lblRechercherLePatientpar = new JLabel("affichez la liste des RDV occup\u00E9 a partire de la date courante");
		lblRechercherLePatientpar.setFont(new Font("SimSun", Font.BOLD, 20));
		lblRechercherLePatientpar.setBounds(123, 95, 692, 93);
		add(lblRechercherLePatientpar);
		
		
		Object[] column2 = {"Prénom","Nom","Date","Heure"};
		Object[] row2 = new Object[5];
		


		
		JButton occuper = new JButton("Recherche");
		occuper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setColumnIdentifiers(column2);
				table.setModel(model);
				
				scrollPane.setViewportView(table);
				
				for (int i = model.getRowCount()-1; i >= 0; i--) {
					model.removeRow(i);
				}
				clientTCP.exeTcp("select;;occuper;;rdv");
				String chaine = clientTCP.getTcp();
		   	 	String ligne[] = chaine.split(";;");

		   	 	for (String str:ligne) {
			   	 	String element[] = str.split("#");
					for(String elt:element) {
				   	 	row2[i]=elt;
				   	 	i++;
					}
					i=0;
					model.addRow(row2);
		   	 	}
			}
		});
		occuper.setFont(new Font("Tahoma", Font.BOLD, 15));
		occuper.setBounds(365, 172, 217, 41);
		add(occuper);
		setVisible(false);
	}
}
