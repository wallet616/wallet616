package pl.wallet616.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {

	// Main Frames
	protected static JFrame frame;
	
	public JPanel container = new JPanel();
	public static JPanel leftTable = new JPanel();
	public static JPanel rightTable = new JPanel();
	
	// Style variables
	public static String headerStyle = "<html><div style='font-family: \"SansSerif\"; font-size: 11px; font-weight: bold; color: #FFFFFF; padding: 2px 5px; width: 180px'>";
	public static String greyStyle = "<html><div style='font-family: \"SansSerif\"; font-size: 9px; font-weight: normal; color: #DEDEDE'>";
	public static String greyBoldStyle = "<html><div style='font-family: \"SansSerif\"; font-size: 9px; font-weight: bold; color: #DEDEDE'>";
	public static String whiteStyle = "<html><div style='font-family: \"SansSerif\"; font-size: 9px; font-weight: bold; color: #FFFFFF'>";
	
	public static Font font = new Font("SansSerif", Font.PLAIN, 13);
	public static Color grayColor = new Color(59, 59, 59);
	
	// Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Startup
					new Main();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Main application launching
	public Main() {
		initialize();
	}

	// Initialize the contents of the frame.
	public void initialize() {
		
		// Frame
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Resources/ico.png")));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 400);
        frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		
		// Container panel
		container.setLayout((LayoutManager) new FlowLayout(FlowLayout.LEADING, 0, 0));
		container.setBackground(grayColor);
		frame.getContentPane().add(container);
		
		
		// Table to hold menu and content
		leftTable.setLayout(new BoxLayout(leftTable, BoxLayout.Y_AXIS));
		rightTable.setLayout(new BorderLayout());
		rightTable.setBorder(BorderFactory.createEmptyBorder());
		rightTable.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, grayColor));
		
		rightTable.setPreferredSize(new Dimension(394, 371));
		rightTable.setMaximumSize(new Dimension(394, 371));
		leftTable.setPreferredSize(new Dimension(200, 371));
		leftTable.setMaximumSize(new Dimension(200, 371));
		leftTable.setBackground(new Color(194, 0, 0));
		rightTable.setBackground(grayColor);
		
		container.add(leftTable);
		container.add(rightTable);
		
		Menu.initialization();
	}

}
