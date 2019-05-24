package pages;

import managment.RaceManagement;
import managment.TaskManagement;
import models.Race;
import models.Runner;
import models.Task;
import models.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Locale;
import java.util.ResourceBundle;

public class EditTask extends JFrame {

	/**
	 * Window for editing or adding new Tasks to a race
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;
	private JLabel lbUserAssigned;
	private JLabel lbldescription;
	private JCheckBox chckbxNewCheckBox;
	private JTextArea textArea;
	private boolean creatingnew;
	private Task actualtask;
	private JPanel panel_1;
	final static int USER_RUNNER=1;
	
	/**
	 * Constructor of the window
	 * @param r race 
	 * @param u user
	 * @param runner Integer to distinguish if is the organiser or a runner entering
	 */
	
	public EditTask(Race r, User u, int runner, Dashboard das) {
		creatingnew=false;
		ResourceBundle resourceBundle = ResourceBundle.getBundle("SystemMessages", Locale.forLanguageTag(Translation.actual_language));
		setBounds(100, 100, 650, 493);
		contentPanel = new JPanel();
		contentPanel.setLayout(new CardLayout(0, 0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 153, 204));
		panel.setLayout(null);
		contentPanel.add(panel);

		panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.WHITE));
		panel_1.setOpaque(false);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(279, 69, 331, 354);
		panel.add(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.rowHeights = new int[] { 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30 };
		gbl_panel_1.columnWidths = new int[] { 30, 250, 20 };
		gbl_panel_1.columnWeights = new double[] { 1.0, 0.0, 1.0 };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0,
				0.0 };
		panel_1.setLayout(gbl_panel_1);

	
		JPanel panel_3 = new JPanel();
		panel_3.setOpaque(false);


		JLabel lblRaceOrganizer = new JLabel(resourceBundle.getString("tasks_for") + r.getEdition() + ": " + r.getTime().toString());
		lblRaceOrganizer.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblRaceOrganizer.setBounds(27, 22, 504, 27);
		panel.add(lblRaceOrganizer);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 388, 257, 35);
		panel.add(panel_2);
		panel_2.setOpaque(false);		

		JList list = new JList(r.getTasks());
		list.setBounds(12, 111, 240, 264);
		panel.add(list);
		list.addMouseListener(new MouseAdapter() {
			
			

			public void mouseClicked(MouseEvent e) {
				creatingnew=false;
				panel_1.removeAll();
		        if (e.getClickCount() == 2) {
		        actualtask = (Task) list.getSelectedValue();
		       	chckbxNewCheckBox = new JCheckBox(resourceBundle.getString("completed"));
		       	chckbxNewCheckBox.setSelected(actualtask.isCompleted());
				chckbxNewCheckBox.setBackground(new Color(51, 102, 153));
				GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
				gbc_chckbxNewCheckBox.anchor = GridBagConstraints.WEST;
				gbc_chckbxNewCheckBox.insets = new Insets(0, 0, 5, 5);
				gbc_chckbxNewCheckBox.gridx = 1;
				gbc_chckbxNewCheckBox.gridy = 1;
				panel_1.add(chckbxNewCheckBox, gbc_chckbxNewCheckBox);

				if(actualtask.getPerson()!=null) {
					lbUserAssigned = new JLabel(resourceBundle.getString("user_assigned")+actualtask.getUsername());
				}else {
					lbUserAssigned = new JLabel(resourceBundle.getString("no_usser_assigned"));
				}
				
				GridBagConstraints gbc_lbUserAssigned = new GridBagConstraints();
				gbc_lbUserAssigned.anchor = GridBagConstraints.WEST;
				gbc_lbUserAssigned.insets = new Insets(0, 0, 5, 5);
				gbc_lbUserAssigned.gridx = 1;
				gbc_lbUserAssigned.gridy = 2;
				panel_1.add(lbUserAssigned, gbc_lbUserAssigned);

				lbldescription = new JLabel(resourceBundle.getString("description"));
				GridBagConstraints gbc_lbldescription = new GridBagConstraints();
				gbc_lbldescription.anchor = GridBagConstraints.WEST;
				gbc_lbldescription.insets = new Insets(0, 0, 5, 5);
				gbc_lbldescription.gridx = 1;
				gbc_lbldescription.gridy = 3;
				panel_1.add(lbldescription, gbc_lbldescription);

				textArea = new JTextArea(actualtask.getDescription());
				GridBagConstraints gbc_textArea = new GridBagConstraints();
				gbc_textArea.gridheight = 10;
				gbc_textArea.insets = new Insets(0, 0, 5, 5);
				gbc_textArea.fill = GridBagConstraints.BOTH;
				gbc_textArea.gridx = 1;
				gbc_textArea.gridy = 4;
				panel_1.add(textArea, gbc_textArea);

		         }
		    }
		
		});
		
		JButton btnBack = new JButton(resourceBundle.getString("back"));
		panel_2.add(btnBack);
		btnBack.setBackground(new Color(255, 255, 255));
		btnBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				EditTask.this.setVisible(false);

			}
		});
		if(runner!=USER_RUNNER) {
			
			JButton btnNew = new JButton(resourceBundle.getString("new"));
			btnNew.setBounds(12, 82, 61, 25);
			panel.add(btnNew);
			btnNew.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					panel_1.removeAll();
					creatingnew=true;
			       	JCheckBox chckbxNewCheckBox = new JCheckBox(resourceBundle.getString("completed"));
			       	GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
					gbc_chckbxNewCheckBox.anchor = GridBagConstraints.WEST;
					gbc_chckbxNewCheckBox.insets = new Insets(0, 0, 5, 5);
					gbc_chckbxNewCheckBox.gridx = 1;
					gbc_chckbxNewCheckBox.gridy = 1;
					panel_1.add(chckbxNewCheckBox, gbc_chckbxNewCheckBox);
					
					lbldescription = new JLabel(resourceBundle.getString("description"));
					GridBagConstraints gbc_lbldescription = new GridBagConstraints();
					gbc_lbldescription.anchor = GridBagConstraints.WEST;
					gbc_lbldescription.insets = new Insets(0, 0, 5, 5);
					gbc_lbldescription.gridx = 1;
					gbc_lbldescription.gridy = 1;
					panel_1.add(lbldescription, gbc_lbldescription);

					textArea = new JTextArea("");
					GridBagConstraints gbc_textArea = new GridBagConstraints();
					gbc_textArea.gridheight = 10;
					gbc_textArea.insets = new Insets(0, 0, 5, 5);
					gbc_textArea.fill = GridBagConstraints.BOTH;
					gbc_textArea.gridx = 1;
					gbc_textArea.gridy = 2;
					panel_1.add(textArea, gbc_textArea);
				}
			});
		}
		
		
		JButton btnSUBMIT = new JButton(resourceBundle.getString("submit"));
		panel_2.add(btnSUBMIT);
		btnSUBMIT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(runner==USER_RUNNER) {
					actualtask.setPerson(u);
					if(TaskManagement.editTask(r.getRace_id(), actualtask)) {
						JOptionPane.showMessageDialog(EditTask.this,resourceBundle.getString("edit_task_error"));
					}else {
						JOptionPane.showMessageDialog(EditTask.this,resourceBundle.getString("edit_task_error"));
					}
				}else {
					if(creatingnew) {
						if(TaskManagement.addTask(r.getRace_id(), textArea.getText())) {
							JOptionPane.showMessageDialog(EditTask.this,resourceBundle.getString("add_task_succes"));
						}else {
							JOptionPane.showMessageDialog(EditTask.this,resourceBundle.getString("add_task_error"));
						}
					}else {
						actualtask.setCompleted(chckbxNewCheckBox.isSelected());
						actualtask.setDescription(textArea.getText());
						if(TaskManagement.editTask(r.getRace_id(), actualtask)) {
							JOptionPane.showMessageDialog(EditTask.this,resourceBundle.getString("edit_task_error"));
						}else {
							JOptionPane.showMessageDialog(EditTask.this,resourceBundle.getString("edit_task_error"));
						}
					}
				}
					
			}
		});
		btnSUBMIT.setBackground(Color.WHITE);
	}
}
