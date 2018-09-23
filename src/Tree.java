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
    
    public ArrayList<Node> removeDuplicates(ArrayList<Node> remove){
    	Set<Node> temp = new HashSet<>();
    	temp.addAll(remove);
    	remove.clear();
    	remove.addAll(temp);
    	return remove;
    	
    }
    
    public ArrayList<Node> depthFirst(Node start) {
    	Stack<Node> stack = new Stack<>();
    	stack.add(start);
    	Node current;
    	while(!stack.isEmpty()) {
    		current = stack.pop();
    		if(current.visited == true) {
    			continue;
    		}
    		if(current.getContent() == '*') {
    			return current.tail;
    		}
    		current.visited = true;
    		current.tail.add(current);
    		for(Edge e : current.getOutgoingEdges()) {
    			
    			e.getToNode().tail.addAll(removeDuplicates(current.tail));
    			stack.add(e.getToNode());
    		}
    		
    	}
    	return null;
    }
    
    MyComparator compare = new MyComparator();
    public ArrayList<Node> greedyFirst(Node start) {
    	PriorityQueue<Node> pq = new PriorityQueue<>(10, compare);
    	pq.add(start);
    	Node current;
    	while(!pq.isEmpty()) {
    		current = pq.poll();
    		if(current.visited == true) {
    			continue;
    		}
    		if(current.getContent() == '*') {
    			return current.tail;
    		}
    		current.visited = true;
    		current.tail.add(current);
    		for(Edge e : current.getOutgoingEdges()) {
    			
    			e.getToNode().tail.addAll(removeDuplicates(current.tail));
    			pq.add(e.getToNode());
    		}
    		
    	}
    	return null;
    }
    
	
}
class MyComparator implements Comparator<Node>{
	public int compare(Node a, Node b) {
		Integer atail = a.tail.size();
		Integer btail = b.tail.size();
		return atail.compareTo(btail);
	}
}
