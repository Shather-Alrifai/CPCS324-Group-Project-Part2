
 /*
CPCS 324 project pt2 
compute max bipartite match for a network 
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
public class max_bipartite {

    //Constructing the applicants and the hospitals arrays 
    static String[] Applicants = {"Ahmed", "Mahmoud", "Eman", "Fatimah", "Kamel", "Nojood"};
    static String[] Hospital = {"King Abdelaziz University", "King Fahd", "East Jeddah", "King Fahad Armed Forces", "King Faisal Specialist", "Ministry of National Guard"};

// matching set for the applicants and hospitals implemented as arraylist 
    static ArrayList<Integer> Match_set = new ArrayList<>();

    // M = number of applicant and N= number of hospitals
    public static int M = Applicants.length, N = Hospital.length;

  
   
    /**
     * function for checking if a matching for applicant is possible 
     return true if a match is found
     * @param bipGraph
     * @param app
     * @param isVisited
     * @param assign
     * @return true, when hospital is not assigned or previously assigned else, false
     * 
     */
    public static boolean bipartiteMatch(int[][] bipGraph, int app, boolean isVisited[], int assign[]) {
       
        for (int Hos = 0; Hos < N ; Hos++) {    //for all hospitals 
            if (bipGraph[app][Hos] == 1 && !isVisited[Hos]) {    //when app is interested and the hospital is not visited  by them
                isVisited[Hos] = true;    //mark hospital as visited
                //when hos is not assigned or previously assigned
                if (assign[Hos] ==-1 || bipartiteMatch(bipGraph, assign[Hos], isVisited, assign)) {
                    assign[Hos] = app;    //update assignment of hospital to applicant 
                    System.out.println("\n>> "+Applicants[app] + " is assigned to " + Hospital[Hos] + "\n");
                    
                    Match_set.set(app, Hos); // add the edge (between hospital and applicant ) to matching set 
                
                    return true;
                }
            }
        }
        return false;
    }

     
    /**
     * maximum matching method to find  number of matching in the graph
     * @param graph
     * @return total number of  matches  made
     */
    public static int maxMatch(int[][] graph) {
        int assign[] = new int[N];    //an array to track which hospital is assigned to which applicant
        for (int i = 0; i < N; i++) {
            assign[i] = -1;    //initially set all jobs are not assigned to anyone 
            Match_set.add(-1);     //initialize the matching set to -1 (no matches yet)
        }
        //count how many matches were made 
        int matchCount = 0;
       
        //for all applicants have an array to track which hospitals are they intersted in , check possible match 
        for (int app = 0; app < M; app++) {   
            
            boolean Hospital_of_intrest[] = new boolean[N];
            
            if (bipartiteMatch(graph, app, Hospital_of_intrest, assign)) {//when applicant  gets a hospital
            //update the Matched set 
                System.out.println("Matched set : \n");
                for (int i = 0; i < Match_set.size(); i++) {
                    //if there as a match print the info 
                    if (Match_set.get(i) > -1) {
                        System.out.print("("+(i+1)+")" + Applicants[i] + " -> " + Hospital[Match_set.get(i)] + "\n");
                    }
                }
                System.out.println("\n--------------------------------------------------------------");
               //increment number of  matches  made 
                matchCount++;
            }

        }        
        //retrun total number of  matches  made 
        return matchCount;
    }

    /**
     * main method
     * @param args 
     */
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("\t------------------------------------------------------");
        System.out.println("\t\tWelcom to  maximum bipartite matching ");
        System.out.println("\t------------------------------------------------------\n");
        System.out.println("please enter the number of applicants or hospitals: ");
        int verts1;
        verts1 = input.nextInt();

        WeightedGraph graph = new WeightedGraph(verts1);
       System.out.println("Enter the edge weights between source vertecies and destinatin vertecies\nif there is no weight enter 1 ,if there is no edge inter weight 0\n");

          for (int i = 0; i < verts1; i++) {
         
          for (int j =0;j<verts1;j++){
       System.out.println("\nWeight between source vertex "+(i+1)+" and destinatin vertex "+(j+1)+" = ");
        // int src=i,dest=j;
         int weight=input.nextInt();
              graph.addEdge(i,j,weight);
          }
        }
      

//    int gragh[][] = new int[][]{ //A graph with M applicant and N hospitals
//        {1, 1, 0, 0, 0, 0},
//        {0, 0, 0, 0, 0, 1},
//        {1, 0, 0, 1, 0, 0},
//        {0, 0, 1, 0, 0, 0},
//        {0, 0, 0, 1, 1, 0},
//        {0, 0, 0, 0, 0, 1}
//    };
//    
     int maxMatch = maxMatch(graph.getMatrix());
        System.out.println("\n The maximum possible number of assignments of hospitals to applicants: " + maxMatch + "\n");
    }
}
