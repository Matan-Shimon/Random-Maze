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
        // printing the walls (the ones you can't go through them)
        for (int i = 0; i < this.randomMaze.edges.size(); i++) {
            if (this.randomMaze.edges.get(i).getAppear()) {
                int x1 = this.randomMaze.edges.get(i).getSource().getX() * this.line_size;
                int y1 = this.randomMaze.edges.get(i).getSource().getY() * this.line_size;
                int x2 = this.randomMaze.edges.get(i).getDest().getX() * this.line_size;
                int y2 = this.randomMaze.edges.get(i).getDest().getY() * this.line_size;
                graphics.drawLine(x1+340, y1+50, x2+340, y2+50);
            }
        }
        // print start and finish edges
        // start
        graphics.setColor(Color.green);
        graphics.drawLine(0+340, 0+50, 0+340, this.line_size+50);
        //  finish
        graphics.setColor(Color.red);
        int x1 = (this.number_of_columns-1) * this.line_size;
        int y1 = (this.number_of_rows-2) * this.line_size;
        int x2 = (this.number_of_columns-1) * this.line_size;
        int y2 = (this.number_of_rows-1) * this.line_size;
        graphics.drawLine(x1+340, y1+50, x2+340, y2+50);
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