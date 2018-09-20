public class Edge {
    private Node toNode;
    private Node fromNode;

    public Edge(Node FromNode, Node ToNode) {
        toNode = ToNode;
        fromNode = FromNode;
    }

    public Node getToNode() {
        return toNode;
    }

    public Node getFromNode() {
        return fromNode;
    }

    public String toString() { return "Edge: " + fromNode.toString() + " -> " + toNode.toString() + ")"; }
}
