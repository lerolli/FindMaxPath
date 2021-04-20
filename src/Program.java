import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;

public class Program {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("in.txt"));
        var matrix = new ArrayList<String>();
        while(sc.hasNextLine()){
            matrix.add(sc.nextLine());
        }
        Graph g = new Graph(Integer.parseInt(matrix.get(0)));
        matrix.remove(0);
        var endEdgeNumber =   Integer.parseInt(matrix.get(matrix.size() - 1));
        matrix.remove(matrix.size() - 1);
        var startEdgeNumber =   Integer.parseInt(matrix.get(matrix.size() - 1));
        matrix.remove(matrix.size() - 1);

        g.MakeGraph(matrix);

        try(FileWriter writer = new FileWriter("result.txt", false))
        {
            String text = findMaxPath(g, g.edges.get(startEdgeNumber - 1), g.edges.get(endEdgeNumber - 1));
            writer.write(text);
            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }


    }

    public static String findMaxPath(Graph g, Edge startEdge, Edge endEdge){
        var d = new int[g.size];
        d[startEdge.number] = 0;

        var stack = new Stack<Edge>();
        var visited = new ArrayList<Edge>();

        stack.push(startEdge);

        while (stack.size() != 0){

            var edge = stack.pop();

            if (edge.number == endEdge.number){
                
            }

            for (var neighbor : edge.neighbor){
                stack.push(neighbor);

            }

        }
        return "N";
    }
}