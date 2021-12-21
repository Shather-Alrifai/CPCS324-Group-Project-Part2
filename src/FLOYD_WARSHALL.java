 /*
CPCS 324 project pt2 
compute all paairs shortest path using Floyd-Warshall's algorithm 
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





public class FLOYD_WARSHALL {

    // global variables declaration 
    static final int INF = Integer.MAX_VALUE;

    static char[] ver = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
        'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
        'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    static double startTime;

    /**
     * main method
     * @param args 
     */
    public static void main(String args[]) {


        // ---------------------------------------------------------------------
        Scanner in = new Scanner(System.in);
        System.out.println("------------------- WELCOME TO FLOYD-WARSHALL ALGORITHM SOLVER -------------------");
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
                //invoke floyed
                FloydWarshall(DAGs.getGraph_1());
                break;
            case 2:
                //invoke floyed
                FloydWarshall(DAGs.getGraph_2());
                break;
            case 3:
                //invoke floyed
                FloydWarshall(DAGs.getGraph_3());
                break;
            case 4:
                //invoke floyed
                FloydWarshall(DAGs.getGraph_4());
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
        System.out.println("Total runtime of Floyd Warshall Algorithm: " + (finishTime - startTime) + " ms.");
        // ---------------------------------------------------------------------
    }

    /**
     * Floyd Warshall's algorithm for finding all pairs shortest path
     * @param graph 
     */
    public static void FloydWarshall(int[][] graph) {
        //print time for trace 
        startTime = System.currentTimeMillis();
        System.out.println("start time inside :" + (startTime / 1000));
        int n = graph.length;

        System.out.println("> The following matrices shows the shortest path for each iteration\n");
        // printing D(0) which is equivalent to the adj matrix
        System.out.print("D(0)=\n\t");
        for (int i = 0; i < n; i++) {
            // printing the vertices labels 
            System.out.print(ver[i] + "\t");
        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            // printing the vertices labels
            System.out.print(ver[i] + "\t");
            for (int j = 0; j < n; j++) {
                System.out.print((graph[i][j] == INF ? "∞" : graph[i][j]) + (n - 1 == j ? "\n" : "\t"));
            }
        }
        System.out.println();
        // BEGINNING OF FLOYD ALGORITHM
        for (int i = 0; i < n; i++) {
            System.out.print("D(" + (i + 1) + ")=\n\t");
            // extra loop for printing the vertices labels (not needed for computations) 
            for (int j = 0; j < n; j++) {
                System.out.print(ver[j] + "\t");
            }
            
            System.out.println();
            for (int j = 0; j < n; j++) {
                System.out.print(ver[j] + "\t");
                for (int k = 0; k < n; k++) {
                    if (graph[j][k] > graph[j][i] + graph[i][k] && graph[j][i] != INF && graph[i][k] != INF) {
                        graph[j][k] = graph[j][i] + graph[i][k];
                    }
                    System.out.print((graph[j][k] == INF ? "∞" : graph[j][k]) + (n - 1 == k ? "\n" : "\t"));
                }
            }
            System.out.println("");

        }
        // END OF THE ALGORITHM
    }

}
