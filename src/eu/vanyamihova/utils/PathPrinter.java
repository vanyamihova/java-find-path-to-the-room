package eu.vanyamihova.utils;

import eu.vanyamihova.models.BuildingSchema;
import eu.vanyamihova.models.link.Link;
import eu.vanyamihova.models.node.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vanya Mihova - Delta Source Bulgaria <vanyamihova@deltasource.eu>
 * @since 2019-06-23
 */
public final class PathPrinter {

    private Integer expenses;
    private List<Node> nodes;
    private StringBuilder path;
    private BuildingSchema buildingSchema;
    private ModelCounter modelCounter;

    public PathPrinter(BuildingSchema buildingSchema) {
        this.buildingSchema = buildingSchema;
        this.modelCounter = new ModelCounter();
    }

    public void generatePath(Node current) {
        nodes = new ArrayList<>();
        expenses = 0;
        modelCounter.reset();

        while (current.getDepth() != 0) {
            append(current);

            for (Node node : buildingSchema.getLinkedNodes(current.getNumber())) {
                if (node.getDepth() == current.getDepth() - 1) {
                    calculateExpenses(current, node);
                    current = node;
                    break;
                }
            }
        }

        append(current);
        buildPath();
    }

    private void calculateExpenses(Node from, Node to) {
        Link link = getLink(from, to);
        if (link == null) {
            return;
        }
        modelCounter.count(link);
        expenses += link.getExpenses();
    }

    private Link getLink(Node from, Node to) {
        for (Link link : from.getLinks()) {
            if (link.getTo().intValue() == to.getNumber().intValue() && link.isEnabled()) {
                return link;
            }
        }
        return null;
    }

    private void append(Node node) {
        modelCounter.count(node);
        nodes.add(0, node);
    }

    private void buildPath() {
        path = new StringBuilder();
        for (Node node : nodes) {
            path.append(node.getNumber());
            path.append(" / ");
        }
    }

    public void print() {
        System.out.println("Path: " + path);
        System.out.println("Expenses: " + expenses);
        System.out.println("Counters: " + modelCounter.getCounters());
    }

}
