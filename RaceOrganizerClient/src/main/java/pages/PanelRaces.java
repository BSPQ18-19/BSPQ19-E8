package pages;

import javax.swing.JLabel;
import javax.swing.JPanel;

import models.Race;
import models.User;

import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import java.awt.Color;

public class PanelRaces extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	public PanelRaces(Race r, User u) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{205, 39, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{14, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblEdition = new JLabel("Edition: "+r.getEdition());
		lblEdition.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblEdition = new GridBagConstraints();
		gbc_lblEdition.insets = new Insets(0, 0, 0, 5);
		gbc_lblEdition.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblEdition.gridx = 0;
		gbc_lblEdition.gridy = 1;
		add(lblEdition, gbc_lblEdition);
		
		JLabel lblDate = new JLabel("Date: "+r.getTime().toString());
		GridBagConstraints gbc_lblDate = new GridBagConstraints();
		gbc_lblDate.anchor = GridBagConstraints.WEST;
		gbc_lblDate.gridwidth = 3;
		gbc_lblDate.insets = new Insets(0, 0, 0, 5);
		gbc_lblDate.gridx = 1;
		gbc_lblDate.gridy = 1;
		add(lblDate, gbc_lblDate);
		
		JButton btnNewButton = new JButton("View");
		btnNewButton.setBackground(new Color(51, 102, 204));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridx = 5;
		gbc_btnNewButton.gridy = 1;
		add(btnNewButton, gbc_btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				RaceDetail rd=new RaceDetail(r, u);
				rd.setVisible(true);
			}
		});
	}
}
