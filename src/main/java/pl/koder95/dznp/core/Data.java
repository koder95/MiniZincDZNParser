/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.koder95.dznp.core;

import java.util.Set;

/**
 * Interfejs z danymi. Służy do odczytywania i zapisywania wartości za pomocą parsera. 
 *
 * @author Koder95
 */
public interface Data {
    
    /**
     * Zwraca zbór kluczów dostępu do wartości.
     * 
     * @return zbór kluczy
     */
	Set<String> keySet();
	
	/**
	 * Sprawdza, czy dane zawierają podany klucz dostępu.
	 * 
	 * @return {@code true} jeśli klucz istnieje 
	 */
	boolean containsKey(String key);
	
	/**
	 * Zwraca wartość przypisaną do klucza.
	 * 
	 * @param key klucz dostępu
	 * @return wartość przypisana do klucza
	 */
    Object getValue(String key);
    
}
