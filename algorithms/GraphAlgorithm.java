package algorithms;
import java.util.*;
import visualizer.Edge;
import visualizer.Vertex;
public interface GraphAlgorithm {
    String run(Map<Vertex, List<Edge>> graph, Vertex start);
    default String processVertex(Vertex vertex) {
        return vertex.getId() + " -> ";
    }
}
