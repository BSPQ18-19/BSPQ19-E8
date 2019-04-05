package pages;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import net.miginfocom.swing.MigLayout;

public class Login extends JDialog {

	private JPanel contentPanel;
	private JTextField username;
	private JPasswordField password;
	private JTextField txtLogin;
	

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
		panel.setBackground(new Color(51, 102, 153));
		panel.setLayout(null);
		contentPanel.add(panel);
		
		JPanel panel_1 =new JPanel();
		panel_1.setBorder(new LineBorder(Color.WHITE));
		panel_1.setOpaque(false);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(314,92,279,232);
		panel.add(panel_1);
		panel_1.setLayout(new MigLayout("", "[][][][]", "[][][][][][][]"));
		
		JButton btnLogin = new JButton("Login");
		panel_1.add(btnLogin, "cell 1 6");
		
		JButton btnNewButton_1 = new JButton("Register");
		panel_1.add(btnNewButton_1, "cell 2 6");
		
		
		setUndecorated(true);
	}
}
