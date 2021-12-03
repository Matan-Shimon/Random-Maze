import java.util.Arrays;

public class UnionFind {
    private int num_of_vertices;
    private int [] rank; // rank of each cell
    private int [] parent; // parent of each cell

    // constructor
    public UnionFind(int num_of_vertices) {
        this.num_of_vertices = num_of_vertices;
        this.rank = new int[this.num_of_vertices];
        // setting the level of each cell at the beginning o b 1
        for (int i = 0; i < this.rank.length; i++) {
            this.rank[i] = 1;
        }
        this.parent = new int[this.num_of_vertices];
        // setting each cell at the beginning to be his own parent
        for (int i = 0; i < this.parent.length; i++) {
            this.parent[i] = i;
        }
    }

    // find function using path compression to make it more efficient
    public int find(int cell_index) {
        // if the cell is not his own parent
        if (this.parent[cell_index] != cell_index) {
            this.parent[cell_index] = find(this.parent[cell_index]);
        }
        return this.parent[cell_index];
    }
    // the function gets two indexes of cells
    // the function unite them
    public void union(int cell_index1, int cell_index2) {
        // we would like to perform a union only if they are not already united
        int parent1 = find(cell_index1);
        int parent2 = find(cell_index2);
        if (parent1 != parent2) {
            if (this.rank[parent1] > this.rank[parent2]) {
                this.parent[parent2] = parent1;
            }
            else if (this.rank[parent2] > this.rank[parent1]) {
                this.parent[parent1] = parent2;
            }
            else { // if their rank is equal
                this.parent[parent2] = parent1;
                this.rank[parent1]++;
            }
        }
    }
    // checking if two cell indexes has same parent
    public boolean sameParent(int cell_index1, int cell_index2) {
        if (find(cell_index1) == find(cell_index2)) {
            return true;
        }
        return false;
    }
}
