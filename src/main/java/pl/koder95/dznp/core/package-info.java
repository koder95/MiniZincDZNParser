/**
 * Jądro definiuje podstawowe założenia dla projektu.
 * 
 * <li>Interfejs {@link Data} definiuje sposób przechowywania danych. Jest on bardzo prosty, aby dostęp do określonych wartości był prosty.</li>
 * <li>Interfejs {@link IParser} definiuje jakie metody ma posiadać parser.</li>
 * <li>Klasa {@link DataBuilder} dostarcza budowniczego instancji interfejsu {@link Data}.</li>
 * 
 * <h1>Używanie jądra</h1>
 * <p>Jądro może być używane do wczytywania i zapisywania danych. Sposób w jaki będzie to wykonane określa implementacja interfejsu {@link IParser}.
 * <h2>Wczytywanie</h2>
 * <blockquote><pre>
 * 	IParser parser;
 *	InputStream input;
 *	String key;
 *	
 *	// inicjalizowanie powyższych zmiennych
 *	
 *	Object desiredValue = null;
 *	try {
 *		Data data = parser.load(input);
 *		if (data.containKey(key))
 *			desiredValue = key;
 *	} catch (IOException ex) {
 *		// obsługa wyjątku
 *	}
 * </pre></blockquote>
 * <h2>Zapisywanie</h2>
 * <blockquote><pre>
 * 	Data data = new DataBuilder().
 *		.put("count", 5) // - przykładowe dane
 *		.build();
 *	IParser parser;
 *	OutputStream output;
 *	
 *	// inicjalizowanie zmiennych parser i output
 *	
 *	try {
 *		parser.save(output, data);
 *	} catch (IOException ex) {
 *		// obsługa wyjątku
 *	}
 * </pre></blockquote>
 */
package pl.koder95.dznp.core;