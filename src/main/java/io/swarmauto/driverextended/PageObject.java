package io.swarmauto.driverextended;

import org.openqa.selenium.WebDriver;

/**
 * A basic page object interface
 */
public interface PageObject
{
    /**
     * Setup any necessary internals.
     */
    void setup();

    /**
     * Navigate to an address with a specified browser and url.
     *
     * @param browser
     * @param url
     */
    void navigate(WebDriver browser, String url);

    /**
     * Navigate to an address with a browser determined by the object.
     *
     * @param url
     */
    void navigate(String url);

    /**
     * Navigate to an address determined by the PageObject with a specific browser.
     *
     * @param browser
     */
    void navigate(WebDriver browser);

    /**
     * Navigate to an address and browser determined by the PageObject.
     */
    void navigate();

    /**
     * Cleanup any necessary internals.
     */
    void tearDown();
}
