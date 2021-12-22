 /*
CPCS 324 project pt2 
computes single shortest path using Dijkstra's algorithm
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


public class Dijsktra {


  
    // array for vertices 
    static String[] verts = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
        "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
        "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    
    //initialize static variables
    static String tree = "";
    static String visited = "";
    static ArrayList<edge> Edges = new ArrayList();
    static int INF = Integer.MAX_VALUE;
    static double startTime;

    /**
     * main method
     * @param args 
     */
    public static void main(String[] args) {
        

        int source = 0;
        // ---------------------------------------------------------------------
        Scanner in = new Scanner(System.in);
        System.out.println("------------------- WELCOME TO Dijkstra ALGORITHM SOLVER -------------------");
        System.out.println(">>Test  cases : (where N is the number of vertices "
                + "and E is the number of edges: ");
        System.out.println(" 1:  N=10 ,  M=16");
        System.out.println(" 2:  N=20 ,  M=19");
        System.out.println(" 3:  N=30 ,  M=40");
        System.out.println(" 4:  N=40 ,  M=55");

        System.out.print("> Enter a case to test: ");
        int uChoice = in.nextInt();

        switch (uChoice) {
            case 1:
                //invoke Dijsktra

                Dijsktra.dijkstra(DAGs.getGraph_1(), source);
                break;
            case 2:
                //invoke Dijsktra

                Dijsktra.dijkstra(DAGs.getGraph_2(), source);
                break;
            case 3:
                //invoke Dijsktra

                Dijsktra.dijkstra(DAGs.getGraph_3(), source);
                break;
            case 4:
                //invoke Dijsktra

                Dijsktra.dijkstra(DAGs.getGraph_4(), source);
                break;
            default:
                System.out.println("Invalid input!");
                System.out.println("Thank you for comming by!");
                break;
        }

        double finishTime = System.currentTimeMillis();
        //print time for trace 
        System.out.println("finish time :" + finishTime);

        //print the total time consumed by the algorithm
        System.out.println("Total runtime of Dijsktra Algorithm: " + (finishTime - startTime) + " ms.");
    }
/**
 * Dikstra's algorithm for finding the single source shortest path
 * @param graph
 * @param source 
 */
    public static void dijkstra(int[][] graph, int source) {

        startTime = System.currentTimeMillis();
        //print start time for trace
        //System.out.println("start time inside :" + (startTime / 1000));
        int count = graph.length;
        System.out.println("count=" + count);
        boolean[] visitedVertex = new boolean[count];
        int[] distance = new int[count];
        // initialize visitedVertex and distance arrays
        for (int i = 0; i < count; i++) {
            visitedVertex[i] = false;
            distance[i] = Integer.MAX_VALUE;
            Edges.add(new edge(source));
        }

        // Distance of self loop is zero
        distance[source] = 0;
        for (int i = 0; i < count; i++) {
            // Update the distance between neighbouring vertex and source vertex
            int u = getMinDistance(distance, visitedVertex);
            System.out.println(" the mini dis U = " + u);
            visitedVertex[u] = true;

            // Update all the neighbouring vertex distances
            for (int v = 0; v < count; v++) {
                if (!visitedVertex[v] && graph[u][v] != 0 && graph[u][v] != INF && (distance[u] + graph[u][v] < distance[v])) {
                    distance[v] = distance[u] + graph[u][v];
                    Edges.get(v).setSrc(u); // save the intermediate source
                    visited += verts[v];  // save the visited nodes labels

                }
            }
            // printing tree vertex 
            System.out.println("\nTree Vertices VT");
            tree += verts[u] + "(" + (verts[source].equals(verts[u]) ? "-" : verts[Edges.get(u).src]) + "," + distance[u] + ")\n";
            System.out.println(tree);
            // printing remaining vertices
            System.out.println("Remaining Vertices V-VT");
            for (int j = 0; j < count - 1; j++) {
                if (!visitedVertex[j]) {
                    System.out.println(verts[j] + "(" + (visited.contains(verts[j]) ? verts[Edges.get(j).src] : "-") + "," + (distance[j] == INF ? "∞" : distance[j]) + ")");
                }
            }
            System.out.println("\n-----------------------------------------");

        }
        // printing final distances
        System.out.println("\nShortest distances from " + verts[source] + " to all other verts are:");
        for (int i = 0; i < distance.length; i++) {
            if (!verts[source].equals(verts[i])) {
                System.out.println(String.format("Distance from %-6s to %-7s is %-4d ", verts[source], verts[i], distance[i]));
            }
        }

    }
/**
 * getMinDistance(): Finding the minimum distance
 * @param distance
 * @param visitedVertex
 * @return minDistanceVertex
 */
     static int getMinDistance(int[] distance, boolean[] visitedVertex) {
        int minDistance = Integer.MAX_VALUE;
        int minDistanceVertex = 0;
        for (int i = 0; i < distance.length; i++) {
            if (!visitedVertex[i] && distance[i] < minDistance) {
                minDistance = distance[i];
                minDistanceVertex = i;
            }
        }
        return minDistanceVertex;
    }

//-------------------------------------Edge Class--------------------------------------------
    static class edge {

        int vert;
        int src;

        edge(int src) {
            this.src = src;
        }

        public int getVert() {
            return vert;
        }

        public void setVert(int vert) {
            this.vert = vert;
        }

        public int getSrc() {
            return src;
        }

        public void setSrc(int src) {
            this.src = src;
        }
    }

}
