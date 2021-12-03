public class Edge {
    private Cell source;
    private Cell dest;
    private UnionFind unionFind;
    private boolean appear;
    // constructor
    public Edge(Cell source, Cell dest, UnionFind  unionFind) {
        this.source = source;
        this.dest = dest;
        this.unionFind = unionFind; // check deep copy for this
        this.appear = false; // default
    }

    public void setAppear(boolean appear) {
        this.appear = appear;
    }

    public boolean getAppear() {
        return this.appear;
    }

    public Cell getSource() {
        return this.source;
    }

    public Cell getDest() {
        return this.dest;
    }

    // if a make function has been called on an edge, the edge will be shown
    public void make() {
        this.unionFind.union(this.source.getIndex(), this.dest.getIndex());
        this.appear = true;
    }

    public String toString() {
        return "src: "+this.source+", dest: "+this.dest;
    }
}
