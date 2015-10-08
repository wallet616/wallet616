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

public class Ts3 extends Main {
	
	// Variables
	static Boolean ts3ToolAwaits = false;
	static Boolean ts3serverStatus = null;
	static JPanel ts3ToolsTable;
	static JLabel connectStatusRepeat;
	
	public static void main() {
		// Status Panel Initialization
		rightTable.removeAll();
		
		JPanel statusPanel = new JPanel();
		statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
		statusPanel.setBackground(grayColor);
		rightTable.add(statusPanel);
		
		// Connection Status
		JLabel connectionLabel = new JLabel(headerStyle + "TS3 server status");
		connectionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		statusPanel.add(connectionLabel);
		
		JPanel connectionTable = new JPanel();
		connectionTable.setLayout(new GridLayout(1, 2, 0, 0));
		connectionTable.setPreferredSize(new Dimension(386, 12));
		connectionTable.setMaximumSize(new Dimension(386, 12));
		connectionTable.setBackground(grayColor);
		statusPanel.add(connectionTable);
		
		JLabel connectStatus = new JLabel(greyStyle + "Server status");
		connectionTable.add(connectStatus);
		
		connectStatusRepeat = new JLabel(greyBoldStyle + "Unknow");
		connectionTable.add(connectStatusRepeat);
		
		
		// Tools
		JLabel securityLabel = new JLabel(headerStyle + "Tools");
		securityLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		statusPanel.add(securityLabel);
		
		ts3ToolsTable = new JPanel();
		ts3ToolsTable.setLayout(new GridBagLayout());
		ts3ToolsTable.setPreferredSize(new Dimension(386, 40));
		ts3ToolsTable.setMaximumSize(new Dimension(386, 40));
		ts3ToolsTable.setBackground(grayColor);
		statusPanel.add(ts3ToolsTable);
		
		// Buttons
		actionButtons();
		
		rightTable.updateUI();
	}

	public static void actionButtons() {
		// Cleanup 
		ts3ToolsTable.removeAll();
		
		// Thread
		Thread ts3StatusThread = new Thread(){
			public void run(){
				if (Listener.run("ts3:status")) {
					connectStatusRepeat.setText(greyBoldStyle + "Online");
					ts3serverStatus = true;
				} else {
					connectStatusRepeat.setText(greyBoldStyle + "Offline");
					ts3serverStatus = false;
				}
				
				ts3ToolAwaits = false;
				rightTable.updateUI();
		    }
		};
		ts3StatusThread.start();
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		JLabel ts3StartTool = new JLabel(whiteStyle + "Start");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridwidth = 1;
		ts3ToolsTable.add(ts3StartTool, gbc);
		
		JLabel ts3StopTool = new JLabel(whiteStyle + "Stop");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridwidth = 1;
		ts3ToolsTable.add(ts3StopTool, gbc);
		
		JLabel ts3RestartTool = new JLabel(whiteStyle + "Restart");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridwidth = 1;
		ts3ToolsTable.add(ts3RestartTool, gbc);
		
		// Threads
		Thread ts3StartThread = new Thread(){
			public void run(){
				if (Listener.run("ts3:start")) {
					actionButtons();
				}
		    }
		};
		
		Thread ts3StopThread = new Thread(){
			public void run(){
				if (Listener.run("ts3:stop")) {
					actionButtons();
				}
		    }
		};
		
		Thread ts3RestartThread = new Thread(){
			public void run(){
				if (Listener.run("ts3:restart")) {
					actionButtons();
				}
		    }
		};
		
			// Buttons 
			ts3StartTool.addMouseListener(new MouseAdapter() { 
				public void mouseClicked(MouseEvent me) {
					// Refresh info
					if (!ts3ToolAwaits) {
						ts3StatusThread.run();
					}
					
					if (!ts3ToolAwaits && !ts3serverStatus)
					{
						ts3ToolAwaits = true;
						
						JLabel ts3StartToolConfirm = new JLabel(whiteStyle + "Confirm");
						gbc.fill = GridBagConstraints.HORIZONTAL;
						gbc.gridx = 1;
						gbc.gridy = 0;
						gbc.weightx = 1;
						gbc.weighty = 1;
						gbc.gridwidth = 1;
						ts3ToolsTable.add(ts3StartToolConfirm, gbc);
						
						JLabel ts3StartToolCancel = new JLabel(whiteStyle + "Cancel");
						gbc.fill = GridBagConstraints.HORIZONTAL;
						gbc.gridx = 2;
						gbc.gridy = 0;
						gbc.weightx = 1;
						gbc.weighty = 1;
						gbc.gridwidth = 1;
						ts3ToolsTable.add(ts3StartToolCancel, gbc);
						
						rightTable.updateUI();
						
						// Confirm
						ts3StartToolConfirm.addMouseListener(new MouseAdapter() { 
							public void mouseClicked(MouseEvent me) {
								ts3ToolsTable.removeAll();
								JLabel awaiting = new JLabel(greyStyle + "Processing...");
								ts3ToolsTable.add(awaiting);
								rightTable.updateUI();
								
								ts3StartThread.start();
							} 
						});
						
						// Cancel
						ts3StartToolCancel.addMouseListener(new MouseAdapter() { 
							public void mouseClicked(MouseEvent me) {
								actionButtons();
							} 
						});
					}
				} 
			});
			
			ts3StopTool.addMouseListener(new MouseAdapter() { 
				public void mouseClicked(MouseEvent me) {
					// Refresh info
					if (!ts3ToolAwaits) {
						ts3StatusThread.run();
					}
					
					if (!ts3ToolAwaits && ts3serverStatus)
					{
						ts3ToolAwaits = true;
						
						JLabel ts3StopToolConfirm = new JLabel(whiteStyle + "Confirm");
						gbc.fill = GridBagConstraints.HORIZONTAL;
						gbc.gridx = 1;
						gbc.gridy = 1;
						gbc.weightx = 1;
						gbc.weighty = 1;
						gbc.gridwidth = 1;
						ts3ToolsTable.add(ts3StopToolConfirm, gbc);
						
						JLabel ts3StopToolCancel = new JLabel(whiteStyle + "Cancel");
						gbc.fill = GridBagConstraints.HORIZONTAL;
						gbc.gridx = 2;
						gbc.gridy = 1;
						gbc.weightx = 1;
						gbc.weighty = 1;
						gbc.gridwidth = 1;
						ts3ToolsTable.add(ts3StopToolCancel, gbc);
						
						rightTable.updateUI();
						
						// Confirm
						ts3StopToolConfirm.addMouseListener(new MouseAdapter() { 
							public void mouseClicked(MouseEvent me) {
								ts3ToolsTable.removeAll();
								JLabel awaiting = new JLabel(greyStyle + "Processing...");
								ts3ToolsTable.add(awaiting);
								rightTable.updateUI();
								
								ts3StopThread.start();
							} 
						});
						
						// Cancel
						ts3StopToolCancel.addMouseListener(new MouseAdapter() { 
							public void mouseClicked(MouseEvent me) {
								actionButtons();
							} 
						});
					}
				} 
			});
			
			ts3RestartTool.addMouseListener(new MouseAdapter() { 
				public void mouseClicked(MouseEvent me) {
					// Refresh info
					if (!ts3ToolAwaits) {
						ts3StatusThread.run();
					}
					
					if (!ts3ToolAwaits && ts3serverStatus)
					{
						ts3ToolAwaits = true;
						
						JLabel ts3RestartToolConfirm = new JLabel(whiteStyle + "Confirm");
						gbc.fill = GridBagConstraints.HORIZONTAL;
						gbc.gridx = 1;
						gbc.gridy = 2;
						gbc.weightx = 1;
						gbc.weighty = 1;
						gbc.gridwidth = 1;
						ts3ToolsTable.add(ts3RestartToolConfirm, gbc);
						
						JLabel ts3RestartToolCancel = new JLabel(whiteStyle + "Cancel");
						gbc.fill = GridBagConstraints.HORIZONTAL;
						gbc.gridx = 2;
						gbc.gridy = 2;
						gbc.weightx = 1;
						gbc.weighty = 1;
						gbc.gridwidth = 1;
						ts3ToolsTable.add(ts3RestartToolCancel, gbc);
						
						rightTable.updateUI();
						
						// Confirm
						ts3RestartToolConfirm.addMouseListener(new MouseAdapter() { 
							public void mouseClicked(MouseEvent me) {
								ts3ToolsTable.removeAll();
								JLabel awaiting = new JLabel(greyStyle + "Processing...");
								ts3ToolsTable.add(awaiting);
								rightTable.updateUI();
								
								ts3RestartThread.start();
							} 
						});
						
						// Cancel
						ts3RestartToolCancel.addMouseListener(new MouseAdapter() { 
							public void mouseClicked(MouseEvent me) {
								actionButtons();
							} 
						});
					}
				} 
			});
			
		rightTable.updateUI();
	}

}
