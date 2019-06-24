package eu.vanyamihova.models.node;

import eu.vanyamihova.models.link.Link;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Vanya Mihova - Delta Source Bulgaria <vanyamihova@deltasource.eu>
 * @since 2019-05-27
 */
public abstract class Node {

    /**
     * Номер (име на стая )
     */
    private Integer number;

    /**
     * Координата X
     */
    private Integer x;

    /**
     * Координата Y
     */
    private Integer y;

    /**
     * Номер на етаж
     */
    private Short floor;

    /**
     * Вид стая
     */
    private NodeType type;

    private boolean expanded;
    private boolean tested;
    private List<Link> links;
    private int depth = -1;
    private double distanceToGoal = 0.0;

    protected Node(NodeType type, Integer number, Integer x, Integer y, Short floor) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.floor = floor;
        this.number = number;
        this.links = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Node {" +
                "number=" + number +
                ", x=" + x +
                ", y=" + y +
                ", floor=" + floor +
                ", type=" + type +
                ", depth=" + depth +
                ", distanceToGoal=" + distanceToGoal +
                ", links=" + links +
                '}';
    }

    public void tested() {
        tested = true;
    }

    public boolean isTested() {
        return tested;
    }

    public void expanded() {
        expanded = true;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public Integer getNumber() {
        return number;
    }

    public Short getFloor() {
        return floor;
    }

    public NodeType getType() {
        return type;
    }

    public void addLink(Link link) {
        if (link == null) {
            return;
        }
        links.add(link);
    }

    public List<Link> getLinks() {
        return Collections.unmodifiableList(links);
    }

    public void sortLinksByPriority() {
        links.sort((Link l1, Link l2) -> Double.compare(l2.getPriority(), l1.getPriority()));
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getDepth() {
        return depth;
    }

    public Integer getY() {
        return y;
    }

    public Integer getX() {
        return x;
    }

    public void setDistanceToGoal(double distanceToGoal) {
        this.distanceToGoal = distanceToGoal;
    }

    public double getDistanceToGoal() {
        return distanceToGoal;
    }

    public void reset() {
        this.expanded = false;
        this.tested = false;
        this.depth = -1;
        this.distanceToGoal = 0.0;
    }
}
