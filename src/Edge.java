import java.util.ArrayList;
import java.util.HashMap;

public class Edge {
    int number;
    int sortNumber;
    Edge ancestor;
    ArrayList<Edge> neighbor;


    @Override
    public String toString() {
        return "Edge number = " + number + " sort number = " + sortNumber;
    }
}
