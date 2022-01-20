package graphics;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import anadocteur.ClientTCP;

import config.Configuration;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class OperationZone extends JPanel {
	private final static Dimension SizeInformationZone = new Dimension(Configuration.OPERATIONZONE_WIDTH,Configuration.OPERATIONZONE_HEIGHT);

	private ClientTCP clientTCP;
	private OP_RDV op_RDV;
	private OP_Consultation op_Consultation;
	private OP_Patient op_Patient;
	/**
	 * Create the panel.
	 * @param clientTCP 
	 */
	public OperationZone( ClientTCP clientTCP) {
		this.clientTCP = clientTCP;
	}
	public void Information(Dashboard dashboard){
		OperationZone instance = this;
		instance.setBackground(Color.GRAY);
		instance.setBorder(BorderFactory.createLoweredBevelBorder());
		instance.setPreferredSize(SizeInformationZone);
		
		
		op_Patient = new OP_Patient(dashboard);
		op_RDV = new OP_RDV(dashboard);
		op_Consultation=new OP_Consultation(dashboard);
		

		instance.add(op_Patient);
		instance.add(op_RDV);
		instance.add(op_Consultation);
	}

	public OP_RDV getOp_RDV() {
		return op_RDV;
	}
	public OP_Patient getOp_Patient() {
		return op_Patient;
	}
	public  OP_Consultation getOp_Consultation() {
		return op_Consultation;
	}
	public void setOP_RDV(){
		this.add(op_RDV);
	}
	public void setOP_Patient(){
		this.add(op_Patient);
	}
	public void setOP_Consultation(){
		this.add(op_Consultation);
	}
	

}
