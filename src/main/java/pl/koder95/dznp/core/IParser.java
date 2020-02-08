/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.koder95.dznp.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Analizator składniowy dla plików typu DZN, które są plikami danych
 * programu MiniZinc.
 *
 * @author Koder95
 */
public interface IParser {
    
	/**
	 * Buduje dane na podstawie strumienia wejścia.
	 * 
	 * @param input strumień, który ma zostać przetworzony
	 * @return dane odczytane
	 * @throws IOException nie jest możliwe odczytanie strumienia
	 */
    Data load(InputStream input) throws IOException;
    
    /**
     * Zapisuje dane do strumienia wyjścia.
     * 
     * @param output strumień wyjściowy dla danych
     * @param dzn dane, które mają zostać zapisane
     * @throws IOException nie jest możliwe zapisywanie do strumienia
     */
    void save(OutputStream output, Data dzn) throws IOException;
}
