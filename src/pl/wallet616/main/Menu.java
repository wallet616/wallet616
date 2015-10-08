package pl.wallet616.main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

public class Menu extends Main {
	
	// Menu Styles and Variables
	public static String menuStyleDis = "<html><div style='font-family: \"SansSerif\"; font-size: 12px; color: #000000; font-weight: bold; padding: 7px 10px 8px; width: 180px'>";
	public static String menuStyleEna = "<html><div style='font-family: \"SansSerif\"; font-size: 12px; color: #FFFFFF; font-weight: bold; background-color: #3B3B3B; padding: 7px 10px 8px; width: 180px'>";
	public static String[] menuComponents = {"Status", "TS3"};
	public static String menuCurrentPosition = menuComponents[0];
	private static JLabel[] labelList = new JLabel[menuComponents.length];
	
	// Menu Initialization
	public static void initialization() {
		for (int i = 0; i < menuComponents.length; i++)
		{
			labelList[i] = new JLabel(menuStyleDis + menuComponents[i]);
			leftTable.add(labelList[i]);
		}
		
		frame.setTitle("Wallet616 :: " + menuComponents[0] + " Panel");
		labelList[0].setText(menuStyleEna + menuComponents[0]);
		leftTable.updateUI();
		
		Status.main();
		
		// Status move 
		labelList[0].addMouseListener(new MouseAdapter() { 
			public void mouseClicked(MouseEvent me) {
				if (!menuCurrentPosition.equals(menuComponents[0]))
				{
					frame.setTitle("Wallet616 :: " + menuComponents[0] + " Panel");
					menuCurrentPosition = menuComponents[0];
					
					for (int i = 0; i < menuComponents.length; i++)
					{
						labelList[i].setText(menuStyleDis + menuComponents[i]);
					}
					labelList[0].setText(menuStyleEna + menuComponents[0]);
					
					Status.main();
					leftTable.updateUI();
				}
			}
		});
		
		// TS3 move
		labelList[1].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (!menuCurrentPosition.equals(menuComponents[1]))
				{
					frame.setTitle("Wallet616 :: " + menuComponents[1] + " Panel");
					menuCurrentPosition = menuComponents[1];
					
					for (int i = 0; i < menuComponents.length; i++)
					{
						labelList[i].setText(menuStyleDis + menuComponents[i]);
					}
					labelList[1].setText(menuStyleEna + menuComponents[1]);
					
					Ts3.main();
					leftTable.updateUI();
				}
			}
		});
	}

}
