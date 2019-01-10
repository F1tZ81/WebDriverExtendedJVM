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

    /**
     * This gets base URL which consists of the protocol and the host name
     * (i.e. http://example.com) It should never end in a trailing slash, and
     * should follow a similar format as shown in the example.
     * @return
     */
    String getBaseUrl();

    /**
     * Sets the base URL
     * @param baseUrl String should look similar (i.e. http://example.com) if there
     *                is a port, then that too should be included.
     */
    void setBaseUrl(String baseUrl);

    /**
     * Gets the path which should consist of a leading slash (i.e. /blog)
     * @return
     */
    String getPath();

    void setPath(String path);

    /**
     *
     * @return Full Path of the formatted string (i.e. http://example.com/blog)
     */
    String getPageUrl();

    DynamicElement getDynamicElement();

    /** Get the the Diplay name used in reporting */
    String getDisplayName();

    /** Get the accocited webdriver in the page object. */
    WebDriver getDriver();
}
