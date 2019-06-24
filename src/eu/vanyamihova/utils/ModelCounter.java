package eu.vanyamihova.utils;

import eu.vanyamihova.models.link.ClimbLink;
import eu.vanyamihova.models.link.LiftLink;
import eu.vanyamihova.models.link.Link;
import eu.vanyamihova.models.link.WalkLink;
import eu.vanyamihova.models.node.Node;
import eu.vanyamihova.models.node.RoomNode;
import eu.vanyamihova.models.node.TransitNode;

/**
 * @author Vanya Mihova - Delta Source Bulgaria <vanyamihova@deltasource.eu>
 * @since 2019-06-23
 */
final class ModelCounter {

    private int climbLinksCount;
    private int liftLinksCount;
    private int walkLinksCount;

    private int roomNodesCount;
    private int transitNodesCount;

    ModelCounter() {
        reset();
    }

    void reset() {
        this.climbLinksCount = 0;
        this.liftLinksCount = 0;
        this.walkLinksCount = 0;
        this.roomNodesCount = 0;
        this.transitNodesCount = 0;
    }

    void count(Node node) {
        if (node instanceof RoomNode) {
            roomNodesCount++;
        } else if (node instanceof TransitNode) {
            transitNodesCount++;
        }
    }

    void count(Link link) {
        if (link instanceof ClimbLink) {
            climbLinksCount++;
        } else if (link instanceof LiftLink) {
            liftLinksCount++;
        } else if (link instanceof WalkLink) {
            walkLinksCount++;
        }
    }

    String getCounters() {
        return "Room nodes: " + roomNodesCount + "; " +
                "Transit nodes: " + transitNodesCount + "; " +
                "Climb links: " + climbLinksCount + "; " +
                "Lift links: " + liftLinksCount + "; " +
                "Walk links: " + walkLinksCount + "; ";
    }

}
