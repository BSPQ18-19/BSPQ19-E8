package pages;

import javax.swing.*;

import models.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Dashboard extends JFrame {

	/**
	 *
	 */

	private static final long serialVersionUID = 1L;

	private JPanel contentpane;
	private JPanel panel_top;
	private JLayeredPane content;
	private int actualpane;
	private JButton btnMyUser;
	private JButton btnDashboard;
	private User actualuser;

	private JPanel paneldashboard;

	private JPanel panelRaces;

	private JButton btnRaces;

	private JButton buttonSearch;

	private JPanel panelSearchRaces;

	private JPanel panelmyUser;

	public static void main(String[] args) {
		Dashboard das = new Dashboard(null, null);
		das.setVisible(true);
	}

	public Dashboard(Login login, User user) {
		
		User user1=new User(1, "user", "Usuario", "Usuario", "user@email.com" , "1998/01/01");
		actualuser=user1;
		
		//actualuser=user;
		
		


		setBounds(100, 100, 1050, 855);
		contentpane = new JPanel();
		contentpane.setBackground(new Color(51, 102, 153));
		setContentPane(contentpane);
		GridBagLayout gbl_contentpane = new GridBagLayout();
		gbl_contentpane.columnWidths = new int[] { 300, 750 };
		gbl_contentpane.rowHeights = new int[] { 100, 755 };
		gbl_contentpane.columnWeights = new double[] { 1.0, 1.0 };
		gbl_contentpane.rowWeights = new double[] { 1.0, 1.0 };
		contentpane.setLayout(gbl_contentpane);

		/*
		 * Top Panel
		 */

		panel_top = new JPanel();
		panel_top.setBackground(new Color(255, 204, 51));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentpane.add(panel_top, gbc_panel);
		panel_top.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel_top.add(panel, BorderLayout.EAST);
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		JButton btnNewButton = new JButton("Log out");
		panel.add(btnNewButton);
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		JLabel lblRaceOrganizer = new JLabel("Race Organizer");
		lblRaceOrganizer.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblRaceOrganizer.setHorizontalAlignment(SwingConstants.CENTER);
		lblRaceOrganizer.setFont(new Font("Tahoma", Font.BOLD, 30));
		panel_top.add(lblRaceOrganizer);

		/*
		 * Content
		 */

		JLayeredPane content = new JLayeredPane();
		GridBagConstraints gbc_content = new GridBagConstraints();
		gbc_content.fill = GridBagConstraints.BOTH;
		gbc_content.gridx = 1;
		gbc_content.gridy = 1;
		contentpane.add(content, gbc_content);

		paneldashboard = new JPanel();
		paneldashboard.setBackground(Color.WHITE);
		content.setLayer(paneldashboard, 1);
		paneldashboard.setBounds(0, 0, 724, 719);
		JLabel lblDashboard_1 = new JLabel("Welcome "+actualuser.getFirst_name() );
		lblDashboard_1.setFont(new Font("Tahoma", Font.PLAIN, 26));
		paneldashboard.add(lblDashboard_1);
		actualpane = 0;
		
		
		panelRaces = new JPanel();
		panelRaces.setBackground(Color.WHITE);
		content.setLayer(panelRaces, 0);
		panelRaces.setBounds(0, 0, 724, 719);
		
		panelSearchRaces = new JPanel();
		panelSearchRaces.setBackground(Color.WHITE);
		content.setLayer(panelRaces, 0);
		panelSearchRaces.setBounds(0, 0, 724, 719);
		
		panelmyUser = new JPanel();
		panelmyUser.setBackground(Color.WHITE);
		content.setLayer(panelRaces, 0);
		panelmyUser.setBounds(0, 0, 724, 719);
		
		content.add(paneldashboard, new Integer(1));
		content.add(panelRaces, new Integer(0));
		content.add(panelSearchRaces, new Integer(0));
		content.add(panelmyUser, new Integer(0));

	
		/*
		 * Menu Panel
		 */

		JPanel menu_bar = new JPanel();
		menu_bar.setBackground(new Color(51, 102, 153));
		GridBagConstraints gbc_menu_bar = new GridBagConstraints();
		gbc_menu_bar.insets = new Insets(0, 0, 0, 5);
		gbc_menu_bar.fill = GridBagConstraints.BOTH;
		gbc_menu_bar.gridx = 0;
		gbc_menu_bar.gridy = 1;
		contentpane.add(menu_bar, gbc_menu_bar);
		GridBagLayout gbl_menu_bar = new GridBagLayout();
		gbl_menu_bar.columnWidths = new int[] { 25, 35, 200, 40 };
		gbl_menu_bar.rowHeights = new int[] { 60, 80, 80, 80, 80, 350 };
		gbl_menu_bar.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_menu_bar.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		menu_bar.setLayout(gbl_menu_bar);

		JLabel lblNewLabel = new JLabel("");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		menu_bar.add(lblNewLabel, gbc_lblNewLabel);

		btnDashboard = new JButton("Dashboard");
		btnDashboard.setForeground(Color.WHITE);
		btnDashboard.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnDashboard.setOpaque(false);
		btnDashboard.setContentAreaFilled(false);
		btnDashboard.setBorderPainted(false);
		GridBagConstraints gbc_btnDashboard = new GridBagConstraints();
		gbc_btnDashboard.anchor = GridBagConstraints.WEST;
		gbc_btnDashboard.insets = new Insets(0, 0, 5, 5);
		gbc_btnDashboard.gridx = 2;
		gbc_btnDashboard.gridy = 1;
		menu_bar.add(btnDashboard, gbc_btnDashboard);
		
		btnRaces = new JButton("My Races");
		btnRaces.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnRaces.setOpaque(false);
		btnRaces.setContentAreaFilled(false);
		btnRaces.setBorderPainted(false);
		GridBagConstraints gbc_btnRaces = new GridBagConstraints();
		gbc_btnRaces.anchor = GridBagConstraints.WEST;
		gbc_btnRaces.insets = new Insets(0, 0, 5, 5);
		gbc_btnRaces.gridx = 2;
		gbc_btnRaces.gridy = 2;
		menu_bar.add(btnRaces, gbc_btnRaces);

		buttonSearch = new JButton("Search Races");
		buttonSearch.setOpaque(false);
		buttonSearch.setFont(new Font("Tahoma", Font.PLAIN, 22));
		buttonSearch.setContentAreaFilled(false);
		buttonSearch.setBorderPainted(false);
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.anchor = GridBagConstraints.WEST;
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 2;
		gbc_button.gridy = 3;
		menu_bar.add(buttonSearch, gbc_button);

		btnMyUser = new JButton("My User");
		btnMyUser.setBackground(Color.WHITE);
		btnMyUser.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnMyUser.setOpaque(false);
		btnMyUser.setContentAreaFilled(false);
		btnMyUser.setBorderPainted(false);
		GridBagConstraints gbc_btnMyUser = new GridBagConstraints();
		gbc_btnMyUser.anchor = GridBagConstraints.WEST;
		gbc_btnMyUser.insets = new Insets(0, 0, 5, 5);
		gbc_btnMyUser.gridx = 2;
		gbc_btnMyUser.gridy = 4;
		menu_bar.add(btnMyUser, gbc_btnMyUser);
		
		
		
		
		btnDashboard.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				switch (actualpane) {
				case 1:
					btnDashboard.setForeground(Color.BLACK);
					content.setLayer(paneldashboard, 0);
					break;
				case 2:
					buttonSearch.setForeground(Color.BLACK);
					content.setLayer(panelSearchRaces, 0);
					break;
				case 3:
					btnMyUser.setForeground(Color.BLACK);
					content.setLayer(panelmyUser, 0);
					break;
				default:
					break;
				}
				content.setLayer(paneldashboard, 1);
				btnDashboard.setForeground(Color.WHITE);
				actualpane=0;
			}
		});
		
		
		btnRaces.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				switch (actualpane) {
				case 0:
					btnDashboard.setForeground(Color.BLACK);
					content.setLayer(paneldashboard, 0);
					break;
				case 2:
					buttonSearch.setForeground(Color.BLACK);
					content.setLayer(panelSearchRaces, 0);
					break;
				case 3:
					btnMyUser.setForeground(Color.BLACK);
					content.setLayer(panelmyUser, 0);
					break;
				default:
					break;
				}
				content.setLayer(panelRaces, 1);
				btnRaces.setForeground(Color.WHITE);
				actualpane=1;
			}
		});

			buttonSearch.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				switch (actualpane) {
				case 0:
					btnDashboard.setForeground(Color.BLACK);
					content.setLayer(paneldashboard, 0);
					break;
				case 1:
					btnRaces.setForeground(Color.BLACK);
					content.setLayer(panelRaces, 0);
					break;
				case 3:
					btnMyUser.setForeground(Color.BLACK);
					content.setLayer(panelmyUser, 0);
					break;
				default:
					break;
				}
				content.setLayer(panelSearchRaces, 1);
				buttonSearch.setForeground(Color.WHITE);
				actualpane=2;
				
			}
		});
		
			btnMyUser.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) {
					switch (actualpane) {
					case 0:
						btnDashboard.setForeground(Color.BLACK);
						content.setLayer(paneldashboard, 0);
						break;
					case 1:
						btnRaces.setForeground(Color.BLACK);
						content.setLayer(panelRaces, 0);
						break;
					case 2:
						buttonSearch.setForeground(Color.BLACK);
						content.setLayer(panelSearchRaces, 0);
						break;
					default:
						break;
					}
					content.setLayer(panelmyUser, 1);
					btnMyUser.setForeground(Color.WHITE);
					actualpane=3;
					
				}
			});
			

	}
}
