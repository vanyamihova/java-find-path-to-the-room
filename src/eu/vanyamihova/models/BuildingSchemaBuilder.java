package eu.vanyamihova.models;

import eu.vanyamihova.models.link.Link;
import eu.vanyamihova.models.link.LinkFactory;
import eu.vanyamihova.models.node.Node;
import eu.vanyamihova.models.node.NodeFactory;

/**
 * @author Vanya Mihova - Delta Source Bulgaria <vanyamihova@deltasource.eu>
 * @since 2019-05-27
 */
public final class BuildingSchemaBuilder {

    private LinkFactory linkFactory;
    private NodeFactory nodeFactory;
    private BuildingSchema buildingSchema;

    public BuildingSchemaBuilder(BuildingSchema buildingSchema) {
        this.buildingSchema = buildingSchema;
        this.linkFactory = new LinkFactory();
        this.nodeFactory = new NodeFactory();
    }

    public void buildFrom(String content) {
        String[] rows = content.split(";");

        for (String row : rows) {
            String[] items = row.split(", ");
            if (items == null) {
                return;
            }
            buildElement(items[0], items[1], items[2], items[3], items[4]);
        }
    }

    private void buildElement(String col1, String col2, String col3, String col4, String col5) {
//        System.out.println("col1: " + col1 + " col2: " + col2 + " col3: " + col3 + " col4: " + col4 + " col5: " + col5);
        if (isNumeric(col3)) {
            Node node = nodeFactory.create(Integer.parseInt(col1), Integer.parseInt(col2), Integer.parseInt(col3), Short.parseShort(col4), col5);
            buildingSchema.addNode(node);
            return;
        }
        Link[] links = linkFactory.create(Integer.valueOf(col1), Integer.parseInt(col2), col3, Integer.parseInt(col4), new Bidirectional(col5));
        buildingSchema.addLink(Integer.valueOf(col1), links[0]);
        buildingSchema.addLink(Integer.valueOf(col2), links[1]);
    }

    private boolean isNumeric(String string) {
        return string.matches("-?\\d+(\\.\\d+)?");
    }

}
