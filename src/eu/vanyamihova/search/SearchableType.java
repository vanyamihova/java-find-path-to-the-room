package eu.vanyamihova.search;

/**
 * @author Vanya Mihova - Delta Source Bulgaria <vanyamihova@deltasource.eu>
 * @since 2019-06-23
 */
public enum SearchableType {
    AVOID_LINK_TYPES(1),
    COORDINATES(2),
    USING_PRIORITIZED_LINK_TYPES(3);

    SearchableType(int type) {
        this.type = type;
    }

    private int type;

    public static SearchableType getSearchTypeByType(int type) {
        for (SearchableType searchableType : values()) {
            if (type == searchableType.type) {
                return searchableType;
            }
        }
        return null;
    }

}
