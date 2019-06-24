package eu.vanyamihova.models;

import eu.vanyamihova.models.link.Link;
import eu.vanyamihova.models.link.LinkType;
import eu.vanyamihova.models.node.Node;
import eu.vanyamihova.models.node.TransitNode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Vanya Mihova - Delta Source Bulgaria <vanyamihova@deltasource.eu>
 * @since 2019-05-27
 */
public final class BuildingSchema {

    private HashMap<Integer, Node> graph = new HashMap<>();
    private Comparator<Node> byName = (Node n1, Node n2) -> n1.getNumber().compareTo(n2.getNumber());
    private Comparator<Node> byDistance = (Node n1, Node n2) -> Double.compare(n1.getDistanceToGoal(), n2.getDistanceToGoal());

    @Override
    public String toString() {
        String result = "";
        for (Map.Entry<Integer, Node> entry : graph.entrySet()) {
            result += " [" + entry.getKey() + "] = " + entry.getValue() + "\n";
        }
        return result;
    }

    public void addLink(Integer nodeNumber, Link link) {
        if (!graph.containsKey(nodeNumber)) {
            System.err.println("Wrong or missing node names");
            return;
        }
        Node node = graph.get(nodeNumber);
        node.addLink(link);
    }

    public void addNode(Node node) {
        if (node == null || graph.containsKey(node.getNumber())) {
            System.err.println("Node number already exists");
            return;
        }
        graph.put(node.getNumber(), node);
    }

    public boolean containsNode(Integer number) {
        return graph.containsKey(number);
    }

    public Node getNode(Integer number) {
        return graph.get(number);
    }

    public void setDepths(Integer number) {
        Node node = getNode(number);
        for (Node n : getLinkedNodes(number)) {
            if (n.getDepth() == -1) {
                n.setDepth(node.getDepth() + 1);
            }
        }
    }

    public void setPriorityOnLinksByType(LinkType linkType, int priority) {
        for (Map.Entry<Integer, Node> entry : graph.entrySet()) {
            Node node = entry.getValue();
            if (!(node instanceof TransitNode)) {
                continue;
            }
            for (Link link : node.getLinks()) {
                if (link.isType(linkType)) {
                    link.setPriority(priority);
                }
            }
            node.sortLinksByPriority();
        }
    }

    public void multiplyExpensesForLinksByType(LinkType linkType) {
        for (Map.Entry<Integer, Node> entry : graph.entrySet()) {
            Node node = entry.getValue();
            for (Link link : node.getLinks()) {
                if (link.isType(linkType)) {
                    link.multiplyExpenses();
                }
            }
        }
    }

    public void disableLinksBetweenFloorsByType(LinkType avoidedLinkType) {
        for (Map.Entry<Integer, Node> entry : graph.entrySet()) {
            Node node = entry.getValue();
            for (Link link : node.getLinks()) {
                if (link.isType(avoidedLinkType)) {
                    Node nextNode = graph.get(link.getTo());
                    if (node.getFloor() != nextNode.getFloor()) {
                        link.disable();
                    }
                }
            }
        }
    }

    public void disableLinksByType(LinkType avoidedLinkType) {
        for (Map.Entry<Integer, Node> entry : graph.entrySet()) {
            Node node = entry.getValue();
            for (Link link : node.getLinks()) {
                if (link.isType(avoidedLinkType)) {
                    link.disable();
                }
            }
        }
    }

    public ArrayList<Node> getLinkedNodes(Integer number) {
        ArrayList<Node> linkedNodes = new ArrayList<>();
        Node node = graph.get(number);
        for (Link l : node.getLinks()) {
            if (l.isEnabled()) {
                linkedNodes.add(graph.get(l.getTo()));
            }
        }
        return linkedNodes;
    }

    public double findDistance(Integer nameOne, Integer nameTwo) {
        Node nodeOne = getNode(nameOne);
        Node nodeTwo = getNode(nameTwo);

        return Math.sqrt(Math.pow(nodeOne.getX() - nodeTwo.getX(), 2)
                + Math.pow(nodeOne.getY() - nodeTwo.getY(), 2));
    }

    public void sortByDistance(ArrayList<Node> list) {
        list.sort(byDistance.thenComparing(byName));
    }

    public void resetAllNodes() {
        graph.forEach((k, v) -> v.reset());
    }

}
