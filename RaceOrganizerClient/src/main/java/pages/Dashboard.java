package pages;

import managment.AuthManagement;
import managment.RaceManagement;
import models.Race;
import models.User;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

public class Dashboard extends JFrame {

    /**
     * Dashboard Window
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
    private JPanel panel_races_mid;
    private JPanel panel_races_runner;
    private JLabel lblMyRacesRunner;
    private JLabel lblRacesIveVolunteered;
    private JButton btnAddRace;
    private JPanel panel_races_helper;
    private JScrollPane scrollPane_3;
    private JPanel panel_search_top;
    private JPanel panel_search_mid;
    private JTextField textField_search_edition;
    private JLabel lblSearch_place;
    private JLabel lblSearch_edition;
    private JButton btnSeach;
    private JTextField textField_search_place;
    private JPanel panel_search_results;
    private JScrollPane scrollPane_search_results;
    private RaceManagement RaceManagement;
    private JPanel panel_1;
    private JPanel panel_2;
    private JLabel lblWelcome_myuser;
    private JLabel lblMyUser_username;
    private JLabel lblMyUser_firstname;
    private JLabel lblMyUser_LastName;
    private JLabel lblMyUser_icon;
    private JLabel lblMyUser_MyBirthday;
    private JLabel lblMyUser_email;
    private JPanel panel_organised_races;
    private JLabel lblMyUser_myorganisedraces;

    private JScrollPane scrollPane_organised_races;

    private Login pWindow;

    /**
     * Main Window
     *
     * @param login login window
     * @param user  user that has logged in
     */
    public Dashboard(Login login, User user) {
        pWindow = login;
        RaceManagement = new RaceManagement();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("SystemMessages", Locale.forLanguageTag(Translation.actual_language));

        actualuser = user;

        setBounds(100, 100, 1050, 855);
        contentpane = new JPanel();
        contentpane.setBackground(new Color(51, 102, 153));
        setContentPane(contentpane);
        GridBagLayout gbl_contentpane = new GridBagLayout();
        gbl_contentpane.columnWidths = new int[]{300, 750};
        gbl_contentpane.rowHeights = new int[]{100, 755};
        gbl_contentpane.columnWeights = new double[]{1.0, 1.0};
        gbl_contentpane.rowWeights = new double[]{1.0, 1.0};
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

        JComboBox comboBox = new JComboBox(Translation.languages);
        comboBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                Translation.changeLanguage((String) comboBox.getItemAt(comboBox.getSelectedIndex()));
                refresh();
            }
        });
        panel.add(comboBox);

        JButton btnlogout = new JButton(Translation.getString("log_out"));
        panel.add(btnlogout);
        btnlogout.setBackground(Color.WHITE);
        btnlogout.setOpaque(false);
        btnlogout.setContentAreaFilled(false);
        btnlogout.setBorderPainted(false);
        btnlogout.addActionListener(e -> {
            // TODO Auto-generated method stub
            AuthManagement.logout();
            Dashboard.this.dispose();
            login.setVisible(true);
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
        paneldashboard.setBounds(0, 0, 724, 719);
        paneldashboard.setLayout(new BorderLayout(0, 0));
        actualpane = 0;
        content.setLayer(paneldashboard, 1);
        content.add(paneldashboard);

        panel_dashboard_top = new JPanel();
        panel_dashboard_top.setOpaque(false);
        panel_dashboard_top.setBorder(new EmptyBorder(10, 10, 20, 10));
        paneldashboard.add(panel_dashboard_top, BorderLayout.NORTH);
        GridBagLayout gbl_panel_dashboard_top = new GridBagLayout();
        gbl_panel_dashboard_top.columnWidths = new int[]{704, 0};
        gbl_panel_dashboard_top.rowHeights = new int[]{36, 0, 0};
        gbl_panel_dashboard_top.columnWeights = new double[]{0.0, Double.MIN_VALUE};
        gbl_panel_dashboard_top.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        panel_dashboard_top.setLayout(gbl_panel_dashboard_top);
        JLabel lblDashboard_1 = new JLabel(Translation.getString("welcome") + actualuser.getFirst_name());
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
        gbl_panel_dashboard_mid.columnWidths = new int[]{224, 500};
        gbl_panel_dashboard_mid.rowHeights = new int[]{30, 300, 30, 30, 80, 30, 100};
        gbl_panel_dashboard_mid.columnWeights = new double[]{0.0, 0.0};
        gbl_panel_dashboard_mid.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        panel_dashboard_mid.setLayout(gbl_panel_dashboard_mid);

        lblNextRaces = new JLabel(Translation.getString("next_races"));
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

        if (actualuser.getRunner_races() != null) {
            for (Race r : getNextRaces(actualuser.getRunner_races())) {
                PanelRaces next_race = new PanelRaces(RaceManagement.getRace(r.getRace_id()), actualuser, 1, Dashboard.this);
                panel_next_races.add(next_race);
            }
        } else {
            JLabel lblNoraces = new JLabel(Translation.getString("no_races_found"));
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

        btnAddRace = new JButton(Translation.getString("create_race"));
        btnAddRace.setFont(new Font("Tahoma", Font.BOLD, 24));
        btnAddRace.setBackground(Color.WHITE);
        btnAddRace.setPreferredSize(new Dimension(240, 40));
        btnAddRace.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                CreateRace cr = new CreateRace(Dashboard.this);
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
        content.add(panelRaces);
        panelRaces.setBounds(0, 0, 724, 719);

        /*
         * Mid Panel
         */
        panelRaces.setLayout(new BorderLayout(5, 15));

        panel_races_mid = new JPanel();
        panel_races_mid.setBackground(Color.WHITE);
        panelRaces.add(panel_races_mid);
        GridBagLayout gbl_panel_races_mid = new GridBagLayout();
        gbl_panel_races_mid.columnWidths = new int[]{30, 600, 50};
        gbl_panel_races_mid.rowHeights = new int[]{50, 280, 30, 50, 280};
        gbl_panel_races_mid.columnWeights = new double[]{1.0, 0.0};
        gbl_panel_races_mid.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0};
        panel_races_mid.setLayout(gbl_panel_races_mid);

        /*
         * Panel for races as runner
         */

        lblMyRacesRunner = new JLabel(Translation.getString("races_running"));
        GridBagConstraints gbc_lblMyRacesRunner = new GridBagConstraints();
        gbc_lblMyRacesRunner.anchor = GridBagConstraints.WEST;
        gbc_lblMyRacesRunner.insets = new Insets(0, 0, 5, 0);
        gbc_lblMyRacesRunner.gridx = 1;
        gbc_lblMyRacesRunner.gridy = 0;
        panel_races_mid.add(lblMyRacesRunner, gbc_lblMyRacesRunner);
        lblMyRacesRunner.setVerticalAlignment(SwingConstants.BOTTOM);
        lblMyRacesRunner.setFont(new Font("Tahoma", Font.PLAIN, 16));

        panel_races_runner = new JPanel();
        panel_races_runner.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, null, null, null));
        panel_races_runner.setBackground(Color.WHITE);
        scrollPane_1 = new JScrollPane(panel_races_runner);
        GridBagLayout gbl_panel_races_runner = new GridBagLayout();
        gbl_panel_races_runner.columnWidths = new int[]{250};
        gbl_panel_races_runner.rowHeights = new int[]{75};
        gbl_panel_races_runner.columnWeights = new double[]{Double.MIN_VALUE};
        gbl_panel_races_runner.rowWeights = new double[]{Double.MIN_VALUE};
        panel_races_runner.setLayout(gbl_panel_races_runner);
        GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
        gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
        gbc_scrollPane_1.anchor = GridBagConstraints.NORTHWEST;
        gbc_scrollPane_1.gridx = 1;
        gbc_scrollPane_1.gridy = 1;
        panel_races_mid.add(scrollPane_1, gbc_scrollPane_1);

        if (actualuser.getRunner_races() != null) {
            int i = 0;
            for (Race r : getNextRaces(actualuser.getRunner_races())) {
                PanelRaces race = new PanelRaces(RaceManagement.getRace(r.getRace_id()), actualuser, 1, Dashboard.this);
                GridBagConstraints gbc_races = new GridBagConstraints();
                gbc_races.insets = new Insets(0, 0, 5, 0);
                gbc_races.fill = GridBagConstraints.BOTH;
                gbc_races.gridx = 0;
                gbc_races.gridy = i;
                panel_races_runner.add(race, gbc_races);
                i++;
            }
        } else {
            JLabel lblNoraces = new JLabel(Translation.getString("no_races_found"));
            lblNoraces.setFont(new Font("Tahoma", Font.PLAIN, 20));
            panel_races_runner.add(lblNoraces);

        }
        gbl_panel_races_runner.columnWidths = new int[]{250};
        gbl_panel_races_runner.rowHeights = new int[]{75};
        gbl_panel_races_runner.columnWeights = new double[]{Double.MIN_VALUE};
        gbl_panel_races_runner.rowWeights = new double[]{Double.MIN_VALUE};

        /*
         * Panel for races as helper
         */

        lblRacesIveVolunteered = new JLabel(Translation.getString("races_helping"));
        GridBagConstraints gbc_lblRacesIveVolunteered = new GridBagConstraints();
        gbc_lblRacesIveVolunteered.anchor = GridBagConstraints.WEST;
        gbc_lblRacesIveVolunteered.insets = new Insets(0, 0, 5, 0);
        gbc_lblRacesIveVolunteered.gridx = 1;
        gbc_lblRacesIveVolunteered.gridy = 3;
        panel_races_mid.add(lblRacesIveVolunteered, gbc_lblRacesIveVolunteered);
        lblRacesIveVolunteered.setFont(new Font("Tahoma", Font.PLAIN, 16));

        panel_races_helper = new JPanel();
        panel_races_helper.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, null, null, null));
        panel_races_helper.setBackground(Color.WHITE);
        scrollPane_3 = new JScrollPane(panel_races_helper);
        GridBagLayout gbl_panel_races_helper = new GridBagLayout();
        gbl_panel_races_helper.columnWidths = new int[]{250};
        gbl_panel_races_helper.rowHeights = new int[]{75};
        gbl_panel_races_helper.columnWeights = new double[]{Double.MIN_VALUE};
        gbl_panel_races_helper.rowWeights = new double[]{Double.MIN_VALUE};
        panel_races_helper.setLayout(gbl_panel_races_helper);
        GridBagConstraints gbc_scrollPane_3 = new GridBagConstraints();
        gbc_scrollPane_3.anchor = GridBagConstraints.NORTHWEST;
        gbc_scrollPane_3.gridx = 1;
        gbc_scrollPane_3.gridy = 4;
        panel_races_mid.add(scrollPane_3, gbc_scrollPane_3);

        if (actualuser.getHelper_races() != null) {
            int i = 0;
            for (Race r : getNextRaces(actualuser.getHelper_races())) {
                PanelRaces race = new PanelRaces(RaceManagement.getRace(r.getRace_id()), actualuser, 1, Dashboard.this);

                GridBagConstraints gbc_races = new GridBagConstraints();
                gbc_races.insets = new Insets(0, 0, 5, 0);
                gbc_races.fill = GridBagConstraints.BOTH;
                gbc_races.gridx = 0;
                gbc_races.gridy = i;
                panel_races_helper.add(race, gbc_races);
                i++;
            }

        } else {
            JLabel lblNoraces = new JLabel(Translation.getString("no_races_found"));
            lblNoraces.setFont(new Font("Tahoma", Font.PLAIN, 20));
            panel_races_helper.add(lblNoraces);

        }

        /*
         * Panel Search Races
         */

        panelSearchRaces = new JPanel();
        panelSearchRaces.setBackground(Color.WHITE);
        content.setLayer(panelSearchRaces, 0);
        panelSearchRaces.setBounds(0, 0, 724, 719);

        content.add(panelSearchRaces, 0);
        GridBagLayout gbl_panelSearchRaces = new GridBagLayout();
        gbl_panelSearchRaces.columnWidths = new int[]{724, 0};
        gbl_panelSearchRaces.rowHeights = new int[]{200, 600, 10, 0};
        gbl_panelSearchRaces.columnWeights = new double[]{0.0, Double.MIN_VALUE};
        gbl_panelSearchRaces.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        panelSearchRaces.setLayout(gbl_panelSearchRaces);

        panel_search_top = new JPanel();
        GridBagConstraints gbc_panel_search_top = new GridBagConstraints();
        gbc_panel_search_top.fill = GridBagConstraints.HORIZONTAL;
        gbc_panel_search_top.insets = new Insets(0, 0, 5, 0);
        gbc_panel_search_top.gridx = 0;
        gbc_panel_search_top.gridy = 0;
        panelSearchRaces.add(panel_search_top, gbc_panel_search_top);
        GridBagLayout gbl_panel_search_top = new GridBagLayout();
        gbl_panel_search_top.columnWidths = new int[]{70, 250, 20, 60, 220};
        gbl_panel_search_top.rowHeights = new int[]{80, 50, 50, 0, 20};
        gbl_panel_search_top.columnWeights = new double[]{1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_panel_search_top.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        panel_search_top.setLayout(gbl_panel_search_top);

        lblSearch_edition = new JLabel(Translation.getString("edition"));
        GridBagConstraints gbc_lblSearch_edition = new GridBagConstraints();
        gbc_lblSearch_edition.anchor = GridBagConstraints.EAST;
        gbc_lblSearch_edition.insets = new Insets(0, 0, 5, 5);
        gbc_lblSearch_edition.gridx = 0;
        gbc_lblSearch_edition.gridy = 1;
        panel_search_top.add(lblSearch_edition, gbc_lblSearch_edition);

        textField_search_edition = new JTextField();
        GridBagConstraints gbc_textField_search_edition = new GridBagConstraints();
        gbc_textField_search_edition.insets = new Insets(0, 0, 5, 5);
        gbc_textField_search_edition.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_search_edition.gridx = 1;
        gbc_textField_search_edition.gridy = 1;
        panel_search_top.add(textField_search_edition, gbc_textField_search_edition);
        textField_search_edition.setColumns(10);

        lblSearch_place = new JLabel(Translation.getString("place"));
        GridBagConstraints gbc_lblSearch_place = new GridBagConstraints();
        gbc_lblSearch_place.insets = new Insets(0, 0, 5, 5);
        gbc_lblSearch_place.anchor = GridBagConstraints.EAST;
        gbc_lblSearch_place.gridx = 0;
        gbc_lblSearch_place.gridy = 2;
        panel_search_top.add(lblSearch_place, gbc_lblSearch_place);

        textField_search_place = new JTextField();
        textField_search_place.setToolTipText("");
        textField_search_place.setColumns(10);
        GridBagConstraints gbc_textField_search_place = new GridBagConstraints();
        gbc_textField_search_place.insets = new Insets(0, 0, 5, 5);
        gbc_textField_search_place.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_search_place.gridx = 1;
        gbc_textField_search_place.gridy = 2;
        panel_search_top.add(textField_search_place, gbc_textField_search_place);

        btnSeach = new JButton(Translation.getString("search"));
        btnSeach.setBackground(new Color(51, 102, 153));
        GridBagConstraints gbc_btnSeach = new GridBagConstraints();
        gbc_btnSeach.insets = new Insets(0, 0, 5, 0);
        gbc_btnSeach.gridx = 3;
        gbc_btnSeach.gridy = 2;
        panel_search_top.add(btnSeach, gbc_btnSeach);

        panel_search_mid = new JPanel();
        GridBagConstraints gbc_panel_search_mid = new GridBagConstraints();
        gbc_panel_search_mid.insets = new Insets(0, 0, 5, 0);
        gbc_panel_search_mid.anchor = GridBagConstraints.NORTH;
        gbc_panel_search_mid.fill = GridBagConstraints.HORIZONTAL;
        gbc_panel_search_mid.gridx = 0;
        gbc_panel_search_mid.gridy = 1;
        panelSearchRaces.add(panel_search_mid, gbc_panel_search_mid);
        GridBagLayout gbl_panel_search_mid = new GridBagLayout();
        gbl_panel_search_mid.columnWidths = new int[]{20, 600};
        gbl_panel_search_mid.rowHeights = new int[]{30, 400};
        gbl_panel_search_mid.columnWeights = new double[]{0.0, Double.MIN_VALUE};
        gbl_panel_search_mid.rowWeights = new double[]{0.0, 0.0};
        panel_search_mid.setLayout(gbl_panel_search_mid);

        panel_search_results = new JPanel();
        scrollPane_search_results = new JScrollPane(panel_search_results);
        GridBagLayout gbl_panel_search_results = new GridBagLayout();
        gbl_panel_search_results.columnWidths = new int[]{0};
        gbl_panel_search_results.rowHeights = new int[]{0};
        gbl_panel_search_results.columnWeights = new double[]{Double.MIN_VALUE};
        gbl_panel_search_results.rowWeights = new double[]{Double.MIN_VALUE};
        panel_search_results.setLayout(gbl_panel_search_results);
        GridBagConstraints gbc_scrollPane_search_results = new GridBagConstraints();
        gbc_scrollPane_search_results.fill = GridBagConstraints.BOTH;
        gbc_scrollPane_search_results.gridx = 1;
        gbc_scrollPane_search_results.gridy = 1;
        panel_search_mid.add(scrollPane_search_results, gbc_scrollPane_search_results);

        Race[] allraces = RaceManagement.getRaces();

        if (allraces != null) {
            int i = 0;
            for (Race r : allraces) {
                PanelRaces race = new PanelRaces(RaceManagement.getRace(r.getRace_id()), actualuser, 1, Dashboard.this);
                GridBagConstraints gbc_races = new GridBagConstraints();
                gbc_races.insets = new Insets(0, 0, 5, 0);
                gbc_races.fill = GridBagConstraints.BOTH;
                gbc_races.gridx = 0;
                gbc_races.gridy = i;
                panel_search_results.add(race, gbc_races);
                i++;

            }
        } else {
            JLabel lblNoraces = new JLabel(Translation.getString("no_races_found"));
            lblNoraces.setFont(new Font("Tahoma", Font.PLAIN, 20));
            panel_races_helper.add(lblNoraces);

        }

        btnSeach.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                search(textField_search_edition.getText(), textField_search_place.getText());

            }
        });

        /*
         * Panel MyUser
         */

        panelmyUser = new JPanel();
        content.setLayer(panelmyUser, 0);
        panelmyUser.setBackground(Color.WHITE);

        panelmyUser.setBounds(0, 0, 724, 719);

        content.add(panelmyUser);
        panelmyUser.setLayout(new BorderLayout(0, 0));

        panel_1 = new JPanel();
        FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
        flowLayout_1.setVgap(30);
        panelmyUser.add(panel_1, BorderLayout.NORTH);

        lblWelcome_myuser = new JLabel(Translation.getString("welcome_myuser"));
        lblWelcome_myuser.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel_1.add(lblWelcome_myuser);

        panel_2 = new JPanel();
        panelmyUser.add(panel_2, BorderLayout.CENTER);
        GridBagLayout gbl_panel_2 = new GridBagLayout();
        gbl_panel_2.columnWidths = new int[]{50, 200, 30, 400, 0};
        gbl_panel_2.rowHeights = new int[]{40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 150, 40, 40};
        gbl_panel_2.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0,
                Double.MIN_VALUE};
        panel_2.setLayout(gbl_panel_2);

        lblMyUser_username = new JLabel(Translation.getString("username") + actualuser.getUsername());
        lblMyUser_username.setFont(new Font("Tahoma", Font.PLAIN, 14));
        GridBagConstraints gbc_lblMyUser_username = new GridBagConstraints();
        gbc_lblMyUser_username.anchor = GridBagConstraints.WEST;
        gbc_lblMyUser_username.insets = new Insets(0, 0, 5, 5);
        gbc_lblMyUser_username.gridx = 1;
        gbc_lblMyUser_username.gridy = 0;
        panel_2.add(lblMyUser_username, gbc_lblMyUser_username);

        lblMyUser_icon = new JLabel("");
        GridBagConstraints gbc_lblMyUser_icon = new GridBagConstraints();
        gbc_lblMyUser_icon.insets = new Insets(0, 0, 5, 5);
        gbc_lblMyUser_icon.gridheight = 5;
        gbc_lblMyUser_icon.gridx = 3;
        gbc_lblMyUser_icon.gridy = 0;
        lblMyUser_icon.setIcon(new ImageIcon(("/mnt/70BDB63A6ECBC5EB/Cloud/Github/BSPQ19-E8/RaceOrganizerClient/src/main/java/icons/icon.png")));
        panel_2.add(lblMyUser_icon, gbc_lblMyUser_icon);

        lblMyUser_firstname = new JLabel(Translation.getString("first_name") + actualuser.getFirst_name());
        lblMyUser_firstname.setFont(new Font("Tahoma", Font.PLAIN, 14));
        GridBagConstraints gbc_lblMyUser_firstname = new GridBagConstraints();
        gbc_lblMyUser_firstname.anchor = GridBagConstraints.WEST;
        gbc_lblMyUser_firstname.insets = new Insets(0, 0, 5, 5);
        gbc_lblMyUser_firstname.gridx = 1;
        gbc_lblMyUser_firstname.gridy = 1;
        panel_2.add(lblMyUser_firstname, gbc_lblMyUser_firstname);

        lblMyUser_LastName = new JLabel(Translation.getString("last_name") + actualuser.getLast_name());
        lblMyUser_LastName.setFont(new Font("Tahoma", Font.PLAIN, 14));
        GridBagConstraints gbc_lblMyUser_LastName = new GridBagConstraints();
        gbc_lblMyUser_LastName.anchor = GridBagConstraints.WEST;
        gbc_lblMyUser_LastName.insets = new Insets(0, 0, 5, 5);
        gbc_lblMyUser_LastName.gridx = 1;
        gbc_lblMyUser_LastName.gridy = 2;
        panel_2.add(lblMyUser_LastName, gbc_lblMyUser_LastName);

        lblMyUser_MyBirthday = new JLabel(Translation.getString("my_birthday") + actualuser.getBirth_date());
        lblMyUser_MyBirthday.setFont(new Font("Tahoma", Font.PLAIN, 14));
        GridBagConstraints gbc_lblMyUser_MyBirthday = new GridBagConstraints();
        gbc_lblMyUser_MyBirthday.anchor = GridBagConstraints.WEST;
        gbc_lblMyUser_MyBirthday.insets = new Insets(0, 0, 5, 5);
        gbc_lblMyUser_MyBirthday.gridx = 1;
        gbc_lblMyUser_MyBirthday.gridy = 3;
        panel_2.add(lblMyUser_MyBirthday, gbc_lblMyUser_MyBirthday);

        lblMyUser_email = new JLabel(Translation.getString("email") + actualuser.getEmail());
        lblMyUser_email.setFont(new Font("Tahoma", Font.PLAIN, 14));
        GridBagConstraints gbc_lblMyUser_email = new GridBagConstraints();
        gbc_lblMyUser_email.anchor = GridBagConstraints.WEST;
        gbc_lblMyUser_email.insets = new Insets(0, 0, 5, 5);
        gbc_lblMyUser_email.gridx = 1;
        gbc_lblMyUser_email.gridy = 4;
        panel_2.add(lblMyUser_email, gbc_lblMyUser_email);

        lblMyUser_myorganisedraces = new JLabel(Translation.getString("races_organised"));
        lblMyUser_myorganisedraces.setFont(new Font("Tahoma", Font.BOLD, 15));
        GridBagConstraints gbc_lblMyUser_myorganisedraces = new GridBagConstraints();
        gbc_lblMyUser_myorganisedraces.anchor = GridBagConstraints.WEST;
        gbc_lblMyUser_myorganisedraces.insets = new Insets(0, 0, 5, 5);
        gbc_lblMyUser_myorganisedraces.gridx = 1;
        gbc_lblMyUser_myorganisedraces.gridy = 7;
        panel_2.add(lblMyUser_myorganisedraces, gbc_lblMyUser_myorganisedraces);

        panel_organised_races = new JPanel();
        panel_organised_races.setBackground(Color.WHITE);

        scrollPane_organised_races = new JScrollPane(panel_organised_races);
        GridBagLayout gbl_panel_organised_races = new GridBagLayout();
        gbl_panel_organised_races.columnWidths = new int[]{0};
        gbl_panel_organised_races.rowHeights = new int[]{0};
        gbl_panel_organised_races.columnWeights = new double[]{Double.MIN_VALUE};
        gbl_panel_organised_races.rowWeights = new double[]{Double.MIN_VALUE};
        panel_organised_races.setLayout(gbl_panel_organised_races);
        GridBagConstraints gbc_panel_organised_races = new GridBagConstraints();
        gbc_panel_organised_races.insets = new Insets(0, 0, 0, 5);
        gbc_panel_organised_races.gridheight = 4;
        gbc_panel_organised_races.gridwidth = 3;
        gbc_panel_organised_races.fill = GridBagConstraints.BOTH;
        gbc_panel_organised_races.gridx = 1;
        gbc_panel_organised_races.gridy = 8;
        panel_2.add(scrollPane_organised_races, gbc_panel_organised_races);

        if (actualuser.getOrganizer_races() != null) {
            int i = 0;
            for (Race r : getNextRaces(actualuser.getOrganizer_races())) {
                PanelRaces race = new PanelRaces(RaceManagement.getRace(r.getRace_id()), actualuser, 1, Dashboard.this);
                GridBagConstraints gbc_races = new GridBagConstraints();
                gbc_races.insets = new Insets(0, 0, 5, 0);
                gbc_races.fill = GridBagConstraints.BOTH;
                gbc_races.gridx = 0;
                gbc_races.gridy = i;
                panel_organised_races.add(race, gbc_races);
                i++;
            }
        } else {
            JLabel lblNoraces = new JLabel(Translation.getString("no_races_found"));
            lblNoraces.setFont(new Font("Tahoma", Font.PLAIN, 20));
            panel_organised_races.add(lblNoraces);

        }

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
        gbl_menu_bar.columnWidths = new int[]{25, 35, 200, 40};
        gbl_menu_bar.rowHeights = new int[]{60, 80, 80, 80, 80, 350};
        gbl_menu_bar.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        gbl_menu_bar.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        menu_bar.setLayout(gbl_menu_bar);

        JLabel lblNewLabel = new JLabel("");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel.gridx = 1;
        gbc_lblNewLabel.gridy = 1;
        menu_bar.add(lblNewLabel, gbc_lblNewLabel);

        btnDashboard = new JButton(Translation.getString("dashboard"));
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

        btnRaces = new JButton(Translation.getString("my_races"));
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

        buttonSearch = new JButton(Translation.getString("search_races"));
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

        btnMyUser = new JButton(Translation.getString("my_user"));
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
                        resourceBundle.getString("exit_msg"), resourceBundle.getString("exit_msg_title"),
                        JOptionPane.YES_NO_OPTION);

                if (result == JOptionPane.YES_OPTION)
                    Dashboard.this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }

        });

    }

    /**
     * Search the array with two criteria and shows the result
     *
     * @param searchfield_edition edition of the race
     * @param searchfield_places  city of the race
     */
    private void search(String searchfield_edition, String searchfield_places) {
        int i = 0;
        panel_search_results.removeAll();

        Race[] races = RaceManagement.getRaces(searchfield_edition);

        for (Race r : races) {
            PanelRaces race = new PanelRaces(RaceManagement.getRace(r.getRace_id()), actualuser, 1, Dashboard.this);
            GridBagConstraints gbc_races = new GridBagConstraints();
            gbc_races.insets = new Insets(0, 0, 5, 0);
            gbc_races.fill = GridBagConstraints.BOTH;
            gbc_races.gridx = 0;
            gbc_races.gridy = i;
            panel_search_results.add(race, gbc_races);
            i++;
        }

        panel_search_results.updateUI();
    }

    /**
     * Sorts an array from earliest date to latest
     *
     * @param races array of races
     * @return order races
     */
    private Race[] getNextRaces(Race[] races) {
        Arrays.sort(races);
        return races;

    }

    public void refresh() {
        //SwingUtilities.updateComponentTreeUI(Dashboard.this);
        Dashboard.this.dispose();
        Dashboard das = new Dashboard(pWindow, actualuser);
        das.setVisible(true);
    }
}
