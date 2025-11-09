import java.util.*;
class Node{
        int vertex;
        int weight;
        
        Node(int vertex, int weight){
            this.vertex = vertex;
            this.weight = weight;
        }
    }
    
    
class Main{
    
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int E = sc.nextInt();
        
        ArrayList<ArrayList<Node>> list = new ArrayList<>();
        
        for(int i=0; i<N; i++){
            list.add(new ArrayList<Node>());
        }
        
        for(int i=0; i<E; i++){
            int src = sc.nextInt();
            int dest = sc.nextInt();
            int wei = sc.nextInt();
            
            list.get(src).add(new Node(dest, wei));
            list.get(dest).add(new Node(src, wei));
        }
        
        int source = sc.nextInt();
        int destination = sc.nextInt();
        int dist[] = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;
        
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(n -> n.weight));
        
        queue.add(new Node(source, 0));
        
        while(!queue.isEmpty()){
            Node temp = queue.poll();
            int ver = temp.vertex;
            int w = temp.weight;
            
            
            for(Node e : list.get(ver)){
                int des = e.vertex;
                int deswei = e.weight;
                
                int newDist = w+deswei;
                
                if(dist[des] > newDist){
                    dist[des] =  newDist;
                    queue.add(new Node(des, newDist));
                }
            }
        }
        
        for(int i=0 ;i<N; i++){
        System.out.println("From Source: "+source+" To Destination: "+i+" The Weight is: "+ dist[i]);
        }
    }
}