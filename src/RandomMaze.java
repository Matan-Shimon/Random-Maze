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
        int basic_edge_ind1 = 0;
        int basic_edge_ind2 = 0;
        int basic_edge_ind3 = 0;
        int basic_edge_ind4 = 0;
        // set horizontal edges
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n-1; j++) {
                int source_index = i * n + j;
                int dest_index = i * n + j + 1;
                Cell source_cell = new Cell(source_index,j,i);
                Cell dest_cell = new Cell(dest_index,j+1,i);
                Edge edge = new Edge(source_cell, dest_cell, this.unionFind);
                // we want that the basic edges walls (top and bottom) always will be shown
                if (i == 0 || i == m-1) {
                    edge.setAppear(true);
                    this.unionFind.union(source_index, dest_index);
                    if (i == 0) {
                        basic_edge_ind1 = source_index;
                    }
                    else { // i = m-1
                        basic_edge_ind2 = source_index;
                    }
                }
                this.edges.add(edge);
            }
        }
        // set vertical edges
        for (int i = 0; i < m-1; i++) {
            for (int j = 0; j < n; j++) {
                if (!(i == 0 && j == 0) && !(i == m-2 && j == n-1)) {
                    int source_index = i * n + j;
                    int dest_index = (i+1) * n + j;
                    Cell source_cell = new Cell(source_index, j, i);
                    Cell dest_cell = new Cell(dest_index,j,i+1);
                    Edge edge = new Edge(source_cell, dest_cell, this.unionFind);
                    // we want that the basic edges walls (left and right) always will be shown
                    if (j == 0 || j == n-1) {
                        edge.setAppear(true);
                        this.unionFind.union(source_index, dest_index);
                        if (j == 0) {
                            basic_edge_ind3 = source_index;
                        }
                        else { // j = n-1
                            basic_edge_ind4 = source_index;
                        }
                    }
                    this.edges.add(edge);
                }
            }
        }
        // uniting all the basic walls (top, bottom, left and right)
        this.unionFind.union(basic_edge_ind1, basic_edge_ind2);
        this.unionFind.union(basic_edge_ind3, basic_edge_ind4);
        this.unionFind.union(basic_edge_ind1, basic_edge_ind3);
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