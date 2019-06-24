package eu.vanyamihova.models;

/**
 * @author Vanya Mihova - Delta Source Bulgaria <vanyamihova@deltasource.eu>
 * @since 2019-05-27
 */
public final class Bidirectional {

    private Boolean value;

    public Bidirectional(String bidirectional) {
        this.value = "yes".equalsIgnoreCase(bidirectional);
    }

    public Boolean getValue() {
        return value;
    }
}
