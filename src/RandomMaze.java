import java.util.ArrayList;
import java.util.Collections;

public class RandomMaze {
    private int m; // number of rows
    private int n; // number of columns
    public UnionFind unionFind;
    public ArrayList<Edge> edges;
    public ArrayList<Edge> basic_edges;
    // constructor
    public RandomMaze(int m, int n) {
        this.m = m;
        this.n = n;
        this.unionFind = new UnionFind(m*n);
        setEdges();
    }
    public int getNumOfRows() {
        return this.m;
    }
    public int getNumOfColumns() {
        return this.n;
    }

    public void setEdges() {
        this.edges = new ArrayList<>();
        // set horizontal edges
        for (int i = 0; i < m; i++) { // need to change to 0 to (m-1)
            for (int j = 0; j < n-1; j++) {
                int source_index = i * n + j;
                int dest_index = i * n + j + 1;
                Cell source_cell = new Cell(source_index,i,j);
                Cell dest_cell = new Cell(dest_index,i,j+1);
                Edge edge = new Edge(source_cell, dest_cell, this.unionFind);
                this.edges.add(edge);
            }
        }
        // set vertical edges
        for (int i = 0; i < m-1; i++) {
            for (int j = 0; j < n; j++) { // need to change to 0 to (n-1)
                int source_index = i * n + j;
                int dest_index = (i+1) * n + j;
                Cell source_cell = new Cell(source_index,i,j);
                Cell dest_cell = new Cell(dest_index,i+1,j);
                Edge edge = new Edge(source_cell, dest_cell, this.unionFind);
                this.edges.add(edge);
            }
        }
    }
    // shuffling the edges array list
    public void shuffleEdges() {
        Collections.shuffle(this.edges);
    }
    // choose edges that will be shown
    public void showEdges() {
        for (int i = 0; i < this.edges.size(); i++) {
            if (this.edges.get(i).getAppear() == false) {
                if (!this.unionFind.sameParent(this.edges.get(i).getSource().getIndex(), this.edges.get(i).getDest().getIndex())) {
                    this.edges.get(i).make();
                    this.unionFind.union(this.edges.get(i).getSource().getIndex(), this.edges.get(i).getDest().getIndex());
                }
            }
        }
    }
}