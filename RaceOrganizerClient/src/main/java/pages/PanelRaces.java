package pages;

import models.Race;
import models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

public class PanelRaces extends JPanel {

	/**
	 *	Panel that displays info about the race
	 */
	private static final long serialVersionUID = 1L;
	private final int USER_SETTASK = 2;

	/**
	 * Constructor of the panel
	 * @param r Race
	 * @param u User 
	 * @param org int if the org is the one thats entering: 2
	 * @param das dashboard window
	 */

	public PanelRaces(Race r, User u, int org, Dashboard das) {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("SystemMessages", Locale.forLanguageTag(Translation.actual_language));

		setBackground(Color.LIGHT_GRAY);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{180, 100, 0, 30, 0};
		gridBagLayout.rowHeights = new int[]{30, 30, 30, 30};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0};
		setLayout(gridBagLayout);

		JLabel lblEdition = new JLabel(resourceBundle.getString("edition") + r.getEdition());
		lblEdition.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblEdition = new GridBagConstraints();
		gbc_lblEdition.anchor = GridBagConstraints.WEST;
		gbc_lblEdition.insets = new Insets(0, 0, 5, 5);
		gbc_lblEdition.gridx = 0;
		gbc_lblEdition.gridy = 1;
		add(lblEdition, gbc_lblEdition);

		JLabel lblDate = new JLabel(resourceBundle.getString("date") + r.getTime().toString());
		GridBagConstraints gbc_lblDate = new GridBagConstraints();
		gbc_lblDate.gridheight = 2;
		gbc_lblDate.anchor = GridBagConstraints.WEST;
		gbc_lblDate.gridwidth = 2;
		gbc_lblDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblDate.gridx = 1;
		gbc_lblDate.gridy = 1;
		add(lblDate, gbc_lblDate);

		JButton btnNewButton = new JButton(resourceBundle.getString("view"));
		btnNewButton.setBackground(new Color(51, 102, 204));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridheight = 2;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 1;
		add(btnNewButton, gbc_btnNewButton);

		JLabel lblNewLabel = new JLabel(resourceBundle.getString("place") + r.getPlace());
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 2;
		add(lblNewLabel, gbc_lblNewLabel);
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				RaceDetail rd = new RaceDetail(r, u, das);
				rd.setVisible(true);
			}
		});

		if (org == USER_SETTASK) {
			JButton btnNewButton_1 = new JButton(resourceBundle.getString("edit_task"));
			btnNewButton_1.setBackground(new Color(51, 102, 153));
			GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
			gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
			gbc_btnNewButton_1.gridx = 4;
			gbc_btnNewButton_1.gridy = 1;
			add(btnNewButton_1, gbc_btnNewButton_1);
			btnNewButton_1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					EditTask et=new EditTask(r, u, 0, das);
					et.setVisible(true);
				}
			});
		}

	}
}
