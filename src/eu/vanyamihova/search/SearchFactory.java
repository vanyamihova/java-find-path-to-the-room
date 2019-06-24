package eu.vanyamihova.search;

import eu.vanyamihova.models.BuildingSchema;
import eu.vanyamihova.models.link.LinkType;
import eu.vanyamihova.utils.PathPrinter;

/**
 * @author Vanya Mihova - Delta Source Bulgaria <vanyamihova@deltasource.eu>
 * @since 2019-06-23
 */
public class SearchFactory {

    private BuildingSchema buildingSchema;
    private PathPrinter pathPrinter;

    public SearchFactory(BuildingSchema buildingSchema, PathPrinter pathPrinter) {
        this.buildingSchema = buildingSchema;
        this.pathPrinter = pathPrinter;
    }

    public Searchable factory(SearchableType searchableType, LinkType linkType) {
        switch (searchableType) {
            case AVOID_LINK_TYPES:
                return new AvoidLinkTypesSearch(buildingSchema, pathPrinter, linkType);
            case COORDINATES:
                return new CoordinatesSearch(buildingSchema, pathPrinter);
            case USING_PRIORITIZED_LINK_TYPES:
                return new UsingPrioritizedLinkTypesSearch(buildingSchema, pathPrinter);
        }
        return null;
    }
}
