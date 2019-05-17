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

public class CreateRace extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;
	private JTextField TxtEdition;
	private JTextField textField_price;
	private JTextField textField_time;
	private JTextField textFieldSponsor;
	private JTextField textField_place;
	private JTextField textFieldPrize;

	public CreateRace() {
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

		JLabel lblEdition = new JLabel("Edition");
		GridBagConstraints gbc_lblEdition = new GridBagConstraints();
		gbc_lblEdition.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblEdition.insets = new Insets(0, 0, 5, 0);
		gbc_lblEdition.gridx = 2;
		gbc_lblEdition.gridy = 0;
		panel_1.add(lblEdition, gbc_lblEdition);

		TxtEdition = new JTextField("");
		TxtEdition.setToolTipText("");
		GridBagConstraints gbc_TxtEdition = new GridBagConstraints();
		gbc_TxtEdition.fill = GridBagConstraints.BOTH;
		gbc_TxtEdition.insets = new Insets(0, 0, 5, 0);
		gbc_TxtEdition.gridx = 2;
		gbc_TxtEdition.gridy = 1;
		panel_1.add(TxtEdition, gbc_TxtEdition);
		TxtEdition.setBackground(new Color(245, 245, 245));
		TxtEdition.setBorder(new LineBorder(new Color(245, 245, 245)));
		TxtEdition.setColumns(10);

		JLabel lblSponsor = new JLabel("Sponsor");
		GridBagConstraints gbc_lblSponsor = new GridBagConstraints();
		gbc_lblSponsor.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblSponsor.insets = new Insets(0, 0, 5, 0);
		gbc_lblSponsor.gridx = 2;
		gbc_lblSponsor.gridy = 2;
		panel_1.add(lblSponsor, gbc_lblSponsor);

		textFieldSponsor = new JTextField("");
		textFieldSponsor.setToolTipText("");
		textFieldSponsor.setColumns(10);
		textFieldSponsor.setBorder(new LineBorder(new Color(245, 245, 245)));
		textFieldSponsor.setBackground(new Color(245, 245, 245));
		GridBagConstraints gbc_textFieldSponsor = new GridBagConstraints();
		gbc_textFieldSponsor.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldSponsor.fill = GridBagConstraints.BOTH;
		gbc_textFieldSponsor.gridx = 2;
		gbc_textFieldSponsor.gridy = 3;
		panel_1.add(textFieldSponsor, gbc_textFieldSponsor);

		JLabel labelPlace = new JLabel("Place");
		GridBagConstraints gbc_labelPlace = new GridBagConstraints();
		gbc_labelPlace.anchor = GridBagConstraints.WEST;
		gbc_labelPlace.insets = new Insets(0, 0, 5, 0);
		gbc_labelPlace.gridx = 2;
		gbc_labelPlace.gridy = 4;
		panel_1.add(labelPlace, gbc_labelPlace);

		textField_place = new JTextField("");
		textField_place.setToolTipText("");
		textField_place.setColumns(10);
		textField_place.setBorder(new LineBorder(new Color(245, 245, 245)));
		textField_place.setBackground(new Color(245, 245, 245));
		GridBagConstraints gbc_textField_place = new GridBagConstraints();
		gbc_textField_place.insets = new Insets(0, 0, 5, 0);
		gbc_textField_place.fill = GridBagConstraints.BOTH;
		gbc_textField_place.gridx = 2;
		gbc_textField_place.gridy = 5;
		panel_1.add(textField_place, gbc_textField_place);

		JLabel labeltimedate = new JLabel("Time & Date");
		GridBagConstraints gbc_labeltimedate = new GridBagConstraints();
		gbc_labeltimedate.anchor = GridBagConstraints.WEST;
		gbc_labeltimedate.insets = new Insets(0, 0, 5, 0);
		gbc_labeltimedate.gridx = 2;
		gbc_labeltimedate.gridy = 6;
		panel_1.add(labeltimedate, gbc_labeltimedate);

		textField_time = new JTextField("");
		GridBagConstraints gbc_textField_time = new GridBagConstraints();
		gbc_textField_time.insets = new Insets(0, 0, 5, 0);
		gbc_textField_time.fill = GridBagConstraints.BOTH;
		gbc_textField_time.gridx = 2;
		gbc_textField_time.gridy = 7;
		panel_1.add(textField_time, gbc_textField_time);
		textField_time.setColumns(10);

		JLabel lblPrice = new JLabel("Price");
		GridBagConstraints gbc_lblPrice = new GridBagConstraints();
		gbc_lblPrice.anchor = GridBagConstraints.WEST;
		gbc_lblPrice.insets = new Insets(0, 0, 5, 0);
		gbc_lblPrice.gridx = 2;
		gbc_lblPrice.gridy = 8;
		panel_1.add(lblPrice, gbc_lblPrice);

		textField_price = new JTextField("");
		GridBagConstraints gbc_textField_price = new GridBagConstraints();
		gbc_textField_price.insets = new Insets(0, 0, 5, 0);
		gbc_textField_price.fill = GridBagConstraints.BOTH;
		gbc_textField_price.gridx = 2;
		gbc_textField_price.gridy = 10;
		panel_1.add(textField_price, gbc_textField_price);
		textField_price.setColumns(10);

		JLabel lblPrize = new JLabel("Prize");
		GridBagConstraints gbc_lblPrize = new GridBagConstraints();
		gbc_lblPrize.anchor = GridBagConstraints.WEST;
		gbc_lblPrize.insets = new Insets(0, 0, 5, 0);
		gbc_lblPrize.gridx = 2;
		gbc_lblPrize.gridy = 11;
		panel_1.add(lblPrize, gbc_lblPrize);

		textFieldPrize = new JTextField("");
		textFieldPrize.setColumns(10);
		GridBagConstraints gbc_textFieldPrize = new GridBagConstraints();
		gbc_textFieldPrize.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldPrize.fill = GridBagConstraints.BOTH;
		gbc_textFieldPrize.gridx = 2;
		gbc_textFieldPrize.gridy = 12;
		panel_1.add(textFieldPrize, gbc_textFieldPrize);

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
		panel_2.setBounds(351, 362, 257, 35);
		panel.add(panel_2);
		panel_2.setOpaque(false);

		JButton btnBack = new JButton("Back");
		panel_2.add(btnBack);
		btnBack.setBackground(new Color(255, 255, 255));
		btnBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CreateRace.this.setVisible(false);

			}
		});

		JButton btnNewButton_1 = new JButton("Create");
		panel_2.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RaceGateway rgw=new RaceGateway();
				DateFormat formatter = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
				Date date = null;
				try {
					date = formatter.parse(textField_time.getText());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Runner[] runners=null;
				User[] users=null;
				Race r=new Race(TxtEdition.getText(), textFieldSponsor.getText(), textField_place.getText(), date, Float.parseFloat(textField_price.getText()), Float.parseFloat(textFieldPrize.getText()),runners, users);
				if (rgw.addRace(r)) {
					JOptionPane.showMessageDialog(CreateRace.this, "Race Created");
				} else {
					JOptionPane.showMessageDialog(CreateRace.this, "Error while creating the race. Try again");
				}

			}
		});
		btnNewButton_1.setBackground(new Color(255, 255, 255));

	}
}
