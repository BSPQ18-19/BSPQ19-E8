package pages;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import networking.SignupGateway;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Registration extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPanel;
    private JTextField username;
    private JPasswordField passwordField;
    private JPasswordField passwordField_1;
    private JTextField textField_name;
    private JTextField textField_surname;
    private JTextField textField_email;


    /**
     * Create the dialog.
     */
    public Registration(Login login) {
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
        panel_1.setBounds(12, 59, 331, 374);
        panel.add(panel_1);
        GridBagLayout gbl_panel_1 = new GridBagLayout();
        gbl_panel_1.rowHeights = new int[] {50, 30, 25, 30, 25, 30, 30, 30, 30, 30, 30, 60};
        gbl_panel_1.columnWidths = new int[]{30, 0, 250, 20};
        gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
        gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0};
        panel_1.setLayout(gbl_panel_1);

        JLabel lblUserLogin = new JLabel("User Registration");
        GridBagConstraints gbc_lblUserLogin = new GridBagConstraints();
        gbc_lblUserLogin.insets = new Insets(0, 0, 5, 0);
        gbc_lblUserLogin.fill = GridBagConstraints.BOTH;
        gbc_lblUserLogin.gridx = 2;
        gbc_lblUserLogin.gridy = 0;
        panel_1.add(lblUserLogin, gbc_lblUserLogin);
        lblUserLogin.setFont(new Font("Tahoma", Font.PLAIN, 18));

        JLabel lblUser = new JLabel("");
        GridBagConstraints gbc_lblUser = new GridBagConstraints();
        gbc_lblUser.fill = GridBagConstraints.BOTH;
        gbc_lblUser.insets = new Insets(0, 0, 5, 5);
        gbc_lblUser.gridx = 1;
        gbc_lblUser.gridy = 1;
        panel_1.add(lblUser, gbc_lblUser);
        lblUser.setIcon(new ImageIcon("icons/user.png"));

        username = new HintTextField("Username");
        username.setToolTipText("Username");
        GridBagConstraints gbc_textField_username = new GridBagConstraints();
        gbc_textField_username.fill = GridBagConstraints.BOTH;
        gbc_textField_username.insets = new Insets(0, 0, 5, 0);
        gbc_textField_username.gridx = 2;
        gbc_textField_username.gridy = 1;
        panel_1.add(username, gbc_textField_username);
        username.setBackground(new Color(245, 245, 245));
        username.setBorder(new LineBorder(new Color(245, 245, 245)));
        username.setColumns(10);

        JLabel lblPassword = new JLabel("");
        GridBagConstraints gbc_lblPassword = new GridBagConstraints();
        gbc_lblPassword.fill = GridBagConstraints.BOTH;
        gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
        gbc_lblPassword.gridx = 1;
        gbc_lblPassword.gridy = 2;
        panel_1.add(lblPassword, gbc_lblPassword);
        lblPassword.setIcon(new ImageIcon("icons/key.png"));

        JLabel lblPassword_1 = new JLabel("Password");
        GridBagConstraints gbc_lblPassword_1 = new GridBagConstraints();
        gbc_lblPassword_1.anchor = GridBagConstraints.SOUTHWEST;
        gbc_lblPassword_1.insets = new Insets(0, 0, 5, 0);
        gbc_lblPassword_1.gridx = 2;
        gbc_lblPassword_1.gridy = 2;
        panel_1.add(lblPassword_1, gbc_lblPassword_1);

        passwordField = new JPasswordField();
        GridBagConstraints gbc_passwordField = new GridBagConstraints();
        gbc_passwordField.fill = GridBagConstraints.BOTH;
        gbc_passwordField.insets = new Insets(0, 0, 5, 0);
        gbc_passwordField.gridx = 2;
        gbc_passwordField.gridy = 3;
        panel_1.add(passwordField, gbc_passwordField);
        passwordField.setBackground(new Color(245, 245, 245));
        passwordField.setBorder(new LineBorder(new Color(245, 245, 245)));

        JLabel lblConfirmPassword = new JLabel("Confirm Password");
        GridBagConstraints gbc_lblConfirmPassword = new GridBagConstraints();
        gbc_lblConfirmPassword.anchor = GridBagConstraints.SOUTHWEST;
        gbc_lblConfirmPassword.insets = new Insets(0, 0, 5, 0);
        gbc_lblConfirmPassword.gridx = 2;
        gbc_lblConfirmPassword.gridy = 4;
        panel_1.add(lblConfirmPassword, gbc_lblConfirmPassword);

        passwordField_1 = new JPasswordField();
        GridBagConstraints gbc_passwordField_1 = new GridBagConstraints();
        gbc_passwordField_1.insets = new Insets(0, 0, 5, 0);
        gbc_passwordField_1.fill = GridBagConstraints.BOTH;
        gbc_passwordField_1.gridx = 2;
        gbc_passwordField_1.gridy = 5;
        panel_1.add(passwordField_1, gbc_passwordField_1);
        passwordField_1.setBorder(new LineBorder(new Color(245, 245, 245)));
        passwordField_1.setBackground(new Color(245, 245, 245));
        
        HintTextField DNI_TextField = new HintTextField("DNI");
        DNI_TextField.setColumns(10);
        GridBagConstraints gbc_DNI_TextField = new GridBagConstraints();
        gbc_DNI_TextField.insets = new Insets(0, 0, 5, 0);
        gbc_DNI_TextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_DNI_TextField.gridx = 2;
        gbc_DNI_TextField.gridy = 6;
        panel_1.add(DNI_TextField, gbc_DNI_TextField);

        JLabel lblemail = new JLabel("");
        lblemail.setIcon(new ImageIcon(Registration.class.getResource("/src/icons/envelope.png")));
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel.gridx = 1;
        gbc_lblNewLabel.gridy = 7;
        panel_1.add(lblemail, gbc_lblNewLabel);

        textField_email = new HintTextField("Email");
        GridBagConstraints gbc_textField_email = new GridBagConstraints();
        gbc_textField_email.insets = new Insets(0, 0, 5, 0);
        gbc_textField_email.fill = GridBagConstraints.BOTH;
        gbc_textField_email.gridx = 2;
        gbc_textField_email.gridy = 7;
        panel_1.add(textField_email, gbc_textField_email);
        textField_email.setColumns(10);

        textField_name = new HintTextField("Name");
        GridBagConstraints gbc_textField_name = new GridBagConstraints();
        gbc_textField_name.insets = new Insets(0, 0, 5, 0);
        gbc_textField_name.fill = GridBagConstraints.BOTH;
        gbc_textField_name.gridx = 2;
        gbc_textField_name.gridy = 9;
        panel_1.add(textField_name, gbc_textField_name);
        textField_name.setColumns(10);

        textField_surname = new HintTextField("Surname");
        GridBagConstraints gbc_textField_surname = new GridBagConstraints();
        gbc_textField_surname.insets = new Insets(0, 0, 5, 0);
        gbc_textField_surname.fill = GridBagConstraints.BOTH;
        gbc_textField_surname.gridx = 2;
        gbc_textField_surname.gridy = 10;
        panel_1.add(textField_surname, gbc_textField_surname);
        textField_surname.setColumns(10);
        
        HintTextField txt_birthdate = new HintTextField("Birthdate");
        txt_birthdate.setColumns(10);
        GridBagConstraints gbc_txt_birthdate = new GridBagConstraints();
        gbc_txt_birthdate.anchor = GridBagConstraints.NORTH;
        gbc_txt_birthdate.fill = GridBagConstraints.HORIZONTAL;
        gbc_txt_birthdate.gridx = 2;
        gbc_txt_birthdate.gridy = 11;
        panel_1.add(txt_birthdate, gbc_txt_birthdate);

        JLabel lblicon = new JLabel("");
        lblicon.setBounds(370, 46, 222, 212);
        panel.add(lblicon);
        lblicon.setHorizontalAlignment(SwingConstants.CENTER);
        lblicon.setIcon(new ImageIcon("icons/icon.png"));

        JLabel lblRaceOrganizer = new JLabel("RACE ORGANIZER");
        lblRaceOrganizer.setFont(new Font("Tahoma", Font.BOLD, 22));
        lblRaceOrganizer.setBounds(27, 22, 230, 27);
        panel.add(lblRaceOrganizer);

        JPanel panel_2 = new JPanel();
        panel_2.setBounds(353, 388, 257, 35);
        panel.add(panel_2);
        panel_2.setOpaque(false);

        JButton btnBack = new JButton("Back");
        panel_2.add(btnBack);
        btnBack.setBackground(new Color(255, 255, 255));
        btnBack.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                Registration.this.setVisible(false);
                login.setVisible(true);

            }
        });

        JButton btnNewButton_1 = new JButton("Register");
        panel_2.add(btnNewButton_1);
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	String password=String.valueOf(passwordField.getPassword());
            	if(password.equals(String.valueOf(passwordField_1.getPassword()))) {
            		SignupGateway sgw =new SignupGateway(username.getText(), password, textField_name.getText(), textField_surname.getText(), textField_email.getText(), DNI_TextField.getText(), txt_birthdate.getText());
                    if(sgw.signUp()) {
                    	Registration.this.setVisible(false);
                        Dashboard das = new Dashboard(login);
                        das.setVisible(true);
                    }else {
                    	JOptionPane.showMessageDialog(Registration.this, "Error while signup. Try again");
                    }
            	}else {
            		JOptionPane.showMessageDialog(Registration.this, "Passwords are not the same. Try again");
            	}
            	
            }
        });
        btnNewButton_1.setBackground(new Color(255, 255, 255));

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
