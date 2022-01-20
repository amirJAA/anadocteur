package graphics;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JTextField;

import anadocteur.ClientTCP;

import config.Configuration;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import java.io.IOException;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.WindowListener;

import java.awt.event.WindowEvent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Window2 extends JFrame implements WindowListener, Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private InformationZone informationZone;
	private OperationZone operationZone;
	private final static Dimension SizeDashboard = new Dimension(Configuration.WINDOW_WIDTH, Configuration.WINDOW_HEIGHT);
	private Dashboard dashboard;
	private ClientTCP clientTCP ;
	private OP_Consultation op_Patient;
	private OP_RDV op_RDV;
	

	/**
	 * Create the application.
	 * @param port 
	 * @throws IOException 
	 */
	public Window2(String title, int port) throws IOException   {
		super(title);
		clientTCP = new ClientTCP(port);
		clientTCP.start();
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		addWindowListener(this);
		
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		

		
		dashboard = new Dashboard(clientTCP);
		informationZone = new InformationZone();
		operationZone = new OperationZone(clientTCP);


		informationZone.Information();
		contentPane.add(informationZone,BorderLayout.NORTH);

		dashboard.Information(this);
		contentPane.add(dashboard, BorderLayout.CENTER);
	
		
		operationZone.Information(dashboard);
		contentPane.add(operationZone,BorderLayout.WEST);
		
		

		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		//************************MENU GENERALE***************************************
		JMenu Menugenerale = new JMenu("Générale");
		menuBar.add(Menugenerale);
		
				JMenuItem menupatients = new JMenuItem("Patients");
				menupatients.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						operationZone.getOp_Patient().setVisible(true);
						operationZone.getOp_RDV().setVisible(false);
						operationZone.getOp_Consultation().setVisible(false);
						
						dashboard.getViewConsultation().setVisible(false);
						dashboard.getConnection().setVisible(true);
						dashboard.getShearch().setVisible(false);
						dashboard.getViewPatient().setVisible(false);
						dashboard.getCompte_rendu().setVisible(false);
						dashboard.getRdv().setVisible(false);
					}
				});
				Menugenerale.add(menupatients);
				
				JMenuItem menuconsultation = new JMenuItem("Consultation");
				menuconsultation.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						operationZone.getOp_Patient().setVisible(false);
						operationZone.getOp_RDV().setVisible(false);
						
						operationZone.getOp_Consultation().setVisible(true);
						
						dashboard.getViewConsultation().setVisible(true);
						dashboard.setViewConsultation();
						dashboard.getConnection().setVisible(false);
						dashboard.getShearch().setVisible(false);
						dashboard.getViewPatient().setVisible(false);
						dashboard.getCompte_rendu().setVisible(false);
						dashboard.getRdv().setVisible(false);
					}
				});
				Menugenerale.add(menuconsultation);
				
				JMenuItem menurdv = new JMenuItem("RDV");
				
				menurdv.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						operationZone.getOp_Patient().setVisible(false);
						operationZone.getOp_RDV().setVisible(true);
						operationZone.getOp_Consultation().setVisible(false);
						
						dashboard.getViewConsultation().setVisible(false);
						dashboard.getConnection().setVisible(false);
						dashboard.getShearch().setVisible(false);
						dashboard.getViewPatient().setVisible(false);
						dashboard.getCompte_rendu().setVisible(false);
						dashboard.getRdv().setVisible(true);
						dashboard.setRdv();
					}
				});
				Menugenerale.add(menurdv);
		

		pack();

		setVisible(true);
		Thread gameThread = new Thread((Runnable) this);
		gameThread.start();


		
	}
	
	
	@Override
	public void windowClosing(WindowEvent e) {
		try {
			clientTCP.close();
			System.out.println("fermeture du logicel coté client");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	

	@Override
	public void windowOpened(WindowEvent e) {
	}



	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowActivated(WindowEvent e) {	
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}


}
