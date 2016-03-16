package com.ostusa;

public interface StatefulPageObject extends PageObject
{
	void addState(String key, String value) throws InvalidStateException;

	ApplicationState getState(String key) throws InvalidStateException;

	void updateState(String key, String value) throws InvalidStateException;

	void removeState(String key) throws InvalidStateException;
}
