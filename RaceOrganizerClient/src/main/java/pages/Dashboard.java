package pages;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import models.User;
import networking.RaceGateway;
import models.Race;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class Dashboard extends JFrame {

	/**
	 *	Dashboard Window
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
	private JPanel panel_dashboard_top;
	private JPanel panel_dashboard_mid;
	private JLabel lblNextRaces;
	private JPanel panel_next_races;
	private JScrollPane scrollPane_1;
	private JPanel panel_races_top;
	private JPanel panel_races_mid;
	private JPanel panel_races_runner;
	private JLabel lblMyRacesRunner;
	private JLabel lblMyOrganisedRaces;
	private JLabel lblRacesIveVolunteered;
	private JButton btnAddRace;

	public static void main(String[] args) {
		Dashboard das = new Dashboard(null, null);
		das.setVisible(true);
	}

	public Dashboard(Login login, User user) {

		User user1 = new User(1, "user", "Usuario", "Usuario", "user@email.com", "1998/01/01");

		/*Si conectado cambiar user1 por user*/
		actualuser = user1;

		

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

		JButton btnlogout = new JButton("Log out");
		panel.add(btnlogout);
		btnlogout.setBackground(Color.WHITE);
		btnlogout.setOpaque(false);
		btnlogout.setContentAreaFilled(false);
		btnlogout.setBorderPainted(false);
		btnlogout.addActionListener(new ActionListener() {

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

		content = new JLayeredPane();
		content.setBackground(Color.WHITE);
		GridBagConstraints gbc_content = new GridBagConstraints();
		gbc_content.fill = GridBagConstraints.BOTH;
		gbc_content.gridx = 1;
		gbc_content.gridy = 1;
		contentpane.add(content, gbc_content);

		/*
		 * Panel Dashboard
		 */

		paneldashboard = new JPanel();
		paneldashboard.setBackground(Color.WHITE);
		content.setLayer(paneldashboard, 1);
		paneldashboard.setBounds(0, 0, 724, 719);
		paneldashboard.setLayout(new BorderLayout(0, 0));
		actualpane = 0;
		content.add(paneldashboard, new Integer(0));

		panel_dashboard_top = new JPanel();
		panel_dashboard_top.setOpaque(false);
		panel_dashboard_top.setBorder(new EmptyBorder(10, 10, 20, 10));
		paneldashboard.add(panel_dashboard_top, BorderLayout.NORTH);
		GridBagLayout gbl_panel_dashboard_top = new GridBagLayout();
		gbl_panel_dashboard_top.columnWidths = new int[] { 704, 0 };
		gbl_panel_dashboard_top.rowHeights = new int[] { 36, 0, 0 };
		gbl_panel_dashboard_top.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_panel_dashboard_top.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panel_dashboard_top.setLayout(gbl_panel_dashboard_top);
		JLabel lblDashboard_1 = new JLabel("Welcome " + actualuser.getFirst_name());
		lblDashboard_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblDashboard_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblDashboard_1.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblDashboard_1 = new GridBagConstraints();
		gbc_lblDashboard_1.anchor = GridBagConstraints.NORTH;
		gbc_lblDashboard_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblDashboard_1.gridx = 0;
		gbc_lblDashboard_1.gridy = 1;
		panel_dashboard_top.add(lblDashboard_1, gbc_lblDashboard_1);
		lblDashboard_1.setFont(new Font("Trebuchet MS", Font.BOLD, 30));

		panel_dashboard_mid = new JPanel();
		panel_dashboard_mid.setBackground(Color.WHITE);
		panel_dashboard_mid.setOpaque(false);
		paneldashboard.add(panel_dashboard_mid, BorderLayout.CENTER);
		GridBagLayout gbl_panel_dashboard_mid = new GridBagLayout();
		gbl_panel_dashboard_mid.columnWidths = new int[] { 224, 500 };
		gbl_panel_dashboard_mid.rowHeights = new int[] { 30, 300, 30, 30, 80, 30, 100 };
		gbl_panel_dashboard_mid.columnWeights = new double[] { 0.0, 0.0 };
		gbl_panel_dashboard_mid.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_dashboard_mid.setLayout(gbl_panel_dashboard_mid);

		lblNextRaces = new JLabel("Next Races");
		lblNextRaces.setFont(new Font("Tahoma", Font.BOLD, 24));
		GridBagConstraints gbc_lblNextRaces = new GridBagConstraints();
		gbc_lblNextRaces.insets = new Insets(0, 0, 5, 5);
		gbc_lblNextRaces.fill = GridBagConstraints.VERTICAL;
		gbc_lblNextRaces.gridx = 0;
		gbc_lblNextRaces.gridy = 1;
		panel_dashboard_mid.add(lblNextRaces, gbc_lblNextRaces);

		panel_next_races = new JPanel();
		panel_next_races.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, null, null, null));
		panel_next_races.setBackground(Color.WHITE);
		panel_next_races.setLayout(new GridLayout(5, 1, 0, 0));
		// int row=0;
		if (actualuser.getRunner_races() != null) {
			for (Race r : getNextRaces(actualuser.getRunner_races())) {
				PanelRaces next_race = new PanelRaces(r, actualuser);
				panel_next_races.add(next_race);
			}
		} else {
			JLabel lblNoraces = new JLabel("No Next Races");
			lblNoraces.setFont(new Font("Tahoma", Font.PLAIN, 20));
			panel_next_races.add(lblNoraces);

		}

		JScrollPane scrollPane = new JScrollPane(panel_next_races);

		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		panel_dashboard_mid.add(scrollPane, gbc_scrollPane);

		btnAddRace = new JButton("Create Race");
		btnAddRace.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnAddRace.setBackground(Color.WHITE);
		btnAddRace.setPreferredSize(new Dimension(240, 40));
		btnAddRace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CreateRace cr = new CreateRace();
				cr.setVisible(true);

			}
		});
		GridBagConstraints gbc_btnAddRace_1 = new GridBagConstraints();
		gbc_btnAddRace_1.anchor = GridBagConstraints.WEST;
		gbc_btnAddRace_1.weightx = 1.0;
		gbc_btnAddRace_1.insets = new Insets(5, 0, 5, 0);
		gbc_btnAddRace_1.gridx = 1;
		gbc_btnAddRace_1.gridy = 4;
		panel_dashboard_mid.add(btnAddRace, gbc_btnAddRace_1);

		/*
		 * Panel Races
		 */

		panelRaces = new JPanel();
		panelRaces.setBackground(Color.WHITE);
		content.setLayer(panelRaces, 0);
		panelRaces.setBounds(0, 0, 724, 719);
		
		panel_races_top = new JPanel();
		panel_races_top.setBackground(Color.WHITE);
		FlowLayout flowLayout = (FlowLayout) panel_races_top.getLayout();
		flowLayout.setVgap(25);
		flowLayout.setHgap(80);
		panelRaces.add(panel_races_top, BorderLayout.NORTH);
		
		lblMyRacesRunner = new JLabel("Races I've registered");
		lblMyRacesRunner.setVerticalAlignment(SwingConstants.BOTTOM);
		lblMyRacesRunner.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_races_top.add(lblMyRacesRunner);

		lblMyOrganisedRaces = new JLabel("Races I've Organised");
		lblMyOrganisedRaces.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_races_top.add(lblMyOrganisedRaces);

		lblRacesIveVolunteered = new JLabel("Races I've Volunteered");
		lblRacesIveVolunteered.setFont(new Font("Tahoma", Font.PLAIN, 16));

		panel_races_top.add(lblRacesIveVolunteered);
		

		panel_races_mid = new JPanel();
		panel_races_mid.setBackground(Color.WHITE);
		panelRaces.add(panel_races_mid, BorderLayout.WEST);
		GridBagLayout gbl_panel_races_mid = new GridBagLayout();
		gbl_panel_races_mid.columnWidths = new int[] { 250, 250, 250 };
		gbl_panel_races_mid.rowHeights = new int[] { 700, 0 };
		gbl_panel_races_mid.columnWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel_races_mid.rowWeights = new double[] { 0.0, 1.0 };
		panel_races_mid.setLayout(gbl_panel_races_mid);

		panel_races_runner = new JPanel();
		panel_races_runner.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, null, null, null));
		panel_races_runner.setBackground(Color.WHITE);
		scrollPane_1 = new JScrollPane(panel_races_runner);
		GridBagLayout gbl_panel_races_runner = new GridBagLayout();
		gbl_panel_races_runner.columnWidths = new int[] { 250 };
		gbl_panel_races_runner.rowHeights = new int[] { 75 };
		gbl_panel_races_runner.columnWeights = new double[] { Double.MIN_VALUE };
		gbl_panel_races_runner.rowWeights = new double[] { Double.MIN_VALUE };
		panel_races_runner.setLayout(gbl_panel_races_runner);
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_1.anchor = GridBagConstraints.NORTHWEST;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 0;
		panel_races_mid.add(scrollPane_1, gbc_scrollPane_1);

		if (actualuser.getRunner_races() != null) {
			for (Race r : getNextRaces(actualuser.getRunner_races())) {
				PanelRaces race = new PanelRaces(r, actualuser);
				panel_races_runner.add(race);
			}
		} else {
			JLabel lblNoraces = new JLabel("No Races Found");
			lblNoraces.setFont(new Font("Tahoma", Font.PLAIN, 20));
			panel_races_runner.add(lblNoraces);

		}
		
	
		/*
		 * Panel Search Races
		 */

		panelSearchRaces = new JPanel();
		panelSearchRaces.setBackground(Color.WHITE);
		content.setLayer(panelRaces, 0);
		panelSearchRaces.setBounds(0, 0, 724, 719);

		content.add(panelSearchRaces, new Integer(0));

		/*
		 * Panel MyUser
		 */

		panelmyUser = new JPanel();
		panelmyUser.setBackground(Color.WHITE);
		content.setLayer(panelRaces, 0);
		panelmyUser.setBounds(0, 0, 724, 719);

		content.add(panelRaces);
		panelRaces.setLayout(new BorderLayout(0, 0));

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
		GridBagConstraints gbc_buttonSearch = new GridBagConstraints();
		gbc_buttonSearch.anchor = GridBagConstraints.WEST;
		gbc_buttonSearch.insets = new Insets(0, 0, 5, 5);
		gbc_buttonSearch.gridx = 2;
		gbc_buttonSearch.gridy = 3;
		menu_bar.add(buttonSearch, gbc_buttonSearch);

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
					btnRaces.setForeground(Color.BLACK);
					content.setLayer(panelRaces, 0);
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
				actualpane = 0;
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
				actualpane = 1;
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
				actualpane = 2;

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
				actualpane = 3;

			}
		});

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {

				int result = JOptionPane.showConfirmDialog(Dashboard.this,
						"Are you sure you want to exit the application?", "Exit Application",
						JOptionPane.YES_NO_OPTION);

				if (result == JOptionPane.YES_OPTION)
					Dashboard.this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}

		});

	}

	private Race[] getNextRaces(Race[] races) {
		Race[] nextRaces = new Race[5];

		return nextRaces;

	}
}
