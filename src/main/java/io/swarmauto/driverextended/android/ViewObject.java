package io.swarmauto.driverextended.android;


import io.selendroid.client.SelendroidDriver;

import org.openqa.selenium.WebDriver;

public abstract class ViewObject
{
	public String viewName;
	public WebDriver webDriver;

	public abstract void setup();

	public void hitBack(WebDriver app)
	{
		SelendroidDriver driver = (SelendroidDriver) app;
		driver.getKeyboard().sendKeys("\uE100");
	}

	public abstract void tearDown();
}
