/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/
import java.util.*;
class Node{
    int parent;
    int vertex;
    int weight;
    
    Node(int parent, int vertex, int weight){
        this.parent = parent;
        this.vertex = vertex;
        this.weight = weight;
    }
}

class Prims{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        int mat[][] = new int[n][n];
        
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                mat[i][j] = sc.nextInt();
            }
        }
        
        PriorityQueue<Node> queue = new PriorityQueue<>((a,b)-> a.weight-b.weight);
        boolean vis[] = new boolean[n];
        ArrayList<String> list = new ArrayList<>();
        
        queue.add(new Node(-1,0,0));
        int totalCost = 0;
        
        while(!queue.isEmpty()){
            Node temp = queue.poll();
            int src = temp.vertex;
            
            if(vis[src])continue;
            
            
            vis[src] = true;
            totalCost+= temp.weight;
            if(temp.parent!=-1){
            list.add(""+temp.parent+"-----> "+ src+ "\t" + temp.weight);
            }
            
            for(int i=0; i<n; i++){
                if(!vis[i] && mat[src][i]!=0){
                    queue.add(new Node(src, i, mat[src][i]));
                }
            }
        }
        
        System.out.println(totalCost);
        System.out.println("Parent  ---------->  Child\t Weight");
        
        for(String a : list) System.out.println(a);
        
        
    }
}