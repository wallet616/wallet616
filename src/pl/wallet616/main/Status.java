package pl.wallet616.main;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Status extends Main {
	
	private static Boolean keyBeingEnetry;
	
	public static void main() {
		// Status Panel Initialization
		rightTable.removeAll();
		
		JPanel statusPanel = new JPanel();
		statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
		statusPanel.setBackground(grayColor);
		rightTable.add(statusPanel);
		
		// Connection Status
		JLabel connectionLabel = new JLabel(headerStyle + "Connection status");
		connectionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		statusPanel.add(connectionLabel);
		
		JPanel connectionTable = new JPanel();
		connectionTable.setLayout(new GridLayout(3, 2, 0, 0));
		connectionTable.setPreferredSize(new Dimension(386, 41));
		connectionTable.setMaximumSize(new Dimension(386, 41));
		connectionTable.setBackground(grayColor);
		statusPanel.add(connectionTable);
		
		JLabel connectStatus = new JLabel(greyStyle + "Server status");
		connectionTable.add(connectStatus);
		
		JLabel connectStatusRepeat = new JLabel(greyBoldStyle + "Unknow");
		connectionTable.add(connectStatusRepeat);
		
		JLabel connectSecurity = new JLabel(greyStyle + "Security health");
		connectionTable.add(connectSecurity);
		
		JLabel connectSecurityRepeat = new JLabel(greyBoldStyle + "Unknow");
		connectionTable.add(connectSecurityRepeat);
		
		JLabel connectLastTime = new JLabel(greyStyle + "Last time used");
		connectionTable.add(connectLastTime);
		
		JLabel connectLastTimeRepeat = new JLabel(greyBoldStyle + "Unknow");
		connectionTable.add(connectLastTimeRepeat);
		
		
		// Security Components
		JLabel securityLabel = new JLabel(headerStyle + "Security components");
		securityLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		statusPanel.add(securityLabel);
		
		JPanel securityTable = new JPanel();
		securityTable.setLayout(new GridLayout(2, 2, 0, 0));
		securityTable.setPreferredSize(new Dimension(386, 27));
		securityTable.setMaximumSize(new Dimension(386, 27));
		securityTable.setBackground(grayColor);
		statusPanel.add(securityTable);
		
		JLabel securityStatus = new JLabel(greyStyle + "Current security key");
		securityTable.add(securityStatus);
		
		JLabel securityStatusRepeat = new JLabel(greyBoldStyle + "Unreadable data file.");
		securityTable.add(securityStatusRepeat);
		
		JLabel securitySecurity = new JLabel(greyStyle + "Assign new key");
		securityTable.add(securitySecurity);
		
		JLabel securitySecurityRepeat = new JLabel(whiteStyle + "Enter key");
		securityTable.add(securitySecurityRepeat);
		
		// Threads
		Thread connectionSecurityThread = new Thread(){
			public void run(){
				securityStatusRepeat.setText(greyBoldStyle + DataRead.readData(0));
		    }
		};
		connectionSecurityThread.start();
		
		Thread connectionStatusThread = new Thread(){
			public void run(){
				if (Listener.run("security:status")) {
					connectStatusRepeat.setText(greyBoldStyle + "Online");
					
					if (Listener.run("security:key")) {
						connectSecurityRepeat.setText(greyBoldStyle + "Good");
					} else {
						connectSecurityRepeat.setText(greyBoldStyle + "Bad");
					}
				} else {
					connectStatusRepeat.setText(greyBoldStyle + "Offline");
				}
		    }
		};
		connectionStatusThread.start();
		
		keyBeingEnetry = false;
		
			// Key input
			securitySecurityRepeat.addMouseListener(new MouseAdapter() { 
				public void mouseClicked(MouseEvent me) {
					if (!keyBeingEnetry)
					{
						keyBeingEnetry = true;
						securitySecurityRepeat.setText(greyBoldStyle + "Enter key");
						
						JPanel securityKeyTable = new JPanel();
						securityKeyTable.setLayout(new GridBagLayout());
					    securityKeyTable.setPreferredSize(new Dimension(386, 32));
						securityKeyTable.setMaximumSize(new Dimension(386, 32));
						securityKeyTable.setBackground(grayColor);
						statusPanel.add(securityKeyTable);
						
						GridBagConstraints gbc = new GridBagConstraints();
						
						JTextField securityNewKey = new JTextField();
						securityKeyTable.add(securityNewKey);
						gbc.fill = GridBagConstraints.HORIZONTAL;
						gbc.gridx = 0;
						gbc.gridy = 0;
						gbc.weightx = 1;
						gbc.weighty = 1;
						gbc.gridwidth = 2;
						securityKeyTable.add(securityNewKey, gbc);
					    
						JLabel securityAssignNew = new JLabel(whiteStyle + "Assign");
					    gbc.fill = GridBagConstraints.HORIZONTAL;
						gbc.gridx = 0;
						gbc.gridy = 1;
						gbc.gridwidth = 1;
						securityKeyTable.add(securityAssignNew, gbc);
						
						JLabel securityCancelNew = new JLabel(whiteStyle + "Canel");
					    gbc.fill = GridBagConstraints.HORIZONTAL;
						gbc.gridx = 1;
						gbc.gridy = 1;
						gbc.gridwidth = 1;
						securityKeyTable.add(securityCancelNew, gbc);
						
						rightTable.updateUI();
						
						// Key input assign new
						securityAssignNew.addMouseListener(new MouseAdapter() { 
							public void mouseClicked(MouseEvent me) {
								if (!securityNewKey.getText().equals("")) {
									DataSave.saveData(0, securityNewKey.getText());
								}
								
								Status.main();
							} 
						});
						
						// Key input cancel
						securityCancelNew.addMouseListener(new MouseAdapter() { 
							public void mouseClicked(MouseEvent me) {
								Status.main();
							} 
						});
					}
				} 
			});
			
		rightTable.updateUI();
	}

}
