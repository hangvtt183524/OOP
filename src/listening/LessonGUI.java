package listening;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Utilities;
import vocab.WordList;
import vocab.Database;
import vocab.Word;

public class LessonGUI extends JPanel implements MouseListener{
	private LessonListening lesson;
	private Thread th;
	private JLabel play = new JLabel();
	private JLabel pause = new JLabel();
	private JLabel resume = new JLabel();
	private JTextArea textTranscript = new JTextArea();
	private JScrollPane scrollPane = new JScrollPane();
	private JScrollPane scrollPaneMenu;
	
	public LessonGUI(int numbericalOrder)
	{
		super();
		
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		
		this.play.setSize(20, 20);
		this.play.setIcon(resizeImage(play, "img/play.png"));
		
		this.pause.setSize(20, 20);
		this.pause.setIcon(resizeImage(pause, "img/pause.png"));
		
		this.resume.setSize(20, 20);
		this.resume.setIcon(resizeImage(resume, "img/resume.png"));
		
		setTextPane();
		
		layout.setHorizontalGroup(
				   layout.createParallelGroup()
				   .addGroup(layout.createSequentialGroup()
				           .addComponent(this.play,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE, 20)
				           .addComponent(this.pause,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE, 20)
				           .addComponent(this.resume,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE, 20))
				    .addComponent(this.scrollPane,700,700, Short.MAX_VALUE )
				);
		
		layout.setVerticalGroup(
				   layout.createSequentialGroup()
				      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				           .addComponent(this.play,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE, 20)
				           .addComponent(this.pause,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE, 20)
				           .addComponent(this.resume,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE, 20))
				      
				      .addComponent(this.scrollPane,650,650, Short.MAX_VALUE)
				);

		
		
		this.lesson = new LessonListening("database/listening.db", "Lesson", numbericalOrder);
		this.th = new Thread(new PlayAudio(this.lesson.getAudio()));
		
		setTranscriptInPanel();
		
		this.textTranscript.addMouseListener(this);
		
		this.play.addMouseListener(new MouseAdapter() {
			 public void mouseReleased(MouseEvent e)
			 {
				 th.start();
			 }
		});
		this.pause.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e)
			{
				th.suspend();
			}
		});
		this.resume.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e)
			{
				th.resume();
			}
		});
		
		//this.setVisible(true);
	}
	
	private ImageIcon resizeImage(JComponent comp, String image)
	{
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File(image));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(comp.getWidth(), comp.getHeight(),
		        Image.SCALE_SMOOTH);
		
		ImageIcon imageIcon = new ImageIcon(dimg);
		
		return imageIcon;
	}
	
	private void setTextPane()
	{
		this.scrollPane.setViewportView(this.textTranscript);
		this.scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	}
	
	private void setTranscriptInPanel()
	{
		this.textTranscript.setFont(new Font("", Font.PLAIN, 20));
		File file = new File(this.lesson.getTranscript());
        try
        {
        	InputStream inputStream = new FileInputStream(file);
            Reader rd = new java.io.InputStreamReader(inputStream, "utf8");
            BufferedReader reader = new BufferedReader(rd);
     
            String line = "";
            while((line = reader.readLine()) != null){
            	this.textTranscript.append("\n        " + line);
    	}}
            catch (Exception e)
            {
            	e.printStackTrace();
            }
	}
	
	protected void stopAudio()
	{
		this.th.stop();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		JTextArea def = new JTextArea();
		try {
		int caretPosition = this.textTranscript.getCaretPosition();
		
		int start = Utilities.getWordStart(this.textTranscript, caretPosition);
        int end = Utilities.getWordEnd(this.textTranscript, caretPosition);
        
        //this.textTranscript.getHighlighter().addHighlight(start, end, DefaultHighlighter.DefaultPainter);
        
        String word = this.textTranscript.getText(start, end-start);
		
		List<String> result = Database.searchDefinitions(word);
		for (String s: result)
		{
			def.append("\n" + s);
			def.append("\n");
		}
		this.textTranscript.setCaretPosition(caretPosition);
        def.setEditable(false);
        def.setWrapStyleWord(true);
        def.setLineWrap(true);
        
        def.setFont(new Font("Arial", Font.PLAIN, 18));
        
		JPopupMenu popupMenu = new JPopupMenu();
		scrollPaneMenu = new JScrollPane();
		scrollPaneMenu.setPreferredSize(new Dimension(400, 500));
		JButton saveBtn = new JButton("Save");
		JLabel savedLb = new JLabel("Saved");
		savedLb.setFont(new Font("Tahoma", Font.PLAIN, 20));
		savedLb.setHorizontalAlignment(SwingConstants.CENTER);
		saveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				WordList.insertWord(word);
				scrollPaneMenu.remove(saveBtn);
				scrollPaneMenu.setColumnHeaderView(savedLb);
				scrollPaneMenu.revalidate();
				scrollPaneMenu.repaint();
			}
		});
		
		if (WordList.hasWord(word)) {
			scrollPaneMenu.setColumnHeaderView(savedLb);
		}
		else {
			scrollPaneMenu.setColumnHeaderView(saveBtn);
		}
		scrollPaneMenu.setViewportView(def);
        popupMenu.add(scrollPaneMenu);
        popupMenu.show(textTranscript, e.getX(), e.getY() + 20);

		}
		catch(BadLocationException bex)
		{
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
