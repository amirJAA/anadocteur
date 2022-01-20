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

public class Shearch extends JPanel {
	private final static Dimension SizeDashboard = new Dimension(Configuration.WINDOW_WIDTH,Configuration.WINDOW_HEIGHT);

	private JTextField id;
	private JTable table;
	private JScrollPane scrollPane;
	private DefaultTableModel model;
	private JLabel lblRechercherLePatientpar;
	private JTextField nom;
	private JButton btnNewButton_1;
	private ClientTCP clientTCP;
	private int i;

	/**
	 * Create the panel.
	 * @param clientTCP 
	 * @throws IOException 
	 */
	public Shearch( ClientTCP clientTCP) throws IOException {
		setBackground(new Color(0, 206, 209));
		setPreferredSize(SizeDashboard);
		this.clientTCP = clientTCP;
		setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 321, 880, 258);
		add(scrollPane);
		
		table = new JTable();
		table.setBackground(new Color(176, 224, 230));
		model =new DefaultTableModel();
		Object[] column = {"ID","Prénom","Nom","Date de naissance","numero","e-mail","Mot de passe"};
		Object[] row = new Object[7];
		

		model.setColumnIdentifiers(column);
		table.setModel(model);
		
		scrollPane.setViewportView(table);
		
		
		
		
		
		JLabel lblNewLabel = new JLabel("Rechercher le patientpar son ID");
		lblNewLabel.setFont(new Font("SimSun", Font.BOLD, 18));
		lblNewLabel.setBounds(316, -15, 364, 93);
		add(lblNewLabel);
	
		
		clientTCP.exeTcp("select;;id_patient;;patient");
		String res = clientTCP.getTcp();
   	 	String optionsToChoose[] = res.split(";;");
        
   	 	JComboBox<String> id = new JComboBox<>(optionsToChoose);
   	 	id.setBounds(365, 59, 217, 36);
		add(id);
		
		

		
		
		
		JButton btnNewButton = new JButton("Recherche");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = model.getRowCount()-1; i >= 0; i--) {
					model.removeRow(i);
				}
				clientTCP.exeTcp("select;;*;;patient;;id_patient;;"+id.getItemAt(id.getSelectedIndex()));
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
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(365, 105, 217, 41);
		add(btnNewButton);
		
		lblRechercherLePatientpar = new JLabel("Rechercher le patientpar son Nom");
		lblRechercherLePatientpar.setFont(new Font("SimSun", Font.BOLD, 18));
		lblRechercherLePatientpar.setBounds(316, 150, 364, 93);
		add(lblRechercherLePatientpar);
		
		nom = new JTextField();
		nom.setFont(new Font("Tahoma", Font.BOLD, 15));
		nom.setColumns(10);
		nom.setBounds(365, 221, 217, 36);
		add(nom);
		
		btnNewButton_1 = new JButton("Recherche");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = model.getRowCount()-1; i >= 0; i--) {
					model.removeRow(i);
				}
				if(nom.getText().isEmpty())
					clientTCP.exeTcp("select;;*;;patient");
				else
					clientTCP.exeTcp("select;;*;;patient;;nom;;"+nom.getText());
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
		   	 	}
				
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_1.setBounds(365, 267, 217, 41);
		add(btnNewButton_1);
		setVisible(false);
	}
}
