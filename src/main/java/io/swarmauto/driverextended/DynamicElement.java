package io.swarmauto.driverextended;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DynamicElement implements WebElement
{
    private WebDriver driver;
    private WebElement rootElement;
	protected String displayName = null;
    private boolean NoCache = false;
    protected String ParentPage = null;
    private ArrayList<By> searchOptions = new ArrayList<By>();
    private DynamicElement ParentElement;
    private Report report;

    //#region Constructors
    @Deprecated
    public DynamicElement() { }

    public DynamicElement(WebDriver driver) {
        this(driver, "Unknown");
    }

    public DynamicElement(WebDriver driver, String DisplayName) {
        this.driver = driver;
        this.displayName = displayName;
    }

    public DynamicElement(WebDriver driver, Report report) {
        this(driver, report, "Unknown");
    }

    public DynamicElement(WebDriver driver, Report report, String displayName) {
        this.driver = driver;
        this.report = new SafeReport(report);
        this.displayName = displayName;
    }

    private DynamicElement(WebDriver driver, WebElement rootElement) {
        this.rootElement = rootElement;
        this.driver = driver;
        displayName = "Unknown";
        this.report = null;
    }

    public DynamicElement(WebDriver driver, String displayName, WebElement rootElement) {
        this(driver, rootElement);
        this.displayName = displayName;

    }

    /**
     * Creates a Dynamic Element
     *
     * @param page the page object that this element belongs too
     * @param displayName the display name of the element (human readable)
     */
    public DynamicElement(PageObject page, String displayName) {
        this(page, displayName, null);
    }

    /**
     * Creates a dynamic element that uses the parent element as the base of the search
     *
     * @param page
     * @param displayName
     * @param parentElement
     */
    public DynamicElement(PageObject page, String displayName, DynamicElement parentElement) {
        ParentElement = parentElement;
        this.driver = page.getDriver();
        this.ParentPage = page.getDisplayName();
        this.displayName = displayName;
        this.report = null;
    }

    /**
     * Creates a Dynamic Element
     *
     * @param driver web driver instance to attach to the element
     * @param page the name of the page this element belongs too
     * @param displayName the display name of the element (human readable)
     */
    public DynamicElement(WebDriver driver, String page, String displayName) {
        this.ParentElement = null;
        this.driver = driver;
        this.ParentPage = page;
        this.displayName = displayName;
        this.report = null;
    }

    //#endregion

    //#region Dynamic Functions
    public DynamicElement addSearch(By byToAdd) {
        searchOptions.add(byToAdd);
        return this;
    }

    public void clearSearches(){
        searchOptions.clear();
    }

    public DynamicElement findDynamicElement(By by) {
        find();
        return new DynamicElement(driver, rootElement.findElement(by));
    }

    public List<DynamicElement> findDynamicElements(By by) {
        this.find();
        List<WebElement> baseElements = rootElement.findElements(by);
        List<DynamicElement> elementsToReturn = new ArrayList<>();

        for (WebElement currentElement : baseElements) {
            elementsToReturn.add(new DynamicElement(driver, currentElement));
        }

        return elementsToReturn;
    }

    public List<DynamicElement> findDynamicElements() {
        boolean foundElements = false;
        List<WebElement> elements = null;
        List<DynamicElement> elementsToReturn = new ArrayList<>();

        for (By currentSearch : searchOptions) {
            if (!foundElements) {
                elements = driver.findElements(currentSearch);
                if (!elements.isEmpty()) {
                    foundElements = true;
                }
            }
        }

        if (foundElements) {
            for (WebElement currentElement : elements) {
                elementsToReturn.add(new DynamicElement(driver, currentElement));
            }

            Collections.reverse(elementsToReturn); // TODO: Why do we even need a reversal?
        }

        return elementsToReturn;
    }

    private boolean elementStale() {
        try {
            rootElement.getLocation();
            return false;
        }
        catch ( @SuppressWarnings(value = "unchecked") StaleElementReferenceException e) {
            // Do nothing.
        }
        return true;
    }

    private DynamicElement find() {
        try {
            if (rootElement == null || NoCache) {
                for (By currentBy : searchOptions) {
                    try {
                        if (ParentElement == null) {
                            rootElement = driver.findElement(currentBy);
                        } else {
                            rootElement = ParentElement.findElement(currentBy);
                        }
                        return this;
                    } catch (Exception e) {

                    }
                }

                throw new WebDriverException(
                    "Error finding " + displayName + " on the page / screen" + ParentPage);
                //return this;

            }

            return this;
        } catch (Exception e) {
            throw new WebDriverException(
                "Error finding "
                    + displayName
                    + " on the page / screen"
                    + ParentPage
                    + "\n"
                    + e.toString());
        }
    }
    //#endregion

    //#region Webdirver Overloads
    @Override
    public void clear() {
        this.find();
        this.rootElement.clear();
        report.writeStep("Clear element " + displayName);
    }

    @Override
    public void click() {
        this.find();
        this.rootElement.click();
        report.writeStep("Click element " + displayName);
    }

    @Override
    public WebElement findElement(By by) {
        rootElement = rootElement.findElement(by);
        return rootElement;
    }

    @Override
    public List<WebElement> findElements(By by) {
        return Collections.unmodifiableList(rootElement.findElements(by));

    }

    @Override
    public String getAttribute(String arg0) {
        this.find();
        return this.rootElement.getAttribute(arg0);
    }

    @Override
    public String getCssValue(String arg0) {
        this.find();
        return this.rootElement.getCssValue(arg0);
    }

    @Override
    public Point getLocation() {
        this.find();
        return this.rootElement.getLocation();
    }


    @Override
    public Dimension getSize() {
        this.find();
        return this.rootElement.getSize();
    }

    @Override
    public String getTagName() {
        this.find();
        return this.rootElement.getTagName();
    }

    @Override
    public String getText() {
        this.find();
        return this.rootElement.getText();
    }

    @Override
    public boolean isDisplayed() {
        this.find();
        return this.rootElement.isDisplayed();
    }

    @Override
    public boolean isEnabled() {
        this.find();
        return this.rootElement.isEnabled();
    }

    @Override
    public boolean isSelected() {
        this.find();
        return this.rootElement.isSelected();
    }

    @Override
    public void sendKeys(CharSequence... arg0) {
        this.find();
        this.rootElement.sendKeys(arg0);
        report.writeStep("Send Keys (" + arg0 + ") to  element " + displayName);
    }

    @Override
    public void submit() {
        this.find();
        this.rootElement.submit();
        report.writeStep("Submit element " + displayName);
    }
    //#endregion

    //#region Webdriver Extensions
    public void sendKeysSensitive(CharSequence... arg0) {
        this.find();
        try {

            this.rootElement.sendKeys(arg0);
        } catch (Exception e) {
            throw new WebDriverException(
                "Error with send keys on "
                    + displayName
                    + " on the page / screen"
                    + ParentPage
                    + "\n"
                    + e.toString()
                    + " with input of "
                    + arg0); //report.writeStep("Click element " + displayName);
        }

        //report.writeStep("Send Keys (" + arg0 + ") to  element " + displayName);
    }

    private WebElement checkCondition(By by) {
        try {
            return driver.findElement(by);
        } catch (WebDriverException e) {
            return null;
        }
    }

    public void isClickable() {
        LocalDateTime endTime = LocalDateTime.now().plusSeconds(10);

        do {
            try {
                ExpectedConditions.elementToBeClickable(searchOptions.get(0));
                endTime = LocalDateTime.now();
            } catch (Exception notClickable) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException threadSleepException) {
                    threadSleepException.printStackTrace();
                }
            }
        } while (endTime.isAfter(LocalDateTime.now()));
    }

    public boolean Exists() {
        DynamicElement result;
        try {
            result = find();
        } catch (Exception e) {
            result = null;
        }

        if (result == null || result.rootElement == null) {
            return false;
        }

        return true;
    }
    //#endregion

    //#region Wait
    public void Wait(int timeOutinSec, By by) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutinSec);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void waitToSendKeys(int timeOut, String keys) {
        LocalDateTime endTime = LocalDateTime.now().plusSeconds(timeOut);

        do {
            try {
                this.isEnabled();
                this.sendKeys(keys);
                endTime = LocalDateTime.now();
            } catch (Exception keysUnsent) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException threadSleepException) {
                    threadSleepException.printStackTrace();
                }
            }
        } while (endTime.isAfter(LocalDateTime.now()));
    }

    public void WaitForInvsibility(int timeOutinSec) {
        if (searchOptions.size() > 0) {
            LocalDateTime startTime = LocalDateTime.now();
            LocalDateTime iterationTime = startTime;

            while (TimeSpan.getSpan(startTime, iterationTime).Time.getSeconds() < timeOutinSec) {
                iterationTime = LocalDateTime.now();
                for (By currentBy : searchOptions) {
                    WebElement foundElement = checkCondition(currentBy);
                    try {
                        boolean visibilityState = foundElement.isEnabled();
                        if (visibilityState == false) return;
                    } catch (StaleElementReferenceException elementNoLongerVisible) {
                        // Returns out of the loop because stale element reference implies that element
                        // is no longer visible.
                        return;
                    } catch (NoSuchElementException elementDoesntExist) {
                        // Returns out of the loop because the element is not present in DOM. The
                        // try block checks if the element is present but is invisible.
                        return;
                    } catch (NullPointerException elementClearedOut) {
                        //DynElement clears out the element when it doest exist on the page anymore
                        return;
                    }
                }
            }
        } else {
            throw new WebDriverException("No Search Bys to wait for");
        }
    }

    public void Wait(int timeOutinSec) {
        if (searchOptions.size() > 0) {
            LocalDateTime startTime = LocalDateTime.now();

            while (TimeSpan.getSpan(startTime, LocalDateTime.now()).Time.getSeconds() < timeOutinSec) {
                for (By currentBy : searchOptions) {
                    WebElement foundElement = checkCondition(currentBy);
                    if (foundElement != null) {
                        if (foundElement.isEnabled() && foundElement.isDisplayed()) {
                            return;
                        }
                    }
                }
            }
            throw new WebDriverException("Error waiting for element " + displayName);

        } else {
            throw new WebDriverException("No Search Bys to wait for");
        }
    }

    public void WaitforDisplay(int timeOutinSec) {
        if (searchOptions.size() > 0) {
            LocalDateTime startTime = LocalDateTime.now();
            LocalDateTime iterationTime = startTime;

            while (TimeSpan.getSpan(startTime, iterationTime).Time.getSeconds() < timeOutinSec) {
                iterationTime = LocalDateTime.now();
                for (By currentBy : searchOptions) {
                    WebElement foundElement = checkCondition(currentBy);
                    if (foundElement != null) {
                        if (foundElement.isEnabled()) {
                            return;
                        }
                    }
                }
            }
            throw new WebDriverException("Error watiting for element " + displayName);

        } else {
            throw new WebDriverException("No Search Bys to wait for");
        }
    }
    //#endregion

    public WebElement returnRoot() {
        return rootElement;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String name) {
        displayName = name;
    }

    public String getParentName(){
        return ParentPage;
    }

    public DynamicElement getParentElement(){
        return ParentElement;
    }
}
