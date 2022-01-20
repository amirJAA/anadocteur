package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.AttributeSet.ColorAttribute;

import config.Configuration;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InformationZone extends JPanel {
	private Font font = new Font(Font.MONOSPACED, Font.ITALIC,25);
	private final static Dimension SizeInformationZone = new Dimension(Configuration.INFORMATIONZONE_WIDTH,50);
	private Date date = new Date();
	private SimpleDateFormat dateFormat = new SimpleDateFormat("EEEEE dd MMMMM yyyy") ;
	/**
	 * Create the panel.
	 */
	public InformationZone() {
	}
	public void Information(){
		String strdate = dateFormat.format(new Date());
		JLabel dateLabel = new JLabel(strdate);
		dateLabel.setFont(font);
		dateLabel.setForeground(Color.white);
		InformationZone instance = this;
		instance.setBackground(Color.GRAY);
		instance.setBorder(BorderFactory.createLoweredBevelBorder());
		instance.setPreferredSize(SizeInformationZone);
		instance.add(dateLabel);
	}
}
