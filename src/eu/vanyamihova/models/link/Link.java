package eu.vanyamihova.models.link;

/**
 * @author Vanya Mihova - Delta Source Bulgaria <vanyamihova@deltasource.eu>
 * @since 2019-05-27
 */
public abstract class Link {

    /**
     * До стая(име)
     */
    private Integer to;

    /**
     * Вид преход
     */
    private LinkType type;

    /**
     * Разход за преминаване
     */
    private Integer expenses;

    /**
     * Двупосочна да/не
     */
    private Boolean bidirectional;

    private Boolean enabled;
    private int priority;

    protected Link(LinkType type, Integer to, Integer expenses) {
        this.to = to;
        this.type = type;
        this.expenses = expenses;
        this.enabled = true;
        this.priority = 0;
    }

    @Override
    public String toString() {
        return "Link {" +
                "to=" + to +
                ", type=" + type +
                ", expenses=" + expenses +
                ", bidirectional=" + bidirectional +
                '}';
    }

    public Integer getTo() {
        return to;
    }

    public Integer getExpenses() {
        return expenses;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public void disable() {
        this.enabled = false;
    }

    public boolean isType(LinkType linkType) {
        return this.type == linkType;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public void multiplyExpenses() {
        expenses *= 2;
    }
}
