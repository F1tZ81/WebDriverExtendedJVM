package io.swarmauto.driverextended;

import java.util.Date;

/**
 * An object used to track state with in a page object
 */
public class PageState {

    private String key;
    private String value;
    private Date lastInteraction;

    /**
     * Constructor to track state
     * @param key used to interact with a state
     * @param value the current value of the state
     */
    public PageState(String key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * @return The key used to interact with a page state
     */
    public String getKey() {
        return key;
    }

    /**
     * @return The value of a page state
     */
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        lastInteraction = new Date();
    }

    /**
     * @return the last time a state has been updated
     */
    public Date getLastInteraction() {
        return lastInteraction;
    }
}
