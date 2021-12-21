
/*
CPCS 324 project pt2 
section:GAR
team members:
Shather Mohammed Alshubbak
Reem Abdulrhman Alghamdi 
Jumana abdulrahman almadhoun
21/12/2021
 */
import java.util.*;

/**
 *
 * @author Shather Mohammed Alshubbak
 * @author Reem Abdulrhman Alghamdi
 * @author Jumana abdulrahman almadhoun
 */
public class WeightedGraph {

    //data members 
    /**
     * number of vertices
     */
    private int verts;

    /**
     * number of edges
     */
    private int edges;

    private int[][] matrix;

    // Graph constructor 
    /**
     * Graph constructor
     *
     * @param vertices number of vertices
     * @param edges number of edges
     */
    WeightedGraph(int vertices, int edges) {
        this.verts = vertices;
        this.edges = edges;
    }

    WeightedGraph(int vertices) {
        this.verts = vertices;
        matrix = new int[vertices][vertices];
    }

    public int getVerts() {
        return verts;
    }

    public int getEdges() {
        return edges;
    }

    public void setVerts(int verts) {
        this.verts = verts;
    }

    public void setEdges(int edges) {
        this.edges = edges;
    }

    public int[][] getMatrix() {
        return this.matrix;
    }

    
    public int[][] make_graph(WeightedGraph graph) {
        Scanner input = new Scanner(System.in);
        int wGraph[][] = new int[graph.getVerts()][graph.getVerts()];

        System.out.println("Enter the edge weight between vertecies.\n If there is no connection enter 0 ,if there is no weight enter 1\n");

        for (int i = 0; i < graph.getVerts(); i++) {

            for (int j = 0; j < graph.getVerts(); j++) {
                System.out.println("Enter the edge weight between source vertex " + (i + 1) + " and destinatin vertex " + (j + 1));
                wGraph[i][j] = input.nextInt();
            }
        }
        return wGraph;

    }

    
    
    
    public void addEdge(int source, int destination, int weight) {

        for (int i = 0; i < this.verts; i++) {

            for (int j = 0; j < this.verts; j++) {
                this.matrix[i][j] = weight;
            }
        }

    }

}
