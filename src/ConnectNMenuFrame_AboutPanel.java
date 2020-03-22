package ConnectN;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class ConnectNMenuFrame_AboutPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public ConnectNMenuFrame_AboutPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{33, 225, 0};
		gridBagLayout.rowHeights = new int[]{38, 33, 27, 38, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblConnectN = new JLabel("Connect N");
		lblConnectN.setForeground(Color.MAGENTA);
		lblConnectN.setBackground(Color.WHITE);
		lblConnectN.setFont(new Font("Goudy Stout", Font.PLAIN, 22));
		GridBagConstraints gbc_lblConnectN = new GridBagConstraints();
		gbc_lblConnectN.fill = GridBagConstraints.BOTH;
		gbc_lblConnectN.insets = new Insets(0, 0, 5, 0);
		gbc_lblConnectN.gridx = 1;
		gbc_lblConnectN.gridy = 0;
		add(lblConnectN, gbc_lblConnectN);
		
		JLabel lblJoeyBitar = new JLabel("Joey Bitar");
		lblJoeyBitar.setForeground(Color.CYAN);
		lblJoeyBitar.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		GridBagConstraints gbc_lblJoeyBitar = new GridBagConstraints();
		gbc_lblJoeyBitar.insets = new Insets(0, 0, 5, 0);
		gbc_lblJoeyBitar.gridheight = 3;
		gbc_lblJoeyBitar.gridx = 1;
		gbc_lblJoeyBitar.gridy = 0;
		add(lblJoeyBitar, gbc_lblJoeyBitar);
		
		JLabel label = new JLabel("2018");
		label.setForeground(Color.BLUE);
		label.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 20));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.NORTH;
		gbc_label.insets = new Insets(0, 0, 5, 0);
		gbc_label.gridx = 1;
		gbc_label.gridy = 2;
		add(label, gbc_label);
		
		JLabel lblHeritageCollege = new JLabel("Heritage College");
		lblHeritageCollege.setForeground(Color.PINK);
		lblHeritageCollege.setFont(new Font("Gill Sans Ultra Bold Condensed", Font.PLAIN, 20));
		GridBagConstraints gbc_lblHeritageCollege = new GridBagConstraints();
		gbc_lblHeritageCollege.fill = GridBagConstraints.VERTICAL;
		gbc_lblHeritageCollege.gridx = 1;
		gbc_lblHeritageCollege.gridy = 3;
		add(lblHeritageCollege, gbc_lblHeritageCollege);

	}

}
