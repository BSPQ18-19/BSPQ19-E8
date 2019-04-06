package pages;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JDialog {

	private JPanel contentPanel;
	private JTextField username;
	private JPasswordField password;
	private JTextField txtLogin;
	private JTextField textField;
	private JPasswordField passwordField;
	

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
	public Login() {
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
		panel_1.setBounds(300,100,280,230);
		panel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		panel_1.add(panel_2, BorderLayout.SOUTH);
		
		JButton btnLogin = new JButton("Login");
		panel_2.add(btnLogin);
		btnLogin.setBackground(new Color(255, 255, 255));
		
		JButton btnNewButton_1 = new JButton("Register");
		panel_2.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		
		JPanel panel_3 = new JPanel();
		panel_3.setOpaque(false);
		panel_1.add(panel_3, BorderLayout.CENTER);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[] {30, 35, 166, 0, 0};
		gbl_panel_3.rowHeights = new int[] {30, 50, 30, 40, 30, 40, 80};
		gbl_panel_3.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		panel_3.setLayout(gbl_panel_3);
		
		JLabel lblUserLogin = new JLabel("User Login");
		lblUserLogin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_lblUserLogin = new GridBagConstraints();
		gbc_lblUserLogin.insets = new Insets(0, 0, 5, 5);
		gbc_lblUserLogin.gridx = 2;
		gbc_lblUserLogin.gridy = 2;
		panel_3.add(lblUserLogin, gbc_lblUserLogin);
		
		JLabel lblUser = new JLabel("");
		lblUser.setIcon(new ImageIcon(Login.class.getResource("/icons/user.png")));
		GridBagConstraints gbc_lblUser = new GridBagConstraints();
		gbc_lblUser.insets = new Insets(0, 0, 5, 5);
		gbc_lblUser.anchor = GridBagConstraints.EAST;
		gbc_lblUser.gridx = 1;
		gbc_lblUser.gridy = 3;
		panel_3.add(lblUser, gbc_lblUser);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 3;
		panel_3.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("");
		lblPassword.setIcon(new ImageIcon(Login.class.getResource("/icons/key.png")));
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.insets = new Insets(0, 0, 0, 5);
		gbc_lblPassword.anchor = GridBagConstraints.EAST;
		gbc_lblPassword.gridx = 1;
		gbc_lblPassword.gridy = 4;
		panel_3.add(lblPassword, gbc_lblPassword);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 0, 5);
		gbc_passwordField.fill = GridBagConstraints.BOTH;
		gbc_passwordField.gridx = 2;
		gbc_passwordField.gridy = 4;
		panel_3.add(passwordField, gbc_passwordField);
		
		JLabel lblicon = new JLabel("");
		lblicon.setBounds(12, 37, 302, 287);
		panel.add(lblicon);
		lblicon.setHorizontalAlignment(SwingConstants.CENTER);
		lblicon.setIcon(new ImageIcon(Login.class.getResource("/icons/icon.png")));
		
		JLabel lblRaceOrganizer = new JLabel("RACE ORGANIZER");
		lblRaceOrganizer.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblRaceOrganizer.setBounds(349, 52, 230, 27);
		panel.add(lblRaceOrganizer);
		
		
		
	}
}
