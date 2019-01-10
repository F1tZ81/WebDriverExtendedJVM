package io.swarmauto.driverextended;

import org.openqa.selenium.WebDriver;

public interface PageObject {
    /**
     * Setup any necessary internals.
     */
    void setup();

    /**
     * Cleanup any necessary internals.
     */
    void tearDown();

    /** Get the the Diplay name used in reporting */
    String getDisplayName();

    /** Get the accocited webdriver in the page object. */
    WebDriver getDriver();
}
