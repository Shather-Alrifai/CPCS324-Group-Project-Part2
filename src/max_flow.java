

/*
CPCS 324 project pt2 
compute max flow for a network 
section:GAR
team members:
Shather Mohammed Alshubbak
Reem Abdulrhman Alghamdi 
Jumana abdulrahman almadhoun
21/12/2021
 */
import java.util.*;

public class max_flow {

    /**
     * main function
     *
     * @param args
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws java.lang.Exception {
//        int graph[][] = new int[][]{
//      // 1  2  3  4  5  6
//        {0, 2, 7, 0, 0, 0},//1
//        {0, 0, 0, 3, 4, 0},//2
//        {0, 0, 0, 4, 2, 0},//3
//        {0, 0, 0, 0, 0, 1},//4
//        {0, 0, 0, 0, 0, 5},//5
//        {0, 0, 0, 0, 0, 0}//6
//        };
//        

        /* 
            1 > 2 =2
            1 > 3 =7
            2 > 4 =3
            2 > 5 =4
            3 > 4 =4
            3 > 5 =2
            4 > 6 =1
            5 > 6 =5
            6 is the sink (will be a destination )
         */
        Scanner input = new Scanner(System.in);

        System.out.println("-----------------------------------------------");
        System.out.println("\t\t   Welcom to  maximum flow (Edmonds-Karp based) calculator ");
        System.out.println("-----------------------------------------------");
        System.out.println("please enter the number of nodes(vertices) of your graph ");
        int verts, edges;
        verts = input.nextInt();

        WeightedGraph wgraph = new WeightedGraph(verts);
        int[][] graph = wgraph.make_graph(wgraph);

        //send to the maxflow method the graph ,the source 0 and the sink that last vertix ant index graph length -1  
        Max_Flow_clc(graph, 0, graph.length - 1);

    }

    /**
     * (Edmonds-Karp,ford Fulkerson ) maximum-flow algorithm using BFS to find
     * the maximum flow of the network, and the corresponding min-cut using DFS
     *
     * @param graph
     * @param source
     * @param sink
     */
    public static void Max_Flow_clc(int[][] graph, int source, int sink) {
        System.out.println("\n-----------------------------------------------");
        System.out.println("\t\t Maximum flow");
        System.out.println("-----------------------------------------------\n");
        System.out.println("Found paths:");
        int src, dest;

        /**
          Make a moc Residual graph with the same size as the original grapth to store the
          residual capacity of edges  
         */
        //get the number of vertices of the graph.
        int Ver = graph[1].length;

        int Rgraph[][] = new int[Ver][Ver];
        for (src = 0; src < Ver; src++) {
            for (dest = 0; dest < Ver; dest++) {
                Rgraph[src][dest] = graph[src][dest];
            }
        }

        // parent array to store path by BFS
        int parent[] = new int[Ver];

        //initialize the max flow =0 
        int MaxFlow = 0;
        //------------------------------------------

        //while there is path from source to sink, set the flow direction "->" or "<-" 
        while (BFS(Rgraph, source, sink, parent)) {
            String pathway = "";

            //initialize the max flow to infinite 
            int pathFlow = Integer.MAX_VALUE;

            // find the maximum flow through the path found from BFS .
            for (dest = sink; dest != source; dest = parent[dest]) {
                // set the flow direction and the source is the parent
                String direction = " <- ";
                src = parent[dest];
                //update the path flow 
                pathFlow = Math.min(pathFlow, Rgraph[src][dest]);

                if (graph[src][dest] != 0) {
                    direction = " -> ";
                }
                //update the pathway 
                pathway = direction + (dest + 1) + pathway;
            }
            //update the pathway and print it 
            pathway = (dest + 1) + pathway;
            System.out.printf("%-17s\n", pathway);

            /** 
              update residual capacities of the edges and
              reverse edges along the path
             */
            for (dest = sink; dest != source; dest = parent[dest]) {
                src = parent[dest];
                Rgraph[src][dest] -= pathFlow;
                Rgraph[dest][src] += pathFlow;
            }
            // Add path flow value to total max flow
            MaxFlow += pathFlow;
            System.out.println("Updated flow: " + MaxFlow + "\n");

        }

        // print max-flow
        System.out.println("> The maximum flow is " + MaxFlow);

        //get the mini cut
        System.out.println("\n-----------------------------------------------");
        System.out.println("\t\tMinimum cut");
        System.out.println("-----------------------------------------------");
        // print min-cut edges
        System.out.println("\n> Edges included in the min-cut");
        //is vesited array to keep track of the visited nodes
        boolean[] isVisited = new boolean[graph.length];
        //trverse the path with DFS to get the minimum cut
        DFS(Rgraph, source, isVisited);

        // Print all edges from a vertex to other neighbor vertices 
        int totalCut_cap = 0;
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                //if there is an edge with weight between i ,j print it and add it to the mini cut
                if (graph[i][j] > 0 && isVisited[i] && !isVisited[j]) {
                    System.out.print("\nEdge between : " + (i + 1) + "->" + (j + 1));
                    System.out.println("\n capacity = " + graph[i][j]);
                    //update the total mini cut capacity 
                    totalCut_cap += graph[i][j];
                    System.out.println("new min-cut capacity: " + totalCut_cap);
                }
            }
        }
        //print final total mini cut capacity
        System.out.println("\n> The total min-cut capacity is " + totalCut_cap + "\n");
    }

    //-------------------------------------Supporting Methods--------------------------------------------
    /**
     * //DFS is used to find the mini cut
     *
     * @param Rgraph
     * @param src
     * @param visited
     */
    public static void DFS(int[][] Rgraph, int src, boolean[] visited) {
        visited[src] = true;
        //iterate of the graph to visit the nodes of a path 
        for (int i = 0; i < Rgraph.length; i++) {
            if (Rgraph[src][i] > 0 && !visited[i]) {
                DFS(Rgraph, i, visited);
            }
        }
    }

    /**
     * //BFS is used to get all the path possible from the source to the sink
     *
     * @param Rgraph
     * @param source
     * @param sink
     * @param parent
     * @return return true If we reached sink else return false
     */
    public static boolean BFS(int Rgraph[][], int source, int sink, int parent[]) {

        int Ver = Rgraph[1].length;

        // set all vertices as not visited
        boolean isVisited[] = new boolean[Ver];
        for (int i = 0; i < isVisited.length; ++i) {
            isVisited[i] = false;
        }

        /* 
          Create a queue implemented as linklist to keep track of the path 
         add source vertex to the queue and mark
          source vertex as visited
         */
        LinkedList<Integer> pathQueue = new LinkedList<>();
        pathQueue.add(source);
        isVisited[source] = true;
        //the source has no parent 
        parent[source] = -1;

        //traverse the vertices using BFS approche while there are verts in the queue   
        while (!pathQueue.isEmpty()) {
            //consider the head of the queue as the source
            int src = pathQueue.poll();
            for (int dest = 0; dest < Ver; dest++) {
                //if the destination has not been visited and there is an edge with a capacity between src & dest
                // add is to the queue and set it as visited , set the the parent of dest is the src
                if (isVisited[dest] == false && Rgraph[src][dest] > 0) {
                    pathQueue.add(dest);
                    parent[dest] = src;
                    isVisited[dest] = true;
                }
            }
        }
        //check if we reach the sink using the found path using BFS
        //  return true If we reached sink else return false

        return (isVisited[sink] == true);
    }//end of BFS

}
