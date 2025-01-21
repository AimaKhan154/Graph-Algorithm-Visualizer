package visualizer;
import algorithms.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
public class Graph extends JPanel implements MouseListener {
    protected static List<Vertex> edgeVertices = new ArrayList<>();
    protected static List<List<String>> availableEdges = new ArrayList<>();
    public Graph() {
        setName("Graph");
        setBackground(MainFrame.BACKGROUND_COLOR);
        setLayout(null);
        setSize(MainFrame.WIDTH, MainFrame.HEIGHT);
        setLocation(0, 0);
        addMouseListener(this);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if (MainFrame.mode == Mode.ADD_A_VERTEX) {
            boolean validPosition = validPlacementForVertex(e.getX(), e.getY());
            if (validPosition) {
                while (true) {
                    String input = JOptionPane.showInputDialog(this, "Enter the Vertex ID (Should be 1 char):",
                            "Vertex", JOptionPane.QUESTION_MESSAGE);
                    if (input == null) {
                        break;
                    } else {
                        input = input.trim();
                        if (input.length() == 1 && validVertexID(input)) {
                            int xValue = e.getX() - Vertex.SIZE / 2;
                            int yValue = e.getY() - Vertex.SIZE / 2;
                            createVertex(xValue, yValue, input);
                            return;
                        }
                    }
                }
            }
        } else if (MainFrame.mode == Mode.ADD_AN_EDGE) {
            
            Vertex vertex = clickedOnVertex(e.getX(), e.getY());
            if (vertex != null) {
                edgeVertices.add(vertex);
                if (edgeVertices.size() == 2) {
                 
                    for (List<String> verticesOfAnEdge: availableEdges) {
                        String id1 = edgeVertices.get(0).getId();
                        String id2 = edgeVertices.get(1).getId();
                        if (verticesOfAnEdge.contains(id1) && verticesOfAnEdge.contains(id2)) {
                            edgeVertices.clear();
                            return;
                        }
                    }
                   
                    Vertex vertex1 = edgeVertices.get(0);
                    Vertex vertex2 = edgeVertices.get(1);
                    edgeVertices.clear();
                    List<String> newIdCouple = new ArrayList<>();
                    newIdCouple.add(vertex1.getId());
                    newIdCouple.add(vertex2.getId());
                    availableEdges.add(newIdCouple);
                    drawEdge(vertex1, vertex2);
                }
            }
        } else if (MainFrame.mode == Mode.REMOVE_A_VERTEX) {
           
            Vertex vertex = clickedOnVertex(e.getX(), e.getY());
            if (vertex != null) {
              
                String id = vertex.getId();
                Vertex.vertices.remove(id);
                List<Edge> edges = new ArrayList<>();
               
                for (Edge edge: Edge.edges) {
                    Vertex vertex1 = edge.getVertex1();
                    Vertex vertex2 = edge.getVertex2();
                    if (vertex.equals(vertex1) || vertex.equals(vertex2)) {
                     
                        if (edge.getLabel() != null) this.remove(edge.getLabel());
                       
                        this.remove(edge);
                      
                        String id1 = vertex1.getId();
                        String id2 = vertex2.getId();
                        List<List<String>> newAvailableEdges = new ArrayList<>();
                        for (List<String> verticesOfAnEdge: availableEdges) {
                            if (!(verticesOfAnEdge.contains(id1) && verticesOfAnEdge.contains(id2))) {
                                newAvailableEdges.add(verticesOfAnEdge);
                            }
                        }
                        availableEdges = newAvailableEdges;
                    } else { 
                        edges.add(edge);
                    }
                }
               
                Edge.edges = edges;
               
                this.remove(vertex);
                this.repaint();
            }
        } else if (MainFrame.mode == Mode.REMOVE_AN_EDGE) {
                Edge edge = clickedOnEdge(e.getX(), e.getY());
                if (edge != null) {
                
                    List<Edge> edges = new ArrayList<>();
                    List<Edge> edgesToBeExcluded = new ArrayList<>();
                    for (Edge otherEdge: Edge.edges) {
                        if (edge.equals(otherEdge)) {
                            this.remove(otherEdge);
                            if (otherEdge.getLabel() != null) {
                                this.remove(otherEdge.getLabel());
                            }
                            edgesToBeExcluded.add(otherEdge);
                        } else {
                            edges.add(otherEdge);
                        }
                    }
                   
                    Edge.edges = edges;
                   
                    for (Edge excludedEdge: edgesToBeExcluded) removeEdgeFromStaticList(excludedEdge);
                    this.repaint();
                }
        } else if (MainFrame.mode == Mode.NONE && MainFrame.getAlgorithmDisplayLabel().isVisible()) {
            
            Vertex vertex = clickedOnVertex(e.getX(), e.getY());
            if (vertex != null) {
                AlgorithmSetter algorithmSetter = new AlgorithmSetter();
                algorithmSetter.setAlgorithm(MainFrame.algorithm.getAlgorithmInstance());
               
                Map<Vertex, List<Edge>> graph = createGraphDataStructure();
              
                String path = algorithmSetter.execute(graph, vertex);
               
                MainFrame.getAlgorithmDisplayLabel().setText("Please wait...");
               
                Timer timer = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                       
                        MainFrame.getAlgorithmDisplayLabel().setText(path);
                    }
                });
                timer.setRepeats(false);
                timer.start();
            }
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    private static Vertex clickedOnVertex(int x, int y) {
        for (var entry: Vertex.vertices.entrySet()) {
            Vertex vertex = entry.getValue();
            int xLocation = vertex.getXLocation();
            int yLocation = vertex.getYLocation();
            if (x >= xLocation && x <= xLocation + Vertex.SIZE
                    && y >= yLocation && y <= yLocation + Vertex.SIZE) {
                return vertex;
            }
        }
        return null;
    }
    private static Edge clickedOnEdge(int x, int y) {
        for (Edge edge: Edge.edges) {
        
            int x1 = edge.getX();
            int y1 = edge.getY() + (edge.getTopEqualsLeft() ? 0 : edge.getHeight());
            int x2 = edge.getX() + edge.getWidth();
            int y2 = edge.getY() + (edge.getTopEqualsLeft() ? edge.getHeight() : 0);
           
            double dist = java.awt.geom.Line2D.ptLineDistSq((double) x1, (double) y1,
                    (double) x2, (double) y2,
                    (double) x, (double) y);
            if (dist < 5) return edge;
        }
        return null;
    }
    private static boolean validPlacementForVertex(int x, int y) {
        
        Edge edge = clickedOnEdge(x, y);
        if (edge != null) return false;
        for (Vertex vertex: Vertex.vertices.values()) {
            int xLocation = vertex.getXLocation();
            int yLocation = vertex.getYLocation();
            if (x >= xLocation - Vertex.SIZE / 2 && x <= xLocation + Vertex.SIZE + Vertex.SIZE / 2
                    && y >= yLocation - Vertex.SIZE / 2 && y <= yLocation + Vertex.SIZE + Vertex.SIZE / 2) {
                return false;
            }
        }
        return true;
    }
    private static boolean validVertexID(String userInput) {
        for (String id: Vertex.vertices.keySet()) {
            if (userInput.equals(id)) return false;
        }
        return true;
    }
    private void createVertex(int xValue, int yValue, String id) {
        JPanel vertex = new Vertex(xValue, yValue, id);
        this.add(vertex);
        vertex.repaint();
    }
    private void drawEdge(Vertex vertex1, Vertex vertex2) {
        while (true) {
            String input = JOptionPane.showInputDialog(this, "Enter Weight:",
                    "Input", JOptionPane.QUESTION_MESSAGE);
            if (input == null) {
                break;
            } else {
                if (input.matches("(-?[1-9]\\d*|0)")) {
                    Edge edge1 = new Edge(vertex1, vertex2, Integer.valueOf(input));
                    Edge edge2 = new Edge(vertex2, vertex1, Integer.valueOf(input));
                    this.add(edge1);
                    this.add(edge2);
                    this.add(edge1.getLabel());
                    this.repaint();
                    return;
                }
            }
        }
    }
    private static void removeEdgeFromStaticList(Edge edge) {
        String id1 = edge.getVertex1().getId();
        String id2 = edge.getVertex2().getId();
        List<List<String>> newEdgesList = new ArrayList<>();
        for (List<String> otherEdge: availableEdges) {
            if ((!(otherEdge.contains(id1) && otherEdge.contains(id2)))) {
                newEdgesList.add(otherEdge);
            }
       }

        availableEdges = newEdgesList;
    }
    private static Map<Vertex, List<Edge>> createGraphDataStructure() {
        Map<Vertex, List<Edge>> output = new HashMap<>();
        
        for (Vertex vertex: Vertex.vertices.values()) {
            output.put(vertex, new ArrayList<>());
        }
       
        for (Edge edge: Edge.edges) {
            output.get(edge.getVertex1()).add(edge);
        }

        return output;
    }

}