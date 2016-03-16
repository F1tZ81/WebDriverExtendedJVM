package com.ostusa;

/**
 * 
 * This advance form of the page object interface
 * allows for a page object to be loaded from a file
 * and have a key value pair of Elements.
 */
public interface AdvancePageObject extends PageObject {
    /**
     * Add a dynamicElement to the key element list.
     *
     * @param name
     * @param elementToAdd
     */
    void addElement(String name, DynamicElement elementToAdd);

    /**
     * Load a page object from a file.
     *
     * @param filePath
     */
    void loadPage(String filePath);

    /**
     * Remove an element from the key element store.
     *
     * @param name
     */
    void removeElement(String name);
}
