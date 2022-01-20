package graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import anadocteur.ClientTCP;
import config.Configuration;

public class Connection extends JPanel {
	private final static Dimension SizeDashboard = new Dimension(Configuration.WINDOW_WIDTH,Configuration.WINDOW_HEIGHT);
	private JTextField prenom;
	private JTextField nom;
	private JTextField date_n;
	private JTextField numero;
	private JTextField email;
	private JPasswordField password;

	private ClientTCP clientTCP;
	/**
	 * Create the panel.
	 * @param clientTCP 
	 */

	public Connection( ClientTCP clientTCP) {

		this.clientTCP = clientTCP;
	}
	
	public void init() {
		Connection connection =this;
		connection.setPreferredSize(SizeDashboard);
		connection.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ajoutez un patient dans la base de donn\u00E9es");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(300, 10, 404, 39);
		connection.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("PRENOM");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(100, 75, 200, 40);
		connection.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("NOM");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(100, 150,200,40);
		connection.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("DATE DE NAISSANCE");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3.setBounds(100, 225, 200, 40);
		connection.add(lblNewLabel_3);
		
		JLabel lbl = new JLabel("mm/jj/aaaa");
		//lbl.setFont(new Font(15));
		lbl.setBounds(310, 250, 200, 40);
		connection.add(lbl);
		
		JLabel lblNewLabel_4 = new JLabel("NUMERO");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_4.setBounds(100, 300, 200, 40);
		connection.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("E-MAIL");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_5.setBounds(100, 375,200,40);
		connection.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("MOT DE PASSE");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_6.setBounds(100, 450, 200, 40);
		connection.add(lblNewLabel_6);
		
		//----------------------------------------------
		
		prenom = new JTextField();
		prenom.setBounds(310, 75, 250, 40);
		prenom.setFont(new Font("Tahoma", Font.BOLD, 15));
		connection.add(prenom);
		prenom.setColumns(10);
		
		nom = new JTextField();
		nom.setBounds(310,150, 250, 40);
		nom.setFont(new Font("Tahoma", Font.BOLD, 15));
		connection.add(nom);
		nom.setColumns(10);
		
		date_n = new JTextField();
		date_n.setBounds(310,225, 250, 40);
		date_n.setFont(new Font("Tahoma", Font.BOLD, 15));
		connection.add(date_n);
		date_n.setColumns(10);
		
		numero = new JTextField();
		numero.setBounds(310,300, 250, 40);
		numero.setFont(new Font("Tahoma", Font.BOLD, 15));
		connection.add(numero);
		numero.setColumns(10);
		
		email = new JTextField();
		email.setBounds(310, 375, 250, 40);
		email.setFont(new Font("Tahoma", Font.BOLD, 15));
		connection.add(email);
		email.setColumns(10);

		
		password = new JPasswordField();
		password.setBounds(310, 450, 250, 40);
		password.setFont(new Font("Tahoma", Font.PLAIN, 15));
		connection.add(password);
		password.setColumns(10);
		
		
		JButton add = new JButton("Ajouter");
		add.setBounds(310, 525, 250, 40);
		add.setFont(new Font("Tahoma", Font.PLAIN, 19));
		connection.add(add);
		
		
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clientTCP.exeTcp("insert;;patient;;"+prenom.getText()+";;"+nom.getText()+";;"+date_n.getText()+";;"+numero.getText()+";;"+email.getText()+";;"+password.getText());
			}
		});
		
		setVisible(true);

	}

}
