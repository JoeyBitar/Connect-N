package ConnectN;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class ConnectNMenuFrame_InstructionPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public ConnectNMenuFrame_InstructionPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{41, 385, 0};
		gridBagLayout.rowHeights = new int[]{33, 27, 44, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblInstructions = new JLabel("Instructions");
		lblInstructions.setForeground(Color.ORANGE);
		lblInstructions.setFont(new Font("Lucida Console", Font.PLAIN, 20));
		GridBagConstraints gbc_lblInstructions = new GridBagConstraints();
		gbc_lblInstructions.fill = GridBagConstraints.VERTICAL;
		gbc_lblInstructions.insets = new Insets(0, 0, 5, 0);
		gbc_lblInstructions.gridx = 1;
		gbc_lblInstructions.gridy = 0;
		add(lblInstructions, gbc_lblInstructions);
		
		JLabel lblNewLabel = new JLabel("To win Connect N you must be the first player to get four of your colored checkers in a row either horizontally, vertically or diagonally.");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 2;
		add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("either horizontally, vertically or diagonally.");
		lblNewLabel_1.setFont(new Font("Yu Gothic", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 3;
		add(lblNewLabel_1, gbc_lblNewLabel_1);

	}
}
