package graphics;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;

public class Window {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 929, 623);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 28, 915, 548);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Pr\u00E9nom");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblNewLabel.setBounds(10, 30, 214, 50);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(259, 113, 214, 50);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nom");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblNewLabel_1.setBounds(10, 112, 214, 50);
		panel.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(259, 30, 214, 50);
		panel.add(textField_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Numero");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblNewLabel_1_1.setBounds(10, 268, 214, 50);
		panel.add(lblNewLabel_1_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(259, 269, 214, 50);
		panel.add(textField_2);
		
		JLabel lblDateDeNaissance = new JLabel("Date de naissance");
		lblDateDeNaissance.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblDateDeNaissance.setBounds(10, 186, 214, 50);
		panel.add(lblDateDeNaissance);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(259, 186, 214, 50);
		panel.add(textField_3);
		
		JLabel lblNewLabel_1_2 = new JLabel("Mot de passe");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblNewLabel_1_2.setBounds(10, 429, 214, 50);
		panel.add(lblNewLabel_1_2);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(259, 430, 214, 50);
		panel.add(textField_4);
		
		JLabel lblAdresseMail = new JLabel("Adresse mail");
		lblAdresseMail.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblAdresseMail.setBounds(10, 347, 214, 50);
		panel.add(lblAdresseMail);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(259, 347, 214, 50);
		panel.add(textField_5);
		
		JButton btnNewButton = new JButton("ajout patient");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnNewButton.setBounds(645, 347, 225, 50);
		panel.add(btnNewButton);
		
		JButton btnNetoiez = new JButton("nettoyer la saisie");
		btnNetoiez.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnNetoiez.setBounds(645, 429, 225, 50);
		panel.add(btnNetoiez);
		
		JLabel lblNewLabel_2 = new JLabel("Ajouter un patient dans la base de donn\u00E9");
		lblNewLabel_2.setBounds(595, 53, 262, 265);
		panel.add(lblNewLabel_2);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("TEST");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("TEST");
		mnNewMenu.add(mntmNewMenuItem);
	}
}
