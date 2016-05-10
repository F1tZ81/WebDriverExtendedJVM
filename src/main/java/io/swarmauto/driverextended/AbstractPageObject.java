package io.swarmauto.driverextended;

import org.openqa.selenium.WebDriver;

/**
 * Created by harolddost on 4/5/16.
 */
public abstract class AbstractPageObject implements PageObject{

    private WebDriver driver;
    private Report report;

    @Override
    public void navigate(WebDriver webDriver, String page) {
        webDriver.navigate().to(page);
    }

    @Override
    public void navigate(WebDriver webDriver) {
        navigate(webDriver,getPageUrl());
    }

    @Override
    public void navigate(String s) {
        navigate(driver,s);
    }

    @Override
    public void navigate() {
        navigate(getPageUrl());
    }

    @Override
    public String getPageUrl() {
        return getBaseUrl() + getPath();
    }

    @Override
    public DynamicElement getDynamicElement() {
        return new DynamicElement(driver,report);
    }
}
