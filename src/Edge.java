public class Edge {
    private Node toNode;
    private Node fromNode;
    //small custom edges class to help with building organized network
    //also added custom toString
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
