Interactive Graph Algorithm Visualizer
An engaging Java GUI application to visualize graph-based algorithms using Swing.

📋 Project Description
This project involves the development of an interactive Java application that serves as a platform for learning and experimenting with fundamental graph algorithms. With an intuitive interface, users can design custom graphs and observe the behavior of algorithms like:

Breadth-First Search (BFS)
Depth-First Search (DFS)
Dijkstra's Algorithm
Prim's Algorithm
The application provides a hands-on approach to understanding graph algorithms, bridging the gap between theoretical concepts and practical implementation.

Core Features
Graph Construction:
Users can switch between modes to add or remove vertices and edges, enabling dynamic graph creation and modification.

Algorithm Execution:
After constructing a graph, users can choose an algorithm to analyze the graph’s structure and properties, observing results in real time.

🎯 Why We Developed This Project
This project is designed to deepen the understanding of data structures and algorithms while fostering skills in creating user-friendly graphical applications.

Key motivations include:

Practical Learning: To make abstract graph concepts accessible and engaging through interactive visualization.
Teaching Aid: To create a versatile tool that can integrate additional algorithms, supporting both teaching and self-learning.
Skill Development: To gain experience in managing user interactions, implementing graph operations, and building a responsive GUI using Swing.
✨ Objectives
To enhance the application’s functionality and usability, we aim to:

Enhance UX:

Add features like drag-and-drop, zoom, and robust error handling for an improved user experience.
Interactive Execution:

Enable step-by-step visualization of algorithms with highlights for better understanding.
Expand Algorithm Library:

Implement additional algorithms to broaden the scope of practical learning.
Compare algorithms for insights into their performance and use cases.
🚀 Key Takeaways
This project is a perfect blend of theoretical knowledge and hands-on implementation, providing opportunities to:

Create and connect vertices with edges dynamically.
Understand graph traversal and optimization algorithms through visualization.
Build an interactive and user-friendly Java Swing application.                                                                                                                                                                                                            
📚 Data Structures Used in the Project
This project utilizes several fundamental data structures to efficiently represent and process graph elements and algorithms.

1. Graph
The graph is the core data structure representing a collection of connected vertices (nodes) and edges (connections). It integrates visual components and logical representations to facilitate interaction, visualization, and algorithm execution.

Key Features
Adding and Removing Vertices:

Users can add vertices by clicking on the canvas and assigning a unique ID.
Vertices are stored in a static map and visually displayed on the JPanel.
Adding and Removing Edges:

Users can connect two vertices by selecting them sequentially.
Edges are represented both visually and logically.
Interactivity:

Graph interactions are handled through mouse clicks.
User actions are validated to ensure proper functionality.
Algorithms:

The graph structure is converted into an adjacency list for algorithm input.
Algorithms such as BFS, DFS, and Dijkstra's run on the graph, with results displayed visually.
Visualization:

Graphs are visualized on a JPanel, with vertices and edges rendered dynamically.
Labels and shapes represent weights and connections for clarity.
Graph Representation in Code
Vertices:

Represented as instances of the Vertex class.
Rendered as small panels on the GUI.
Edges:

Represented as instances of the Edge class.
Can connect two vertices and optionally carry a weight.
2. Maps
A Map stores key-value pairs, where keys are unique, and each key maps to a specific value.

Usage in the Project
Key (Vertex): Represents a graph node. Each Vertex object acts as a unique key.
Value (List<Edge>): Represents edges originating from a vertex. Each vertex maps to a list of its edges.
3. Lists
A List is an ordered collection of elements that allows duplicates and provides dynamic resizing.

Usage in the Project
Storing Vertices:

The vertices list in the Graph class holds all graph vertices, enabling dynamic addition and removal.
Storing Edges:

The availableEdges list stores all graph edges, allowing efficient management of edge operations.
Temporary Storage:

The edgeVertices list temporarily holds vertices during edge operations for consistency and efficiency.
Iteration:

Lists are iterated over for tasks like printing edges or processing graph data.
4. Sets (HashSet)
A Set holds a collection of unique elements. It is ideal for ensuring no duplicates and for quick lookups.

Usage in the Project
Visited Vertices:

In BFS, DFS, and Prim’s Algorithm, a Set<Vertex> keeps track of visited vertices to prevent redundant processing.
Dijkstra's Algorithm:

A set can track unprocessed vertices, ensuring efficient traversal.
5. Queue
A Queue follows the FIFO (First-In-First-Out) principle, ideal for processing elements in order.

Usage in the Project
Breadth-First Search (BFS):

A Queue<Vertex> ensures vertices are processed level by level.
Priority Queue (Min-Heap):

In advanced implementations of Dijkstra’s Algorithm or Prim’s Algorithm, a PriorityQueue<Vertex> can prioritize vertices with the smallest tentative distance.
