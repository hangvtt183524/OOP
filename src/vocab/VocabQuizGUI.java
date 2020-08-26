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
import javax.swing.JRadioButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ButtonGroup;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class VocabQuizGUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private static VocabQuizGUI frame;
	private VocabQuizModel quiz;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton backBtn;
	private JButton homeBtn;
	private JButton submitBtn, nextBtn;
	private JRadioButton answer1Btn, answer2Btn, answer3Btn, answer4Btn;
	private JPanel panel;
	private JLabel resultLabel, questionLabel;
	private JTextArea questionArea;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new VocabQuizGUI();
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
	public VocabQuizGUI() {
		quiz = new VocabQuizModel();
		quiz.getNewQuiz();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		backBtn = new JButton("Back");
		backBtn.addActionListener(this);
		
		homeBtn = new JButton("Home");
		homeBtn.addActionListener(this);
		
		panel = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(backBtn)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(homeBtn))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(12)
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(backBtn)
						.addComponent(homeBtn))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		
		answer1Btn = new JRadioButton();
		answer1Btn.setFont(new Font("Tahoma", Font.PLAIN, 19));
		buttonGroup.add(answer1Btn);
		
		answer2Btn = new JRadioButton();
		answer2Btn.setFont(new Font("Tahoma", Font.PLAIN, 19));
		buttonGroup.add(answer2Btn);
		
		answer3Btn = new JRadioButton();
		answer3Btn.setFont(new Font("Tahoma", Font.PLAIN, 19));
		buttonGroup.add(answer3Btn);
		
		answer4Btn = new JRadioButton();
		answer4Btn.setFont(new Font("Tahoma", Font.PLAIN, 19));
		buttonGroup.add(answer4Btn);
		answer1Btn.setText(quiz.getAnswer1());
		answer2Btn.setText(quiz.getAnswer2());
		answer3Btn.setText(quiz.getAnswer3());
		answer4Btn.setText(quiz.getAnswer4());
		
		resultLabel = new JLabel();
		resultLabel.setForeground(Color.RED);
		resultLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JScrollPane scrollPane = new JScrollPane();
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(126)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(answer1Btn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
						.addComponent(answer2Btn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE))
					.addGap(73)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(answer4Btn, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
						.addComponent(answer3Btn, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE))
					.addGap(189))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(25)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
					.addGap(27))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(273)
					.addComponent(resultLabel, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
					.addGap(304))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(21)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
					.addGap(45)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(answer1Btn, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
						.addComponent(answer3Btn, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
					.addGap(39)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(answer2Btn, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
							.addGap(49))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(answer4Btn, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
							.addGap(49)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(resultLabel, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
					.addGap(64))
		);
		
		questionArea = new JTextArea();
		questionArea.setWrapStyleWord(true);
		questionArea.setRows(3);
		questionArea.setLineWrap(true);
		questionArea.setFont(new Font("Arial", Font.PLAIN, 24));
		questionArea.setBackground(Color.CYAN);
		questionArea.setText(quiz.getQuestion());
		scrollPane.setViewportView(questionArea);
		
		questionLabel = new JLabel("Select the word that best fits the definition");
		questionLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		scrollPane.setColumnHeaderView(questionLabel);
		panel_1.setLayout(gl_panel_1);
		
		submitBtn = new JButton("Submit");
		submitBtn.addActionListener(this);
		submitBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(submitBtn, BorderLayout.SOUTH);

		
		nextBtn = new JButton("Next");
		nextBtn.addActionListener(this);
		nextBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		contentPane.setLayout(gl_contentPane);
	}
	
	
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == submitBtn) {
			if (answer1Btn.isSelected()) {
				quiz.setChoice(1);
			}
			if (answer2Btn.isSelected()) {
				quiz.setChoice(2);
			}
			if (answer3Btn.isSelected()) {
				quiz.setChoice(3);
			}
			if (answer4Btn.isSelected()) {
				quiz.setChoice(4);
			}
			
			if (quiz.isCorrect()) {
				resultLabel.setText("Correct");
			}
			else {
				resultLabel.setText("Wrong");
			}
			panel.remove(submitBtn);
			panel.add(nextBtn, BorderLayout.SOUTH);
			panel.revalidate();
			panel.repaint();
		}
		
		if (e.getSource() == nextBtn) {
			quiz.getNewQuiz();
			questionArea.setText(quiz.getQuestion());
			answer1Btn.setText(quiz.getAnswer1());
			answer2Btn.setText(quiz.getAnswer2());
			answer3Btn.setText(quiz.getAnswer3());
			answer4Btn.setText(quiz.getAnswer4());
			buttonGroup.clearSelection();
			resultLabel.setText(null);
			panel.remove(nextBtn);
			panel.add(submitBtn, BorderLayout.SOUTH);
			panel.revalidate();
			panel.repaint();
		}
		
		if (e.getSource() == homeBtn) {
			frame.setVisible(false);
			GUI.main(null);
		}
		
		if (e.getSource() == backBtn) {
			frame.setVisible(false);
			VocabGUI.main(null);
		}
		
	}
	public JButton getBackBtn() {
		return backBtn;
	}
	public JButton getHomeBtn() {
		return homeBtn;
	}
	public JPanel getContentPane() {
		return contentPane;
	}
	public JButton getSubmitBtn() {
		return submitBtn;
	}
}
