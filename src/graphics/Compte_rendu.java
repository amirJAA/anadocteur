package graphics;

import java.awt.Dimension;
import java.awt.FileDialog;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import anadocteur.ClientTCP;
import config.Configuration;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JScrollBar;

public class Compte_rendu extends JPanel {
	private final static Dimension SizeDashboard = new Dimension(Configuration.WINDOW_WIDTH,Configuration.WINDOW_HEIGHT);



	/**
	 * Create the panel.
	 * @param clientTCP 
	 * @throws IOException 
	 */
	public Compte_rendu(ClientTCP clientTCP,JFrame topframe) throws IOException {
		setBackground(new Color(0, 206, 209));
		setPreferredSize(SizeDashboard);
		setLayout(null);
		
		clientTCP.exeTcp("select;;id_patient;;patient");
		String res = clientTCP.getTcp();
   	 	String optionsToChoose[] = res.split(";;");
        
   	 	
   	 	
   	 	
   	 	JComboBox<String> addID = new JComboBox<>(optionsToChoose);
   	 	addID.setBounds(314, 96, 188, 48);
		add(addID);
		

		
		JButton btnNewButton = new JButton("Ajouter CR");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileDialog fd = new FileDialog(topframe,"Choose a file",FileDialog.LOAD);
				fd.setVisible(true);
				File[] f = fd.getFiles();
				String file="";
				if(f.length > 0){
					file =fd.getFiles()[0].getAbsolutePath();
				}

				clientTCP.exeTcp("insert;;compte_rendu;;"+addID.getItemAt(addID.getSelectedIndex())+";;"+file);
	
			}
		});
		btnNewButton.setBounds(314, 165, 188, 42);
		add(btnNewButton);
	
		
		clientTCP.exeTcp("select;;id_patient;;compte_rendu;;++;;++");
		String result = clientTCP.getTcp();

   	 	String optionsToChoose2[] = result.split(";;");
		
   	 	JComboBox<String> id_patient = new JComboBox<>(optionsToChoose2);
   	 	id_patient.setBounds(314, 296, 188, 48);
		add(id_patient);
	
		JButton btnNewButton1 = new JButton("recup\u00E9rer CR");
		btnNewButton1.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String home = System.getProperty("user.home");
				String file =home+"/Documents/";
				clientTCP.exeTcp("select;;*;;compte_rendu;;id_patient;;"+id_patient.getItemAt(id_patient.getSelectedIndex())+";;"+file);
			}
		});
		btnNewButton1.setBounds(314, 354, 188, 42);
		add(btnNewButton1);
		

		JLabel lblNewLabel = new JLabel("Recuperer le compte rendu du patienr de ID : ");
		lblNewLabel.setFont(new Font("Sitka Text", Font.BOLD, 20));
		lblNewLabel.setBounds(187, 249, 467, 53);
		add(lblNewLabel);
		

		JLabel lblAjouterUnCompte = new JLabel("Ajouter un compte rendu au patient de ID : ");
		lblAjouterUnCompte.setFont(new Font("Sitka Text", Font.BOLD, 20));
		lblAjouterUnCompte.setBounds(202, 46, 452, 53);
		add(lblAjouterUnCompte);

		setVisible(false);
	}
}
