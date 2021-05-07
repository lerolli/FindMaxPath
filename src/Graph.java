import java.util.ArrayList;
import java.util.LinkedList;

public class Graph {
    int size;
    ArrayList<Edge> edges;
    LinkedList<Edge> arrayListSortEdge;
    int [][] array;

    public Graph(int size){
        this.size = size;
        edges = new ArrayList<>();
        arrayListSortEdge = new LinkedList<>();
        for (int i = 0; i < size; i++){
            var edge = new Edge();
            edge.number = i;
            edge.sortNumber = -1;
            edge.neighbor = new ArrayList<>();
            edges.add(edge);
        }
    }

    public int findPathSize(Edge e1, Edge e2){
        return array[e1.number][e2.number];
    }

    public void MakeGraph(ArrayList<String> matrix){
        array = new int[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++){
                array[i][j] = -1;
            }

        for (var i = 0; i < matrix.size(); i++) {
            var strArray = matrix.get(i).split(" ");
            var j = 0;
            while (Integer.parseInt(strArray[j]) != 0){
                array[i][Integer.parseInt(strArray[j]) - 1] = Integer.parseInt(strArray[j + 1]);
                j += 2;
            }
        }
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (array[i][j] != -1)
                    edges.get(i).neighbor.add(edges.get(j));
        var visited = new ArrayList<>();
        var number = size;
        while (true){
            var allHaveSortNumber = true;
            for (var edge: edges) {
                if (edge.sortNumber == -1) {
                    allHaveSortNumber = false;
                    var haveNotVisitedNeighbor = true;
                    for (var neighbor : edge.neighbor){
                        if (!visited.contains(neighbor)){
                            haveNotVisitedNeighbor = false;
                            break;
                        }
                    }
                    if (haveNotVisitedNeighbor) {
                        edge.sortNumber = number;
                        number--;
                        visited.add(edge);
                        arrayListSortEdge.addFirst(edge);
                    }
                }
            }
            if (allHaveSortNumber)
                break;
        }
    }


    public String arrayToString(){
        var strBuilder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (array[i][j] == -1)
                    strBuilder.append("0, ");
                else
                    strBuilder.append(array[i][j]).append(", ");
            }
            strBuilder.append("\n");
        }
        return strBuilder.toString();
    }

    public String findMaxPath(int startEdgeNumber, int endEdgeNumber) {
        var startEdge = edges.get(startEdgeNumber);
        var endEdge = edges.get(endEdgeNumber);
        var D = new int[size];
        for (int i = 0; i < arrayListSortEdge.size(); i++) {
            var currentEdge = arrayListSortEdge.get(i);
            for (var neighbor : currentEdge.neighbor) {
                var pathSize = findPathSize(currentEdge, neighbor);
                var indexEdge = arrayListSortEdge.indexOf(neighbor);
                if (D[indexEdge] < D[arrayListSortEdge.indexOf(currentEdge)] + pathSize) {
                    D[indexEdge] = D[arrayListSortEdge.indexOf(currentEdge)] + pathSize;
                    neighbor.ancestor = currentEdge;
                }
            }
        }

        if (D[edges.get(endEdgeNumber).sortNumber - 1] == 0)
          return "N";

        var strBuilder = new StringBuilder();

        var tempEdge = endEdge;
        var resultArray = new ArrayList<Integer>();

        strBuilder.append("Y \n");
        while (tempEdge.ancestor != null) {
            resultArray.add(tempEdge.number + 1);
            tempEdge = tempEdge.ancestor;
        }
        resultArray.add(startEdge.number + 1);
        for (int i = resultArray.size() - 1; i > -1; i--) {
            strBuilder.append(resultArray.get(i) + " ");
        }
        var p = D[arrayListSortEdge.indexOf(endEdge)];
        strBuilder.append("\n" + D[arrayListSortEdge.indexOf(endEdge)]);
        return strBuilder.toString();
    }

}
