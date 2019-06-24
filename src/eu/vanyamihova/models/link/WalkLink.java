package eu.vanyamihova.models.link;

/**
 * @author Vanya Mihova - Delta Source Bulgaria <vanyamihova@deltasource.eu>
 * @since 2019-05-27
 */
public final class WalkLink extends Link {

    protected WalkLink(Integer to, Integer expenses) {
        super(LinkType.WALK, to, expenses);
    }

}
