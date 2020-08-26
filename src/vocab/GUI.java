package vocab;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import listening.ListeningGUI;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class GUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton vocabNavBtn;
	private JButton listeningNavBtn;
	private static GUI frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new GUI();
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
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("3musketeers");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		listeningNavBtn = new JButton("Listening");
		listeningNavBtn.addActionListener(this);
		
		vocabNavBtn = new JButton("Vocabulary");
		vocabNavBtn.addActionListener(this);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(112)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(vocabNavBtn, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
						.addComponent(listeningNavBtn, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(22)
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(31)))
					.addGap(89))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(39)
					.addComponent(listeningNavBtn, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
					.addGap(30)
					.addComponent(vocabNavBtn, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
					.addGap(66))
		);
		contentPane.setLayout(gl_contentPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == listeningNavBtn) {
			frame.setVisible(false);
			ListeningGUI.main(null);
		}
		
		if (e.getSource() == vocabNavBtn) {
			frame.setVisible(false);
			VocabGUI.main(null);
		}
	}

	public JButton getVocabNavBtn() {
		return vocabNavBtn;
	}
	public JButton getReadingNavBtn() {
		return readingNavBtn;
	}
	public JButton getListeningNavBtn() {
		return listeningNavBtn;
	}
}
