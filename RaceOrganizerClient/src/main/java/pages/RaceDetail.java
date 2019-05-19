package pages;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import models.Race;
import models.Runner;
import models.User;
import networking.RaceGateway;

public class RaceDetail extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;
	private boolean running=false;
	private boolean helper=false;

	public RaceDetail(Race r, User u) {
		Runner myrunner=null;
		for (Runner runner:r.getRunners()) {
			if (runner.getUser_id()==u.getUser_id()) {
				running=true;
				myrunner=runner;
			}
		}
		
		for (User user:r.getHelpers()) {
			if (user.getUser_id()==u.getUser_id()) {
				helper=true;
			}
		}
		
		
		setBounds(100, 100, 650, 493);
		contentPanel = new JPanel();
		contentPanel.setLayout(new CardLayout(0, 0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 153, 204));
		panel.setLayout(null);
		contentPanel.add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.WHITE));
		panel_1.setOpaque(false);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 59, 331, 374);
		panel.add(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.rowHeights = new int[] { 50, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30 };
		gbl_panel_1.columnWidths = new int[] { 30, 0, 250, 20 };
		gbl_panel_1.columnWeights = new double[] { 1.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0,
				0.0 };
		panel_1.setLayout(gbl_panel_1);

		JLabel lblEdition = new JLabel("Edition: "+r.getEdition());
		GridBagConstraints gbc_lblEdition = new GridBagConstraints();
		gbc_lblEdition.anchor = GridBagConstraints.WEST;
		gbc_lblEdition.insets = new Insets(0, 0, 5, 0);
		gbc_lblEdition.gridx = 2;
		gbc_lblEdition.gridy = 1;
		panel_1.add(lblEdition, gbc_lblEdition);

		JLabel lblSponsor = new JLabel("Sponsor: "+r.getSponsor());
		GridBagConstraints gbc_lblSponsor = new GridBagConstraints();
		gbc_lblSponsor.anchor = GridBagConstraints.WEST;
		gbc_lblSponsor.insets = new Insets(0, 0, 5, 0);
		gbc_lblSponsor.gridx = 2;
		gbc_lblSponsor.gridy = 2;
		panel_1.add(lblSponsor, gbc_lblSponsor);

		JLabel labelPlace = new JLabel("Place: "+r.getPlace());
		GridBagConstraints gbc_labelPlace = new GridBagConstraints();
		gbc_labelPlace.anchor = GridBagConstraints.WEST;
		gbc_labelPlace.insets = new Insets(0, 0, 5, 0);
		gbc_labelPlace.gridx = 2;
		gbc_labelPlace.gridy = 3;
		panel_1.add(labelPlace, gbc_labelPlace);

		JLabel labeltimedate = new JLabel("Time & Date: "+r.getTime().toString());
		GridBagConstraints gbc_labeltimedate = new GridBagConstraints();
		gbc_labeltimedate.anchor = GridBagConstraints.WEST;
		gbc_labeltimedate.insets = new Insets(0, 0, 5, 0);
		gbc_labeltimedate.gridx = 2;
		gbc_labeltimedate.gridy = 4;
		panel_1.add(labeltimedate, gbc_labeltimedate);

		JLabel lblPrice = new JLabel("Price: "+r.getPrice());
		GridBagConstraints gbc_lblPrice = new GridBagConstraints();
		gbc_lblPrice.anchor = GridBagConstraints.WEST;
		gbc_lblPrice.insets = new Insets(0, 0, 5, 0);
		gbc_lblPrice.gridx = 2;
		gbc_lblPrice.gridy = 5;
		panel_1.add(lblPrice, gbc_lblPrice);

		JLabel lblPrize = new JLabel("Prize: "+r.getPrize());
		GridBagConstraints gbc_lblPrize = new GridBagConstraints();
		gbc_lblPrize.anchor = GridBagConstraints.WEST;
		gbc_lblPrize.insets = new Insets(0, 0, 5, 0);
		gbc_lblPrize.gridx = 2;
		gbc_lblPrize.gridy = 6;
		panel_1.add(lblPrize, gbc_lblPrize);
		
		
		

		JButton btnRun = new JButton("Run");
		GridBagConstraints gbc_btnRun = new GridBagConstraints();
		gbc_btnRun.anchor = GridBagConstraints.WEST;
		gbc_btnRun.insets = new Insets(0, 0, 5, 0);
		gbc_btnRun.gridx = 2;
		gbc_btnRun.gridy = 10;
		panel_1.add(btnRun, gbc_btnRun);
		if(running) {
			btnRun.setEnabled(false);
			JLabel labelNumber = new JLabel("My Number: "+myrunner.getNumber());
			GridBagConstraints gbc_labelNumber = new GridBagConstraints();
			gbc_labelNumber.anchor = GridBagConstraints.WEST;
			gbc_labelNumber.insets = new Insets(0, 0, 5, 0);
			gbc_labelNumber.gridx = 2;
			gbc_labelNumber.gridy = 7;
			panel_1.add(labelNumber, gbc_labelNumber);
		}
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RaceGateway rgw = new RaceGateway();
				if (rgw.addHelperToRace(u, r, 1)) {
					JOptionPane.showMessageDialog(RaceDetail.this, "You've been registered to run ");
				} else {
					JOptionPane.showMessageDialog(RaceDetail.this, "There's been an error.");
				}

			}
		});
		btnRun.setBackground(Color.WHITE);

		JButton btnHelp = new JButton("Help");
		btnHelp.setBackground(Color.WHITE);
		GridBagConstraints gbc_btnHelp = new GridBagConstraints();
		gbc_btnHelp.anchor = GridBagConstraints.WEST;
		gbc_btnHelp.insets = new Insets(0, 0, 5, 0);
		gbc_btnHelp.gridx = 2;
		gbc_btnHelp.gridy = 11;
		panel_1.add(btnHelp, gbc_btnHelp);
		
		helper=true; //Always disable not gateway
		if(helper) {
			btnHelp.setEnabled(false);
		}
		
		
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RaceGateway rgw = new RaceGateway();
				if (rgw.addHelperToRace(u, r, 2)) {
					JOptionPane.showMessageDialog(RaceDetail.this, "You've been registered to heelp ");
				} else {
					JOptionPane.showMessageDialog(RaceDetail.this, "There's been an error.");
				}

			}
		});
		JPanel panel_3 = new JPanel();
		panel_3.setOpaque(false);

		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 2;
		gbc_panel_3.gridy = 14;
		panel_1.add(panel_3, gbc_panel_3);

		JLabel lblicon = new JLabel("");
		lblicon.setBounds(370, 85, 222, 212);
		panel.add(lblicon);
		lblicon.setHorizontalAlignment(SwingConstants.CENTER);
		lblicon.setIcon(new ImageIcon(Registration.class.getResource("/icons/icon.png")));

		JLabel lblRaceOrganizer = new JLabel("Create Race");
		lblRaceOrganizer.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblRaceOrganizer.setBounds(27, 22, 230, 27);
		panel.add(lblRaceOrganizer);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(351, 320, 257, 35);
		panel.add(panel_2);
		panel_2.setOpaque(false);

		JButton btnBack = new JButton("Back");
		panel_2.add(btnBack);
		btnBack.setBackground(new Color(255, 255, 255));
		btnBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				RaceDetail.this.setVisible(false);

			}
		});

	}
}
