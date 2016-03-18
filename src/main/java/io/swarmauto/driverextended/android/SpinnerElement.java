package io.swarmauto.driverextended.android;

import java.util.ArrayList;
import java.util.List;

import io.swarmauto.driverextended.DynamicElement;

public class SpinnerElement extends DynamicElement
{
	List<DynamicElement> Options;

	public SpinnerElement()
	{
		super();
		Options = new ArrayList<DynamicElement>();
	}

	public void addOptionElement(DynamicElement element)
	{
		Options.add(element);
	}

	public void clickOption(String value)
	{
		for(DynamicElement currentEle : Options)
		{
			if(currentEle.getAttribute("Value") == value)
			{
				currentEle.click();
			}
		}
	}

	public void clickOption(int index)
	{
		Options.get(index).click();
	}

	public List<DynamicElement> getOptions()
	{
		return Options;
	}

}
