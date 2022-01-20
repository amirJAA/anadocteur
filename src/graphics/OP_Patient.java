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

public class OP_Patient extends JPanel {
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
	public OP_Patient(Dashboard dashboard) {
		connection = dashboard.getConnection();
		shearch = dashboard.getShearch();
		vpatient = dashboard.getViewPatient();		
		compte_rendu = dashboard.getCompte_rendu();
		rdv=dashboard.getRdv();
		vconsultation=dashboard.getViewConsultation();
		setPreferredSize(SizeOperationZone);
		setBackground(Color.GRAY);
		setLayout(null);
		
		
		//************************************************
		JButton add = new JButton("Ajouter un patient");
		add.setFont(new Font("Sylfaen", Font.BOLD, 13));
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vpatient.setVisible(false);
				vconsultation.setVisible(false);
				shearch.setVisible(false);
				compte_rendu.setVisible(false);
				connection.setVisible(true);
				rdv.setVisible(false);
				dashboard.setConnection();
				dashboard.repaint();
			}
		});
		
		add.setBounds(10, 200, 180, 30);
		add(add);
		
		//***************************************************
		JButton view = new JButton("Voir liste des patient");
		view.setFont(new Font("Sylfaen", Font.BOLD, 13));
		view.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection.setVisible(false);
				shearch.setVisible(false);
				vconsultation.setVisible(false);
				compte_rendu.setVisible(false);
				rdv.setVisible(false);
				vpatient.setVisible(true);
				dashboard.setViewPatient();
				dashboard.repaint();
			}
		});
		view.setBounds(10, 300, 180, 30);
		add(view);

		
		JButton search = new JButton("recherche patient");
		search.setFont(new Font("Sylfaen", Font.BOLD, 13));
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vpatient.setVisible(false);
				connection.setVisible(false);
				vconsultation.setVisible(false);
				compte_rendu.setVisible(false);
				rdv.setVisible(false);
				shearch.setVisible(true);
				dashboard.setShearch();
				dashboard.repaint();
			}
		});
		search.setBounds(10, 400, 180, 30);
		add(search);
		
		JButton cr = new JButton("Ajouter un compte rendu");
		cr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vpatient.setVisible(false);
				connection.setVisible(false);
				vconsultation.setVisible(false);
				shearch.setVisible(false);
				compte_rendu.setVisible(true);
				rdv.setVisible(false);
				dashboard.setCompte_rendu();
				dashboard.repaint();
			}
		});
		cr.setFont(new Font("Sylfaen", Font.BOLD, 13));
		cr.setBounds(10, 100, 180, 30);
		add(cr);
		

	}
}
