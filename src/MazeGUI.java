import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class MazeGUI {
    private MazePanel mazePanel;
    private JButton shuffle;

    public MazeGUI(int m, int n) {
        JFrame frame = new JFrame("Random Maze");
        // size of the frame
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setPreferredSize(new Dimension(screenSize.width, screenSize.height));

        mazePanel = new MazePanel(new RandomMaze(m, n), screenSize);
        shuffle = new JButton("Shuffle");
        mazePanel.add(shuffle);

        shuffle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // any time the shuffle has been clicked, a new random maze will be shown
                mazePanel = new MazePanel(new RandomMaze(m, n), screenSize);
                frame.add(mazePanel);
                // organize
                frame.pack();
            }
        });

        frame.add(mazePanel);
        // organize
        frame.pack();
        // when we close the frame window, the program will end
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // shot the frame
        frame.setVisible(true);
    }
}