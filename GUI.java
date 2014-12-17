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
import javax.swing.JFrame;
import java.awt.Window;
import java.awt.Frame;

public class GUI extends JFrame
{

    MusicOrganizer mo = new MusicOrganizer();
    MusicPlayer player = new MusicPlayer();

    private JMenuBar menuBar;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JList list1;

    public GUI()
    {
        setTitle("Music Organizer");
        setSize(500,400);

        generateMenu();
        setJMenuBar(menuBar);

        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(500,400));
        contentPane.setBackground(new Color(255,255,153));
        button1 = new JButton();
        button1.setBounds(99,46,90,35);
        button1.setBackground(new Color(214,217,223));
        button1.setForeground(new Color(0,0,0));
        button1.setEnabled(true);
        button1.setFont(new Font("sansserif",0,12));
        button1.setText("StartPlayer");
        button1.setVisible(true);

        button1.addMouseListener(new MouseAdapter()
            {
                public void mouseClicked(MouseEvent evt)
                {
                    StartPlayer(evt);
                }
            });

        button2 = new JButton();
        button2.setBounds(302,46,90,35);
        button2.setBackground(new Color(255,102,102));
        button2.setForeground(new Color(0,0,0));
        button2.setEnabled(true);
        button2.setFont(new Font("sansserif",0,12));
        button2.setText("StopPlayer");
        button2.setVisible(true);
        button2.addMouseListener(new MouseAdapter()
            {
                public void mouseClicked(MouseEvent evt)
                {
                    StopPlayer(evt);
                }
            });

        button3 = new JButton();
        button3.setBounds(108,144,90,35);
        button3.setBackground(new Color(214,217,223));
        button3.setForeground(new Color(0,0,0));
        button3.setEnabled(true);
        button3.setFont(new Font("sansserif",0,12));
        button3.setText("PlaySong");
        button3.setVisible(true);
        button3.addMouseListener(new MouseAdapter()
            {
                public void mouseClicked(MouseEvent evt)
                {
                    PlaySong(evt);
                }
            });

        button4 = new JButton();
        button4.setBounds(320,138,90,35);
        button4.setBackground(new Color(255,51,51));
        button4.setForeground(new Color(0,0,0));
        button4.setEnabled(true);
        button4.setFont(new Font("sansserif",0,12));
        button4.setText("StopSong");
        button4.setVisible(true);
        button4.addMouseListener(new MouseAdapter()
            {
                public void mouseClicked(MouseEvent evt)
                {
                    StopSong(evt);
                }
            });

        list1 = new JList();
        list1.setBounds(217,247,150,100);
        list1.setBackground(new Color(255,255,255));
        list1.setForeground(new Color(0,0,0));
        list1.setEnabled(true);
        list1.setFont(new Font("sansserif",0,12));
        list1.setVisible(true);

        contentPane.add(button1);
        contentPane.add(button2);
        contentPane.add(button3);
        contentPane.add(button4);
        contentPane.add(list1);

        getContentPane().add(contentPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

    private void StartPlayer (MouseEvent evt)
    {
        mo.startMusicOrganizer();
    }

    private void StopPlayer (MouseEvent evt)
    {
        System.exit(0);
    }

    private void PlaySong (MouseEvent evt)
    {
        mo.playRandom();
    }

    private void StopSong (MouseEvent evt)
    {
        mo.stopPlaying();
    }

    private void ListAllFiles (ActionEvent evt)
    {
        mo.listAllFiles();
    }

    private void actionPerformed(ActionEvent e)
    {

    }

    public void generateMenu()
    {
        menuBar = new JMenuBar();

        JMenu file = new JMenu("File");
        JMenu tools = new JMenu("Tools");
        JMenu help = new JMenu("Help");

        JMenuItem open = new JMenuItem("Open ");
        JMenuItem save = new JMenuItem("Save ");
        JMenuItem exit = new JMenuItem("Exit ");
        JMenuItem preferences = new JMenuItem("Preferences ");
        JMenuItem about = new JMenuItem("About ");

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


    public static void main(String[] args)
    {
        System.setProperty("swing.defaultlaf", "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        javax.swing.SwingUtilities.invokeLater(new Runnable()
            {
                public void run()
                {
                    new GUI();
                }
            });
    }

}