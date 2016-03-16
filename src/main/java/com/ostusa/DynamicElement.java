package com.ostusa;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.*;

public class DynamicElement implements WebElement
{
    private WebDriver driver;
    private WebElement rootElement;
    private ArrayList<By> searchOptions = new ArrayList<By>();
	public String displayName = null;

    Report Reporting;

    public DynamicElement() { }

    public DynamicElement(WebDriver driver) {
        this(driver, null, "Unknown");
    }

    public DynamicElement(WebDriver driver, String DisplayName) {
        this(driver, null, DisplayName);
    }

    public DynamicElement(WebDriver driver, Report reporting) {
        this(driver, reporting, "Unknown");
    }

    public DynamicElement(WebDriver driver, Report reporting, String displayName) {
        this.driver = driver;
        Reporting = reporting;
        this.displayName = displayName;
    }

    private DynamicElement(WebDriver driver, WebElement rootElement) {
        this.rootElement = rootElement;
        this.driver = driver;
        displayName = "Unknown";
    }

	private DynamicElement Find() {
        if(rootElement == null || ElementStale())
        {
            for (By currentBy: searchOptions)
            {
				try
                {
                    rootElement = driver.findElement(currentBy);
                    return this;
                }
                catch (Exception e)
                {
                    // contiune on
                }
            }

            if(Reporting != null) Reporting.validate("Could not find the element " + displayName, false);
            return this;
        }

        return this;
    }

    public DynamicElement AddSearch(By byToAdd) {
        searchOptions.add(byToAdd);
        return this;
    }

    @Override
    public void clear() {
        this.Find();
        this.rootElement.clear();
        Reporting.writeStep("Clear element " + displayName);
    }

    @Override
    public void click() {
        this.Find();
        this.rootElement.click();
        Reporting.writeStep("Click element " + displayName);
    }

    @Override
    public WebElement findElement(By arg0) {
        throw new WebDriverException("Incorrect Usage");
    }

    @Override
    public List<WebElement> findElements(By arg0) {
        throw new WebDriverException("Incorrect Usage");
    }

    @Override
    public String getAttribute(String arg0) {
        this.Find();
        return this.rootElement.getAttribute(arg0);
    }

	@Override
    public String getCssValue(String arg0) {
        this.Find();
        return this.rootElement.getCssValue(arg0);
    }

    @Override
    public Point getLocation() {
        this.Find();
        return this.rootElement.getLocation();
    }

    @Override
    public Dimension getSize() {
        this.Find();
        return this.rootElement.getSize();
    }

    @Override
    public String getTagName() {
        this.Find();
        return this.rootElement.getTagName();
    }

    @Override
    public String getText() {
        this.Find();
        return this.rootElement.getText();
    }

	@Override
    public boolean isDisplayed() {
        this.Find();
        return this.rootElement.isDisplayed();
    }

	@Override
    public boolean isEnabled() {
		this.Find();
		return this.rootElement.isEnabled();
    }

	@Override
    public boolean isSelected() {
		this.Find();
		return this.rootElement.isSelected();
    }

    @Override
    public void sendKeys(CharSequence... arg0) {
        this.Find();
        this.rootElement.sendKeys(arg0);
        Reporting.writeStep("Send Keys (" + arg0 + ") to  element " + displayName);
    }

    @Override
    public void submit() {
        this.Find();
        this.rootElement.submit();
        Reporting.writeStep("Submit element " + displayName);
    }

    private boolean ElementStale() {
        try
        {
            Point staleCheck = rootElement.getLocation();
            return false;
        }
        catch (StaleElementReferenceException e)
        {
            return true;
        }
    }



}
