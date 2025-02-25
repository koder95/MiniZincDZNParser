/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.koder95.dznp.core;

import java.util.*;

/**
 * Budowniczy interfejsu, który zawiera dane wczytane z pliku DZN.
 *
 * @author Koder95
 */
public class DataBuilder {
    
    private final Map<String, Object> map;

    /**
     * Tworzy budowniczego z konkretną implementacją mapy.
     * 
     * @param map mapa, która zostanie wykorzystana jako kontener
     */
    public DataBuilder(Map<String, Object> map) {
        this.map = Objects.requireNonNull(map);
    }

    /**
     * Tworzy budowniczego z implementacją mapy typu {@link HashMap}.
     */
    public DataBuilder() {
        this(new HashMap<>());
    }
    
    /**
     * Pozwala modyfikować mapę, przypisując kluczowi wartość.
     * 
     * @param key klucz dostępu do wartości
     * @param value wartość skojarzona z kluczem
     * @return {@code this}
     */
    public DataBuilder put(String key, Object value) {
        if (!(key == null || key.isEmpty())) map.put(key, value);
        return this;
    }
    
    /**
     * Buduje interfejs Data z danymi.
     * @return instancja {@link Data}
     */
    public Data build() {
        return new Data() {
            public Set<String> keySet() {
                return Collections.unmodifiableSet(map.keySet());
            }

            public boolean containsKey(String key) {
				return map.containsKey(key);
			}

			public Object getValue(String key) {
                return map.get(key);
            }
        };
    }
}
