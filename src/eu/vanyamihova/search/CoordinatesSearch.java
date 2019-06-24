package eu.vanyamihova.search;

import eu.vanyamihova.models.BuildingSchema;
import eu.vanyamihova.utils.PathPrinter;
import eu.vanyamihova.models.node.Node;

import java.util.ArrayList;

/**
 * Търсене на стая като се възползвате от координатите и етажите, за
 * да достигнете до стаята с минимален брой стъпки (преминаване през
 * минимален брой стаи);
 *
 * @author Vanya Mihova - Delta Source Bulgaria <vanyamihova@deltasource.eu>
 * @since 2019-05-27
 */
final class CoordinatesSearch implements Searchable {

    private BuildingSchema buildingSchema;
    private PathPrinter pathPrinter;

    CoordinatesSearch(BuildingSchema buildingSchema, PathPrinter pathPrinter) {
        this.buildingSchema = buildingSchema;
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
                    node.setDistanceToGoal(buildingSchema.findDistance(node.getNumber(), endName));
                    list.add(node);
                }
            }
            buildingSchema.sortByDistance(list);
            temp.expanded();

        }

        return false;
    }

}
