package ConnectN;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

public class ConnectNMenuFrame extends JFrame implements ActionListener { // Class
	ConnectNGame gameOjbect; // ConnectNGame object
	private JMenuBar menuBar = new JMenuBar();// Creating menu bar
	private JMenu menu = new JMenu("Menu");// Creating menu
	private JMenuItem restoreGame = new JMenuItem("Restore Game"); // Restore
																	// menu
	private JMenuItem exit = new JMenuItem("Exit"); // Exit menu
	private JMenu help = new JMenu("Help"); // Help menu
	private JMenuItem instructions = new JMenuItem("Instructions"); // Instruction
																	// menu
	private JMenuItem about = new JMenuItem("About"); // about menu
	private JPanel cbxNN; // Creating jpanel for cbx boxes
	private JTextField fldPlayerOne; // p1 text field
	private JTextField fldPlayerTwo; // p2 text field
	private JLabel lblRows; // row label
	private JComboBox cbxRows; // cbx for orw
	private JLabel lblN; // label for n
	private JComboBox cbxNs; // cbx for n
	private JLabel lblN2; // label n number 2
	private JLabel lblColumns; // label for column
	private JComboBox cbxColumn; // cbx for column
	private JLabel lblPlayerOne; // label for p1
	private JLabel lblPlayerTwo; // label for p2
	private JButton btnPlay; // button to play the game

	// Constructor which initializes the frame and game, and the position of the
	// frame.
	public ConnectNMenuFrame() {
		setJMenuBar(menuBar);
		menuBar.add(menu);
		menu.add(restoreGame);
		menu.add(exit);
		menuBar.add(help);
		help.add(instructions);
		help.add(about);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 649, 388);
		cbxNN = new JPanel();
		cbxNN.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(cbxNN);
		cbxNN.setLayout(null);

		lblRows = new JLabel("Number of Rows:");
		lblRows.setFont(new Font("Impact", Font.PLAIN, 20));
		lblRows.setBounds(15, 16, 163, 53);
		cbxNN.add(lblRows);

		cbxRows = new JComboBox();
		cbxRows.setModel(new DefaultComboBoxModel(new String[] { "", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
		cbxRows.setBackground(Color.CYAN);
		cbxRows.setBounds(155, 31, 50, 26);
		cbxNN.add(cbxRows);

		lblN = new JLabel("N:");
		lblN.setFont(new Font("Impact", Font.PLAIN, 20));
		lblN.setBounds(254, 32, 30, 20);
		cbxNN.add(lblN);

		cbxNs = new JComboBox();
		cbxNs.setBackground(Color.MAGENTA);
		cbxNs.setModel(new DefaultComboBoxModel(new String[] { "", "3", "4", "5", "6", "7", "8" }));
		cbxNs.setBounds(275, 31, 36, 26);
		cbxNN.add(cbxNs);

		lblN2 = new JLabel(":N");
		lblN2.setFont(new Font("Impact", Font.PLAIN, 20));
		lblN2.setBounds(315, 30, 36, 25);
		cbxNN.add(lblN2);

		lblColumns = new JLabel(":Number of Columns");
		lblColumns.setFont(new Font("Impact", Font.PLAIN, 20));
		lblColumns.setBounds(451, 25, 163, 35);
		cbxNN.add(lblColumns);

		cbxColumn = new JComboBox();
		cbxColumn.setBackground(Color.PINK);
		cbxColumn.setModel(
				new DefaultComboBoxModel(new String[] { "", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
		cbxColumn.setBounds(377, 31, 59, 26);
		cbxNN.add(cbxColumn);

		lblPlayerOne = new JLabel("Player One:");
		lblPlayerOne.setFont(new Font("Impact", Font.PLAIN, 20));
		lblPlayerOne.setBounds(60, 117, 98, 20);
		cbxNN.add(lblPlayerOne);

		fldPlayerOne = new JTextField();
		fldPlayerOne.setBounds(155, 116, 98, 26);
		cbxNN.add(fldPlayerOne);
		fldPlayerOne.setColumns(10);

		lblPlayerTwo = new JLabel(":Player Two");
		lblPlayerTwo.setFont(new Font("Impact", Font.PLAIN, 20));
		lblPlayerTwo.setBounds(451, 117, 105, 20);
		cbxNN.add(lblPlayerTwo);

		fldPlayerTwo = new JTextField();
		fldPlayerTwo.setBounds(340, 116, 105, 26);
		cbxNN.add(fldPlayerTwo);
		fldPlayerTwo.setColumns(10);

		btnPlay = new JButton("Play");
		btnPlay.setBounds(230, 176, 132, 70);
		cbxNN.add(btnPlay);
		about.addActionListener(this);
		instructions.addActionListener(this);
		btnPlay.addActionListener(this);
		restoreGame.addActionListener(this);
		JOptionPane.showMessageDialog(this,
				"WARNING: I was able to complete everything in this assignment except the diagonals for the frame(NOT CLI).\n I tried to creates"
						+ " a couple of algorithms in order to finish the game but I was unsuccesful\n, so be warned the last rows in the frame MIGHT give you a compiling error it doesn't\n. It doesn't always happen though. Probably 1/5 times",
				"WARNING!!!", JOptionPane.ERROR_MESSAGE);
	} // ConnectNMenuFrame()

	// validates the player's board input, and then creates the connect n
	// board/frame
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnPlay) {
			if (cbxRows.getSelectedIndex() == 0)
				JOptionPane.showMessageDialog(this, "You must select a valid row", "Invalid row",
						JOptionPane.ERROR_MESSAGE);
			else if (cbxColumn.getSelectedIndex() == 0)
				JOptionPane.showMessageDialog(this, "You must select a valid Column", "Invalid column",
						JOptionPane.ERROR_MESSAGE);
			else if (cbxNs.getSelectedIndex() == 0)
				JOptionPane.showMessageDialog(this, "You must select a N", "Invalid N", JOptionPane.ERROR_MESSAGE);
			else if (fldPlayerOne.getText().isEmpty() || fldPlayerTwo.getText().isEmpty())
				JOptionPane.showMessageDialog(this, "You must enter your name in order to play", "Invalid name",
						JOptionPane.ERROR_MESSAGE);
			else {
				String playerOne = fldPlayerOne.getText();
				String playerTwo = fldPlayerTwo.getText();
				int n = rowAndColumnCbxConverter(cbxNs.getSelectedIndex());
				int row = rowAndColumnCbxConverter(cbxRows.getSelectedIndex());
				int col = rowAndColumnCbxConverter(cbxColumn.getSelectedIndex());
				ConnectNFrame frame = new ConnectNFrame(playerOne, playerTwo, n, row, col, false);
				frame.setVisible(true);
				frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			} // else
		} // btnPlay

		if (e.getSource() == restoreGame) { // Restores previous game
			gameOjbect = new ConnectNGame();
			gameOjbect.openAndRead();
			ConnectNFrame frame = new ConnectNFrame(gameOjbect.getPlayerOneName(), gameOjbect.getPlayerTwoName(),
					gameOjbect.getN(), gameOjbect.getRow(), gameOjbect.getColumn(), true);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		} // restore game

		if (e.getSource() == about) { // Displays about frame
			JOptionPane.showMessageDialog(this, new ConnectNMenuFrame_AboutPanel(), "About", JOptionPane.PLAIN_MESSAGE);
		} // about
		else if (e.getSource() == instructions) { // Displays instructions for
													// the user
			JOptionPane.showMessageDialog(this, new ConnectNMenuFrame_InstructionPanel(), "Instructions",
					JOptionPane.PLAIN_MESSAGE);
		} // Instructios
	}// ActionPerformed

	// Converts cbx index for row and columns
	public int rowAndColumnCbxConverter(int index) {
		int value = 0;
		if (index == 1)
			value = 3;
		if (index == 2)
			value = 4;
		if (index == 3)
			value = 5;
		if (index == 4)
			value = 6;
		if (index == 5)
			value = 7;
		if (index == 6)
			value = 8;
		if (index == 7)
			value = 9;
		if (index == 8)
			value = 11;
		if (index == 9)
			value = 12;
		return value;
	}// rowAndColumnCbxConverter()

	// Converts cbx index for N
	public int nCbxConverter(int n) {
		int value = 0;
		if (n == 1)
			value = 3;
		if (n == 2)
			value = 4;
		if (n == 3)
			value = 5;
		if (n == 4)
			value = 6;
		if (n == 5)
			value = 7;
		if (n == 6)
			value = 8;
		return value;
	}// nCbxConverter()

	// Main
	public static void main(String[] args) {

		ConnectNMenuFrame frame = new ConnectNMenuFrame();
		frame.setVisible(true);
	}// Main
}// ConnectNFrame class
