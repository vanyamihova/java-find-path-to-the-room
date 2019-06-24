package eu.vanyamihova.search;

import eu.vanyamihova.models.BuildingSchema;
import eu.vanyamihova.models.link.LinkType;
import eu.vanyamihova.models.node.Node;
import eu.vanyamihova.utils.PathPrinter;

import java.util.ArrayList;

/**
 * Да се намери път до стая, като се избягват определен тип преход – например
 * избягваме стълби между етажи, поради болки в краката... (ако не може да се
 * достигне до стаята без да се мине през стълби се приема, че път няма);
 *
 * @author Vanya Mihova - Delta Source Bulgaria <vanyamihova@deltasource.eu>
 * @since 2019-05-27
 */
final class AvoidLinkTypesSearch implements Searchable {

    private BuildingSchema buildingSchema;
    private PathPrinter pathPrinter;

    AvoidLinkTypesSearch(BuildingSchema buildingSchema, PathPrinter pathPrinter, LinkType linkType) {
        this.buildingSchema = buildingSchema;
        this.buildingSchema.disableLinksByType(linkType);
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
