package eu.vanyamihova.models.link;

import eu.vanyamihova.models.Bidirectional;

/**
 * @author Vanya Mihova - Delta Source Bulgaria <vanyamihova@deltasource.eu>
 * @since 2019-05-27
 */
public class LinkFactory {

    public Link[] create(Integer from, Integer to, String type, Integer expenses, Bidirectional bidirectional) {
        Link[] links = new Link[2];
        LinkType linkType = LinkType.valueOf(type.toUpperCase());
        links[0] = createLinkByType(linkType, to, expenses);
        if (bidirectional.getValue()) {
            links[1] = createLinkByType(linkType, from, expenses);
        }
        return links;
    }

    private Link createLinkByType(LinkType linkType, Integer number, Integer expenses) {
        switch (linkType) {
            case LIFT:
                return new LiftLink(number, expenses);
            case CLIMB:
                return new ClimbLink(number, expenses);
            case WALK:
                return new WalkLink(number, expenses);
        }
        return null;
    }

}
