package visualizer;
import java.awt.*;
import javax.swing.*;
public class MainFrame extends JFrame {
    protected static final int WIDTH = 1300;
    protected static final int HEIGHT = 700;
    protected static final Color BACKGROUND_COLOR = Color.decode("#BD7E93");
    private JLabel modeLabel;
    private static final JLabel algorithmDisplayLabel;
    private Graph graphPanel;
    protected static Mode mode = Mode.ADD_A_VERTEX;
    protected static Algorithm algorithm = null;
    static {
        algorithmDisplayLabel = new JLabel();
        algorithmDisplayLabel.setName("Display");
        algorithmDisplayLabel.setText("Please choose a starting vertex");
        algorithmDisplayLabel.setForeground(MainFrame.BACKGROUND_COLOR);
        algorithmDisplayLabel.setBackground(Color.white);
        algorithmDisplayLabel.setHorizontalAlignment(SwingConstants.CENTER);
        algorithmDisplayLabel.setVerticalAlignment(SwingConstants.CENTER);
        algorithmDisplayLabel.setLayout(new FlowLayout(FlowLayout.TRAILING));
        algorithmDisplayLabel.setVisible(false);
    }
    public MainFrame() {
        super("Graph-Algorithms Visualizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(MainFrame.WIDTH, MainFrame.HEIGHT);
        setLayout(new BorderLayout());
        setResizable(false);
        setModeJLabel();
        this.add(algorithmDisplayLabel, BorderLayout.SOUTH);
        setJMenu();
        add(this.graphPanel = new Graph(), BorderLayout.CENTER);
        setVisible(true);
    }
    private void setJMenu() {
        
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
      
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        
        JMenu modeMenu = new JMenu("Mode");
        menuBar.add(modeMenu);
       
        JMenu algorithmsMenu = new JMenu("Algorithms");
        menuBar.add(algorithmsMenu);
       
        JMenuItem addAVertex = new JMenuItem("Add a Vertex");
        addAVertex.setName("Add a Vertex");
        JMenuItem addAnEdge = new JMenuItem("Add an Edge");
        addAnEdge.setName("Add an Edge");
        JMenuItem none = new JMenuItem("None");
        none.setName("None");
        JMenuItem removeAVertex = new JMenuItem("Remove a Vertex");
        removeAVertex.setName("Remove a Vertex");
        JMenuItem removeAnEdge = new JMenuItem("Remove an Edge");
        removeAnEdge.setName("Remove an Edge");
        modeMenu.add(addAVertex);
        modeMenu.add(addAnEdge);
        modeMenu.add(removeAVertex);
        modeMenu.add(removeAnEdge);
        modeMenu.add(none);
       
        JMenuItem newReset = new JMenuItem("New");
        newReset.setName("New");
        JMenuItem exit = new JMenuItem("Exit");
        exit.setName("Exit");
        fileMenu.add(newReset);
        fileMenu.add(exit);
      
        JMenuItem DFS = new JMenuItem("Depth-First Search");
        DFS.setName("Depth-First Search");
        JMenuItem BFS = new JMenuItem("Breadth-First Search");
        BFS.setName("Breadth-First Search");
        JMenuItem Dijkstras = new JMenuItem("Dijkstra's Algorithm");
        Dijkstras.setName("Dijkstra's Algorithm");
        JMenuItem Prims = new JMenuItem("Prim's Algorithm");
        Prims.setName("Prim's Algorithm");
        algorithmsMenu.add(DFS);
        algorithmsMenu.add(BFS);
        algorithmsMenu.add(Dijkstras);
        algorithmsMenu.add(Prims);
       
        addAVertex.addActionListener(e -> {
           
            mode = Mode.ADD_A_VERTEX;
           
            changeTextForModeLabel("Current Mode -> " + mode.getDescription());
          
            Graph.edgeVertices.clear();
          
            algorithmDisplayLabel.setVisible(false);
            algorithmDisplayLabel.setText("Please choose a starting vertex");
        });
        addAnEdge.addActionListener(e -> {
           
            mode = Mode.ADD_AN_EDGE;
          
            changeTextForModeLabel("Current Mode -> " + mode.getDescription());
           
            algorithmDisplayLabel.setVisible(false);
            algorithmDisplayLabel.setText("Please choose a starting vertex");
        });
        none.addActionListener(e -> {
           
            mode = Mode.NONE;
          
            changeTextForModeLabel("Current Mode -> " + mode.getDescription());
          
            Graph.edgeVertices.clear();
           
            algorithmDisplayLabel.setVisible(false);
            algorithmDisplayLabel.setText("Please choose a starting vertex");
        });
        removeAVertex.addActionListener(e -> {
          
            mode = Mode.REMOVE_A_VERTEX;
           
            changeTextForModeLabel("Current Mode -> " + mode.getDescription());
           
            Graph.edgeVertices.clear();
          
            algorithmDisplayLabel.setVisible(false);
            algorithmDisplayLabel.setText("Please choose a starting vertex");
        });
        removeAnEdge.addActionListener(e -> {
           
            mode = Mode.REMOVE_AN_EDGE;
           
            changeTextForModeLabel("Current Mode -> " + mode.getDescription());
          
            Graph.edgeVertices.clear();
            
            algorithmDisplayLabel.setVisible(false);
            algorithmDisplayLabel.setText("Please choose a starting vertex");
        });
       
        newReset.addActionListener(e -> {
            this.remove(this.graphPanel);
            this.repaint();
            this.revalidate();
            this.add(this.graphPanel = new Graph());
           
            Edge.edges.clear();
            Vertex.vertices.clear();
            Graph.edgeVertices.clear();
            Graph.availableEdges.clear();
         
            mode = Mode.ADD_A_VERTEX;
          
            changeTextForModeLabel("Current Mode -> " + mode.getDescription());
          
            algorithmDisplayLabel.setVisible(false);
            algorithmDisplayLabel.setText("Please choose a starting vertex");
        });
        exit.addActionListener(e -> {
            this.dispose();
        });
       
        DFS.addActionListener(e -> {
            none.doClick();
          
            algorithmDisplayLabel.setVisible(true);
           
            algorithm = Algorithm.DFS;
        });
        BFS.addActionListener(e -> {
            none.doClick();
           
            algorithmDisplayLabel.setVisible(true);
          
            algorithm = Algorithm.BFS;
        });
        Dijkstras.addActionListener(e -> {
            none.doClick();
           
            algorithmDisplayLabel.setVisible(true);
          
            algorithm = Algorithm.DIJKSTRAS;
        });
        Prims.addActionListener(e -> {
            none.doClick();
           
            algorithmDisplayLabel.setVisible(true);
           
            algorithm = Algorithm.PRIMS;
        });
    }
    private void changeTextForModeLabel(String newText) {
        this.modeLabel.setText(newText);
        modeLabel.setSize(modeLabel.getPreferredSize());
        this.modeLabel.setLocation(MainFrame.WIDTH - modeLabel.getWidth() - 20, 0);
    }
    private void setModeJLabel() {
        modeLabel = new JLabel();
        this.add(modeLabel, BorderLayout.NORTH);
        modeLabel.setName("Mode");
        modeLabel.setText("Current Mode -> Add a Vertex");
        modeLabel.setOpaque(true);
        modeLabel.setForeground(Vertex.VERTEX_COLOR);
        modeLabel.setBackground(MainFrame.BACKGROUND_COLOR);
        modeLabel.setLayout(new FlowLayout(FlowLayout.CENTER));
        modeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    }
    public static JLabel getAlgorithmDisplayLabel() {
        return algorithmDisplayLabel;
    }

}
