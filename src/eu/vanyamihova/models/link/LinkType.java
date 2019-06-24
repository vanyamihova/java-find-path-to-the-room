package eu.vanyamihova.models.link;

/**
 * @author Vanya Mihova - Delta Source Bulgaria <vanyamihova@deltasource.eu>
 * @since 2019-05-27
 */
public enum LinkType {

    /**
     * Коридор
     */
    WALK(null),

    /**
     * Стълби
     */
    CLIMB(1),

    /**
     * Асансьор
     */
    LIFT(2);

    LinkType(Integer type) {
        this.type = type;
    }

    private Integer type;

    public static LinkType getLinkTypeByType(int type) {
        for (LinkType linkType : values()) {
            if (linkType.type != null && type == linkType.type) {
                return linkType;
            }
        }
        return null;
    }
}
