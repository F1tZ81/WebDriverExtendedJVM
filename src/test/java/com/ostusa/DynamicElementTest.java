package com.ostusa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

public class DynamicElementTest{
    private DynamicElement dynamicElement;
    private WebElement webElement;

    @Before
    public void setup(){
        webElement = setupWebElement();

        WebDriver webDriver = setupWebDriver();

        dynamicElement = new DynamicElement( webDriver );
    }

    private WebElement setupWebElement() {
        WebElement element =  mock(WebElement.class);
        when(element.getText()).thenReturn( "Test Text" );

        return element;
    }

    private WebDriver setupWebDriver() {
        WebDriver driver = mock(WebDriver.class);
        when(driver.findElement( (By) any() )).thenReturn( webElement );

        return driver;
    }

    @After
    public void tearDown(){

    }

    @Test
    public void testGetText(){
        assertEquals("Test Text", dynamicElement.getText());
    }

}
