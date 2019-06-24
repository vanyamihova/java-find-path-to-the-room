package eu.vanyamihova.models.node;

/**
 * @author Vanya Mihova - Delta Source Bulgaria <vanyamihova@deltasource.eu>
 * @since 2019-05-27
 */
public final class TransitNode extends Node {
    protected TransitNode(Integer number, Integer x, Integer y, Short floor) {
        super(NodeType.TRANSIT, number, x, y, floor);
    }
}
