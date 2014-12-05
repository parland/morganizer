/**
*Text genereted by Simple GUI Extension for BlueJ
*/
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.border.Border;
import javax.swing.*;


public class GUI_project extends JFrame {

	private JMenuBar menuBar;
	private JEditorPane editorpane1;
	private JList list1;
	private JTextArea textarea1;
	private JTextField textfield1;

	//Constructor 
	public GUI_project(){

		setTitle("GUI_project");
		setSize(500,400);
		//menu generate method
		generateMenu();
		setJMenuBar(menuBar);

		//pane with null layout
		JPanel contentPane = new JPanel(null);
		contentPane.setPreferredSize(new Dimension(500,400));
		contentPane.setBackground(new Color(192,192,192));


		editorpane1 = new JEditorPane();
		editorpane1.setBounds(12,300,479,87);
		editorpane1.setBackground(new Color(214,217,223));
		editorpane1.setForeground(new Color(0,0,0));
		editorpane1.setEnabled(true);
		editorpane1.setFont(new Font("sansserif",0,12));
		editorpane1.setText("JEditor\nPane");
		editorpane1.setBorder(BorderFactory.createBevelBorder(1));
		editorpane1.setVisible(true);

		list1 = new JList();
		list1.setBounds(7,5,468,98);
		list1.setBackground(new Color(255,255,255));
		list1.setForeground(new Color(0,0,0));
		list1.setEnabled(true);
		list1.setFont(new Font("sansserif",0,12));
		list1.setVisible(true);

		textarea1 = new JTextArea();
		textarea1.setBounds(9,203,486,94);
		textarea1.setBackground(new Color(255,255,255));
		textarea1.setForeground(new Color(0,0,0));
		textarea1.setEnabled(true);
		textarea1.setFont(new Font("sansserif",0,12));
		textarea1.setText("JText\nArea");
		textarea1.setBorder(BorderFactory.createBevelBorder(1));
		textarea1.setVisible(true);

		textfield1 = new JTextField();
		textfield1.setBounds(7,106,485,98);
		textfield1.setBackground(new Color(255,255,255));
		textfield1.setForeground(new Color(0,0,0));
		textfield1.setEnabled(true);
		textfield1.setFont(new Font("sansserif",0,12));
		textfield1.setText("JText\n+Field");
		textfield1.setVisible(true);

		//adding components to contentPane panel
		contentPane.add(editorpane1);
		contentPane.add(list1);
		contentPane.add(textarea1);
		contentPane.add(textfield1);

		//adding panel to JFrame and seting of window position and close operation
		getContentPane().add(contentPane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		pack();
		setVisible(true);
	}

	//method for generate menu
	public void generateMenu(){
		menuBar = new JMenuBar();

		JMenu file = new JMenu("File");
		JMenu tools = new JMenu("Tools");
		JMenu help = new JMenu("Help");

		JMenuItem open = new JMenuItem("Open   ");
		JMenuItem save = new JMenuItem("Save   ");
		JMenuItem exit = new JMenuItem("Exit   ");
		JMenuItem preferences = new JMenuItem("Preferences   ");
		JMenuItem about = new JMenuItem("About   ");


		file.add(open);
		file.add(save);
		file.addSeparator();
		file.add(exit);
		tools.add(preferences);
		help.add(about);

		menuBar.add(file);
		menuBar.add(tools);
		menuBar.add(help);
	}



	 public static void main(String[] args){
		System.setProperty("swing.defaultlaf", "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new GUI_project();
			}
		});
	}

}