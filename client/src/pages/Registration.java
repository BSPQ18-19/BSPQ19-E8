package pages;


import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Registration extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;
	private JTextField username;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Login dialog = new Login();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Registration(Login login) {
		setBounds(100, 100, 650, 400);
		contentPanel=new JPanel();
		contentPanel.setLayout(new CardLayout(0,0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		setModalityType(ModalityType.APPLICATION_MODAL);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 153, 204));
		panel.setLayout(null);
		contentPanel.add(panel);
		
		JPanel panel_1 =new JPanel();
		panel_1.setBorder(new LineBorder(Color.WHITE));
		panel_1.setOpaque(false);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(12,74,319,256);
		panel.add(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.rowHeights = new int[] {10, 35, 10, 25, 25, 25, 60};
		gbl_panel_1.columnWidths = new int[] {30, 0, 250, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblUserLogin = new JLabel("User Registration");
		GridBagConstraints gbc_lblUserLogin = new GridBagConstraints();
		gbc_lblUserLogin.insets = new Insets(0, 0, 5, 0);
		gbc_lblUserLogin.fill = GridBagConstraints.BOTH;
		gbc_lblUserLogin.gridx = 2;
		gbc_lblUserLogin.gridy = 1;
		panel_1.add(lblUserLogin, gbc_lblUserLogin);
		lblUserLogin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblUser = new JLabel("");
		GridBagConstraints gbc_lblUser = new GridBagConstraints();
		gbc_lblUser.fill = GridBagConstraints.BOTH;
		gbc_lblUser.insets = new Insets(0, 0, 5, 5);
		gbc_lblUser.gridx = 1;
		gbc_lblUser.gridy = 3;
		panel_1.add(lblUser, gbc_lblUser);
		lblUser.setIcon(new ImageIcon(Login.class.getResource("/icons/user.png")));
		
		username = new JTextField();
		username.setToolTipText("Username");
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 3;
		panel_1.add(username, gbc_textField);
		username.setBackground(new Color(245, 245, 245));
		username.setBorder(new LineBorder(new Color(245, 245, 245)));
		username.setColumns(10);
		
		JLabel lblPassword = new JLabel("");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.fill = GridBagConstraints.BOTH;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 1;
		gbc_lblPassword.gridy = 4;
		panel_1.add(lblPassword, gbc_lblPassword);
		lblPassword.setIcon(new ImageIcon(Login.class.getResource("/icons/key.png")));
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("Password");
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.fill = GridBagConstraints.BOTH;
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.gridx = 2;
		gbc_passwordField.gridy = 4;
		panel_1.add(passwordField, gbc_passwordField);
		passwordField.setBackground(new Color(245, 245, 245));
		passwordField.setBorder(new LineBorder(new Color(245, 245, 245)));
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setToolTipText("Confirm Password");
		GridBagConstraints gbc_passwordField_1 = new GridBagConstraints();
		gbc_passwordField_1.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField_1.fill = GridBagConstraints.BOTH;
		gbc_passwordField_1.gridx = 2;
		gbc_passwordField_1.gridy = 5;
		panel_1.add(passwordField_1, gbc_passwordField_1);
		passwordField_1.setBorder(new LineBorder(new Color(245, 245, 245)));
		passwordField_1.setBackground(new Color(245, 245, 245));
		
		JLabel lblicon = new JLabel("");
		lblicon.setBounds(370, 46, 222, 212);
		panel.add(lblicon);
		lblicon.setHorizontalAlignment(SwingConstants.CENTER);
		lblicon.setIcon(new ImageIcon(Login.class.getResource("/icons/icon.png")));
		
		JLabel lblRaceOrganizer = new JLabel("RACE ORGANIZER");
		lblRaceOrganizer.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblRaceOrganizer.setBounds(28, 34, 230, 27);
		panel.add(lblRaceOrganizer);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(353, 271, 257, 35);
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
				Registration.this.setVisible(false);
				Dashboard das=new Dashboard();
				das.setVisible(true);
			}
		});
		btnNewButton_1.setBackground(new Color(255, 255, 255));
	}
}
