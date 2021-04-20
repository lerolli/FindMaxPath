import java.util.ArrayList;

public class Graph {
    int size;
    public ArrayList<Edge> edges;

    public Graph(int size){
        this.size = size;
        edges = new ArrayList<>();
        for (int i = 0; i < size; i++){
            var edge = new Edge();
            edge.number = i;
            edge.neighbor = new ArrayList<>();
            edges.add(edge);
        }
    }

    public void MakeGraph(ArrayList<String> matrix){
        var array = new int[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++){
                array[i][j] = -1;
            }

        var k = 0;
        for (var i = 0; i < matrix.size(); i++) {
            var strArray = matrix.get(i).split(" ");
            var j = 0;
            while (Integer.parseInt(strArray[j]) != 0){
                array[i][Integer.parseInt(strArray[j]) - 1] = Integer.parseInt(strArray[j + 1]);
                j+=2;
            }
        }

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++){
                if (array[i][j] != -1){
                    edges.get(i).neighbor.add(edges.get(j));
                }
            }
    }
}
