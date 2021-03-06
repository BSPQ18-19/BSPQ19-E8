package pages;

import managment.AuthManagement;
import managment.UsersManagement;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Login extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPanel;
    private JTextField txtUsername;
    private JPasswordField passwordField;
	private JLabel lblUserLogin;


    /**
     * Create the frame.
     */

    public Login() {
    	new Translation();
        setBounds(100, 100, 650, 400);
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
        panel_1.setBounds(300, 100, 280, 203);
        panel.add(panel_1);
        panel_1.setLayout(new BorderLayout(0, 0));

        JPanel panel_2 = new JPanel();
        panel_2.setOpaque(false);
        panel_1.add(panel_2, BorderLayout.SOUTH);

        JButton btnLogin = new JButton(Translation.getString("login"));
        panel_2.add(btnLogin);
        btnLogin.setBackground(new Color(255, 255, 255));
        btnLogin.addActionListener(e -> {
            // TODO Auto-generated method stub
            if (AuthManagement.login(txtUsername.getText(), String.valueOf(passwordField.getPassword()))) {
                Login.this.setVisible(false);
                Dashboard das = new Dashboard(Login.this, UsersManagement.getLoggedProfile());
                das.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(Login.this, Translation.getString("login_error"));
            }

        });

        JButton btnNewButton_1 = new JButton(Translation.getString("register"));
        panel_2.add(btnNewButton_1);
        btnNewButton_1.addActionListener(arg0 -> {
            Login.this.setVisible(false);
            Registration res = new Registration(Login.this);
            res.setVisible(true);
        });
        btnNewButton_1.setBackground(new Color(255, 255, 255));

        JPanel panel_3 = new JPanel();
        panel_3.setOpaque(false);
        panel_1.add(panel_3, BorderLayout.CENTER);
        GridBagLayout gbl_panel_3 = new GridBagLayout();
        gbl_panel_3.columnWidths = new int[]{30, 35, 206, 0, 30};
        gbl_panel_3.rowHeights = new int[]{30, 45, 30, 30, 40};
        gbl_panel_3.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_panel_3.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
        panel_3.setLayout(gbl_panel_3);

        lblUserLogin = new JLabel(Translation.getString("user_login"));
        lblUserLogin.setFont(new Font("Tahoma", Font.PLAIN, 18));
        GridBagConstraints gbc_lblUserLogin = new GridBagConstraints();
        gbc_lblUserLogin.anchor = GridBagConstraints.WEST;
        gbc_lblUserLogin.insets = new Insets(0, 0, 5, 5);
        gbc_lblUserLogin.gridx = 2;
        gbc_lblUserLogin.gridy = 1;
        panel_3.add(lblUserLogin, gbc_lblUserLogin);

        JLabel lblUser = new JLabel("");
        lblUser.setIcon(new ImageIcon(("/mnt/70BDB63A6ECBC5EB/Cloud/Github/BSPQ19-E8/RaceOrganizerClient/src/main/java/icons/user.png")));
        GridBagConstraints gbc_lblUser = new GridBagConstraints();
        gbc_lblUser.insets = new Insets(0, 0, 5, 5);
        gbc_lblUser.anchor = GridBagConstraints.EAST;
        gbc_lblUser.gridx = 1;
        gbc_lblUser.gridy = 2;
        panel_3.add(lblUser, gbc_lblUser);

        txtUsername = new JTextField();
        txtUsername.setToolTipText(Translation.getString("username"));
        txtUsername.setBackground(new Color(245, 245, 245));
        txtUsername.setBorder(new LineBorder(new Color(245, 245, 245)));
        GridBagConstraints gbc_txtUsername = new GridBagConstraints();
        gbc_txtUsername.fill = GridBagConstraints.BOTH;
        gbc_txtUsername.insets = new Insets(0, 0, 5, 5);
        gbc_txtUsername.gridx = 2;
        gbc_txtUsername.gridy = 2;
        panel_3.add(txtUsername, gbc_txtUsername);
        txtUsername.setColumns(10);

        JLabel lblPassword = new JLabel("");
        lblPassword.setIcon(new ImageIcon(("/mnt/70BDB63A6ECBC5EB/Cloud/Github/BSPQ19-E8/RaceOrganizerClient/src/main/java/icons/key.png")));
        GridBagConstraints gbc_lblPassword = new GridBagConstraints();
        gbc_lblPassword.insets = new Insets(0, 0, 0, 5);
        gbc_lblPassword.anchor = GridBagConstraints.EAST;
        gbc_lblPassword.gridx = 1;
        gbc_lblPassword.gridy = 3;
        panel_3.add(lblPassword, gbc_lblPassword);

        passwordField = new JPasswordField();
        passwordField.setToolTipText(Translation.getString("password"));
        passwordField.setBackground(new Color(245, 245, 245));
        passwordField.setBorder(new LineBorder(new Color(245, 245, 245)));
        GridBagConstraints gbc_passwordField = new GridBagConstraints();
        gbc_passwordField.insets = new Insets(0, 0, 0, 5);
        gbc_passwordField.fill = GridBagConstraints.BOTH;
        gbc_passwordField.gridx = 2;
        gbc_passwordField.gridy = 3;
        panel_3.add(passwordField, gbc_passwordField);

        JLabel lblicon = new JLabel("");
        lblicon.setBounds(12, 37, 302, 287);
        panel.add(lblicon);
        lblicon.setHorizontalAlignment(SwingConstants.CENTER);
        lblicon.setIcon(new ImageIcon(("/mnt/70BDB63A6ECBC5EB/Cloud/Github/BSPQ19-E8/RaceOrganizerClient/src/main/java/icons/icon.png")));

        JLabel lblRaceOrganizer = new JLabel("RACE ORGANIZER");
        lblRaceOrganizer.setFont(new Font("Tahoma", Font.BOLD, 22));
        lblRaceOrganizer.setBounds(334, 60, 230, 27);
        panel.add(lblRaceOrganizer);
        
        @SuppressWarnings("rawtypes")
		JComboBox comboBox = new JComboBox(Translation.languages);
        comboBox.setBounds(558, 24, 52, 22);
        comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Translation.changeLanguage((String)comboBox.getItemAt(comboBox.getSelectedIndex()));
				Login.this.dispose();
				Login log=new Login();
				log.setVisible(true);
				
			}
		});
        panel.add(comboBox);

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
    

}
