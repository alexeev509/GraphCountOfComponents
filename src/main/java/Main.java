import java.util.*;

public class Main {

    private static Random r=new Random();

    private static  Map<Integer,List<Integer>> mapOfEdges;
    private static  Map<Integer,Boolean> mapOfNodes;
    private static int V;

    public static void main(String[] args) {
        System.out.println("Enter number of nodes");
        Scanner scanner = new Scanner(System.in);
        V =scanner.nextInt();
        System.out.println("Enter number of edges");
        int E =scanner.nextInt();
        mapOfEdges= new HashMap<Integer, List<Integer>>(V);
        mapOfNodes=new HashMap<Integer, Boolean>(V);
        for (int i=1; i<V+1;i++){
            mapOfNodes.put(i,false);
            mapOfEdges.put(i,new ArrayList<Integer>());
        }
        //Add edges in graph
        for(int i=0; i<E;i++){
            int startOfEdge=r.nextInt(V)+1;
            int endOfEdge=r.nextInt(V)+1;
            //I mustn't add element in mapOfEdges if startOfEdge==endOfEdge, for example 1-1 or 2-2
            //
            if(startOfEdge!=endOfEdge &&
                    !mapOfEdges.get(startOfEdge).contains(endOfEdge))
            mapOfEdges.get(startOfEdge).add(endOfEdge);
            if(startOfEdge!=endOfEdge &&
                    !mapOfEdges.get(endOfEdge).contains(startOfEdge))
            mapOfEdges.get(endOfEdge).add(startOfEdge);
        }

        for (Map.Entry entry:mapOfEdges.entrySet()) {
            System.out.println(entry.getKey()+" "+entry.getValue());
        }

        System.out.println(countOfComponentsOfConnective(1, 0));

    }
    public static int countOfComponentsOfConnective(int currentNodeNumber,int countOfComponents){
        if(currentNodeNumber>V)
            return countOfComponents;
        if(mapOfNodes.get(currentNodeNumber))
            return countOfComponentsOfConnective(++currentNodeNumber,countOfComponents);
        //nop
              DFS(currentNodeNumber);
              countOfComponents++;
        //
        return countOfComponentsOfConnective(++currentNodeNumber,countOfComponents);
    }

    public static void DFS(int currentNodeNumber){
        if(mapOfNodes.get(currentNodeNumber))
            return;
        mapOfNodes.replace(currentNodeNumber,true);
        List<Integer> edges = mapOfEdges.get(currentNodeNumber);
        for (int i = 0; i < edges.size(); i++) {
            DFS(edges.get(i));
        }
    }

}
