package graphics;

import javax.swing.JPanel;

import config.Configuration;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class OP_Consultation extends JPanel {
	//private Dashboard dashboard;
	private Connection 	connection ;
	private Shearch shearch;
	private ViewPatient vpatient ;
	private ViewConsultation vconsultation;
	private Rdv rdv;
	private Compte_rendu compte_rendu;
	private final static Dimension SizeOperationZone = new Dimension(Configuration.OPERATIONZONE_WIDTH,Configuration.OPERATIONZONE_HEIGHT);
	/**
	 * Create the panel.
	 */
	public OP_Consultation(Dashboard dashboard) {
		connection = dashboard.getConnection();
		shearch = dashboard.getShearch();
		vpatient = dashboard.getViewPatient();		
		compte_rendu = dashboard.getCompte_rendu();
		rdv=dashboard.getRdv();
		vconsultation=dashboard.getViewConsultation();
		
		
		compte_rendu.setVisible(false);
		shearch.setVisible(false);
		vpatient.setVisible(false);
		connection.setVisible(false);
		
		
		setPreferredSize(SizeOperationZone);
		setBackground(Color.GRAY);
		setLayout(null);
		setVisible(false);

		

	}
}
