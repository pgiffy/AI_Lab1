import java.util.*;

public class Node {
	public boolean visited = false;
	public ArrayList<Node> tail = new ArrayList<>();
    private ArrayList<Edge> outgoingEdges = new ArrayList<>();
    private ArrayList<Edge> incomingEdges = new ArrayList<>();
    private int id;
    private int X;
    private int Y;
    private char content;
    private ArrayList<Node> parents = new ArrayList<>();
    private Node end;

    public Node(int nodeId, char inside, int xSpot, int ySpot){ 
    	id = nodeId;
    	content = inside;
    	X = xSpot;
    	Y = ySpot;
    }
    
    public int getManhattan() {
    		return Math.abs(X - end.getX()) + Math.abs(Y - end.getY());
    	
    }
    public Node getEnd() {return end;}
    public void setEnd(Node e) {end = e;}
    public void setContent(char newContent) {content = newContent;}
    
    public void addParent(Node parent) { parents.add(parent); } 
    public void addAllParents(ArrayList<Node> newParents) {parents.addAll(newParents);}
    public ArrayList<Node> getParents(){ return parents; } 
    
    public void addOutgoingEdge(Edge newEdge){ outgoingEdges.add(newEdge); }

    public void addIncomingEdge(Edge newEdge) { incomingEdges.add(newEdge); }

    public ArrayList<Edge> getIncomingEdges() {return incomingEdges;}
    public ArrayList<Edge> getOutgoingEdges() {return outgoingEdges;}

    public void removeIncomingEdge(Edge e) { incomingEdges.remove(e); }
    public void removeOutgoingEdge(Edge e) { outgoingEdges.remove(e); }

    public int numIncomingEdges() { return incomingEdges.size(); }

    public int numOutgoingEdges() { return outgoingEdges.size(); }

    public int getId(){ return id; }
    
    public char getContent() { return content; }

    public void setId(int newId) { id = newId; }
    
    public int getX() { return X; }
    public int getY() { return Y; }
    
    public String toString() { return "<" + id + ">"; }
}
