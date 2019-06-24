package eu.vanyamihova.models.link;

/**
 * @author Vanya Mihova - Delta Source Bulgaria <vanyamihova@deltasource.eu>
 * @since 2019-05-27
 */
public final class LiftLink extends Link {

    protected LiftLink(Integer to, Integer expenses) {
        super(LinkType.LIFT, to, expenses);
    }

}
