package eu.vanyamihova.search;

import eu.vanyamihova.models.BuildingSchema;
import eu.vanyamihova.models.link.LinkType;
import eu.vanyamihova.models.node.Node;
import eu.vanyamihova.utils.PathPrinter;

import java.util.ArrayList;

/**
 * Да се намери път до стая, като се използва преход lift между етажите.
 * Ако между два етажа няма такъв преход да се използва преход climb, но
 * разходите за този преход да се удвоят при пресмятане на разходите за
 * достигане на целта.
 *
 * @author Vanya Mihova - Delta Source Bulgaria <vanyamihova@deltasource.eu>
 * @since 2019-05-27
 */
final class UsingPrioritizedLinkTypesSearch implements Searchable {

    private BuildingSchema buildingSchema;
    private PathPrinter pathPrinter;

    UsingPrioritizedLinkTypesSearch(BuildingSchema buildingSchema, PathPrinter pathPrinter) {
        this.buildingSchema = buildingSchema;
        this.buildingSchema.setPriorityOnLinksByType(LinkType.LIFT, 2);
        this.buildingSchema.setPriorityOnLinksByType(LinkType.CLIMB, 1);
        this.buildingSchema.multiplyExpensesForLinksByType(LinkType.CLIMB);
        this.buildingSchema.disableLinksBetweenFloorsByType(LinkType.WALK);
        this.pathPrinter = pathPrinter;
    }

    @Override
    public boolean search(Integer startName, Integer endName) {
        if (!buildingSchema.containsNode(startName) || !buildingSchema.containsNode(endName)) {
            return false;
        }

        Node startNode = buildingSchema.getNode(startName);
        Node endNode = buildingSchema.getNode(endName);
        ArrayList<Node> list = new ArrayList<>();
        list.add(startNode);
        startNode.setDepth(0);

        Node temp;

        while (!list.isEmpty()) {
            temp = list.get(0);
            System.out.println("Positioning in: " + temp.getNumber() + " (" + temp.getType() + ") and depth is: " + temp.getDepth());

            if (endNode == temp) {
                pathPrinter.generatePath(temp);
                return true;
            }
            buildingSchema.setDepths(temp.getNumber());
            temp.tested();
            list.remove(0);

            for (Node node : buildingSchema.getLinkedNodes(temp.getNumber())) {
                if (!node.isTested() && !list.contains(node)) {
                    list.add(node);
                }
            }
            temp.expanded();

        }

        return false;
    }

}
