package vocab;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.BoxLayout;
import javax.swing.JSplitPane;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Font;

public class VocabGUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton flashCardBtn;
	private JButton quizBtn;
	private static VocabGUI frame;
	private JButton backBtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new VocabGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VocabGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		backBtn = new JButton("Back");
		backBtn.addActionListener(this);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.1);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(backBtn)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(12)
							.addComponent(splitPane, GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(backBtn)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(splitPane, GroupLayout.DEFAULT_SIZE, 611, Short.MAX_VALUE))
		);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setRightComponent(scrollPane);
		
		JLabel lblNewLabel = new JLabel("Words");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		scrollPane.setColumnHeaderView(lblNewLabel);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 20));
		
		JPanel panel_1 = new JPanel();
		scrollPane.setRowHeaderView(panel_1);
		
		//panel.setLayout(null);
		
		WordList wl = new WordList();
		List<Word> arrayList = wl.getWordList();
		
		for (Word w : arrayList) {
			JTextArea txtArea = new JTextArea(w.getWord() + ": " + w.getFirstDefinition());
			//txtArea.setRows(2);
			txtArea.setFont(new Font("Arial", Font.PLAIN, 15));
			panel.add(txtArea);
		}
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setResizeWeight(0.45);
		splitPane.setLeftComponent(splitPane_1);
		
		flashCardBtn = new JButton("Flash Card");
		splitPane_1.setLeftComponent(flashCardBtn);
		flashCardBtn.addActionListener(this);
		
		quizBtn = new JButton("Quiz");
		splitPane_1.setRightComponent(quizBtn);
		quizBtn.addActionListener(this);
		
		contentPane.setLayout(gl_contentPane);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == flashCardBtn) {
			frame.setVisible(false);
			FlashCardGUI.main(null);
		}
		
		if (e.getSource() == quizBtn) {
			frame.setVisible(false);
			VocabQuizGUI.main(null);
		}
		
		if (e.getSource() == backBtn) {
			frame.setVisible(false);
			GUI.main(null);
		}
	}
	
	public JButton getFlashCardBtn() {
		return flashCardBtn;
	}
	public JButton getQuizBtn() {
		return quizBtn;
	}
	public JButton getBackBtn() {
		return backBtn;
	}
}
