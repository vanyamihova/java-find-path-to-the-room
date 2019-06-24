package eu.vanyamihova.models.node;

/**
 * @author Vanya Mihova - Delta Source Bulgaria <vanyamihova@deltasource.eu>
 * @since 2019-05-27
 */
public final class RoomNode extends Node {
    protected RoomNode(Integer number, Integer x, Integer y, Short floor) {
        super(NodeType.ROOM, number, x, y, floor);
    }
}
