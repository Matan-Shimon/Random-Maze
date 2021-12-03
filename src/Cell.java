public class Cell {
    private int index;
    private int x;
    private int y;

    public Cell(int index, int x, int y) {
        this.x = x;
        this.y = y;
        this.index = index;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getIndex() {
        return this.index;
    }

    public String toString() {
        return "index: "+this.index+", x: "+this.x+", y: "+this.y;
    }
}
