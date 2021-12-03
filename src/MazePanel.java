import javax.swing.*;
import java.awt.*;

public class MazePanel extends JPanel implements Scrollable{
    public RandomMaze randomMaze;
    private int number_of_rows;
    private int number_of_columns;
    private int line_size;

    // constructor
    public MazePanel(RandomMaze randomMaze, Dimension screenSize) {
        this.randomMaze = randomMaze;
        this.number_of_rows = randomMaze.getNumOfRows();
        this.number_of_columns = randomMaze.getNumOfColumns();
        setBackground(Color.black);
        // setting the line size that the whole maze will be shown
        int min = screenSize.height-100;
        if (screenSize.width-100 < min) {
            min = screenSize.width-100;
        }
        int max = this.number_of_rows;
        if (this.number_of_columns > max) {
            max = this.number_of_columns;
        }
        this.line_size = min / max;
    }
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        drawMaze(graphics);
    }
    public void drawMaze(Graphics graphics) {
        graphics.setColor(Color.white);
        this.randomMaze.shuffleEdges();
        this.randomMaze.showEdges();
        // printing the lines that we can go through
        for (int i = 0; i < this.randomMaze.edges.size(); i++) {
            if (this.randomMaze.edges.get(i).getAppear()) {
                int x1 = this.randomMaze.edges.get(i).getSource().getX() * this.line_size;
                int y1 = this.randomMaze.edges.get(i).getSource().getY() * this.line_size;
                int x2 = this.randomMaze.edges.get(i).getDest().getX() * this.line_size;
                int y2 = this.randomMaze.edges.get(i).getDest().getY() * this.line_size;
                if (x1 != x2) {
                    int diff = x2 - x1;
                    diff /= 10;
                    for (int j = 0; j < 5; j++) {
                        graphics.drawLine(x1+50, y1+50, x1+50+diff, y2+50);
                        x1 += 2*diff;
                    }
                }
                else {
                    int diff = y2 - y1;
                    diff /= 10;
                    for (int j = 0; j < 5; j++) {
                        graphics.drawLine(x1+50, y1+50, x2+50, y1+50+diff);
                        y1 += 2*diff;
                    }
                }
            }
        }
        // printing the other lines (the ones you can't go through them)
        for (int i = 0; i < this.randomMaze.edges.size(); i++) {
            if (!this.randomMaze.edges.get(i).getAppear()) {
                int x1 = this.randomMaze.edges.get(i).getSource().getX() * this.line_size;
                int y1 = this.randomMaze.edges.get(i).getSource().getY() * this.line_size;
                int x2 = this.randomMaze.edges.get(i).getDest().getX() * this.line_size;
                int y2 = this.randomMaze.edges.get(i).getDest().getY() * this.line_size;
                graphics.drawLine(x1+50, y1+50, x2+50, y2+50);
            }
        }
        // printing the cells
        for (int i = 0; i < this.randomMaze.edges.size(); i++) {
            int x1 = this.randomMaze.edges.get(i).getSource().getX() * this.line_size;
            int y1 = this.randomMaze.edges.get(i).getSource().getY() * this.line_size;
            int x2 = this.randomMaze.edges.get(i).getDest().getX() * this.line_size;
            int y2 = this.randomMaze.edges.get(i).getDest().getY() * this.line_size;
            if (x1 == 0 && y1 == 0) {
                graphics.setColor(Color.GREEN);
                graphics.fillOval(x1+50, y1+50, 5, 5);
                graphics.setColor(Color.white);
            }
            else {
                graphics.fillOval(x1+50, y1+50, 5, 5);
            }
            if (x2 == (this.randomMaze.getNumOfColumns()-1)*this.line_size && y2 == (this.randomMaze.getNumOfRows()-1)*this.line_size) {
                graphics.setColor(Color.RED);
                graphics.fillOval(x2+50, y2+50, 5, 5);
                graphics.setColor(Color.white);
            }
            else {
                graphics.fillOval(x2+50, y2+50, 5, 5);
            }
        }
    }
    @Override
    public Dimension getPreferredScrollableViewportSize() {
        return new Dimension(512, 256);
    }
    @Override
    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 128;
    }
    @Override
    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 128;
    }
    @Override
    public boolean getScrollableTracksViewportWidth() {
        return getPreferredSize().width <= getParent().getSize().width;
    }
    @Override
    public boolean getScrollableTracksViewportHeight() {
        return getPreferredSize().height <= getParent().getSize().height;
    }
}