import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Program {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("in.txt"));
        var matrix = new ArrayList<String>();
        while (sc.hasNextLine())
            matrix.add(sc.nextLine());

        Graph g = new Graph(Integer.parseInt(matrix.get(0)));
        matrix.remove(0);

        var endEdgeNumber = Integer.parseInt(matrix.get(matrix.size() - 1));
        matrix.remove(matrix.size() - 1);

        var startEdgeNumber = Integer.parseInt(matrix.get(matrix.size() - 1));
        matrix.remove(matrix.size() - 1);

        g.MakeGraph(matrix);
        try (FileWriter writer = new FileWriter("result.txt", false)) {
            String text = g.findMaxPath(startEdgeNumber - 1,endEdgeNumber - 1);
            writer.write(text);
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
