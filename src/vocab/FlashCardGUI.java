package vocab;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JSplitPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;


public class FlashCardGUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	static FlashCardGUI frame;
	private JTextField txtField;
	private JTextArea txtArea;
	private JLabel en_word, mastery;
	private JPanel panel;
	private JButton homeBtn, backBtn, knewBtn, notKnowBtn;
	private JScrollPane defPane;

	private Word currentWord;
	private WordList wl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new FlashCardGUI();
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
	public FlashCardGUI() {
		
		wl = new WordList();
		currentWord = wl.getArbitraryWord();
		
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
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(homeBtn))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(backBtn)
						.addComponent(homeBtn))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.5);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel.add(splitPane, BorderLayout.SOUTH);
		
		knewBtn = new JButton("I knew this word");
		knewBtn.setFont(new Font("Arial", Font.PLAIN, 20));
		knewBtn.addActionListener(this);
		splitPane.setLeftComponent(knewBtn);
		
		notKnowBtn = new JButton("I didn't know this word");
		notKnowBtn.setFont(new Font("Arial", Font.PLAIN, 20));
		notKnowBtn.addActionListener(this);
		splitPane.setRightComponent(notKnowBtn);
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_1.setResizeWeight(1.0);
		panel.add(splitPane_1, BorderLayout.NORTH);
		
		mastery = new JLabel("Proficiency: " + currentWord.getProficiency());
		mastery.setHorizontalAlignment(SwingConstants.TRAILING);
		splitPane_1.setLeftComponent(mastery);
		
		en_word = new JLabel(currentWord.getWord());
		en_word.setFont(new Font("Tahoma", Font.PLAIN, 25));
		en_word.setHorizontalAlignment(SwingConstants.CENTER);
		splitPane_1.setRightComponent(en_word);
		
		defPane = new JScrollPane();
		
		txtField = new JTextField();
		txtField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				defPane.remove(txtField);
				for (String def : currentWord.getDefinitions()) {
					txtArea.append(def + '\n');
				}
				defPane.setViewportView(txtArea);
				defPane.revalidate();
				defPane.repaint();
			}
		});
		txtField.setFont(new Font("Tahoma", Font.ITALIC, 17));
		txtField.setHorizontalAlignment(SwingConstants.CENTER);
		txtField.setText("Click to see definition and example");
		panel.add(defPane, BorderLayout.CENTER);
		defPane.setViewportView(txtField);
		txtField.setColumns(10);
		
		txtArea = new JTextArea();
		txtArea.setFont(new Font("Tahoma", Font.ITALIC, 17));
		txtArea.setWrapStyleWord(true);
		txtArea.setLineWrap(true);
				
		contentPane.setLayout(gl_contentPane);
	
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == homeBtn) {
			frame.setVisible(false);
			GUI.main(null);
			wl.updateProficiency();
		}
		
		if (e.getSource() == backBtn) {
			frame.setVisible(false);
			VocabGUI.main(null);
			wl.updateProficiency();
		}
		
		if (e.getSource() == knewBtn) {
			// increments the proficiency
			int proficiency = currentWord.getProficiency();
			if (proficiency < 7) {
				currentWord.setProficiency(proficiency + 1);
			}
			
			// get the next word
			currentWord = wl.getArbitraryWord();
			
			// repaint the panel
			txtArea.setText(null);
			defPane.remove(txtArea);
			defPane.setViewportView(txtField);
			en_word.setText(currentWord.getWord());
			mastery.setText(Integer.toString(currentWord.getProficiency()));
			panel.revalidate();
			panel.repaint();
		}
		
		if (e.getSource() == notKnowBtn) {
			
			// decrements the proficiency
			int proficiency = currentWord.getProficiency();
			if (proficiency > 0) {
				currentWord.setProficiency(proficiency - 1);
			}
			
			// get the next word
			currentWord = wl.getArbitraryWord();
			
			// repaint the panel
			txtArea.setText(null);
			defPane.remove(txtArea);
			defPane.setViewportView(txtField);
			en_word.setText(currentWord.getWord());
			mastery.setText("Proficiency: " + currentWord.getProficiency());
			panel.revalidate();
			panel.repaint();
		}
	}

}
