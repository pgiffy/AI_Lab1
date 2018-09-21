import java.util.*;

public class Tree {
	ArrayList<Node> nodes = new ArrayList<>();
	ArrayList<Edge> edges = new ArrayList<>();
	
	public Tree() {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
    }
	
	public void addNode(Node n) {
        nodes.add(n);
    }

    public void addEdge(Edge e) {
        Node fromNode = e.getFromNode();
        Node toNode = e.getToNode();
        edges.add(e);
        fromNode.addOutgoingEdge(e);
        toNode.addIncomingEdge(e);
    }

    public void addEdge(Node fromNode, Node toNode) {
        Edge newEdge = new Edge(fromNode, toNode);
        edges.add(newEdge);
        fromNode.addOutgoingEdge(newEdge);
        toNode.addIncomingEdge(newEdge);
    }
    
    public Node getNode(int id) { return nodes.get(id); }
    public ArrayList<Node> getNodes(){ return nodes; }
    public int numNodes() { return nodes.size(); }
    public ArrayList<Edge> getEdges(){return edges;}
    
    
    
    public void breadthFirst() {
    	
    }
    
	
}
