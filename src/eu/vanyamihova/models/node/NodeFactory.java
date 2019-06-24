package eu.vanyamihova.models.node;

/**
 * @author Vanya Mihova - Delta Source Bulgaria <vanyamihova@deltasource.eu>
 * @since 2019-05-27
 */
public class NodeFactory {

    public Node create(Integer number, Integer x, Integer y, Short floor, String type) {
        NodeType nodeType = NodeType.valueOf(type.toUpperCase());
        switch (nodeType) {
            case ROOM:
                return new RoomNode(number, x, y, floor);
            case TRANSIT:
                return new TransitNode(number, x, y, floor);
        }
        return null;
    }

}
