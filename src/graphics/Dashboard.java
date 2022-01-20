package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.text.AttributeSet.ColorAttribute;
import javax.swing.text.View;

import anadocteur.ClientTCP;
import config.Configuration;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JTextField;

public class Dashboard extends JPanel {
	private final static Dimension SizeDashboard = new Dimension(Configuration.WINDOW_WIDTH,Configuration.WINDOW_HEIGHT);

	private  ClientTCP clientTCP;
	private Connection connection ;
	private Shearch shearch ;
	private ViewConsultation vconsultation;
	private Compte_rendu compte_rendu;
	private Rdv rdv;
	private ViewPatient vpatient;

	/**
	 * Create the panel.
	 * @param clientTCP 
	 */
	public Dashboard( ClientTCP clientTCP) {

		this.clientTCP = clientTCP;
		setLayout(new BorderLayout(0, 0));

	}
	public void Information(JFrame topframe) throws IOException{
		
		Dashboard instance = this;
		instance.setBorder(BorderFactory.createLoweredBevelBorder());
		instance.setPreferredSize(SizeDashboard);
		
		
		compte_rendu = new Compte_rendu( clientTCP,topframe);
		connection = new Connection(clientTCP);
		shearch = new Shearch(clientTCP);
		vpatient=new ViewPatient(clientTCP);
		rdv = new Rdv( clientTCP);
		vconsultation=new ViewConsultation( clientTCP);
		
		
		connection.init();
		instance.add(vconsultation);
		instance.add(connection);
	}
	public void setCompte_rendu() {
		this.add(compte_rendu);
	}
	public void setShearch() {
		this.add(shearch);
	}
	public void setConnection() {
		this.add(connection);
	}
	public void setViewPatient() {
		this.add(vpatient);
	}
	public void setViewConsultation() {
		this.add(vconsultation);
	}
	public void setRdv() {
		this.add(rdv);
	}
	public Connection getConnection() {
		return connection;
	}
	public ViewPatient getViewPatient() {
		return vpatient;
	}
	public ViewConsultation getViewConsultation() {
		return vconsultation;
	}
	public Shearch getShearch() {
		return shearch;
	}
	public Compte_rendu getCompte_rendu() {
		return compte_rendu;
	}
	public Rdv getRdv() {
		return rdv;
	}

}
