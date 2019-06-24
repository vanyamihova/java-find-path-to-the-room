package eu.vanyamihova.models.link;

/**
 * @author Vanya Mihova - Delta Source Bulgaria <vanyamihova@deltasource.eu>
 * @since 2019-05-27
 */
public final class ClimbLink extends Link {

    protected ClimbLink(Integer to, Integer expenses) {
        super(LinkType.CLIMB, to, expenses);
    }

}
