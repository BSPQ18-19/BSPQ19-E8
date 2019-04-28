package pages;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {

    /**
     *
     */

    private static final long serialVersionUID = 1L;


    private JPanel contentpane;
    private JLayeredPane content;

    public Dashboard(Login login) {
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

        JPanel panel_top = new JPanel();
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

        JLabel lblRaceOrganizer = new JLabel("Race Organizer");
        lblRaceOrganizer.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblRaceOrganizer.setHorizontalAlignment(SwingConstants.CENTER);
        lblRaceOrganizer.setFont(new Font("Tahoma", Font.BOLD, 30));
        panel_top.add(lblRaceOrganizer);

        JLayeredPane menu_bar = new JLayeredPane();
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

        JLabel lblDashboard = new JLabel("Dashboard");
        lblDashboard.setFont(new Font("Tahoma", Font.PLAIN, 22));
        GridBagConstraints gbc_lblDashboard = new GridBagConstraints();
        gbc_lblDashboard.anchor = GridBagConstraints.WEST;
        gbc_lblDashboard.insets = new Insets(0, 0, 5, 5);
        gbc_lblDashboard.gridx = 2;
        gbc_lblDashboard.gridy = 1;
        menu_bar.add(lblDashboard, gbc_lblDashboard);

        JLabel lblRaces = new JLabel("Races");
        lblRaces.setFont(new Font("Tahoma", Font.PLAIN, 22));
        GridBagConstraints gbc_lblRaces = new GridBagConstraints();
        gbc_lblRaces.anchor = GridBagConstraints.WEST;
        gbc_lblRaces.insets = new Insets(0, 0, 5, 5);
        gbc_lblRaces.gridx = 2;
        gbc_lblRaces.gridy = 2;
        menu_bar.add(lblRaces, gbc_lblRaces);

        JLabel lblMyUser = new JLabel("My User");
        lblMyUser.setFont(new Font("Tahoma", Font.PLAIN, 22));
        GridBagConstraints gbc_lblMyUser = new GridBagConstraints();
        gbc_lblMyUser.anchor = GridBagConstraints.WEST;
        gbc_lblMyUser.insets = new Insets(0, 0, 5, 5);
        gbc_lblMyUser.gridx = 2;
        gbc_lblMyUser.gridy = 3;
        menu_bar.add(lblMyUser, gbc_lblMyUser);

        JLayeredPane content = new JLayeredPane();
        GridBagConstraints gbc_content = new GridBagConstraints();
        gbc_content.fill = GridBagConstraints.BOTH;
        gbc_content.gridx = 1;
        gbc_content.gridy = 1;
        contentpane.add(content, gbc_content);

        JPanel paneldashboard = new JPanel();
        paneldashboard.setBounds(0, 0, 724, 719);
        content.add(paneldashboard);

        JLabel lblDashboard_1 = new JLabel("Dashboard");
        lblDashboard_1.setFont(new Font("Tahoma", Font.PLAIN, 26));
        paneldashboard.add(lblDashboard_1);

        JPanel panelRaces = new JPanel();
        panelRaces.setBounds(0, 0, 724, 719);
        content.add(panelRaces);


    }
}
