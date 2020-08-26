package listening;

import java.awt.EventQueue;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JComponent;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.border.LineBorder;
import vocab.GUI;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.GroupLayout;

public class ListeningGUI extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton homeBtn = new JButton("Home");
	private JPanel panel = new JPanel();
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuLesson= new JMenu("Lesson");
	private List<JMenuItem> itemLesson = new ArrayList<JMenuItem>();
	private List<LessonGUI> lessonList = new ArrayList<LessonGUI>();
	private static ListeningGUI frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new ListeningGUI(4);
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
	public ListeningGUI(int numberOfLesson) {
		setTitle("Listening");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 70, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		GroupLayout layout = new GroupLayout(contentPane);
		contentPane.setLayout(layout);
		
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		this.homeBtn.setActionCommand("home");
		this.homeBtn.addActionListener(this);
		
		this.panel.setBorder(new LineBorder(SystemColor.controlShadow));
		
		this.menuBar.add(this.menuLesson);
		
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(this.homeBtn, 30, GroupLayout.DEFAULT_SIZE, 100)
						.addComponent(this.menuBar, 30, GroupLayout.DEFAULT_SIZE, 100))
				.addComponent(this.panel, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				);
		
		layout.setHorizontalGroup(
				layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
						.addComponent(this.homeBtn, 30, GroupLayout.DEFAULT_SIZE, 100)
						.addComponent(this.menuBar, 30, GroupLayout.DEFAULT_SIZE, 100))
				.addComponent(this.panel, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				);

        this.panel.setLayout(new GridLayout(0,1));

		for (int i=0;i<numberOfLesson;i++)
		{
			this.lessonList.add(new LessonGUI(i+1));
			//this.panel.add(this.lessonList.get(i));
			
			this.itemLesson.add(new JMenuItem("Lesson "+ (i+1)));
			this.menuLesson.add(this.itemLesson.get(i));
		}
		this.panel.add(this.lessonList.get(0));
		for (int j=0;j<numberOfLesson;j++)
		{
			this.itemLesson.get(j).setActionCommand("lesson "+j);
			this.itemLesson.get(j).addActionListener(this);
		}
		
	}
	private void changePanel(JComponent superPanel, JComponent subPanel)
	{
		superPanel.removeAll();
		superPanel.add(subPanel);
		superPanel.repaint();
		superPanel.revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		int i = 0, j=0;
		while (i<this.lessonList.size())
		{
			if (command.equals("lesson "+i)) 
			{
				while(j<this.lessonList.size())
				{
					if (j!=i) this.lessonList.get(j).stopAudio();
					j++;
				}
				changePanel(this.panel, this.lessonList.get(i)); break;
			}
			i++;
		}
		
		if (e.getSource() == homeBtn) {
			frame.setVisible(false);
			GUI.main(null);
			
			for (LessonGUI ls : lessonList)
			{
				ls.stopAudio();
			}
		}
	}
	
	public JPanel getContentPane() {
		return contentPane;
	}
}
